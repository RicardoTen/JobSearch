package com.boss.login;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

import com.boss.R;
import com.boss.db.User;
import com.boss.register.RegisterActivity;
import com.boss.util.BossConstants;

public class SplashActivity extends Activity {

	private static final int GO_EMPLOYEE = 100;

	private static final int GO_COMPANY = 101;

	private static final int GO_REGISTER = 102;

	private static final int GO_LOGIN = 200;

	private static final int SHOW_TEXT = 300;

	protected static TextView mSplashTv;// 展现文字
	private View parentView;
	LayoutInflater inflater;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		inflater = LayoutInflater.from(this);
		parentView = inflater.inflate(R.layout.activity_login_splash,
				new LinearLayout(this), false);
		setContentView(parentView);
		
//		setContentView(R.layout.activity_login_splash);
		// 提供以下两种方式进行初始化操作：
		// 第一：默认初始化245c4aada00a43e365ef4170471d1ce6
		Bmob.initialize(this, BossConstants.BMOB_ID);

		findVIewById();
		// 第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
		// BmobConfig config =new BmobConfig.Builder(this)
		// //设置appkey
		// .setApplicationId("Your Application ID")
		// //请求超时时间（单位为秒）：默认15s
		// .setConnectTimeout(30)
		// //文件分片上传时每片的大小（单位字节），默认512*1024
		// .setUploadBlockSize(1024*1024)
		// //文件的过期时间(单位为秒)：默认1800s
		// .setFileExpiration(2500)
		// .build();
		// Bmob.initialize(config);
	}

	private void findVIewById() {
		mSplashTv = (TextView) findViewById(R.id.login_splash_content);
	}

	MyHandler ttsHandler = new MyHandler(this); // 开启新线程 用于发送跳转至登录界面还是主界面信息

	static class MyHandler extends Handler { // 静态类handle
		WeakReference<SplashActivity> mActivity; // 弱引用

		MyHandler(SplashActivity activity) {
			mActivity = new WeakReference<SplashActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			SplashActivity theActivity = mActivity.get();
			switch (msg.what) {
			case GO_EMPLOYEE:// 跳转到求职界面
				theActivity.startActivity(new Intent(theActivity,
						MainEmployeeActivity.class));
				theActivity.finish();
				break;
			case GO_LOGIN:// 跳转到登录界面
				theActivity.startActivity(new Intent(theActivity,
						LoginActivity.class));
				theActivity.finish();
				break;

			case GO_REGISTER:// 跳转到注册界面
				theActivity.startActivity(new Intent(theActivity,
						RegisterActivity.class));
				theActivity.finish();
				break;
			case GO_COMPANY:// 跳转到公司界面
				theActivity.startActivity(new Intent(theActivity,
						MainCompanyActivity.class));
				theActivity.finish();
				break;

			case SHOW_TEXT: // 显示Boss直聘
				mSplashTv.setVisibility(View.VISIBLE);
				break;

			}
		}
	};

	@Override
	protected void onResume() {
		super.onResume();


		User user = BmobUser.getCurrentUser(this, User.class);// 获取当前用户对象
		ttsHandler.sendEmptyMessageDelayed(SHOW_TEXT, 200);// 显示BOSS直聘文字 延迟 0.2秒
		if (user != null) {// 用户存在缓存
			if (user.getMainLayout() == null) {
				ttsHandler.sendEmptyMessageDelayed(GO_REGISTER, 500); // 用户进入注册
			} else if (user.getMainLayout() == true) {
				ttsHandler.sendEmptyMessageDelayed(GO_COMPANY, 500); // 用户进入公司
			} else {// 用户进入求职

				ttsHandler.sendEmptyMessageDelayed(GO_EMPLOYEE, 500);

			}
		} else {
			ttsHandler.sendEmptyMessageDelayed(GO_LOGIN, 500);// 用户进入登录 }

		}
	}
	  

}