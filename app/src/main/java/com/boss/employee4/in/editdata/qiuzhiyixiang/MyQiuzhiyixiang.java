package com.boss.employee4.in.editdata.qiuzhiyixiang;

import java.util.ArrayList;
import java.util.List;

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
import com.boss.adapter.QiuzhiyixiangAdapter;
import com.boss.db.User;
import com.boss.ddb.GetWorkYear;
import com.boss.login.BaseActivity;
import com.boss.util.BossConstants;

public class MyQiuzhiyixiang extends BaseActivity implements OnClickListener {
	private View parentView;
	LayoutInflater inflater;
	// 底部
	private PopupWindow pop = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		inflater = LayoutInflater.from(this);
		parentView = inflater.inflate(
				R.layout.activity_employee4_in_editdata_myqiuzhiyixiang,
				new LinearLayout(this), false);
		setContentView(parentView);
		findViewById();
		InitPop1();
		initActionBar();
		initListView();
		initData();

	}

	QiuzhiyixiangAdapter adapter2;
	List<User> mListShow2;

	private void initListView() {
		mListShow2 = new ArrayList<User>();
		User user = User.getCurrentUser(this,User.class);
		if(user.getCity()!=null){
			mListShow2.add(user);	
		}
		adapter2 = new QiuzhiyixiangAdapter(MyQiuzhiyixiang.this, mListShow2);
		mlistView.setAdapter(adapter2);
	}
	private void initData() {
		User user = User.getCurrentUser(this,User.class);
		if (user.getEmployeeWorkyear() != null) {
			mState.setText(user.getWorkState());
		}
		 
	}
	private ListView mlistView;
	/**求职状态	 */
	private TextView mState;
	private void findViewById() {
		findViewById(R.id.employee4_in_editdata_myqiuzhiyixiang_state)
				.setOnClickListener(this);
		mlistView = (ListView) findViewById(R.id.employee4_in_editdata_myqiuzhiyixiang_listview);
		findViewById(R.id.employee4_in_editdata_myqiuzhiyixiang_goQiWangZhiwei)
				.setOnClickListener(this);
		mState = (TextView)findViewById(R.id.employee4_in_editdata_myqiuzhiyixiang_stateTxt);
	}

	private void initActionBar() {
		TextView left = (TextView) findViewById(R.id.actionbar_left);
		left.setText(R.string.zuojiantou);
		left.setOnClickListener(this);
		TextView center = (TextView) findViewById(R.id.actionbar_center);
		center.setText("管理求职意向");

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.actionbar_left:// 返回键
			MyQiuzhiyixiang.this.finish();
			break;
		case R.id.employee4_in_editdata_myqiuzhiyixiang_state:// 更换求职状态
			pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			break;
		case R.id.employee4_in_editdata_myqiuzhiyixiang_goQiWangZhiwei:// 添加期望职位
			User user = User.getCurrentUser(this, User.class);
			if(user.getCity()!=null){
				Toast("求职意向不得超过1个");
			}else{
				startActivity(new Intent(this,AddExpectWorkActivity.class));
			}
			
			 
			break;
		default:
			break;
		}

	}
 

	  

	@Override
	protected void onResume() {
		super.onResume();
		User user = User.getCurrentUser(this,User.class);
		mListShow2.clear();
		mListShow2.add(user);
		adapter2.notifyDataSetChanged();

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
		TextView txt = (TextView)view.findViewById(R.id.pop_register_employeestep1_txt);
		txt.setText("求职状态");
		List<GetWorkYear> mListYear = new ArrayList<GetWorkYear>();
		for (int i = 0; i < BossConstants.STATIC.length; i++) {
			GetWorkYear year = new GetWorkYear();
			year.setYear(BossConstants.STATIC[i]);
			mListYear.add(year);
		}

		ListView mGetWorkYearList = (ListView) view
				.findViewById(R.id.pop_register_employeestep1_workyear_list);
		GetWorkAdapter adapter2 = new GetWorkAdapter(this, mListYear);
		mGetWorkYearList.setAdapter(adapter2);
		mGetWorkYearList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mState.setText(BossConstants.STATIC[arg2]);
				goSave(BossConstants.STATIC[arg2]);
				pop.dismiss();

			}

		});
	}
	
	/**
	 * 保存用户选择的状态
	 */
	private void goSave(String mStatic){
		User newUser = new User();
		newUser.setWorkState(mStatic);
		User bmobUser = User.getCurrentUser(this,User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("修改完成");
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
		/*	@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("修改完成");

				} else {
					Toast("请重试" + e.getMessage());
				}
			}*/

		});
	}
}
