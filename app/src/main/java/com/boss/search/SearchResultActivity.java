package com.boss.search;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.boss.R;
import com.boss.login.MainCompanyActivity;
import com.boss.util.BossConstants;
import com.boss.view.CompanyFragmentIndicator;
import com.boss.view.SearchResultFragmentIndicator;

/**
 * Created by 滕新科 on 2017/4/22.
 */

public class SearchResultActivity extends FragmentActivity {

    //private MainCompanyActivity.MyBroadcastReceiver receiver = new MainCompanyActivity.MyBroadcastReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        setFragmentIndicator(0);

       /* // 注册广播　　　负责监听接收设置更换身份时候推出
        IntentFilter filter = new IntentFilter();
        filter.addAction(BossConstants.ACTION_CHANGE_MAIN_COMPANY_FINISH);
        registerReceiver(receiver, filter);*/

    }

    public static Fragment[] mFragments;

    // *******************************这里是底部指示器****************************************
    // 初始化底部指示器的分页1
    // 设置默认的分页2
    // 设置一个界面只有一个fragment3
    private void setFragmentIndicator(int whichIsDefault) {
        // 1
        mFragments = new Fragment[3];
        mFragments[0] = getSupportFragmentManager().findFragmentById(
                R.id.login_search_tab1);
        mFragments[1] = getSupportFragmentManager().findFragmentById(
                R.id.login_search_tab2);
        mFragments[2] = getSupportFragmentManager().findFragmentById(
                R.id.login_search_tab3);
        // 2
        getSupportFragmentManager().beginTransaction().hide(mFragments[0])
                .hide(mFragments[1]).hide(mFragments[2])
                .show(mFragments[whichIsDefault]).commit();
        SearchResultFragmentIndicator mIndicator = (SearchResultFragmentIndicator) findViewById(R.id.login_main_company_indicator);
        SearchResultFragmentIndicator.setIndicator(whichIsDefault);
        // 3
        mIndicator.setOnIndicateListener(new SearchResultFragmentIndicator.OnIndicateListener() {

            public void onIndicate(View v, int which) {
                getSupportFragmentManager().beginTransaction()
                        .hide(mFragments[0])
                        .hide(mFragments[1])
                        .hide(mFragments[2])
                        .show(mFragments[which]).commit();

            }

            // *********************************************************************************************************************

        });
    }


    // 按两次退出登陆
    private long exitTime = 0;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
                finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }







   /* *//**
     * 自定义广播（用来注册成功，密码登录成功关闭本活动）
     *//*
    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null
                    && BossConstants.ACTION_CHANGE_MAIN_COMPANY_FINISH
                    .equals(intent.getAction())) {
                MainCompanyActivity.this.finish();
            }
        }

    }

    *//**
     * 关闭广播
     *//*
    @Override
    protected void onDestroy() {
        //
        super.onDestroy();
        unregisterReceiver(receiver);
    }*/
}
