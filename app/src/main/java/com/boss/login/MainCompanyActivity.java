package com.boss.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.boss.R;
import com.boss.db.User;
import com.boss.im.event.RefreshEvent;
import com.boss.im.util.IMMLeaks;
import com.boss.search.SearchActivity;
import com.boss.util.BossConstants;
import com.boss.view.CompanyFragmentIndicator;
import com.boss.view.CompanyFragmentIndicator.OnIndicateListener;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

public class MainCompanyActivity extends FragmentActivity {

	@BindView(R.id.img_serch)
	ImageView imgSearch;
	
	private MyBroadcastReceiver receiver = new MyBroadcastReceiver();
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_maincompany);
		setFragmentIndicator(0);
		// 注册广播　　　负责监听接收设置更换身份时候推出
		IntentFilter filter = new IntentFilter();
		filter.addAction(BossConstants.ACTION_CHANGE_MAIN_COMPANY_FINISH);
		registerReceiver(receiver, filter);

		ButterKnife.bind(this);

		InitIM();
	}



	private void InitIM() {

		User user = BmobUser.getCurrentUser(this,User.class);
		BmobIM.connect(user.getObjectId(), new ConnectListener() {
			@Override
			public void done(String uid, BmobException e) {
				if (e == null) {
					Logger.i("connect success");
					//服务器连接成功就发送一个更新事件，同步更新会话及主页的小红点
					EventBus.getDefault().post(new RefreshEvent());
				} else {
					Logger.e(e.getErrorCode() + "/" + e.getMessage());
				}
			}
		});
		//监听连接状态，也可通过BmobIM.getInstance().getCurrentStatus()来获取当前的长连接状态
		BmobIM.getInstance().setOnConnectStatusChangeListener(new ConnectStatusChangeListener() {
			@Override
			public void onChange(ConnectionStatus status) {
				Toast.makeText(MainCompanyActivity.this, "" + status.getMsg(), Toast.LENGTH_SHORT).show();
			}
		});
		//解决leancanary提示InputMethodManager内存泄露的问题
		IMMLeaks.fixFocusedViewLeak(getApplication());
	}

	@OnClick(R.id.img_serch)
	public void onSearchClick(View view){
		Intent intent = new Intent(this, SearchActivity.class);
		startActivity(intent);
	}

	public static Fragment[] mFragments;

	// *******************************这里是底部指示器****************************************
	// 初始化底部指示器的分页1
	// 设置默认的分页2
	// 设置一个界面只有一个fragment3
	private void setFragmentIndicator(int whichIsDefault) {
		// 1
		mFragments = new Fragment[3];
		mFragments[0] = getSupportFragmentManager().findFragmentById(
				R.id.login_main_company_tab1);
		mFragments[1] = getSupportFragmentManager().findFragmentById(
				R.id.login_main_company_tab2);
		mFragments[2] = getSupportFragmentManager().findFragmentById(
				R.id.login_main_company_tab3);
		// 2
		getSupportFragmentManager().beginTransaction().hide(mFragments[0])
				.hide(mFragments[1]).hide(mFragments[2])
				.show(mFragments[whichIsDefault]).commit();
		CompanyFragmentIndicator mIndicator = (CompanyFragmentIndicator) findViewById(R.id.login_main_company_indicator);
		CompanyFragmentIndicator.setIndicator(whichIsDefault);
		// 3
		mIndicator.setOnIndicateListener(new OnIndicateListener() {

			public void onIndicate(View v, int which) {
				getSupportFragmentManager().beginTransaction()
						.hide(mFragments[0])
						.hide(mFragments[1])
						.hide(mFragments[2])
						.show(mFragments[which]).commit();

			}

			// *********************************************************************************************************************

		});
	}
	
	
	// 按两次退出登陆
		private long exitTime = 0;

		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK
					&& event.getAction() == KeyEvent.ACTION_DOWN) {

				if ((System.currentTimeMillis() - exitTime) > 2000) {
					Toast.makeText(getApplicationContext(), "再按一次退出应用",
							Toast.LENGTH_SHORT).show();
					exitTime = System.currentTimeMillis();
				} else {
					finish();
				}
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
					&& BossConstants.ACTION_CHANGE_MAIN_COMPANY_FINISH
							.equals(intent.getAction())) {
				MainCompanyActivity.this.finish();
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
