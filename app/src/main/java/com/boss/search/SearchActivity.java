package com.boss.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.boss.R;
import com.boss.util.BossConstants;

import java.util.Random;

public class SearchActivity extends Activity implements OnClickListener {
	private static final int FEEDKEY_START = 1;
	private ImageView back_arrow;
	private Animation shakeAnim;
	private DeletableEditText searchEdit;
	private KeywordsFlow keywordsFlow;
	private int STATE = 1;

	private static String[] keywords = BossConstants.SEARCH_KEYWORDS;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case FEEDKEY_START:
				keywordsFlow.rubKeywords();
				feedKeywordsFlow(keywordsFlow, keywords);
				keywordsFlow.go2Show(KeywordsFlow.ANIMATION_OUT);
				sendEmptyMessageDelayed(FEEDKEY_START, 5000);
				break;
			}
		};
	};

	private static void feedKeywordsFlow(KeywordsFlow keywordsFlow, String[] arr) {
		Random random = new Random();
		for (int i = 0; i < KeywordsFlow.MAX; i++) {
			int ran = random.nextInt(arr.length);
			String tmp = arr[ran];
			keywordsFlow.feedKeyword(tmp);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		shakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_y);
		initView();
		initEven();
	}

	private void initEven() {

		searchEdit.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {

				if (keyCode == KeyEvent.KEYCODE_ENTER   && event.getAction() == KeyEvent.ACTION_DOWN) {
					Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
					intent.putExtra("content", searchEdit.getText().toString());
					startActivity(intent);
				}
				return false;
			}
		});
	}

	private void initView() {
		keywordsFlow = (KeywordsFlow) findViewById(R.id.keywordsflow);
		keywordsFlow.setDuration(1000l);
		keywordsFlow.setOnItemClickListener(this);
		back_arrow = (ImageView) findViewById(R.id.back_arrow);
		back_arrow.setAnimation(shakeAnim);
		searchEdit = (DeletableEditText) findViewById(R.id.search_view);
		feedKeywordsFlow(keywordsFlow, keywords);
		keywordsFlow.go2Show(KeywordsFlow.ANIMATION_IN);
		handler.sendEmptyMessageDelayed(FEEDKEY_START, 5000);
	}

	@Override
	public void onClick(View v) {
		if (v instanceof TextView) {
			String keyword = ((TextView) v).getText().toString().trim();
			searchEdit.setText(keyword);
			searchEdit.setSelection(keyword.length());

			Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
			intent.putExtra("content", searchEdit.getText().toString());
			startActivity(intent);

		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		back_arrow.clearAnimation();
		handler.removeMessages(FEEDKEY_START);
		STATE = 0;
	}

	@Override
	protected void onStop() {
		super.onStop();
		handler.removeMessages(FEEDKEY_START);
		STATE = 0;
	}

	@Override
	public void onPause() {
		super.onPause();
		handler.removeMessages(FEEDKEY_START);
		STATE = 0;
	}

	@Override
	public void onResume() {
		super.onResume();
		if (STATE == 0) {
			keywordsFlow.rubKeywords();
			handler.sendEmptyMessageDelayed(FEEDKEY_START, 3000);
		}

	}
}
