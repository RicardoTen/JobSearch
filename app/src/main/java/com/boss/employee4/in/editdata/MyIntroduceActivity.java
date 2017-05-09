package com.boss.employee4.in.editdata;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import com.boss.R;
import com.boss.db.User;
import com.boss.login.BaseActivity;

public class MyIntroduceActivity extends BaseActivity implements
		OnClickListener, TextWatcher {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee4_in_editdata_myintrouduce);
		findViewById();
		initActionBar();
	}

	/** 内容 */
	private EditText mContent;
	/** 显示字数 */
	private TextView mLimit;

	public void findViewById() {
		findViewById(R.id.employee4_in_editdata_myintroduce_save)
				.setOnClickListener(this);
		mContent = (EditText) findViewById(R.id.employee4_in_editdata_myintroduce_introuduce);
		mContent.addTextChangedListener(this);
		mLimit = (TextView) findViewById(R.id.employee4_in_editdata_myintroduce);
		User user = User.getCurrentUser(this, User.class);
		mContent.setText(user.getIntroduce());

	}

	private void initActionBar() {
		TextView left = (TextView) findViewById(R.id.actionbar_left);
		left.setText(R.string.zuojiantou);
		left.setOnClickListener(this);
		TextView center = (TextView) findViewById(R.id.actionbar_center);
		center.setText("我的优势");
		TextView right = (TextView) findViewById(R.id.actionbar_right);
		right.setText(R.string.gou);
		right.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.actionbar_left:// 返回
			finish();
			break;

		case R.id.actionbar_right:
		case R.id.register_companystep4_goMainEmployee:// 完成按钮
			goMainEmployee();
			break;

		default:
			break;
		}

	}

	private void goMainEmployee() {
		String content = mContent.getText().toString();
		if (content.length() < 20) {
			Toast("字数不得少于20字");
			return;
		}
		User newUser = new User();
		newUser.setIntroduce(content);
		User bmobUser = User.getCurrentUser(this, User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("修改完成");
				MyIntroduceActivity.this.finish();
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
/*			@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("修改完成");
					MyIntroduceActivity.this.finish();

				} else {
					Toast("请重试" + e.getMessage());
				}
			}*/

		});

	}

	/**
	 * TextWatch接口，当号码输入框有内容时，增加个按钮来快速清空内容
	 */
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
		mLimit.setText(s.length() + "/140");
		if (s.length() == 140) {
			mLimit.setTextColor(getResources().getColor(R.color.app_main));
		} else if (s.length() == 141) {
			mLimit.setTextColor(getResources().getColor(R.color.app_red));
		}
	}

	public void afterTextChanged(Editable s) {

	}
}
