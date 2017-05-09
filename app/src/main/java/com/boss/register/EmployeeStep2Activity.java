package com.boss.register;

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
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

public class EmployeeStep2Activity extends BaseActivity implements
		OnClickListener,TextWatcher {
	// 界面布局
	private View parentView;
	LayoutInflater inflater;
	// 底部
	private PopupWindow pop = null;
	// 底部
	private PopupWindow mpop = null;
	private MyBroadcastReceiver receiver = new MyBroadcastReceiver();

	CircleProgressDialog circleProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inflater = LayoutInflater.from(this);
		parentView = inflater.inflate(R.layout.activity_register_employeestep2,
				new LinearLayout(this), false);
		setContentView(parentView);
		findViewById();
		InitPop1();
		InitPop2();
		initActionBar();
		// 注册退出广播
		IntentFilter filter = new IntentFilter();
		filter.addAction(BossConstants.ACTION_REGISTER_SUCCESS_EMPLOYEE_FINISH);
		registerReceiver(receiver, filter);

	}
 

	/** 公司名字 */
	private TextView mCompanyName;
	/** 参加工作的起始时间 */
	private TextView mBeginTime;
	/** 参加工作的结束时间 */
	private TextView mDoneTime;
	/** 职业类型 */
	private TextView mWorkType;
	/** 技能标签 */
	private TextView mSkill;
	/**工作内容*/
	private TextView mContent;
	/**工作内容的字数*/
	private TextView mLength;

	private void findViewById() {
		findViewById(R.id.register_employeestep2_companyName)
				.setOnClickListener(this);
		mCompanyName = (TextView) findViewById(R.id.register_employeestep2_companyNameTxt);
		findViewById(R.id.register_employeestep2_goEmployeeStep3)
				.setOnClickListener(this);
		mBeginTime = (TextView) findViewById(R.id.register_employeestep2_timeBegin);
		mBeginTime.setOnClickListener(this);
		mDoneTime = (TextView) findViewById(R.id.register_employeestep2_timeDone);
		mDoneTime.setOnClickListener(this);
		findViewById(R.id.register_employeestep2_employeeType)
				.setOnClickListener(this);
		mWorkType = (TextView) findViewById(R.id.register_employeestep2_employeeTypeTxt);
		findViewById(R.id.register_employeestep2_skill)
				.setOnClickListener(this);
		mSkill = (TextView) findViewById(R.id.register_employeestep2_skillTxt);
		mContent = (EditText)findViewById(R.id.register_employeestep2_workContent);
		mContent.addTextChangedListener(this);
		mLength = (TextView)findViewById(R.id.register_employeestep2_txt);

		circleProgressDialog = new CircleProgressDialog(this);
	}
	private void initActionBar() {
		TextView left = (TextView)findViewById(R.id.actionbar_left);
		left.setText(R.string.zuojiantou);
		left.setOnClickListener(this);
		TextView center = (TextView) findViewById(R.id.actionbar_center);
		center.setText("创建微简历");
		TextView right = (TextView) findViewById(R.id.actionbar_right);
		right.setText("下一步");
		right.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.actionbar_left://返回
			finish();
			break;
		case R.id.register_employeestep2_companyName:// 设置曾任公司名字
			Intent setData = new Intent(this, SetDataActivity.class);
			setData.putExtra("title", "公司名称");// 传标题
			setData.putExtra("limit", 16);// 传字数限制
			startActivityForResult(setData, 10);
			break;
		case R.id.register_employeestep2_employeeType:// 职业类型
			Intent setWorkType = new Intent(this, SetWorkTypeActivity.class);
			startActivityForResult(setWorkType, 11);
			break;

		case R.id.register_employeestep2_skill:// 技能标签
			Intent skill = new Intent(this, SetWorkSkillActivity.class);
			startActivityForResult(skill, 12);
			break;
		case R.id.actionbar_right:
		case R.id.register_employeestep2_goEmployeeStep3:// 跳转到工作
			goEmployeeStep3();
			break;
		case R.id.register_employeestep2_timeBegin:// 参加工作的开始时间
			pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			break;

		case R.id.register_employeestep2_timeDone:// 参加工作的结束时间
			mpop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			break;

		case R.id.pop_register_employeestep1_all:// 点击外面
			pop.dismiss();
			mpop.dismiss();
			break;
		case R.id.pop_register_employeestep1_cancel:// 取消按钮
			pop.dismiss();
			mpop.dismiss();
			break;
		case R.id.pop_register_employeestep1_ok:// 确认按钮
			pop.dismiss();
			mpop.dismiss();
			break;
		}

	}

	/**
	 * 
	 */
	private void goEmployeeStep3() {
		String companyName = mCompanyName.getText().toString();
		if (companyName == "") {
			ToastNoMessage("公司名称");
			return;
		}
		String beginTime = mBeginTime.getText().toString();
		if (beginTime == "") {
			ToastNoMessage("时间段的起始时间");
			return;
		}
		String doneTime = mDoneTime.getText().toString();
		if (doneTime == "") {
			ToastNoMessage("时间段的结束时间");
			return;
		}
		String workType = mWorkType.getText().toString();
		if (workType == "") {
			ToastNoMessage("职业类型");
			return;
		}
		String skill = mSkill.getText().toString();
		if (skill == "") {
			ToastNoMessage("职业技能");
			return;
		}
		String content = mContent.getText().toString();
		if(content ==""){
			ToastNoMessage("工作内容");
			return;
		}
		if(content.length()>300){
			Toast("工作内容字数不得超过300字");
			return;
		}

		circleProgressDialog.showDialog();
		User newUser = new User();
		newUser.setCompanyName(companyName);
		newUser.setBeginTime(beginTime);
		newUser.setDoneTime(doneTime);
		newUser.setWorkType(workType);
		newUser.setSkill(skill);
		newUser.setContent(content);
		User bmobUser = User.getCurrentUser(this,User.class);
		newUser.update(this,bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				circleProgressDialog.dismiss();
				startActivity(new Intent(EmployeeStep2Activity.this,
						EmployeeStep3Activity.class));
			}

			@Override
			public void onFailure(int i, String s) {
				circleProgressDialog.dismiss();
				Toast("请重试" + s);
			}
			/*@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("创建数据成功");
					startActivity(new Intent(EmployeeStep2Activity.this,
							EmployeeStep3Activity.class));
				} else {
					Toast("请重试" + e.getMessage());
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case 10:// 提取返回的姓名
				String companyName = data.getStringExtra("data_return");
				mCompanyName.setText(companyName);
				break;
			case 11:// 选择工作类型
				String type = data.getStringExtra("data_return");
				mWorkType.setText(type);
				break;
			case 12:// 设置技能标签
				String skill = data.getStringExtra("data_return");
				mSkill.setText(skill);
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
				EmployeeStep2Activity.this.finish();
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
		mLength.setText(s.length() + "/300");
		if(s.length()==300){
			mLength.setTextColor(getResources().getColor(R.color.app_main));
		}else if(s.length()==301){
			mLength.setTextColor(getResources().getColor(R.color.app_red));
		}
	}

	public void afterTextChanged(Editable s) {

	}
}
