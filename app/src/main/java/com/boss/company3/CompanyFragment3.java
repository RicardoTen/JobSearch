package com.boss.company3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.boss.R;
import com.boss.admin.CheckJobActivity;
import com.boss.company3.in.recievejianli.InterestJianliActivity;
import com.boss.company3.in.recievejianli.ReciveJianliListActivity;
import com.boss.company3.in.release.ReleseJobActivity;
import com.boss.company3.in.release.ReleseJobListActivity;
import com.boss.company3.in.setting.SettingActivity;
import com.boss.db.Company;
import com.boss.db.Job;
import com.boss.db.RecieveJianLi;
import com.boss.db.User;
import com.boss.im.base.ImageLoaderFactory;
import com.boss.login.LoginActivity;
import com.boss.register.CompanyStep1Activity;
import com.boss.statistic.StatisticActivity;
import com.boss.util.BossConstants;
import com.boss.view.MGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;

public class CompanyFragment3 extends Fragment implements OnClickListener {

    @BindView(R.id.company3_companyfragment3_name)
    TextView company3Companyfragment3Name;
    @BindView(R.id.company3_companyfragment3_setting)
    ImageView company3Companyfragment3Setting;
    @BindView(R.id.img_avtar)
    ImageView imgAvtar;
    @BindView(R.id.tv_boss_name)
    TextView tvBossName;
    @BindView(R.id.company3_companyfragment3_girdview1)
    MGridView company3Companyfragment3Girdview1;
    @BindView(R.id.company3_companyfragment3_girdview2)
    MGridView company3Companyfragment3Girdview2;
    @BindView(R.id.company3_in_setting_tip)
    LinearLayout company3InSettingTip;
    @BindView(R.id.company3_in_setting_shiping)
    LinearLayout company3InSettingShiping;
    @BindView(R.id.company3_in_setting_changephone)
    LinearLayout company3InSettingChangephone;
    @BindView(R.id.company3_in_setting_changepassword)
    LinearLayout company3InSettingChangepassword;
    @BindView(R.id.company3_in_setting_updata)
    LinearLayout company3InSettingUpdata;
    @BindView(R.id.company3_in_setting_help)
    LinearLayout company3InSettingHelp;
    @BindView(R.id.company3_in_setting_aboutus)
    LinearLayout company3InSettingAboutus;
    @BindView(R.id.company3_in_setting_changetype)
    LinearLayout company3InSettingChangetype;
    @BindView(R.id.company3_in_setting_exit)
    LinearLayout company3InSettingExit;
    @BindView(R.id.tv_boss_job)
    TextView tvBossJob;
    /**
     * 第一个GridView
     */
    private GridView mGridView1;
    /**
     * 第二个MGridView
     */
    private GridView mGirdView2;

    Company company = null;

    int jobNum = 0,  comunicateNum = 0,  mianshiNum = 0 , prepareNum = 0;


    // 这里结束
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 二手内容区域
        View rootView = inflater.inflate(
                R.layout.fragment_company3_companyfragment3, container, false);
        mGridView1 = (GridView) rootView
                .findViewById(R.id.company3_companyfragment3_girdview1);
        mGirdView2 = (GridView) rootView
                .findViewById(R.id.company3_companyfragment3_girdview2);
//		mNickName = (TextView)rootView.findViewById(R.id.company3_companyfragment3_name);
        rootView.findViewById(R.id.company3_companyfragment3_setting).setOnClickListener(this);
        ButterKnife.bind(this, rootView);

        return rootView;

    }


    @Override
    public void onResume() {
        initData();
        super.onResume();
    }

    private void initJianliNum() {
        BmobQuery<RecieveJianLi> query = new BmobQuery<RecieveJianLi>();
        query.addWhereEqualTo("recieveCompanyName", company.getCompanyName());
        query.count(getActivity(), RecieveJianLi.class, new CountListener() {
            @Override
            public void onSuccess(int count) {
                comunicateNum = count;
                initGridView1();
            }
            @Override
            public void onFailure(int code, String msg) {

            }
        });

        BmobQuery<RecieveJianLi> queryNumInterest = new BmobQuery<RecieveJianLi>();
        queryNumInterest.addWhereEqualTo("recieveCompanyName", company.getCompanyName());
        queryNumInterest.addWhereEqualTo("isInterst", true);
        queryNumInterest.count(getActivity(), RecieveJianLi.class, new CountListener() {
            @Override
            public void onSuccess(int count) {
                mianshiNum = count;
                initGridView1();
            }
            @Override
            public void onFailure(int code, String msg) {

            }
        });

        BmobQuery<Job> quereyPrepareJob = new BmobQuery<Job>();
        {
            BmobQuery<Job> jobCompany = new BmobQuery<>();
            jobCompany.addWhereEqualTo("jobCompany",company.getCompanyName());

            BmobQuery<Job> or1 = new BmobQuery<>();
            or1.addWhereEqualTo("jobState", BossConstants.STATISTICS_ING);
            BmobQuery<Job> or2 = new BmobQuery<>();
            or2.addWhereEqualTo("jobState", BossConstants.STATISTICS_FAIL);

            List<BmobQuery<Job>> queries = new ArrayList<>();
            queries.add(or1);
            queries.add(or2);

            BmobQuery<Job> jobState = new BmobQuery<>();
            jobState = jobState.or(queries);

            List<BmobQuery<Job>> andQuery = new ArrayList<>();
            andQuery.add(jobState);
            andQuery.add(jobCompany);

            quereyPrepareJob.and(andQuery);
        }
       // quereyPrepareJob.addWhereEqualTo("jobCompany", company.getCompanyName());
        //quereyPrepareJob.addWhereEqualTo("isInterst", true);

        quereyPrepareJob.count(getActivity(), Job.class, new CountListener() {
            @Override
            public void onSuccess(int count) {
                prepareNum = count;
                initGridView1();
            }
            @Override
            public void onFailure(int code, String msg) {

            }
        });




    }

    private void initData() {
        User bomb = User.getCurrentUser(getActivity(), User.class);
        tvBossName.setText(bomb.getNick());
        ImageLoaderFactory.getLoader().loadAvator(imgAvtar, bomb.getAvator(), R.drawable.ic_launcher);

        BmobQuery<Company> query = new BmobQuery<>();
        query.addWhereEqualTo("companyBossPhone", bomb.getMobilePhoneNumber());
        query.findObjects(getActivity(), new FindListener<Company>() {
            @Override
            public void onSuccess(List<Company> list) {
                if (list.size() > 0) {
                    company = list.get(0);
                    tvBossJob.setText(company.getCompanyBossJob());

                    initJobNum();
                    initJianliNum();
                }
            }
            @Override
            public void onError(int i, String s) {
                showToast("公司信息加载失败");
            }
        });

    }


    private void initJobNum(){
        BmobQuery<Job>  job= new BmobQuery<>();
        job.addWhereEqualTo("jobCompany",company.getCompanyName());
        job.addWhereEqualTo("jobState",BossConstants.STATISTICS_SUCCSSS);
        job.findObjects(getActivity(), new FindListener<Job>() {
            @Override
            public void onSuccess(List<Job> list) {
                int size = list.size();
                jobNum = size;
                initGridView1();
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    @OnClick(R.id.company3_in_setting_aboutus)
    public void onStatisticClick(View view){
        Intent intent = new Intent(getActivity(), StatisticActivity.class);
        getActivity().startActivity(intent);
    }

    @OnClick(R.id.company3_in_setting_exit)
    public void onClickLogout(View view){
       User.logOut(getActivity());
        BmobIM.getInstance().disConnect();
        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
       // getActivity().sendBroadcast(new Intent(BossConstants.ACTION_CHANGE_MAIN_COMPANY_FINISH));
        getActivity().finish();
    }

    @OnClick(R.id.company3_in_setting_tip)
    public void onSetData(View view){
        if(company != null){
            Intent intent = new Intent(getActivity(), CompanyStep1Activity.class);
            intent.putExtra("company", company);
            startActivity(intent);
        }else{
            Toast.makeText(getActivity(), "没有获取到公司信息",Toast.LENGTH_SHORT).show();
        }


    }




    @OnClick(R.id.company3_in_setting_help)
    public void onClickReleseJob(){
        if(company != null){
            if(company.getCompanyState().equals(BossConstants.STATISTICS_SUCCSSS)){
                Intent intent = new Intent(getActivity(), ReleseJobActivity.class);
                intent.putExtra("company", company);
                startActivity(intent);
            }else{
                showToast("您的信息正在审核中，审核通过即可进行信息发布");
                return;
            }

        }else{
            showToast("公司信息正在加载，请稍后...");
        }

    }


/*    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initGridView1();
        initGridView2();
//		User user = User.getCurrentUser(User.class);
//		mNickName.setText(user.getNick()+"");
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
        data2.setData_content("认证boss");
        mListShow2.add(data2);

        Show2 data3 = new Show2();
        data3.setData_icon(R.drawable.c);
        data3.setData_content("装修公司");
        mListShow2.add(data3);

        Show2 data4 = new Show2();
        data4.setData_icon(R.drawable.d);
        data4.setData_content("登录网页版");
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
        data7.setData_content("登录网页版");
        mListShow2.add(data7);

        Show2 data8 = new Show2();
        data8.setData_icon(R.drawable.h);
        data8.setData_content("赚积分");
        mListShow2.add(data8);

        CompanyFragment3Adapter2 adapter2 = new CompanyFragment3Adapter2(
                getActivity(), mListShow2);
        mGirdView2.setAdapter(adapter2);

        mGirdView2.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
            }
        });

    }

    /**
     * 初始化第一个GridView
     */
    private void initGridView1() {
        // 获取到GridView
        // TODO 动态获取数据
        List<Show1> mListShow1 = new ArrayList<Show1>();
        Show1 data1 = new Show1();
        data1.setData_int(jobNum);
        data1.setData_string("在线职位");
        mListShow1.add(data1);

        Show1 data2 = new Show1();
        data2.setData_int(comunicateNum);
        data2.setData_string("收到简历");
        mListShow1.add(data2);

        Show1 data3 = new Show1();
        data3.setData_int(mianshiNum);
        data3.setData_string("感兴趣");
        mListShow1.add(data3);


        Show1 data4 = new Show1();
        data4.setData_int(prepareNum);
        data4.setData_string("待审核");
        mListShow1.add(data4);

        CompanyFragment3Adapter1 adapter1 = new CompanyFragment3Adapter1(
                getActivity(), mListShow1);
        mGridView1.setAdapter(adapter1);

        mGridView1.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
//
                if(arg2 == 0){
                    if(company != null){
                        Intent intent = new Intent(getActivity(), ReleseJobListActivity.class);
                        intent.putExtra("company", company);
                        startActivity(intent);
                    }else{
                        showToast("无法获取到公司信息");
                    }
                }

                if(arg2 == 1){
                    Intent intent = new Intent(getActivity(), ReciveJianliListActivity.class);
                    intent.putExtra("company", company);
                    startActivity(intent);
                }

                if(arg2 == 2){
                    Intent intent = new Intent(getActivity(), InterestJianliActivity.class);
                    intent.putExtra("company", company);
                    startActivity(intent);
                }

                if(arg2 == 3){
                    Intent intent = new Intent(getActivity(), CheckJobActivity.class);
                    intent.putExtra("company", company);
                    startActivity(intent);
                }


            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.company3_companyfragment3_setting://设置按钮
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //null.unbind();
       // null.unbind();
    }
}
 
