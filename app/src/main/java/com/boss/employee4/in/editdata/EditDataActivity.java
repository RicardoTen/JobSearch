package com.boss.employee4.in.editdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.boss.R;
import com.boss.db.User;
import com.boss.employee4.in.editdata.qiuzhiyixiang.MyQiuzhiyixiang;
import com.boss.login.BaseActivity;

public class EditDataActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee4_in_editdata_editdata);
		findViewById();
		initActionBar();
		initData();
	}

	/**昵称*/
	private TextView mNick;
	private void findViewById() {
		findViewById(R.id.employee4_in_editdata_goMyintroduce).setOnClickListener(this);
		findViewById(R.id.employee4_in_editdata_goMyShejiaozhuye).setOnClickListener(this);
		findViewById(R.id.employee4_in_editdata_goMyQiuzhiyixiang).setOnClickListener(this);
		findViewById(R.id.employee4_in_editdata_goChaneUserData).setOnClickListener(this);
		mNick = (TextView)findViewById(R.id.employee4_in_editdata_nick);
		findViewById(R.id.employee4_in_editdata_goMyWorkExp).setOnClickListener(this);
		findViewById(R.id.employee4_in_editdata_goYulanjianli).setOnClickListener(this);
		findViewById(R.id.employee4_in_editdata_goMyEduExp).setOnClickListener(this);
		findViewById(R.id.employee4_in_editdata_goMyXiangmuExp).setOnClickListener(this);

	}
	private void initData() {
		User user = User.getCurrentUser(this, User.class);
		if (user.getNick() != null) {
			mNick.setText(user.getNick());
		}
		 
	}

	private void initActionBar() {
		TextView left = (TextView) findViewById(R.id.actionbar_left);
		left.setText(R.string.zuojiantou);
		left.setOnClickListener(this);
		TextView center = (TextView) findViewById(R.id.actionbar_center);
		center.setText("编辑微简历");
		// TextView right = (TextView) findViewById(R.id.actionbar_right);
		// right.setText(R.string.gou);
		// right.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.actionbar_left:// 返回按钮
			finish();
			break;
		case R.id.employee4_in_editdata_goMyintroduce://跳转到修改我的优势
			startActivity(new Intent(this,MyIntroduceActivity.class));
			break;
		case R.id.employee4_in_editdata_goMyShejiaozhuye://跳转到修改我的社交主页
			startActivity(new Intent(this,MyShejiaozhuyeActivity.class));
			break;
		case R.id.employee4_in_editdata_goMyQiuzhiyixiang://跳转到管理求职意向
			startActivity(new Intent(this,MyQiuzhiyixiang.class));
			break;
		case R.id.employee4_in_editdata_goChaneUserData://查看个人信息
			 startActivity(new Intent(this,UserDataActivity.class));
			break;
		case R.id.employee4_in_editdata_goMyWorkExp://跳转到工作经历
			startActivity(new Intent(this,MyWorkExpActivity.class));
			break;
			
		case R.id.employee4_in_editdata_goYulanjianli://预览简历
			startActivity(new Intent(this,YuLanJianLiActivity.class));
			break;
			
		case R.id.employee4_in_editdata_goMyEduExp://教育经历
			startActivity(new Intent(this,MyEduExpActivity.class));
			
			break;
		case R.id.employee4_in_editdata_goMyXiangmuExp://项目经历
//			startActivity(new Intent(this,MyXiangmuActivity.class));
			break;
		default:
			break;
		}

	}

}
