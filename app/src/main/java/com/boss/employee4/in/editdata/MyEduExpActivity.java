package com.boss.employee4.in.editdata;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import com.boss.R;
import com.boss.activity.SetDataActivity;
import com.boss.adapter.GetWorkAdapter;
import com.boss.db.User;
import com.boss.ddb.GetWorkYear;
import com.boss.login.BaseActivity;
import com.boss.util.BossConstants;

public class MyEduExpActivity extends BaseActivity implements OnClickListener,
		TextWatcher {

	private View parentView;
	LayoutInflater inflater;
	// 底部
	private PopupWindow pop = null;
	// 底部
	private PopupWindow mpop = null;
	// 底部
	private PopupWindow mmpop = null;
	private MyBroadcastReceiver receiver = new MyBroadcastReceiver();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inflater = LayoutInflater.from(this);
		parentView = inflater.inflate(R.layout.employee4_in_editdata_myeduexp,
				new LinearLayout(this), false);
		setContentView(parentView);
		findViewById();
		InitPop1();
		InitPop2();
		InitPop3();
		initActionBar();
		initData();
		// 注册退出广播
		IntentFilter filter = new IntentFilter();
		filter.addAction(BossConstants.ACTION_REGISTER_SUCCESS_EMPLOYEE_FINISH);
		registerReceiver(receiver, filter);

	}

	private void initData() {

		User user = User.getCurrentUser(this,User.class);
		if (user.getSchoolName() != null) {
			mSchoolName.setText(user.getSchoolName());
		}
		if (user.getMajor() != null) {
			mMajor.setText(user.getMajor());
		}

		if (user.getEduBeginTime() != null) {
			mBeginTime.setText(user.getEduBeginTime());
		}
		if (user.getEduDoneTime() != null) {
			mDoneTime.setText(user.getEduDoneTime());
		}
		if (user.getEducation() != null) {
			mEducation.setText(user.getEducation());
		}
		if (user.getEduJingli() != null) {
			mJingli.setText(user.getEduJingli());
		}
	}

	/** 学校 */
	private TextView mSchoolName;
	/** 专业 */
	private TextView mMajor;
	/** 起始时间 */
	private TextView mBeginTime;
	/** 结束时间 */
	private TextView mDoneTime;
	/** 学历 */
	private TextView mEducation;
	/** 在校经历 */
	private TextView mJingli;
	/** 字数 */
	private TextView mLimit;

	private void findViewById() {
		findViewById(R.id.register_employeestep3_school).setOnClickListener(
				this);
		mSchoolName = (TextView) findViewById(R.id.register_employeestep3_schoolTxt);
		findViewById(R.id.register_employeestep3_major)
				.setOnClickListener(this);
		mBeginTime = (TextView) findViewById(R.id.register_employeestep3_beginTime);
		mBeginTime.setOnClickListener(this);
		mDoneTime = (TextView) findViewById(R.id.register_employeestep3_doneTime);
		mDoneTime.setOnClickListener(this);

		findViewById(R.id.register_employeestep3_goEmployStep4)
				.setOnClickListener(this);
		mMajor = (TextView) findViewById(R.id.register_employeestep3_majorTxt);
		findViewById(R.id.register_employeestep3_education).setOnClickListener(
				this);
		mEducation = (TextView) findViewById(R.id.register_employeestep3_educationTxt);
		mJingli = (EditText) findViewById(R.id.register_companystep4_introduce);
		mJingli.addTextChangedListener(this);
		mLimit = (TextView) findViewById(R.id.register_employeestep2_txt);
	}

	private void initActionBar() {
		TextView left = (TextView) findViewById(R.id.actionbar_left);
		left.setText(R.string.zuojiantou);
		left.setOnClickListener(this);
		TextView center = (TextView) findViewById(R.id.actionbar_center);
		center.setText("创建微简历");
		TextView right = (TextView) findViewById(R.id.actionbar_right);
		right.setText("下一步");
		right.setOnClickListener(this);
	}

	/**
	 * 初始化参加工作的时间段请选择
	 */
	@SuppressWarnings("deprecation")
	public void InitPop1() {

		View view = inflater.inflate(
				R.layout.pop_register_employeestep1_workyear, new LinearLayout(
						this), false);
		// 设置popup的属性
		// 初始化popup
		pop = new PopupWindow(this);
		// 宽
		pop.setWidth(LayoutParams.MATCH_PARENT);
		// 高
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		// 背景位空
		// ColorDrawable dw = new ColorDrawable(0xb0000000);
		pop.setBackgroundDrawable(new BitmapDrawable());
		// 设置焦点点击事件
		pop.setFocusable(true);
		// 设置外边可以被点击
		pop.setOutsideTouchable(true);
		// 设置视图
		pop.setContentView(view);
		view.findViewById(R.id.pop_register_employeestep1_all)
				.setOnClickListener(this);
		view.findViewById(R.id.pop_register_employeestep1_cancel)
				.setOnClickListener(this);

		List<GetWorkYear> mListYear = new ArrayList<GetWorkYear>();
		for (int i = 0; i < BossConstants.GET_WORK_YEAR.length; i++) {
			GetWorkYear year = new GetWorkYear();
			year.setYear(BossConstants.GET_WORK_YEAR[i]);
			mListYear.add(year);
		}

		ListView mGetWorkYearList = (ListView) view
				.findViewById(R.id.pop_register_employeestep1_workyear_list);
		GetWorkAdapter adapter2 = new GetWorkAdapter(this, mListYear);
		mGetWorkYearList.setAdapter(adapter2);
		mGetWorkYearList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mBeginTime.setText(BossConstants.GET_WORK_YEAR[arg2]);
				pop.dismiss();

			}

		});
	}

	@SuppressWarnings("deprecation")
	public void InitPop2() {

		View view = inflater.inflate(
				R.layout.pop_register_employeestep1_workyear, new LinearLayout(
						this), false);
		// 设置popup的属性
		// 初始化popup
		mpop = new PopupWindow(this);
		// 宽
		mpop.setWidth(LayoutParams.MATCH_PARENT);
		// 高
		mpop.setHeight(LayoutParams.WRAP_CONTENT);
		// 背景位空
		// ColorDrawable dw = new ColorDrawable(0xb0000000);
		mpop.setBackgroundDrawable(new BitmapDrawable());
		// 设置焦点点击事件
		mpop.setFocusable(true);
		// 设置外边可以被点击
		mpop.setOutsideTouchable(true);
		// 设置视图
		mpop.setContentView(view);
		view.findViewById(R.id.pop_register_employeestep1_all)
				.setOnClickListener(this);
		view.findViewById(R.id.pop_register_employeestep1_cancel)
				.setOnClickListener(this);

		List<GetWorkYear> mListYear = new ArrayList<GetWorkYear>();
		for (int i = 0; i < BossConstants.GET_WORK_YEAR.length; i++) {
			GetWorkYear year = new GetWorkYear();
			year.setYear(BossConstants.GET_WORK_YEAR[i]);
			mListYear.add(year);
		}

		ListView mGetWorkYearList = (ListView) view
				.findViewById(R.id.pop_register_employeestep1_workyear_list);
		GetWorkAdapter adapter2 = new GetWorkAdapter(this, mListYear);
		mGetWorkYearList.setAdapter(adapter2);
		mGetWorkYearList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mDoneTime.setText(BossConstants.GET_WORK_YEAR[arg2]);
				mpop.dismiss();

			}

		});
	}

	/**
	 * 初始化学历pop
	 */
	@SuppressWarnings("deprecation")
	public void InitPop3() {

		View view = inflater.inflate(
				R.layout.pop_register_employeestep1_workyear, new LinearLayout(
						this), false);
		// 设置popup的属性
		// 初始化popup
		mmpop = new PopupWindow(this);
		// 宽
		mmpop.setWidth(LayoutParams.MATCH_PARENT);
		// 高
		mmpop.setHeight(LayoutParams.WRAP_CONTENT);
		// 背景位空
		// ColorDrawable dw = new ColorDrawable(0xb0000000);
		mmpop.setBackgroundDrawable(new BitmapDrawable());
		// 设置焦点点击事件
		mmpop.setFocusable(true);
		// 设置外边可以被点击
		mmpop.setOutsideTouchable(true);
		// 设置视图
		mmpop.setContentView(view);
		view.findViewById(R.id.pop_register_employeestep1_all)
				.setOnClickListener(this);
		view.findViewById(R.id.pop_register_employeestep1_cancel)
				.setOnClickListener(this);

		List<GetWorkYear> mListYear = new ArrayList<GetWorkYear>();
		for (int i = 0; i < BossConstants.GET_EDUCATION.length; i++) {
			GetWorkYear year = new GetWorkYear();
			year.setYear(BossConstants.GET_EDUCATION[i]);
			mListYear.add(year);
		}

		ListView mGetWorkYearList = (ListView) view
				.findViewById(R.id.pop_register_employeestep1_workyear_list);
		GetWorkAdapter adapter = new GetWorkAdapter(this, mListYear);
		mGetWorkYearList.setAdapter(adapter);
		mGetWorkYearList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mEducation.setText(BossConstants.GET_EDUCATION[arg2]);
				mmpop.dismiss();

			}

		});
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.actionbar_left:// 退出
			finish();
			break;
		case R.id.register_employeestep3_school:// 填写学校名字
			Intent setData = new Intent(this, SetDataActivity.class);
			setData.putExtra("title", "学校");// 传标题
			setData.putExtra("limit", 16);// 传字数限制
			startActivityForResult(setData, 10);
			break;
		case R.id.register_employeestep3_major:// 填写专业名字

			Intent major = new Intent(this, SetDataActivity.class);
			major.putExtra("title", "填写专业");// 传标题
			major.putExtra("limit", 16);// 传字数限制
			startActivityForResult(major, 11);
			break;

		case R.id.register_employeestep3_education:// 设置学历
			mmpop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			break;
		case R.id.actionbar_right:
		case R.id.register_employeestep3_goEmployStep4:// 跳转到求职信息第四项
			goEmployeeStep4();
			break;

		case R.id.register_employeestep3_beginTime:// 起始时间
			pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			break;
		case R.id.register_employeestep3_doneTime:// 结束时间
			mpop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			break;
		case R.id.pop_register_employeestep1_all://
		case R.id.pop_register_employeestep1_cancel://
			pop.dismiss();
			mpop.dismiss();
		default:
			break;
		}

	}

	/**
	 * 下一步
	 */
	private void goEmployeeStep4() {
		String schoolName = mSchoolName.getText().toString();
		if (schoolName == "") {
			ToastNoMessage("学校");
			return;
		}
		String beginTime = mBeginTime.getText().toString();
		if (beginTime == "") {
			ToastNoMessage("学校开始时间");
			return;
		}
		String doneTime = mDoneTime.getText().toString();
		if (doneTime == "") {
			ToastNoMessage("学校毕业时间");
			return;
		}
		String education = mEducation.getText().toString();
		if (education == "") {
			ToastNoMessage("学历");
			return;
		}
		String major = mMajor.getText().toString();
		if (major == "") {
			ToastNoMessage("专业");
			return;
		}
		String jingli = mJingli.getText().toString();
		if (jingli.length() > 300) {
			Toast("在校经历不得超过300字");
		}
		User newUser = new User();
		newUser.setSchoolName(schoolName);
		newUser.setMajor(major);
		newUser.setEduBeginTime(beginTime);
		newUser.setEduDoneTime(doneTime);
		newUser.setEducation(education);
		newUser.setEduJingli(jingli);
		User bmobUser = User.getCurrentUser(this,User.class);
		newUser.update(this,bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("更新成功");
				MyEduExpActivity.this.finish();
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
/*			@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("更新成功");
					MyEduExpActivity.this.finish();

				} else {
					Toast("请重试" + e.getMessage());
				}
			}*/

		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case 10:// 提取返回的姓名
				String companyName = data.getStringExtra("data_return");
				mSchoolName.setText(companyName);
				break;
			case 11:// 填写专业
				String major = data.getStringExtra("data_return");
				mMajor.setText(major);
				break;

			}
		}
	}

	/**
	 * 自定义广播（用来注册成功，密码登录成功关闭本活动）
	 */
	public class MyBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent != null
					&& BossConstants.ACTION_REGISTER_SUCCESS_EMPLOYEE_FINISH
							.equals(intent.getAction())) {
				MyEduExpActivity.this.finish();
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
		mLimit.setText(s.length() + "/300");
		if (s.length() == 300) {
			mLimit.setTextColor(getResources().getColor(R.color.app_main));
		} else if (s.length() == 301) {
			mLimit.setTextColor(getResources().getColor(R.color.app_red));
		}
	}

	public void afterTextChanged(Editable s) {

	}
}
