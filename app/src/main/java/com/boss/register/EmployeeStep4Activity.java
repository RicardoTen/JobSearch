package com.boss.register;

import android.content.Intent;
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
import com.boss.login.MainEmployeeActivity;
import com.boss.util.BossConstants;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

public class EmployeeStep4Activity extends BaseActivity implements
		OnClickListener, TextWatcher {

	CircleProgressDialog circleProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_employeestep4);
		findViewById();
		initActionBar();
	}

	/** 内容 */
	private EditText mContent;
	/** 显示字数 */
	private TextView mLimit;

	public void findViewById() {
		findViewById(R.id.register_companystep4_goMainEmployee)
				.setOnClickListener(this);
		mContent = (EditText) findViewById(R.id.register_companystep4_introduce);
		mContent.addTextChangedListener(this);
		mLimit = (TextView) findViewById(R.id.register_employeestep2_txt);
		circleProgressDialog = new CircleProgressDialog(this);
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
		circleProgressDialog.showDialog();
		User newUser = new User();
		newUser.setIntroduce(content);
		newUser.setMainLayout(false);
		newUser.setRegisterEmployee(true);
		User bmobUser = User.getCurrentUser(this, User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				circleProgressDialog.dismiss();
				Toast("恭喜你完成漫长的注册...");
				startActivity(new Intent(EmployeeStep4Activity.this,
						MainEmployeeActivity.class));
				sendBroadcast(new Intent(
						BossConstants.ACTION_REGISTER_SUCCESS_EMPLOYEE_FINISH));
				finish();
			}

			@Override
			public void onFailure(int i, String s) {
				circleProgressDialog.dismiss();
				Toast("请重试" + s);
			}
	/*		@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("恭喜你完成漫长的注册...");
					startActivity(new Intent(EmployeeStep4Activity.this,
							MainEmployeeActivity.class));
					sendBroadcast(new Intent(
							BossConstants.ACTION_REGISTER_SUCCESS_EMPLOYEE_FINISH));
					finish();

				} else {
					Toast("请重试" + e.getMessage());
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
