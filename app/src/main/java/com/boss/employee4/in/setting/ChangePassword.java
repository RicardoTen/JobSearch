package com.boss.employee4.in.setting;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.boss.login.BaseActivity;

public class ChangePassword extends BaseActivity implements OnClickListener {
	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	 
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

//				if (mEditText1.getText().toString().equals("")
//						|| mEditText2.getText().toString().equals("")
//						|| mEditText3.getText().toString().equals("")) {
//					Toast("密码不能为空");
//					break;
//				} else if (!mEditText1.getText().toString()
//						.equals(mEditText2.getText().toString())) {
//					Toast("两次密码输入不一样");
//					break;
//				} else {
//					BmobUser.updateCurrentUserPassword(mEditText3.getText()
//							.toString(), mEditText1.getText().toString(),
//							new UpdateListener() {
//
//								@Override
//								public void done(BmobException e) {
//									if (e == null) {
//										Toast("密码修改成功，可以用新密码进行登录啦");
//										finish();
//									} else {
//										Toast("密码错误:" + e.getMessage());
//									}
//								}
//
//							});
}
