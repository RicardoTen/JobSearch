package com.boss.login;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.newim.listener.QueryListener;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.RequestSMSCodeListener;

import com.boss.R;
import com.boss.admin.AdminActivity;
import com.boss.db.User;
import com.boss.register.RegisterActivity;
import com.boss.support.IsPhone;
import com.boss.util.BossConstants;
import com.boss.util.CountDownTimerUtils;
import com.boss.util.ErrorMessage;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

public class LoginActivity extends BaseActivity implements OnClickListener,
		TextWatcher {

	private MyBroadcastReceiver receiver = new MyBroadcastReceiver();

	public static final int WAIT_CODE_TIME = 60;

	/** 头像 */
	// private ImageView mAvatar;

	/** 手机号码编辑框 */
	private EditText mPhone;

	/** 删除手机号按钮 */
	//private Button mDeletePhone;

	/** 手机验证码编辑框 */
	private EditText mCode;

	public View progress;

	public View mInputLayout;

	public LinearLayout mName;

	public LinearLayout mPsw;

	private float mWidth, mHeight;

	ValueAnimator animator;

	ObjectAnimator animator2;

	ObjectAnimator animator3;

	Button btnCodeHint;

	CircleProgressDialog circleProgressDialog;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//setContentView(R.layout.activity_login_login);
		setContentView(R.layout.activity_login_new);
		//ButterKnife.bind(this);
		findViewById();

		// 注册退出广播
		IntentFilter filter = new IntentFilter();
		filter.addAction(BossConstants.ACTION_REGISTER_SUCCESS_FINISH);
		registerReceiver(receiver, filter);
	}


	private void findViewById() {
		// mAvatar = (ImageView) findViewById(R.id.login_login_avatar);
		mPhone = (EditText) findViewById(R.id.login_login_phone);
		//mDeletePhone = (Button) findViewById(R.id.login_login_clearPhone);
		//mDeletePhone.setOnClickListener(this);
		mCode = (EditText) findViewById(R.id.login_login_Code);
		mPhone.addTextChangedListener(this);
		findViewById(R.id.login_login_goLogin).setOnClickListener(this);
		//findViewById(R.id.login_login_clearPhone).setOnClickListener(this);

		mName = (LinearLayout) findViewById(R.id.input_layout_name);
		mPsw = (LinearLayout) findViewById(R.id.input_layout_psw);
		mInputLayout = findViewById(R.id.input_layout);
		progress = findViewById(R.id.layout_progress);

		btnCodeHint = (Button) findViewById(R.id.login_login_getCode);

		findViewById(R.id.login_login_getCode).setOnClickListener(this);
		findViewById(R.id.login_login_goUserAggrement).setOnClickListener(this);
		findViewById(R.id.login_login_goPasswordLogin).setOnClickListener(this);

		circleProgressDialog = new CircleProgressDialog(this);

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_login_getCode:// 获取验证码按钮
			String phone = mPhone.getText().toString();
			// 判断手机号格式是否正确
			if (IsPhone.isPhone(phone)) {
				// 发送验证码
				postLoginCode(phone);
			} else {
				Toast("手机号码格式错误");
			}

			break;

		case R.id.login_login_clearPhone: // 清楚手机号编辑框
			// 清空手机号编辑框
			mPhone.setText("");
			// 隐藏删除手机号按钮
			//mDeletePhone.setVisibility(View.INVISIBLE);
			break;
		case R.id.login_login_goLogin: // 登录按钮

		/*	mWidth = v.getMeasuredWidth();
			mHeight = v.getMeasuredHeight();

			mName.setVisibility(View.INVISIBLE);
			mPsw.setVisibility(View.INVISIBLE);*/

			//inputAnimator(mInputLayout, mWidth, mHeight);
			goMain();


			break;
		case R.id.login_login_goPasswordLogin:// 前往密码登录界面
			startActivity(new Intent(this, PasswordLoginActivity.class));
			break;
		case R.id.login_login_goUserAggrement: // 前往用户协议界面
			startActivity(new Intent(this, UserAggrementActivity.class));
			break;
	 

		}
	}

	private void postWaitCodeTime(){
		CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(btnCodeHint, 60000, 1000);
		mCountDownTimerUtils.start();
	}

	/**
	 * @param phone
	 *            获取验证码的手机号 若手机号格式正确，则发送验证码 若手机号格式不正确，则提示充填
	 */
	private void postLoginCode(String phone) {

		//BmobSMS.requestSMSCode();

		circleProgressDialog.showDialog();

		BmobSMS.requestSMSCode(LoginActivity.this,phone, "Boss直聘", new RequestSMSCodeListener() {
			@Override
			public void done(Integer integer, BmobException e) {
				if (e == null) {// 验证码发送成功
//					 if(smsId==10010){
					//Toast(smsId + "");
					circleProgressDialog.dismiss();
					Toast("验证码发送成功， 请注意查收");
//					 }
					postWaitCodeTime();
				}
				else {// 请求失败
					circleProgressDialog.dismiss();
					// ErrorMessage.errorCode(e.getErrorCode()) 将code转为对应错误信息;
					ToastError(ErrorMessage.errorCode(e.getErrorCode()));
				}
			}
		});

		/*new QueryListener() {

			@Override
			public void done(String smsId, BmobException e) {
				// 请求成功

			}
		};*/
	}

	/**
	 * 登录 判断手机号与验证码是否匹配 若匹配，则登录主界面 若不匹配，则提示错误
	 */

	private void goMain() {
		final String phone = mPhone.getText().toString();// 手机号
		if (phone == "") {
			Toast("手机号不能为空");
			return;
		}
		String code = mCode.getText().toString();// 验证码
		if (code == "") {
			Toast("验证码不能为空");
			return;
		}

		circleProgressDialog.showDialog();
		//BmobUser.signOrLoginByMobilePhone(LoginActivity.this, phone, code);

		BmobUser.signOrLoginByMobilePhone(LoginActivity.this,phone, code,// 请求验证
				new LogInListener<User>() {

					@Override
					public void done(User muser, BmobException e) {// 请求成功

						User user = User.getCurrentUser(LoginActivity.this, User.class);


						//User user = User.getCurrentUser(User.class);

						if (user != null) {// 登录成功
							circleProgressDialog.dismiss();
							register(user);// 判断是否注册过
						} else {// 登录失败
							/*progress.setVisibility(View.INVISIBLE);
							mInputLayout.setVisibility(View.VISIBLE);
							mName.setVisibility(View.VISIBLE);
							mPsw.setVisibility(View.VISIBLE);*/

							//animator.cancel();
							//animator2.cancel();

							//animator3.cancel();

						/*	progress.clearAnimation();
							mInputLayout.clearAnimation();
							progress.setVisibility(View.GONE);*/

/*							ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mInputLayout
									.getLayoutParams();
							params.leftMargin = (int) 0;
							params.rightMargin = (int) 0;
							mInputLayout.setLayoutParams(params);*/

							//mInputLayout.setVisibility(View.VISIBLE);
							circleProgressDialog.dismiss();
							ToastError("验证码错误，请重试");
						}
					}
				});
	}

	/**
	 */
	private void register(User user) {
		if(user.getAdMin() != null){
			if(user.getAdMin()){
				startActivity(new Intent(LoginActivity.this,AdminActivity.class));
				LoginActivity.this.finish();
				return;
			}
		}

		if (user.getMainLayout() != null) {// 判断是否注册
			if (user.getMainLayout()) {//注册时为公司，跳转到公司界面
				startActivity(new Intent(LoginActivity.this,MainCompanyActivity.class));
				LoginActivity.this.finish();
			}else{//注册时为员工，跳转到员工界面
				startActivity(new Intent(LoginActivity.this,MainEmployeeActivity.class));
				LoginActivity.this.finish();
			}
		} else {
			// 跳转到注册界面
			startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
			LoginActivity.this.finish();
		}

	}

	/**
	 * 自定义广播（用来注册成功，密码登录成功关闭本活动）
	 */
	public class MyBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent != null
					&& BossConstants.ACTION_REGISTER_SUCCESS_FINISH
							.equals(intent.getAction())) {
				LoginActivity.this.finish();
			}
		}

	}

	/**
	 * 关闭广播
	 */
	@Override
	protected void onDestroy() {
		//
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	/**
	 * TextWatch接口，当号码输入框有内容时，增加个按钮来快速清空内容
	 */
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (s.length() > 1) {
		}
		// 长度为1则显示按钮
		else if (s.length() == 1) {
			//mDeletePhone.setVisibility(View.VISIBLE);
		}
		// 长度
		else if (s.length() == 0) {
			//mDeletePhone.setVisibility(View.INVISIBLE);
		}
	}

	public void afterTextChanged(Editable s) {

	}



	private void inputAnimator(final View view, float w, float h) {

		AnimatorSet set = new AnimatorSet();

		animator = ValueAnimator.ofFloat(0, w);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (Float) animation.getAnimatedValue();
				ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view
						.getLayoutParams();
				params.leftMargin = (int) value;
				params.rightMargin = (int) value;
				view.setLayoutParams(params);
			}
		});


		 animator2 = ObjectAnimator.ofFloat(mInputLayout,
				"scaleX", 1f, 0.5f);


		set.setDuration(1000);
		set.setInterpolator(new AccelerateDecelerateInterpolator());
		set.playTogether(animator, animator2);
		//set.play(animator2);
		set.start();
		set.addListener(new Animator.AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animator animation) {

				progress.setVisibility(View.VISIBLE);
				progressAnimator(progress);
				mInputLayout.setVisibility(View.INVISIBLE);

			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void progressAnimator(final View view) {
		PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
				0.5f, 1f);
		PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
				0.5f, 1f);
		animator3 = ObjectAnimator.ofPropertyValuesHolder(view,
				animator, animator2);
		animator3.setDuration(1000);
		animator3.setInterpolator(new JellyInterpolator());
		animator3.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
				goMain();
			}

			@Override
			public void onAnimationEnd(Animator animation) {

			}

			@Override
			public void onAnimationCancel(Animator animation) {
				/*String content = mPhone.getText().toString();
				setContentView(R.layout.activity_login_new);
				findViewById();
				mPhone.setText(content);*/
				progress.setVisibility(View.GONE);
				mInputLayout.setVisibility(View.VISIBLE);
				//inputAnimator(mInputLayout, 0, 0);
			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}
		});
		animator3.start();

	}

}
