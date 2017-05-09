package com.boss.slpash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
/*import android.support.v7.app.AppCompatActivity;*/
/*import android.support.v7.app.AppCompatActivity;*/
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.boss.R;
import com.boss.admin.AdminActivity;
import com.boss.db.User;
import com.boss.login.LoginActivity;
import com.boss.login.MainCompanyActivity;
import com.boss.login.MainEmployeeActivity;
import com.boss.register.RegisterActivity;
import com.boss.util.ActivityUtils;
import com.boss.util.BossConstants;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016/7/26.
 */
public class SplashActivity extends FragmentActivity {

    private static final int GO_EMPLOYEE = 100;

    private static final int GO_COMPANY = 101;

    private static final int GO_REGISTER = 102;

    private static final int GO_LOGIN = 200;

    private static final int SHOW_TEXT = 300;

    private static final int GO_ADMIN = 400;


    @BindView(R.id.btnEnter)
    Button btnEnter;
    private ActivityUtils activityUtils;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils=new ActivityUtils(this);

        Log.e("a","a ");
        setContentView(R.layout.activity_splash);
    }





    MyHandler ttsHandler = new MyHandler(this); // 开启新线程 用于发送跳转至登录界面还是主界面信息

    static class MyHandler extends Handler { // 静态类handle
        WeakReference<SplashActivity> mActivity; // 弱引用

        MyHandler(SplashActivity activity) {
            mActivity = new WeakReference<SplashActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity theActivity = mActivity.get();
            switch (msg.what) {
                case GO_EMPLOYEE:// 跳转到求职界面
                    theActivity.startActivity(new Intent(theActivity,
                            MainEmployeeActivity.class));
                    theActivity.finish();
                    break;
                case GO_LOGIN:// 跳转到登录界面
                    theActivity.startActivity(new Intent(theActivity,
                            LoginActivity.class));
                    theActivity.finish();
                    break;

                case GO_REGISTER:// 跳转到注册界面
                    theActivity.startActivity(new Intent(theActivity,
                            RegisterActivity.class));
                    theActivity.finish();
                    break;
                case GO_COMPANY:// 跳转到公司界面
                    theActivity.startActivity(new Intent(theActivity,
                            MainCompanyActivity.class));
                    theActivity.finish();
                    break;

                case GO_ADMIN:
                    theActivity.startActivity(new Intent(theActivity,
                            AdminActivity.class));
                    theActivity.finish();
                    break;

                case SHOW_TEXT: // 显示Boss直聘
                    //mSplashTv.setVisibility(View.VISIBLE);
                    break;

            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        User user = BmobUser.getCurrentUser(this, User.class);// 获取当前用户对象
       //ttsHandler.sendEmptyMessageDelayed(SHOW_TEXT, 200);// 显示BOSS直聘文字 延迟 0.2秒
        if (user != null) {// 用户存在缓存
            if(user.getAdMin() != null){
                if(user.getAdMin()){
                    ttsHandler.sendEmptyMessageDelayed(GO_ADMIN, 500); // 用户进入注册
                    return;
                }
            }

            if (user.getMainLayout() == null) {
                ttsHandler.sendEmptyMessageDelayed(GO_REGISTER, 500); // 用户进入注册
            } else if (user.getMainLayout() == true) {
                ttsHandler.sendEmptyMessageDelayed(GO_COMPANY, 500); // 用户进入公司
            } else {// 用户进入求职
                ttsHandler.sendEmptyMessageDelayed(GO_EMPLOYEE, 500);

            }
        } else {
            //没有用户缓存提示用户登录或者注册
            //ttsHandler.sendEmptyMessageDelayed(GO_LOGIN, 500);// 用户进入登录 }
        }
    }



    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btnEnter)
    public void click(){
        activityUtils.startActivity(RegisterActivity.class);
        finish();
    }
    @OnClick(R.id.btnLogin)
    public void login(){
        activityUtils.startActivity(LoginActivity.class);
        finish();
    }





}
