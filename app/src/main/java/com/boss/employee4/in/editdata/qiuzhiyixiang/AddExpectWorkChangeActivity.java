package com.boss.employee4.in.editdata.qiuzhiyixiang;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import com.boss.R;
import com.boss.adapter.GetWorkAdapter;
import com.boss.db.User;
import com.boss.ddb.GetWorkYear;
import com.boss.login.BaseActivity;
import com.boss.register.SetWorkTypeActivity;
import com.boss.util.BossConstants;

public class AddExpectWorkChangeActivity extends BaseActivity implements
		OnClickListener {
	private View parentView;
	LayoutInflater inflater;
	// 底部
	private PopupWindow pop = null;
	// 底部
	private PopupWindow mpop = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		inflater = LayoutInflater.from(this);
		parentView = inflater.inflate(
				R.layout.activity_employee4_in_editdata_addqiuzhiyixiang,
				new LinearLayout(this), false);
		setContentView(parentView);
		InitPop1();
		InitPop2();
		findViewById();
		initActionBar();
	}

	/** 期望职位 */
	private TextView mWorkType;
	/** 期望行业 */
	private TextView mHangye;
	/** 期望城市 */
	private TextView mCity;
	/** 薪资要求 */
	private TextView mBegin;
	private TextView mDone;

	private void findViewById() {
		findViewById(R.id.employee4_in_editdata_addqiuzhiyixiang_GoemployeeType)
				.setOnClickListener(this);
		mWorkType = (TextView) findViewById(R.id.employee4_in_editdata_addqiuzhiyixiang_GoemployeeTypeTxt);
		findViewById(
				R.id.employee4_in_editdata_addqiuzhiyixiang_GoemployeeHangye)
				.setOnClickListener(this);
		mHangye = (TextView) findViewById(R.id.employee4_in_editdata_addqiuzhiyixiang_GoemployeeHangyeTxt);
		findViewById(
				R.id.employee4_in_editdata_addqiuzhiyixiang_GoemployeeChengshi)
				.setOnClickListener(this);
		mCity = (TextView) findViewById(R.id.employee4_in_editdata_addqiuzhiyixiang_GoemployeeChengshiTxt);

		findViewById(R.id.employee4_in_editdata_addqiuzhiyixiang_save)
				.setOnClickListener(this);
		mBegin = (TextView) findViewById(R.id.employee4_in_editdata_addqiuzhiyixiang_xinziBegin);
		mBegin.setOnClickListener(this);
		mDone = (TextView) findViewById(R.id.employee4_in_editdata_addqiuzhiyixiang_xinziDone);
		mDone.setOnClickListener(this);

		Intent intent = getIntent();
		mDone.setText(intent.getStringExtra("done"));
		mBegin.setText(intent.getStringExtra("begin"));
		mWorkType.setText(intent.getStringExtra("zhiwei"));
		mHangye.setText(intent.getStringExtra("hangye"));
		mCity.setText(intent.getStringExtra("city"));

	}

	private void initActionBar() {
		TextView left = (TextView) findViewById(R.id.actionbar_left);
		left.setText(R.string.zuojiantou);
		left.setOnClickListener(this);
		TextView center = (TextView) findViewById(R.id.actionbar_center);
		center.setText("添加求职意向");

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.actionbar_left:// 返回键
			AddExpectWorkChangeActivity.this.finish();
			break;
		case R.id.employee4_in_editdata_addqiuzhiyixiang_GoemployeeType:// 跳转到期望职位
			Intent setWorkType = new Intent(this, SetWorkTypeActivity.class);
			startActivityForResult(setWorkType, 11);
			break;
		case R.id.employee4_in_editdata_addqiuzhiyixiang_GoemployeeHangye:// 跳转到期望行业
			Intent hangye = new Intent(this, SetWorkHangyeActivity.class);
			startActivityForResult(hangye, 12);
			break;
		case R.id.employee4_in_editdata_addqiuzhiyixiang_GoemployeeChengshi:// 工作城市
			Intent city = new Intent(this, SetCityActivity.class);
			startActivityForResult(city, 13);
			break;

		case R.id.employee4_in_editdata_addqiuzhiyixiang_xinziBegin:// 薪资要求
			pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			break;
		case R.id.employee4_in_editdata_addqiuzhiyixiang_xinziDone://
			mpop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			break;
		case R.id.employee4_in_editdata_addqiuzhiyixiang_save:// 保存
			goSave();
			break;

		default:
			break;
		}

	}

	/**
	 * 保存信息
	 */
	private void goSave() {
		String begin = mBegin.getText().toString();
		if (begin == "") {
			ToastNoMessage("薪资");
			return;
		}
		String done = mDone.getText().toString();
		if (done == "") {
			ToastNoMessage("薪资");
			return;
		}
		String city = mCity.getText().toString();
		if (city == "") {
			ToastNoMessage("期望城市");
			return;
		}
		String hangye = mHangye.getText().toString();
		if (hangye == "") {
			ToastNoMessage("期望行业");
			return;
		}
		String worktype = mWorkType.getText().toString();
		if (worktype == "") {
			ToastNoMessage("期望职位");
			return;
		}

		User newUser = new User();
		newUser.setZhiwei(worktype);
		newUser.setHangye(hangye);
		newUser.setXinziBegin(begin);
		newUser.setXinziDone(done);
		newUser.setCity(city);
		User bmobUser = User.getCurrentUser(this, User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("更新用户信息成功");
				AddExpectWorkChangeActivity.this.finish();
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("更新用户信息失败:" + s);
			}
			/*@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("更新用户信息成功");
					AddExpectWorkChangeActivity.this.finish();
				} else {
					Toast("更新用户信息失败:" + e.getMessage());
				}
			}*/
		});

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
		for (int i = 0; i < BossConstants.Money.length; i++) {
			GetWorkYear year = new GetWorkYear();
			year.setYear(BossConstants.Money[i]);
			mListYear.add(year);
		}

		ListView mGetWorkYearList = (ListView) view
				.findViewById(R.id.pop_register_employeestep1_workyear_list);
		GetWorkAdapter adapter2 = new GetWorkAdapter(this, mListYear);
		mGetWorkYearList.setAdapter(adapter2);
		mGetWorkYearList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mBegin.setText(BossConstants.Money[arg2]);
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
		for (int i = 0; i < BossConstants.Money.length; i++) {
			GetWorkYear year = new GetWorkYear();
			year.setYear(BossConstants.Money[i]);
			mListYear.add(year);
		}

		ListView mGetWorkYearList = (ListView) view
				.findViewById(R.id.pop_register_employeestep1_workyear_list);
		GetWorkAdapter adapter2 = new GetWorkAdapter(this, mListYear);
		mGetWorkYearList.setAdapter(adapter2);
		mGetWorkYearList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mDone.setText(BossConstants.Money[arg2]);
				mpop.dismiss();

			}

		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case 11:// 选择工作类型
				String type = data.getStringExtra("data_return");
				mWorkType.setText(type);
				break;
			case 12:// 选择行业
				String hangye = data.getStringExtra("data_return");
				mHangye.setText(hangye);
				break;
			case 13:// 选择城市
				String city = data.getStringExtra("data_return");
				mCity.setText(city);
				break;

			}
		}
	}

}
