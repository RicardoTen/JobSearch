package com.boss.employee4.in.editdata;

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
import com.boss.activity.SetDataActivity;
import com.boss.adapter.GetWorkAdapter;
import com.boss.db.User;
import com.boss.ddb.GetWorkYear;
import com.boss.login.BaseActivity;
import com.boss.register.SetWorkSkillActivity;
import com.boss.register.SetWorkTypeActivity;
import com.boss.util.BossConstants;

/**
 * 工作经历
 * @author Administrator
 */
public class MyWorkExpActivity extends BaseActivity implements OnClickListener {
	// 界面布局
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
				R.layout.activity_employee4_in_editdata_myworkexp,
				new LinearLayout(this), false);
		setContentView(parentView);
		findViewById();
		InitPop1();
		InitPop2();
		initActionBar();
		initData();

	}

	private void initData() {
		User user = User.getCurrentUser(this, User.class);
		if (user.getCompanyName() != null) {
			mCompanyName.setText(user.getCompanyName());
		}
		if (user.getSkill() != null) {
			mSkill.setText(user.getSkill());
		}
		if (user.getDoneTime() != null) {
			mDoneTime.setText(user.getDoneTime());
		}
		if (user.getBeginTime() != null) {
			mBeginTime.setText(user.getBeginTime());
		}
		if (user.getWorkType() != null) {
			mWorkType.setText(user.getWorkType());
		}
		if (user.getZhiyemingcheng() != null) {
			mZhiye.setText(user.getZhiyemingcheng());
		}
		if (user.getmCompanyHangye() != null) {
			mCompanyHangye.setText(user.getmCompanyHangye());
		}
		if (user.getmSuoshubumen() != null) {
			mSuoshubumen.setText(user.getmSuoshubumen());
		}
		if (user.getmGongzuoneirong() != null) {
			mGongzuoneirong.setText(user.getmGongzuoneirong());
		}
		if (user.getmGongzuoyeji() != null) {
			mGongzuoyeji.setText(user.getmGongzuoyeji());
		}

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
	/** 职业名称 */
	private TextView mZhiye;
	/** 公司行业 */
	private TextView mCompanyHangye;
	/** 所属部门 */
	private TextView mSuoshubumen;
	/** 工作内容 */
	private TextView mGongzuoneirong;
	/** 工作业绩 */
	private TextView mGongzuoyeji;

	private void findViewById() {
		findViewById(R.id.employee4_in_editdata_mayworkexp_companyName)
				.setOnClickListener(this);
		mCompanyName = (TextView) findViewById(R.id.employee4_in_editdata_mayworkexp_companyNameTxt);

		mBeginTime = (TextView) findViewById(R.id.employee4_in_editdata_mayworkexp_begin);
		mBeginTime.setOnClickListener(this);
		mDoneTime = (TextView) findViewById(R.id.employee4_in_editdata_mayworkexp_done);
		mDoneTime.setOnClickListener(this);
		findViewById(R.id.employee4_in_editdata_mayworkexp_employeeType)
				.setOnClickListener(this);
		mWorkType = (TextView) findViewById(R.id.employee4_in_editdata_mayworkexp_employeeTypeTxt);
		findViewById(R.id.employee4_in_editdata_mayworkexp_skill)
				.setOnClickListener(this);
		mSkill = (TextView) findViewById(R.id.employee4_in_editdata_mayworkexp_skillTxt);
		mZhiye = (TextView) findViewById(R.id.employee4_in_editdata_mayworkexp_zhiyemingchengTxt);
		findViewById(R.id.employee4_in_editdata_mayworkexp_zhiyemingcheng)
				.setOnClickListener(this);
		mCompanyHangye = (TextView) findViewById(R.id.employee4_in_editdata_mayworkexp_companyHangyeTxt);
		findViewById(R.id.employee4_in_editdata_mayworkexp_companyHangye)
				.setOnClickListener(this);
		mSuoshubumen = (TextView) findViewById(R.id.employee4_in_editdata_mayworkexp_suoshubumenTxt);
		findViewById(R.id.employee4_in_editdata_mayworkexp_suoshubumen)
				.setOnClickListener(this);
		mGongzuoneirong = (TextView) findViewById(R.id.employee4_in_editdata_mayworkexp_workcontentTxt);
		findViewById(R.id.employee4_in_editdata_mayworkexp_workcontent)
				.setOnClickListener(this);
		mGongzuoyeji = (TextView) findViewById(R.id.employee4_in_editdata_mayworkexp_gongzuoyejiTxt);
		findViewById(R.id.employee4_in_editdata_mayworkexp_gongzuoyeji)
				.setOnClickListener(this);
	}

	private void initActionBar() {
		TextView left = (TextView) findViewById(R.id.actionbar_left);
		left.setText(R.string.zuojiantou);
		left.setOnClickListener(this);
		TextView center = (TextView) findViewById(R.id.actionbar_center);
		center.setText("工作经历");

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.actionbar_left:// 返回
			finish();
			break;
		case R.id.employee4_in_editdata_mayworkexp_companyName:// 设置曾任公司名字
			Intent setData = new Intent(this, SetDataActivity.class);
			setData.putExtra("title", "公司名称");// 传标题
			setData.putExtra("limit", 16);// 传字数限制
			startActivityForResult(setData, 10);
			break;
		case R.id.employee4_in_editdata_mayworkexp_employeeType:// 职业类型
			Intent setWorkType = new Intent(this, SetWorkTypeActivity.class);
			startActivityForResult(setWorkType, 11);
			break;

		case R.id.employee4_in_editdata_mayworkexp_skill:// 技能标签
			Intent skill = new Intent(this, SetWorkSkillActivity.class);
			startActivityForResult(skill, 12);
			break;
		case R.id.employee4_in_editdata_mayworkexp_zhiyemingcheng:// 职业名称
			Intent zhiye = new Intent(this, SetDataActivity.class);
			zhiye.putExtra("title", "职位名称");// 传标题
			zhiye.putExtra("limit", 12);// 传字数限制
			startActivityForResult(zhiye, 13);
			break;

		case R.id.employee4_in_editdata_mayworkexp_companyHangye:// 公司行业
			Intent hangye = new Intent(this, SetDataActivity.class);
			hangye.putExtra("title", "公司行业");// 传标题
			hangye.putExtra("limit", 6);// 传字数限制
			startActivityForResult(hangye, 14);
			break;
		case R.id.employee4_in_editdata_mayworkexp_suoshubumen:// 所属部门
			Intent bumen = new Intent(this, SetDataActivity.class);
			bumen.putExtra("title", "所属部门");// 传标题
			bumen.putExtra("limit", 6);// 传字数限制
			startActivityForResult(bumen, 15);
			break;
		case R.id.employee4_in_editdata_mayworkexp_workcontent:// 工作内容
			Intent neirong = new Intent(this, SetDataActivity.class);
			neirong.putExtra("title", "工作内容");// 传标题
			neirong.putExtra("limit", 300);// 传字数限制
			startActivityForResult(neirong, 16);
			break;
		case R.id.employee4_in_editdata_mayworkexp_gongzuoyeji:// 工作业绩
			Intent yeji = new Intent(this, SetDataActivity.class);
			yeji.putExtra("title", "工作业绩");// 传标题
			yeji.putExtra("limit", 300);// 传字数限制
			startActivityForResult(yeji, 17);
			break;
		case R.id.employee4_in_editdata_mayworkexp_begin:// 参加工作的开始时间
			pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			break;

		case R.id.employee4_in_editdata_mayworkexp_done:// 参加工作的结束时间
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
				updataUserBegin(BossConstants.GET_WORK_YEAR[arg2]);
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
				updataUserDone(BossConstants.GET_WORK_YEAR[arg2]);
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
				updataUserCompanyNmae(companyName);
				break;
			case 11:// 选择工作类型
				String type = data.getStringExtra("data_return");
				mWorkType.setText(type);
				updataUserWorkType(type);
				break;
			case 12:// 设置技能标签
				String skill = data.getStringExtra("data_return");
				mSkill.setText(skill);
				updataUserSkill(skill);
				break;
			case 13:// 设置职业名称
				String zhiye = data.getStringExtra("data_return");
				mZhiye.setText(zhiye);
				updataUserZhiye(zhiye);
				break;
			case 14:// 设置职业名称
				String hangye = data.getStringExtra("data_return");
				mCompanyHangye.setText(hangye);
				updataUsermCompanyHangye(hangye);
				break;
			case 15:// 设置职业名称
				String bumen = data.getStringExtra("data_return");
				mSuoshubumen.setText(bumen);
				updataUsermSuoshubumen(bumen);
				break;
			case 16:// 设置职业名称
				String neirong = data.getStringExtra("data_return");
				mGongzuoneirong.setText(neirong);
				updataUsermGongzuoneirong(neirong);
				break;
			case 17:// 设置职业名称
				String yeji = data.getStringExtra("data_return");
				mGongzuoyeji.setText(yeji);
				updataUsermGongzuoyeji(yeji);
				break;

			}
		}
	}

	private void updataUserWorkType(String workType) {
		User newUser = new User();
		newUser.setWorkType(workType);
		User bmobUser = User.getCurrentUser(this, User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("职业类型更新成功");
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
/*			@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("职业类型更新成功");

				} else {
					Toast("请重试" + e.getMessage());
				}
			}*/

		});
	}

	private void updataUserBegin(String begin) {
		User newUser = new User();
		newUser.setBeginTime(begin);
		User bmobUser = User.getCurrentUser(this,User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("开始工作时间更新成功");
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
/*			@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("开始工作时间更新成功");

				} else {
					Toast("请重试" + e.getMessage());
				}
			}*/

		});
	}

	private void updataUserDone(String done) {
		User newUser = new User();
		newUser.setDoneTime(done);
		User bmobUser = User.getCurrentUser(this,User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("结束工作时间更新成功");
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
/*			@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("结束工作时间更新成功");

				} else {
					Toast("请重试" + e.getMessage());
				}
			}*/

		});
	}

	private void updataUserSkill(String skill) {
		User newUser = new User();
		newUser.setSkill(skill);
		User bmobUser = User.getCurrentUser(this,User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("技能标签更新成功");
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("技能标签更新成功");
			}
		/*	@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("技能标签更新成功");

				} else {
					Toast("请重试" + e.getMessage());
				}
			}*/

		});
	}

	private void updataUserCompanyNmae(String name) {
		User newUser = new User();
		newUser.setCompanyName(name);
		User bmobUser = User.getCurrentUser(this,User.class);
		newUser.update(this,bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("公司名称更新成功");
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
			/*@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("公司名称更新成功");

				} else {
					Toast("请重试" + e.getMessage());
				}
			}*/

		});
	}

	private void updataUserZhiye(String zhiye) {
		User newUser = new User();
		newUser.setZhiyemingcheng(zhiye);
		User bmobUser = User.getCurrentUser(this, User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("职位名称更新成功");
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
			/*@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("职位名称更新成功");

				} else {
					Toast("请重试" + e.getMessage());
				}
			}*/

		});
	}

	private void updataUsermCompanyHangye(String zhiye) {
		User newUser = new User();
		newUser.setmCompanyHangye(zhiye);
		User bmobUser = User.getCurrentUser(this,User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("公司行业更新成功");
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
			/*@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("公司行业更新成功");

				} else {
					Toast("请重试" + e.getMessage());
				}
			}*/

		});
	}

	private void updataUsermSuoshubumen(String zhiye) {
		User newUser = new User();
		newUser.setmSuoshubumen(zhiye);
		User bmobUser = User.getCurrentUser(this,User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("所属部门更新成功");
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
		/*	@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("所属部门更新成功");

				} else {
					Toast("请重试" + e.getMessage());
				}
			}*/

		});
	}

	private void updataUsermGongzuoneirong(String zhiye) {
		User newUser = new User();
		newUser.setContent(zhiye);
		User bmobUser = User.getCurrentUser(this, User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("工作内容更新成功");
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
			/*@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("工作内容更新成功");

				} else {
					Toast("请重试" + e.getMessage());
				}
			}*/

		});
	}

	private void updataUsermGongzuoyeji(String zhiye) {
		User newUser = new User();
		newUser.setmGongzuoyeji(zhiye);
		User bmobUser = User.getCurrentUser(this, User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("工作业绩更新成功");
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
	/*		@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("工作业绩更新成功");

				} else {
					Toast("请重试" + e.getMessage());
				}
			}*/



		});
	}

}
