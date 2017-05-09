package com.boss.login;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

	}




	/**
	 * @param toast  土司内容
	 */
	
	public void Toast(String toast){
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
		
	}
	
	
	/**
	 * @param errorMsg 错误信息：
	 */
	public void ToastError(String errorMsg){
		Toast("错误信息："+errorMsg);
	}
	
	
	public void ToastNoMessage(String noMsg){
		Toast(noMsg+"尚未填写");
	}

}
