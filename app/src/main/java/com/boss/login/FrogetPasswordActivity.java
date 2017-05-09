package com.boss.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.ResetPasswordByCodeListener;
import cn.bmob.v3.listener.UpdateListener;

import com.boss.R;
import com.boss.db.User;
import com.boss.register.RegisterActivity;
import com.boss.support.IsPhone;
import com.boss.util.BossConstants;
import com.boss.util.CountDownTimerUtils;
import com.boss.util.ErrorMessage;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

public class FrogetPasswordActivity extends BaseActivity implements
		OnClickListener {

	Button btnCodeHint;

	CircleProgressDialog circleProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_forgetpassword_new);
		findViewById();
	}

	/** 账号 */
	private EditText mPhone;
	/** 验证码 */
	private EditText mCode;
	/** 新密码 */
	private EditText mPassword;

	private void findViewById() {
		mPhone = (EditText) findViewById(R.id.login_frogetpassword_phone);
		mCode = (EditText) findViewById(R.id.login_frogetpassword_code);
		mPassword = (EditText) findViewById(R.id.login_frogetpassword_password);

		btnCodeHint = (Button) findViewById(R.id.login_frogetpassword_getCode);

		findViewById(R.id.login_frogetpassword_getCode)
				.setOnClickListener(this);
		findViewById(R.id.login_frogetpassword_goMain).setOnClickListener(this);

		circleProgressDialog = new CircleProgressDialog(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_frogetpassword_getCode: // 获取验证码
			String phone = mPhone.getText().toString();
			if (IsPhone.isPhone(phone)) {
				// 查询用户是否存在，若存在，则发送短信，若不存在，则前往注册
				postCode(phone);
			} else {
				Toast("手机号码格式错误");
			}

			break;
		case R.id.login_frogetpassword_goMain: // 重置密码
			goMain();
			break;
		}

	}


	private void postWaitCodeTime(){
		CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(btnCodeHint, 60000, 1000);
		mCountDownTimerUtils.start();
	}

	/**
	 * 重置密码 判断手机号与验证码是否匹配 若匹配，则登录主界面 若不匹配，则提示错误
	 */

	private void postCode(String phone) {
		circleProgressDialog.showDialog();

		BmobSMS.requestSMSCode(FrogetPasswordActivity.this,phone, "Boss直聘", new RequestSMSCodeListener() {
			@Override
			public void done(Integer smsId, BmobException e) {
				circleProgressDialog.dismiss();
				// 请求成功
				if (e == null) {// 验证码发送成功

					postWaitCodeTime();
					Toast("验证码发送成功");
				}
				// 请求失败
				else {
					ToastError(ErrorMessage.errorCode(e.getErrorCode()));
				}

			}
		});
	}
	
	/**
	 * 重置密码  匹配验证码是否正确 若不正确，则提示重置密码失败，若正确则跳转到主界面。
	 */

	private void goMain() {
		
		//验证码
		String code = mCode.getText().toString();
		if(code==""){
			Toast("验证码不能为空");
		}
		//密码
		final String password = mPassword.getText().toString();
		if(password==""){
			Toast("新密码不能为空");
		}

		circleProgressDialog.showDialog();


		BmobUser.resetPasswordBySMSCode(FrogetPasswordActivity.this,code, password, new ResetPasswordByCodeListener() {
			@Override
			public void done(BmobException ex) {
				circleProgressDialog.dismiss();
				if (ex == null) {
					Toast("重置密码成功");
					// 跳转到主界面
					final String pwdFinal = password;
					User.loginByAccount(FrogetPasswordActivity.this, mPhone.getText().toString(), pwdFinal, new LogInListener<User>() {

						@Override
						public void done(User user, BmobException e) {
							if(e == null){
								//User user =User.getCurrentUser(FrogetPasswordActivity.this, User.class);
								register(user);
								FrogetPasswordActivity.this.finish();
								// 发广播通知登陆页面退出
								sendBroadcast(new Intent(
										BossConstants.ACTION_REGISTER_SUCCESS_FINISH));
							}
						}
					});


				}

				else {
					Toast("重置密码失败");
				}
			}
		});

	}


	/**
	 */
	private void register(User user) {
		if (user.getMainLayout() != null) {// 判断是否注册
			if (user.getMainLayout()) {//注册时为公司，跳转到公司界面
				startActivity(new Intent(FrogetPasswordActivity.this,MainCompanyActivity.class));
				FrogetPasswordActivity.this.finish();
			}else{//注册时为员工，跳转到员工界面
				startActivity(new Intent(FrogetPasswordActivity.this,MainEmployeeActivity.class));
				FrogetPasswordActivity.this.finish();
			}
		} else {
			// 跳转到注册界面
			startActivity(new Intent(FrogetPasswordActivity.this,RegisterActivity.class));
			FrogetPasswordActivity.this.finish();
		}

	}

}
