package com.boss.employee4.in.editdata.qiuzhiyixiang;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.boss.R;
import com.boss.adapter.SetWorkSkillAdapter;
import com.boss.ddb.SetWorkSkill;
import com.boss.login.BaseActivity;
import com.boss.util.BossConstants;

public class SetCityActivity extends BaseActivity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_setworkskill);
		findViewById();
		iniyListView1();
		initActionBar();
		
	}
	
	private GridView mGridView;
	private void findViewById() {
		mGridView = (GridView)findViewById(R.id.register_setworkskill);
		
	}

	private void initActionBar() {
		TextView left = (TextView) findViewById(R.id.actionbar_left);
		left.setText(R.string.cha);
		left.setOnClickListener(this);
		TextView center = (TextView) findViewById(R.id.actionbar_center);
		center.setText("技能标签}");
		TextView right = (TextView) findViewById(R.id.actionbar_right);
		right.setText(R.string.gou);
		right.setOnClickListener(this);

	}

	/**
	 * 初始化第一个List
	 */
	private void iniyListView1() {
		List<SetWorkSkill> mListSkill = new ArrayList<SetWorkSkill>();
		for (int i = 0; i < BossConstants.CITY.length; i++) {
			SetWorkSkill workSkill = new SetWorkSkill();
			workSkill.setSkill(BossConstants.CITY[i]);
			mListSkill.add(workSkill);
		}
	
		
		SetWorkSkillAdapter adapter1 = new SetWorkSkillAdapter(this, mListSkill);
		mGridView.setAdapter(adapter1);

		mGridView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				String mWorkSkill=BossConstants.CITY[arg2];
				Intent intent = new Intent();
				intent.putExtra("data_return", mWorkSkill);
				setResult(RESULT_OK, intent);
				finish();

			}
		});
	}

	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.actionbar_left://返回
			finish();
			break;
			
		default:
			break;
		}

	}
}
