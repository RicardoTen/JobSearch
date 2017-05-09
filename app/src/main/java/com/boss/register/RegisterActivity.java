package com.boss.register;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import com.boss.R;
import com.boss.db.User;
import com.boss.login.BaseActivity;
import com.boss.login.MainCompanyActivity;
import com.boss.login.MainEmployeeActivity;
import com.boss.util.BossConstants;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

public class RegisterActivity extends BaseActivity implements OnClickListener {

	private MyBroadcastReceiver receiver = new MyBroadcastReceiver();

	CircleProgressDialog circleProgressDialog;

	//ImageView imgEmployee;
	//Image

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_register);
		findViewById();

		// 注册退出广播
		IntentFilter filter = new IntentFilter();
		filter.addAction(BossConstants.ACTION_REGISTER_SUCCESS_COMPANY_FINISH);
		registerReceiver(receiver, filter);
		 
	}

	private void findViewById() {

		findViewById(R.id.register_register_goEmployeeStep1)
				.setOnClickListener(this);
		findViewById(R.id.register_register_goCompanyStep1).setOnClickListener(
				this);

		circleProgressDialog = new CircleProgressDialog(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register_register_goEmployeeStep1:// 我要求职

			circleProgressDialog.showDialog();
			/*startActivity(new Intent(RegisterActivity.this,
					MainEmployeeActivity.class));*/
			
			
			User user = User.getCurrentUser(this, User.class);
			if (user.getRegisterEmployee() != null) {// 判断用户是否注册过求职
				User newUser = new User();
				newUser.setMainLayout(false);// 设置用户主界面为求职
				newUser.update(this, user.getObjectId(), new UpdateListener() {
					@Override
					public void onSuccess() {
						circleProgressDialog.dismiss();
						sendBroadcast(new Intent(
								BossConstants.ACTION_CHANGE_MAIN_EMPLOYEE_FINISH));
						sendBroadcast(new Intent(
								BossConstants.ACTION_CHANGE_MAIN_COMPANY_FINISH));
						startActivity(new Intent(RegisterActivity.this,
								MainEmployeeActivity.class));
						RegisterActivity.this.finish();
					}

					@Override
					public void onFailure(int i, String s) {
						circleProgressDialog.dismiss();
						Toast("更新失败" + s);
					}
		/*			@Override
					public void done(BmobException e) {
						if (e == null) {
							sendBroadcast(new Intent(
									BossConstants.ACTION_CHANGE_MAIN_EMPLOYEE_FINISH));
							sendBroadcast(new Intent(
									BossConstants.ACTION_CHANGE_MAIN_COMPANY_FINISH));
							startActivity(new Intent(RegisterActivity.this,
									MainEmployeeActivity.class));
							RegisterActivity.this.finish();
						} else {
						}
					}*/

				});

				 
			} else {
				circleProgressDialog.dismiss();
				startActivity(new Intent(this, EmployeeStep1Activity.class));
			}
			break;
		case R.id.register_register_goCompanyStep1:// 我要招人

			circleProgressDialog.showDialog();
			
		/*	startActivity(new Intent(RegisterActivity.this,
					MainCompanyActivity.class));*/
			
			
			
			User muser = User.getCurrentUser(this, User.class);
			if (muser.getRegisterCompany() != null) {// 判断用户是否注册过找公司
				User newUser = new User();
				newUser.setMainLayout(true);// 设置用户主界面为招人
				newUser.update(this,muser.getObjectId(), new UpdateListener() {
					@Override
					public void onSuccess() {
						circleProgressDialog.dismiss();
						startActivity(new Intent(RegisterActivity.this,
								MainCompanyActivity.class));
						sendBroadcast(new Intent(
								BossConstants.ACTION_CHANGE_MAIN_EMPLOYEE_FINISH));
						sendBroadcast(new Intent(
								BossConstants.ACTION_CHANGE_MAIN_COMPANY_FINISH));
						finish();
					}

					@Override
					public void onFailure(int i, String s) {
						circleProgressDialog.dismiss();
						Toast("更新失败" + s);
					}
/*					@Override
					public void done(BmobException e) {
						if (e == null) {
							startActivity(new Intent(RegisterActivity.this,
									MainCompanyActivity.class));
							sendBroadcast(new Intent(
									BossConstants.ACTION_CHANGE_MAIN_EMPLOYEE_FINISH));
							sendBroadcast(new Intent(
									BossConstants.ACTION_CHANGE_MAIN_COMPANY_FINISH));
							finish();
							 
							 
						} else {
						}
					}*/

				}); } else {
				circleProgressDialog.dismiss();
				startActivity(new Intent(this, CompanyStep1Activity.class));
			}
			break;

		}

	}

	/**
	 * 重写返回键，不允许用户退出。
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 自定义广播（用来注册成功，密码登录成功关闭本活动）
	 */
	public class MyBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent != null
					&& BossConstants.ACTION_REGISTER_SUCCESS_COMPANY_FINISH
							.equals(intent.getAction())) {
				RegisterActivity.this.finish();
			}
			if (intent != null
					&& BossConstants.ACTION_REGISTER_SUCCESS_EMPLOYEE_FINISH
							.equals(intent.getAction())) {
				RegisterActivity.this.finish();
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

}
