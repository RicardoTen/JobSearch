package com.boss.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.boss.view.BossTransferView;


/**
 * Created by AriaL on 2016/2/23.
 */
public class TurnHelp {
    /**
     * 甯﹀姩鐢昏烦杞鎯呴〉闈�
     */
    public static void turn(Context context, View rootView, View itemView, Bundle para, Activity destActivity) {
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams wmParams;
        BossTransferView temp = new BossTransferView(context, rootView, itemView, wm, para , destActivity);
        wmParams = new WindowManager.LayoutParams();
        wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;     // 绯荤粺鎻愮ず绫诲瀷,閲嶈
        /**
         * 尝试加
         */
        //wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        
        wmParams.format = 1;
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE; // 涓嶈兘鎶㈠崰鑱氱劍鐐�
        wmParams.flags = wmParams.flags | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        wmParams.flags = wmParams.flags | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS; // 鎺掔増涓嶅彈闄愬埗
        wmParams.flags = wmParams.flags | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL; // 鎺掔増涓嶅彈闄愬埗
        wmParams.alpha = 1.0f;
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;   //璋冩暣鎮诞绐楀彛鑷冲乏涓婅
        //浠ュ睆骞曞乏涓婅涓哄師鐐癸紝璁剧疆x銆亂鍒濆鍊�
        wmParams.x = 0;
        wmParams.y = 0;
        //璁剧疆鎮诞绐楀彛闀垮鏁版嵁
        wmParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        wmParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        //鏄剧ずmyFloatView鍥惧儚
        wm.addView(temp, wmParams);
        temp.show();
    }
}
