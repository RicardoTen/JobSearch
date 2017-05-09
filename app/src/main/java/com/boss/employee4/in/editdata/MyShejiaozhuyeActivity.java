package com.boss.employee4.in.editdata;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import com.boss.R;
import com.boss.db.User;
import com.boss.login.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class MyShejiaozhuyeActivity extends BaseActivity implements
		OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee4_in_editdata_myshejiaozhuye);
		findViewById();
		initActionBar();
	}

	/** 社交主页编辑框 */
	private EditText mContent;

	private void findViewById() {
		mContent = (EditText) findViewById(R.id.employee4_in_editdata_myshijiaozhuye_website);
		findViewById(R.id.employee4_in_editdata_myshijiaozhuye_save)
				.setOnClickListener(this);
		User user = User.getCurrentUser(this, User.class);
		mContent.setText(user.getIntroduce());

	}

	private void initActionBar() {
		TextView left = (TextView) findViewById(R.id.actionbar_left);
		left.setText(R.string.zuojiantou);
		left.setOnClickListener(this);
		TextView center = (TextView) findViewById(R.id.actionbar_center);
		center.setText("社交主页");

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.actionbar_left:// 返回键
			MyShejiaozhuyeActivity.this.finish();
			break;
		case R.id.employee4_in_editdata_myshijiaozhuye_save:// 保存
			goSave();
			break;
		default:
			break;
		}

	}

	private void goSave() {
		String content = mContent.getText().toString();
		if (content == "") {
			ToastNoMessage("网址");
			return;
		}
		User newUser = new User();
		newUser.setIntroduce(content);
		User bmobUser = User.getCurrentUser(this, User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("修改完成");
				MyShejiaozhuyeActivity.this.finish();
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
			/*@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("修改完成");
					MyShejiaozhuyeActivity.this.finish();

				} else {
					Toast("请重试" + e.getMessage());
				}
			}*/

		});

	}

}
