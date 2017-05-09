package com.boss.util;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by AriaL on 2016/2/22.
 */
public class Util {
    /**
     * 鑾峰彇灞忓箷鍙傛暟锛岃繖涓幏鍙栧埌鐨勯珮鏄寘鍚鑸爮鍜岀姸鎬佹爮鐨�
     *
     * @param context
     * @return
     */
    public static int[] getScreenParams(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return new int[]{wm.getDefaultDisplay().getWidth(), wm.getDefaultDisplay().getHeight()};
    }

    /**
     * 鑾峰彇鐘舵�佹爮楂樺害
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
