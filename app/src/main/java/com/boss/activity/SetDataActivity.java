package com.boss.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.boss.R;
import com.boss.login.BaseActivity;

public class SetDataActivity extends BaseActivity implements OnClickListener,
		TextWatcher {
	/**
	 * 限制字数
	 */
	int limit;
	String preContent = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_setdata);
		findViewById();
		initActionBar();

	}

	/** 内容编辑框 */
	private EditText mContent;
	/** 字数限制 */
	private TextView mLimit;

	private void findViewById() {
		mContent = (EditText) findViewById(R.id.activity_setdata_content);
		mContent.addTextChangedListener(this);
		mLimit = (TextView) findViewById(R.id.activity_setdata_limit);
		Intent message = getIntent();
		limit = message.getIntExtra("limit", 0);

		mContent.setMinHeight(limit/20 * 10);

		mLimit.setText("0/" + limit);
		findViewById(R.id.activity_setdata_save).setOnClickListener(this);

		preContent = message.getStringExtra("content");
		if(!TextUtils.isEmpty(preContent)){
			mContent.setText(preContent);
		}

	}

	private void initActionBar() {
		TextView left = (TextView) findViewById(R.id.actionbar_left);
		left.setText(R.string.cha);
		left.setOnClickListener(this);
		//
		TextView center = (TextView) findViewById(R.id.actionbar_center);
		Intent message = getIntent();
		String title = message.getStringExtra("title");
		center.setText(title);
		//
		TextView right = (TextView) findViewById(R.id.actionbar_right);
		right.setText(R.string.gou);
		right.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.actionbar_right:
		case R.id.activity_setdata_save://保存
			String content = mContent.getText().toString();
			if(content.length()>limit){
				Toast("字数不得超过"+limit+"个");
				return;
			}
			Intent intent = new Intent();
			intent.putExtra("data_return", content);
			setResult(RESULT_OK, intent);
			finish();
			break;

		case R.id.actionbar_left:
 			finish();
			break;

		default:
			break;
		}

	}

	/**
	 * TextWatch接口，当号码输入框有内容时，增加个按钮来快速清空内容
	 */
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
		mLimit.setText(s.length() + "/" + limit);
		if(s.length()==limit){
			mLimit.setTextColor(getResources().getColor(R.color.app_main));
		}else if(s.length()==(limit+1)){
			mLimit.setTextColor(getResources().getColor(R.color.app_red));
		}

		
	}
	 
	public void afterTextChanged(Editable s) {

	}

}
