package com.boss.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

import com.boss.R;
import com.boss.db.User;
import com.boss.register.RegisterActivity;
import com.boss.util.BossConstants;
import com.boss.util.CountDownTimerUtils;
import com.boss.util.ErrorMessage;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

public class PasswordLoginActivity extends BaseActivity implements
		OnClickListener {


	CircleProgressDialog circleProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_passwordlogin_new);
		findViewById();
	}

	/** 账号 */
	private EditText mAccount;
	/** 密码 */
	private EditText mPassword;

	private void findViewById() {
		mAccount = (EditText) findViewById(R.id.login_passwordlogin_account);
		mPassword = (EditText) findViewById(R.id.login_passwordlogin_password);

		//btnCodeHint = (Button) findViewById(R.id.login_login_getCode);

		findViewById(R.id.login_passwordlogin_goMain).setOnClickListener(this);
		findViewById(R.id.login_passwordlogin_goFrogetPassword)
				.setOnClickListener(this);

		circleProgressDialog = new CircleProgressDialog(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_passwordlogin_goMain: // 登录按钮
			goMain();
			break;
		case R.id.login_passwordlogin_goFrogetPassword: // 忘记密码
			startActivity(new Intent(this, FrogetPasswordActivity.class));
			break;
		}

	}




	/**
	 * 前往主界面 验证账号密码是否正确 若不正确则Toast不正确 若正确则前往主界面
	 */

	private void goMain() {
		// 所填账号
		String account = mAccount.getText().toString();
		if (account == "") {
			Toast("账号不能为空");
			return;
		}
		// 所填密码
		String password = mPassword.getText().toString();
		if (password == "") {
			Toast("密码不能为空");
			return;
		}

		circleProgressDialog.dismiss();

		BmobUser.loginByAccount(this, account, password, new LogInListener<User>() {

			@Override
			public void done(User user, BmobException e) {
				circleProgressDialog.dismiss();
				if (user != null) {
					Toast("登录成功");
					// 发广播通知登陆页面退出
					sendBroadcast(new Intent(
							BossConstants.ACTION_REGISTER_SUCCESS_FINISH));
					if (user.getMainLayout() == null) {// 进入注册界面
						startActivity(new Intent(PasswordLoginActivity.this,
								RegisterActivity.class));
					} else if (user.getMainLayout() == true) {
						startActivity(new Intent(PasswordLoginActivity.this,
								MainCompanyActivity.class));
					} else if (user.getMainLayout() == false) {
						startActivity(new Intent(PasswordLoginActivity.this,
								MainEmployeeActivity.class));
					}
					PasswordLoginActivity.this.finish();
				} else {
					// ErrorMessage.errorCode(e.getErrorCode()) 将code转为对应错误信息;
					ToastError(ErrorMessage.errorCode(e.getErrorCode()));
				}
			}
		});

	}
}
