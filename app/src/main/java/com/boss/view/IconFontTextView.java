package com.boss.view;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class IconFontTextView extends TextView{

	 private Context mContext;  
	  
	    public IconFontTextView(Context context) {  
	        super(context);  
	        mContext = context;  
	        initView();  
	    }  
	  
	    public IconFontTextView(android.content.Context context, android.util.AttributeSet attrs) {  
	        super(context, attrs);  
	        mContext = context;  
	        initView();  
	    }  
	  
	    private void initView()  
	    {  
	        Typeface iconfont = Typeface.createFromAsset(mContext.getAssets(), "iconfont/iconfont.ttf");  
	        setTypeface(iconfont);  
	    }  
	

}
