package com.boss.admin.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.boss.BossApplication;
import com.boss.R;
import com.boss.admin.ChackCompanyActivity;
import com.boss.admin.CheckJobActivity;
import com.boss.admin.CreateAdminActivity;
import com.boss.company3.CompanyFragment3Adapter1;
import com.boss.company3.CompanyFragment3Adapter2;
import com.boss.company3.Show1;
import com.boss.company3.Show2;
import com.boss.company3.in.setting.SettingActivity;
import com.boss.db.Company;
import com.boss.db.Job;
import com.boss.db.PostJianLi;
import com.boss.db.User;
import com.boss.employee4.ct.CaptureActivity;
import com.boss.employee4.in.PostJobListActivity;
import com.boss.employee4.in.editdata.EditDataActivity;
import com.boss.im.ui.ChatActivity;
import com.boss.login.LoginActivity;
import com.boss.statistic.StatisticActivity;
import com.boss.util.BossConstants;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.CountListener;

/**
 * Created by 滕新科 on 2017/5/8.
 */

public class AdminFragment extends Fragment implements View.OnClickListener {
    /**
     * 第一个GridView
     */
    private GridView mGridView1;
    /**
     * 第二个GridView
     */
    private GridView mGirdView2;

    /** 用户头像 */
    private ImageView mAvatar;
    /** 用户昵称 */
    private TextView mNick;
    /** 用户状态 */
    private TextView mState;

    int checkJob = 0;
    int checkCom = 0;

    // 这里结束
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 二手内容区域
        View rootView = inflater
                .inflate(R.layout.fragment_admin,
                        container, false);
        mGridView1 = (GridView) rootView
                .findViewById(R.id.imployee4_imployeefragment4_girdview1);
        mGirdView2 = (GridView) rootView
                .findViewById(R.id.imployee4_imployeefragment4_girdview2);

        rootView.findViewById(R.id.imployee4_imployeefragment4_setting)
                .setOnClickListener(this);
        rootView.findViewById(R.id.imployee4_imployeefragment4_editData)
                .setOnClickListener(this);
        mAvatar = (ImageView) rootView
                .findViewById(R.id.employee4_employeeFragment4_avatar);

        mNick = (TextView) rootView
                .findViewById(R.id.employee4_employeeFragment4_nick);

        mState = (TextView) rootView
                .findViewById(R.id.employee4_employeeFragment4_state);

        ButterKnife.bind(this, rootView);

        return rootView;

    }

    @OnClick(R.id.layout_statistic)
    public void onStatisticClick(View view){
        Intent intent = new Intent(getActivity(), StatisticActivity.class);
        getActivity().startActivity(intent);
    }


    @OnClick(R.id.layout_create_admin_user)
    public void onCreateAdminUserClick(View view){
        Intent intent = new Intent(getActivity(), CreateAdminActivity.class);
        getActivity().startActivity(intent);
    }




    @Override
    public void onResume() {
        initData();
        queryCheckNum();
        super.onResume();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initGridView1();
        initGridView2();
        //initData();
    }

    /**
     * 第二个GridView 导入数据
     */
    private void initGridView2() {
        // TODO 以后数据存储
        // 获取到GridView
        List<Show2> mListShow2 = new ArrayList<Show2>();

        Show2 data1 = new Show2();
        data1.setData_icon(R.drawable.a);
        data1.setData_content("公告");
        mListShow2.add(data1);

        Show2 data2 = new Show2();
        data2.setData_icon(R.drawable.b);
        data2.setData_content("上传简历");
        mListShow2.add(data2);

        Show2 data3 = new Show2();
        data3.setData_icon(R.drawable.c);
        data3.setData_content("上线提醒");
        mListShow2.add(data3);

        Show2 data4 = new Show2();
        data4.setData_icon(R.drawable.d);
        data4.setData_content("退出登陆");
        mListShow2.add(data4);

        Show2 data5 = new Show2();
        data5.setData_icon(R.drawable.e);
        data5.setData_content("我的道具");
        mListShow2.add(data5);

        Show2 data6 = new Show2();
        data6.setData_icon(R.drawable.f);
        data6.setData_content("道具商城");
        mListShow2.add(data6);

        Show2 data7 = new Show2();
        data7.setData_icon(R.drawable.g);
        data7.setData_content("收藏过");
        mListShow2.add(data7);

        Show2 data8 = new Show2();
        data8.setData_icon(R.drawable.h);
        data8.setData_content("赚积分");
        mListShow2.add(data8);

        CompanyFragment3Adapter2 adapter2 = new CompanyFragment3Adapter2(
                getActivity(), mListShow2);
        mGirdView2.setAdapter(adapter2);

        mGirdView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int poi,
                                    long arg3) {
                if (poi == 1) {
                    startActivity(new Intent(getActivity(),
                            CaptureActivity.class));
                }

                //退出登陆
                if(poi == 3){
                    User.logOut(getActivity());

                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().sendBroadcast(new Intent(BossConstants.ACTION_CHANGE_MAIN_EMPLOYEE_FINISH));
                    //getActivity().finish();
                }

                if(poi == 2){
                    getActivity().startActivity(new Intent(getActivity(), ChatActivity.class));
                }
            }
        });

    }

    @OnClick(R.id.company3_in_setting_exit)
    public void onClickLogout(View view){
        User.logOut(getActivity());
        BmobIM.getInstance().disConnect();
        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
        //getActivity().sendBroadcast(new Intent(BossConstants.ACTION_CHANGE_MAIN_EMPLOYEE_FINISH));
        getActivity().finish();
    }

    @OnClick(R.id.layout_ercode)
    public void onClickErCode(View view){
        startActivity(new Intent(getActivity(),
                CaptureActivity.class));
    }

    /**
     * 初始化第一个GridView
     */
    private void initGridView1() {
        // 获取到GridView
        // TODO 动态获取数据
        List<Show1> mListShow1 = new ArrayList<Show1>();
        Show1 data1 = new Show1();
        data1.setData_int(checkJob);
        data1.setData_string("待审核职位");
        mListShow1.add(data1);

        Show1 data2 = new Show1();
        data2.setData_int(checkCom);
        data2.setData_string("待审核公司");
        mListShow1.add(data2);
        CompanyFragment3Adapter1 adapter1 = new CompanyFragment3Adapter1(
                getActivity(), mListShow1);
        mGridView1.setAdapter(adapter1);

        mGridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

               if(arg2 == 0){
                    Intent intent = new Intent(getActivity(), CheckJobActivity.class);
                    getActivity().startActivity(intent);
                }


                if(arg2 == 1){
                    Intent intent = new Intent(getActivity(), ChackCompanyActivity.class);
                    getActivity().startActivity(intent);
                }


            }
        });
    }

    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.imployee4_imployeefragment4_editData:// 编辑个人资料
                startActivity(new Intent(getActivity(), EditDataActivity.class));
                break;
            case R.id.imployee4_imployeefragment4_setting:// 进入设置
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            default:
                break;
        }

    }

    public void initData() {
        User user = User.getCurrentUser(getActivity(), User.class);
        // 判断
        if (user.getNormalavator() == null || user.getNormalavator() == false) {// 否则先判断头像地址是否为空，为空则用默认头像，否则使用地址头像
            String imgUrl = user.getAvator();
            if (imgUrl == null || imgUrl.isEmpty()) {
                mAvatar.setImageResource(R.drawable.ic_launcher);
            } else {

                ImageLoader.getInstance().displayImage(
                        imgUrl,
                        mAvatar,
                        BossApplication.getInstance().getOptions(
                                R.drawable.ic_launcher),
                        new SimpleImageLoadingListener() {

                            @Override
                            public void onLoadingComplete(String imageUri,
                                                          View view, Bitmap loadedImage) {
                                super.onLoadingComplete(imageUri, view,
                                        loadedImage);
                                mAvatar.setImageBitmap(loadedImage);
                            }

                        });
            }

        } else { // 使用系统默认头像
            int drawable = BossConstants.DEFAULT_PICTURE[user
                    .getNormalavatorindex()];
            mAvatar.setImageResource(drawable);
        }

        if (user.getNick() != null) {// 用户昵称
            mNick.setText(user.getNick());
        }
        if (user.getWorkState() != null) {// 用户职业状态
            mState.setText(user.getWorkState());
        }

    }

    private void queryCheckNum() {
        User user = User.getCurrentUser(getActivity(), User.class);
        BmobQuery<Job> query = new BmobQuery<>();
        // 按时间降序查询
        query.order("-createdAt");
        query.addWhereEqualTo("jobState", BossConstants.STATISTICS_ING);
        query.count(getActivity(), Job.class, new CountListener() {
            @Override
            public void onSuccess(int i) {
                checkJob = i;
                initGridView1();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(getActivity(), "查询审核职位数目失败"+s,Toast.LENGTH_SHORT).show();
            }
        });


        BmobQuery<Company> queryCom = new BmobQuery<>();
        // 按时间降序查询
        queryCom.order("-createdAt");
        queryCom.addWhereEqualTo("companyState", BossConstants.STATISTICS_ING);
        queryCom.count(getActivity(), Company.class, new CountListener() {
            @Override
            public void onSuccess(int i) {
                checkCom = i;
                initGridView1();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(getActivity(), "查询审核公司数目失败"+s,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
