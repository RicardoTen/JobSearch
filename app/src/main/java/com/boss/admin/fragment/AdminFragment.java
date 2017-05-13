package com.boss.admin.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.boss.db.RecieveJianLi;
import com.boss.db.User;
import com.boss.employee4.ct.CaptureActivity;
import com.boss.employee4.in.editdata.EditDataActivity;
import com.boss.im.ui.ChatActivity;
import com.boss.login.LoginActivity;
import com.boss.statistic.StatisticActivity;
import com.boss.util.BossConstants;
import com.boss.util.JsonStorage;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;

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

    int userCount = 0;
    int userIndex = 0 ;
    int companyCount = 0;
    int companyIndex = 0;
    int jobCount = 0;
    int jobIndex = 0;
    int postResumeCount = 0;
    int postResumeCountIndex = 0;
    int recieveResumeCount = 0;
    int reciverResumeCountIndex = 0;

    int totalNum = 0;

    int currenNum = 0;
    /**
     * 每次查询50个数据
     */
    int limit = 50;



    JsonStorage storage  = null;

    CircleProgressDialog circleProgressDialog;

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

        circleProgressDialog = new CircleProgressDialog(getActivity());
        circleProgressDialog.setCancelable(false);
        circleProgressDialog.setDialogSize(200);
        circleProgressDialog.setTextColor(Color.parseColor("#FF4081"));

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

    @OnClick(R.id.layout_back_up)
    public void onClickBackUp(View view){
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setMessage("确认要备份么？（这将会耗费大量流量，请尽量在WIFI条件行下进行备份）")
                .setTitle("信息")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        downLoadBackUp();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    private void downLoadBackUp(){
        showDiolog();
         userCount = 0;
         userIndex = 0 ;
         companyCount = 0;
         companyIndex = 0;
         jobCount = 0;
         jobIndex = 0;
         postResumeCount = 0;
         postResumeCountIndex = 0;
         recieveResumeCount = 0;
         reciverResumeCountIndex = 0;
         totalNum = 0;
         currenNum = 0;
         storage = new JsonStorage();
         downUserCount();
    }


    private void showToast(String content){
        Toast.makeText(getActivity(), content,Toast.LENGTH_SHORT).show();
    }

    private void showDiolog(){
        circleProgressDialog.showDialog();
    }

    private void upDateDiolog(int percent , int classFlag){
        //circleProgressDialog.showDialog();
        String content = "";

        switch (classFlag){
            case 0:
                content += "下载用户文件中";
                break;
            case 1:
                content += "下载职位文件中";
                break;
            case 2:
                content += "下载公司文件中";
                break;
            case 3:
                content += "下载接受简历文件中";
                break;
            case 4:
                content += "下载提交简历文件中";
                break;
        }
        content += "    当前进度:" + percent;
        circleProgressDialog.changeText(content);

    }

    private void dismissDiolog(){
        circleProgressDialog.dismiss();
    }



    private void downUserCount(){
        BmobQuery queryCount = new BmobQuery();
        queryCount.count(getActivity(), User.class, new CountListener() {
            @Override
            public void onSuccess(int i) {
                totalNum += i;
                userCount = i;
                userIndex = 0;
                beginDownLoadUser();
            }

            @Override
            public void onFailure(int i, String s) {
                showToast("user" + "get count fail    reason:"  + s);
                dismissDiolog();
            }
        });
    }




    Handler userHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            beginDownLoadUser();
        }
    };

    private void beginDownLoadUser(){
            BmobQuery<User> query = new BmobQuery();
            query.setLimit(limit);
            query.setSkip(userIndex * limit);
            query.findObjects(getActivity(), new FindListener<User>() {
               @Override
               public void onSuccess(List<User> list) {
                    for(User user:list){
                        currenNum++;
                        storage.write(user);
                        int persent = (int)((double)((double)currenNum / (double)userCount) * 100);
                        upDateDiolog(persent,0);
                    }
                   userIndex++;
                   if(userIndex == userCount/limit + 1){
                      dismissDiolog();
                      beginDownLoadJobCount();
                   }else{
                       userHandler.obtainMessage().sendToTarget();
                   }
               }

               @Override
               public void onError(int i, String s) {
                   showToast("user" + "get count fail    reason:"  + s);
                   dismissDiolog();
               }
           });

    }


    private void beginDownLoadJobCount(){
        showDiolog();
        BmobQuery queryCount = new BmobQuery();
        queryCount.count(getActivity(), Job.class, new CountListener() {
            @Override
            public void onSuccess(int i) {
                totalNum += i;
                jobCount = i;
                jobIndex = 0;
                currenNum = 0;
                beginJobDownload();
            }

            @Override
            public void onFailure(int i, String s) {
                showToast("job" + "get count fail    reason:"  + s);
                dismissDiolog();
            }
        });
    }


    private void beginJobDownload(){
        BmobQuery<Job> query = new BmobQuery();
        query.setLimit(limit);
        query.setSkip(jobIndex * limit);
        query.findObjects(getActivity(), new FindListener<Job>() {
            @Override
            public void onSuccess(List<Job> list) {
                for(Job job:list){
                    currenNum++;
                    storage.write(job);
                    int persent = (int)((double)((double)currenNum / (double)jobCount) * 100);
                    upDateDiolog(persent,1);
                }
                jobIndex++;
                if(jobIndex == jobCount/limit + 1){
                    dismissDiolog();
                    beginDownLoadCompanyCount();
                }else{
                    beginJobDownload();
                }
            }
            @Override
            public void onError(int i, String s) {
                showToast("job" + "get count fail    reason:"  + s);
                dismissDiolog();
            }
        });
    }


    private void beginDownLoadCompanyCount(){
        showDiolog();
        BmobQuery queryCount = new BmobQuery();
        queryCount.count(getActivity(), Company.class, new CountListener() {
            @Override
            public void onSuccess(int i) {
                totalNum += i;
                companyCount = i;
                companyIndex = 0;
                currenNum = 0;
                beginCompanyDownload();
            }

            @Override
            public void onFailure(int i, String s) {
                showToast("company" + "get count fail    reason:"  + s);
                dismissDiolog();
            }
        });
    }


    private void beginCompanyDownload(){
        BmobQuery<Company> query = new BmobQuery();
        query.setLimit(limit);
        query.setSkip(companyIndex * limit);
        query.findObjects(getActivity(), new FindListener<Company>() {
            @Override
            public void onSuccess(List<Company> list) {
                for(Company company:list){
                    currenNum++;
                    storage.write(company);
                    int persent = (int)((double)((double)currenNum / (double)companyCount) * 100);
                    upDateDiolog(persent,2);
                }
                companyIndex++;
                if(companyIndex == companyCount/limit + 1){
                    dismissDiolog();
                    beginDownLoadRecieveResumeCount();
                }else{
                    beginCompanyDownload();
                }
            }
            @Override
            public void onError(int i, String s) {
                showToast("company" + "get fail    reason:"  + s);
                dismissDiolog();
            }
        });
    }



   private  void beginDownLoadRecieveResumeCount(){
       showDiolog();
       BmobQuery queryCount = new BmobQuery();
       queryCount.count(getActivity(), RecieveJianLi.class, new CountListener() {
           @Override
           public void onSuccess(int i) {
               totalNum += i;
               recieveResumeCount = i;
               reciverResumeCountIndex = 0;
               currenNum = 0;
               beginReciveResumeDownload();
           }

           @Override
           public void onFailure(int i, String s) {
               showToast("recieveResume" + "get count fail    reason:"  + s);
               dismissDiolog();
           }
       });
   }


    private void beginReciveResumeDownload() {
        BmobQuery<RecieveJianLi> query = new BmobQuery();
        query.setLimit(limit);
        query.setSkip(reciverResumeCountIndex * limit);
        query.findObjects(getActivity(), new FindListener<RecieveJianLi>() {
            @Override
            public void onSuccess(List<RecieveJianLi> list) {
                for(RecieveJianLi recieveJianLi:list){
                    currenNum++;
                    storage.write(recieveJianLi);
                    int persent = (int)((double)((double)currenNum / (double)recieveResumeCount) * 100);
                    upDateDiolog(persent,3);
                }
                reciverResumeCountIndex++;
                if(reciverResumeCountIndex == recieveResumeCount/limit + 1){
                    dismissDiolog();
                    beginDownLoadPostResumeCount();
                }else{
                    beginReciveResumeDownload();
                }
            }
            @Override
            public void onError(int i, String s) {
                showToast("recieveResume" + "get fail    reason:"  + s);
                dismissDiolog();
            }
        });
    }

    private void beginDownLoadPostResumeCount() {
        showDiolog();
        BmobQuery queryCount = new BmobQuery();
        queryCount.count(getActivity(), PostJianLi.class, new CountListener() {
            @Override
            public void onSuccess(int i) {
                totalNum += i;
                postResumeCount = i;
                postResumeCountIndex = 0;
                currenNum = 0;
                beginPostResumeDownload();
            }

            @Override
            public void onFailure(int i, String s) {
                showToast("postJianli" + "get count fail    reason:"  + s);
                dismissDiolog();
            }
        });
    }

    private void beginPostResumeDownload() {
        BmobQuery<PostJianLi> query = new BmobQuery();
        query.setLimit(limit);
        query.setSkip(postResumeCountIndex * limit);
        query.findObjects(getActivity(), new FindListener<PostJianLi>() {
            @Override
            public void onSuccess(List<PostJianLi> list) {
                for(PostJianLi postJianLi:list){
                    currenNum++;
                    storage.write(postJianLi);
                    int persent = (int)((double)((double)currenNum / (double)postResumeCount) * 100);
                    upDateDiolog(persent,4);
                }
                postResumeCountIndex++;
                if(postResumeCountIndex == postResumeCount/limit + 1){
                    dismissDiolog();
                    storage.finalWrite();
                    showToast("备份成功 备份文件保存在"  + JsonStorage.path  + "目录下 \r\n" + "本次共保存" + totalNum + "条数据");
                    System.gc();
                }else{
                    beginPostResumeDownload();
                }
            }
            @Override
            public void onError(int i, String s) {
                showToast("postJianli" + "get fail    reason:"  + s);
                dismissDiolog();
            }
        });
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
