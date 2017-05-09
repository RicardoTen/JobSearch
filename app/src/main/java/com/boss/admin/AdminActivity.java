package com.boss.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.boss.R;
import com.boss.db.User;
import com.boss.im.event.RefreshEvent;
import com.boss.im.util.IMMLeaks;
import com.boss.login.BaseActivity;
import com.boss.login.MainEmployeeActivity;
import com.boss.search.SearchActivity;
import com.boss.view.AdminEmployIndicator;
import com.boss.view.EmployeeFragmentIndicator;
import com.boss.view.IconFontTextView;
import com.boss.view.MGridView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

/**
 * Created by 滕新科 on 2017/5/8.
 */

public class AdminActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ButterKnife.bind(this);
        setFragmentIndicator(1);
        InitIM();
    }




    public static Fragment[] mFragments;

    // *******************************这里是底部指示器****************************************
    // 初始化底部指示器的分页1
    // 设置默认的分页2
    // 设置一个界面只有一个fragment3
    private void setFragmentIndicator(int whichIsDefault) {
        // 1
        mFragments = new Fragment[2];
        mFragments[0] = getSupportFragmentManager().findFragmentById(
                R.id.login_main_admin_tab1);
        mFragments[1] = getSupportFragmentManager().findFragmentById(
                R.id.login_main_admin_tab2);
/*        mFragments[2] = getSupportFragmentManager().findFragmentById(
                R.id.login_main_employee_tab3);
        mFragments[3] = getSupportFragmentManager().findFragmentById(
                R.id.login_main_employee_tab4);*/
        // 2
        getSupportFragmentManager().beginTransaction().hide(mFragments[0])
                .hide(mFragments[1])
                /*.hide(mFragments[2]).hide(mFragments[3])*/
                .show(mFragments[whichIsDefault]).commit();
        AdminEmployIndicator mIndicator = (AdminEmployIndicator) findViewById(R.id.login_main_admin_indicator);
        AdminEmployIndicator.setIndicator(whichIsDefault);
        // 3s
        mIndicator.setOnIndicateListener(new AdminEmployIndicator.OnIndicateListener() {

            public void onIndicate(View v, int which) {
                getSupportFragmentManager().beginTransaction()
                        .hide(mFragments[0]).hide(mFragments[1])
                      /*  .hide(mFragments[2]).hide(mFragments[3])*/
                        .show(mFragments[which]).commit();

            }

            // *********************************************************************************************************************

        });
    }



    private void InitIM() {
        User user = BmobUser.getCurrentUser(this,User.class);
        BmobIM.connect(user.getObjectId(), new ConnectListener() {
            @Override
            public void done(String uid, BmobException e) {
                if (e == null) {
                    Logger.i("connect success");
                    //服务器连接成功就发送一个更新事件，同步更新会话及主页的小红点
                    EventBus.getDefault().post(new RefreshEvent());
                } else {
                    Logger.e(e.getErrorCode() + "/" + e.getMessage());
                }
            }
        });
        //监听连接状态，也可通过BmobIM.getInstance().getCurrentStatus()来获取当前的长连接状态
        BmobIM.getInstance().setOnConnectStatusChangeListener(new ConnectStatusChangeListener() {
            @Override
            public void onChange(ConnectionStatus status) {
                Toast.makeText(AdminActivity.this, "" + status.getMsg(), Toast.LENGTH_SHORT).show();
            }
        });
        //解决leancanary提示InputMethodManager内存泄露的问题
        IMMLeaks.fixFocusedViewLeak(getApplication());
    }

/*    @OnClick(R.id.img_serch)
    public void onClickRight(View view){

    }*/


    @OnClick(R.id.img_serch)
    public void onSearchClick(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
