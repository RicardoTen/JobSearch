package com.boss.company3.in.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.bmob.v3.BmobUser;

import com.boss.R;
import com.boss.login.BaseActivity;
import com.boss.register.RegisterActivity;
import com.boss.util.BossConstants;

public class SettingActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_company3_in_setting);
		findViewById();
		initActionBar();
	}

	private void initActionBar() {
		TextView center = (TextView)findViewById(R.id.actionbar_center);
		center.setText("设置");
		TextView left = (TextView)findViewById(R.id.actionbar_left);
		left.setText(R.string.zuojiantou);
		left.setOnClickListener(this);
		
	}

	private void findViewById() {
		findViewById(R.id.company3_in_setting_exit).setOnClickListener(this);
		findViewById(R.id.company3_in_setting_tip).setOnClickListener(this);
		findViewById(R.id.company3_in_setting_shiping).setOnClickListener(this);
		findViewById(R.id.company3_in_setting_changephone).setOnClickListener(
				this);
		findViewById(R.id.company3_in_setting_changepassword)
				.setOnClickListener(this);
		findViewById(R.id.company3_in_setting_updata).setOnClickListener(this);
		findViewById(R.id.company3_in_setting_help).setOnClickListener(this);
		findViewById(R.id.company3_in_setting_aboutus).setOnClickListener(this);
		findViewById(R.id.company3_in_setting_changetype).setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.company3_in_setting_exit://退出登录
			exitDialogShow();
			break;
		case R.id.company3_in_setting_tip://提醒功能
			break;
		case R.id.company3_in_setting_shiping://视频通话
			break;
		case R.id.company3_in_setting_changephone://修改手机号
			break;
		case R.id.company3_in_setting_changepassword://修改密码
			break;
		case R.id.company3_in_setting_updata://检查更新
			break;
		case R.id.company3_in_setting_help://帮助与反馈
			break;
		case R.id.company3_in_setting_aboutus://关于我们
			break;
		case R.id.company3_in_setting_changetype://更换身份
			//通知MainCompanyActivity关闭
			sendBroadcast(new Intent(BossConstants.ACTION_CHANGE_MAINCOMPANY_FINISH));
			startActivity(new Intent(this,RegisterActivity.class));
			finish();
			break;
		case R.id.actionbar_left://返回
			finish();
			break;
			
			

		}

	}

	// 退出登陆的对话框
	private void exitDialogShow() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("退出登录");
		dialog.setMessage("确定退出？");
		dialog.setCancelable(false);
		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				BmobUser.logOut(SettingActivity.this); // 清除缓存用户对象
				finish();
			}
		});
		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		dialog.show();

	}

}
