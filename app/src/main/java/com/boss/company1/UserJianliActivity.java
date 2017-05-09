package com.boss.company1;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import com.boss.BossApplication;
import com.boss.R;
import com.boss.db.User;
import com.boss.login.BaseActivity;
import com.boss.util.BossConstants;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class UserJianliActivity extends BaseActivity implements OnClickListener {
//	private LoadingDialog mLoadingDialog;//

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee1_in_editdata_yulanjianli);
		findViewById();
		query();
		initActionBar();
		
		
	}

	/**
	 * 
	 */
	private void query() {
		List<User> use = (ArrayList<User>)getIntent().getSerializableExtra("us");
		Intent intent =getIntent();
		int position=intent.getIntExtra("position", 0);
		Toast(position+"111");
		user=use.get(position);
		initData();
		mAllXml.setVisibility(View.VISIBLE);
		mReStart.setVisibility(View.GONE);
		
		 

	}

	User user;

	private void initData( ) {
		// 判断
		if (user.getNormalavator() == null || user.getNormalavator() == false) {// 否则先判断头像地址是否为空，为空则用默认头像，否则使用地址头像 
			String imgUrl = user.getAvator();
			if (imgUrl == null || imgUrl.isEmpty()) {

				mAvatar.setImageResource(R.drawable.ic_launcher);
			} else {

				ImageLoader.getInstance().displayImage(
						imgUrl,
						mAvatar,
						BossApplication.getInstance().getOptions(
								R.drawable.ic_launcher),
						new SimpleImageLoadingListener() {

							@Override
							public void onLoadingComplete(String imageUri,
									View view, Bitmap loadedImage) {
								super.onLoadingComplete(imageUri, view,
										loadedImage);
								mAvatar.setImageBitmap(loadedImage);
							}

						});
			}

		} else { // 使用系统默认头像
			int drawable = BossConstants.DEFAULT_PICTURE[user
					.getNormalavatorindex()];
			mAvatar.setImageResource(drawable);
		}
	 

		if (user.getNick() != null) {
			mNick.setText(user.getNick());
		}
		if (user.getCompanyName() != null && user.getWorkType() != null) {
			mCompanyName2type.setText("现任：" + user.getCompanyName() + ",职位："
					+ user.getWorkType());
		}
		if (user.getCompanyName() != null) {
			mCompanyName.setText(user.getCompanyName());
		}
		if (user.getSkill() != null) {
			mSkill.setText(user.getSkill());
		}
		if (user.getDoneTime() != null && user.getBeginTime() != null) {
			mCompanyData
					.setText(user.getBeginTime() + "-" + user.getDoneTime());

		}
		if (user.getEducation() != null && user.getEmployeeWorkyear() != null
				&& user.getXinziBegin() != null && user.getXinziDone() != null) {
			mEducate.setText(user.getEmployeeWorkyear() + "  "
					+ user.getEducation() + "  " + user.getXinziBegin() + "-"
					+ user.getXinziDone());
		}

		if (user.getIntroduce() != null) {
			mIntroduce.setText(user.getIntroduce());
		}
		if (user.getWorkState() != null) {
			mState.setText(user.getWorkState());
		}
		if (user.getZhiyemingcheng() != null) {
			mGongzuozhiwei.setText(user.getZhiyemingcheng());
		}
		if (user.getZhiwei() != null) {
			mExpectZhiwei.setText(user.getZhiwei());
		}
		if (user.getXinziBegin() != null && user.getXinziDone() != null) {
			mExpectMoney.setText(user.getXinziBegin() + "-"
					+ user.getXinziDone());
		}
		if (user.getHangye() != null) {
			mExceptHangye.setText("期望行业：" + user.getHangye());
		}
		if (user.getCity() != null) {
			mExceptCity.setText("期望城市：" + user.getCity());
		}
		if (user.getContent() != null) {
			mContent.setText(user.getContent());
		}
		if (user.getSchoolName() != null) {
			mSchoolName.setText(user.getSchoolName());
		}
		if (user.getEduBeginTime() != null && user.getEduDoneTime() != null) {
			mSchoolData.setText(user.getEduBeginTime() + "-"
					+ user.getEduDoneTime());
		}
		if (user.getMajor() != null && user.getEducation() != null) {
			mSchoolMajor.setText(user.getMajor() + "|" + user.getSchoolName());
		}
		if (user.getmGongzuoyeji() != null) {
			mCompanyIntroduce.setText(user.getmGongzuoneirong());
		}
		TextView center = (TextView) findViewById(R.id.actionbar_center);
		 
		center.setText(user.getNick());

	}

	/**用户头像*/
	private ImageView mAvatar;
	
	/** 昵称 */
	private TextView mNick;

	/** 公司名字 */
	private TextView mCompanyName2type;

	/** 学历 */
	private TextView mEducate;

	/** 介绍 */
	private TextView mIntroduce;

	/** 状态 */
	private TextView mState;

	/** 期望职位 */
	private TextView mExpectZhiwei;

	/** 期望薪资 */
	private TextView mExpectMoney;

	/** 期望行业 */
	private TextView mExceptHangye;

	/** 期望城市 */
	private TextView mExceptCity;

	/** 工作时间 */
	private TextView mCompanyData;

	/** 工作内容 */
	private TextView mContent;

	/** 工作介绍 */
	private TextView mCompanyIntroduce;

	/** 工作技能 */
	private TextView mSkill;
	/** 公司名字 */
	private TextView mCompanyName;
	/** 公司工作职位 */
	private TextView mGongzuozhiwei;
	/** 学校名字 */
	private TextView mSchoolName;
	/** 学校日期 */
	private TextView mSchoolData;
	/** 学校专业 */
	private TextView mSchoolMajor;
	
	/**加载完后显示内容*/
	private LinearLayout mAllXml;
	/**点击重新加载*/
	private LinearLayout mReStart;
	/**数据加载失败*/
	private TextView mDataFail;
	
	private void findViewById() {
		mNick = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_nick);
		mCompanyName2type = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_companyname);
		mEducate = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_educate);
		mIntroduce = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_introduce);
		mState = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_state);
		mExpectZhiwei = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_exceptZhiwei);
		mExpectMoney = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_exceptMoney);
		mExceptHangye = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_exceptHangye);
		mExceptCity = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_exceptCity);
		mCompanyData = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_companyData);
		mContent = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_companyVontent);
		mCompanyIntroduce = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_companyname1);
		mSkill = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_companyBiaoqian);
		mCompanyName = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_companynamea);
		mGongzuozhiwei = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_exceptZhiwei2);
		mSchoolName = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_eduschool);
		mSchoolData = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_schoolData);
		mSchoolMajor = (TextView) findViewById(R.id.employee4_in_editdata_yulanjianli_schoolMajor);
		mAllXml = (LinearLayout)findViewById(R.id.employee4_in_editdata_yulanjianli_all);
		mReStart = (LinearLayout)findViewById(R.id.include_shujujiazai);
		mReStart.setOnClickListener(this);
		mDataFail =(TextView)findViewById(R.id.include_shujujiazai_txt);
		mAvatar = (ImageView)findViewById(R.id.employee4_in_editdata_yulanjianli_avatar);
	}

	private void initActionBar() {
		TextView left = (TextView) findViewById(R.id.actionbar_left);
		left.setText(R.string.zuojiantou);
		left.setOnClickListener(this);
		 

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.actionbar_left:// 返回按钮
			finish();

			break;
		case R.id.include_shujujiazai://数据加载
			if(mDataFail.getText().toString()=="数据加载失败，点击重试"){
				query();
			}else{
				 
			}
			break;
		default:
			break;
		}

	}

}
