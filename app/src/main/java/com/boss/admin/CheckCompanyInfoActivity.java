package com.boss.admin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.boss.R;
import com.boss.activity.SetDataActivity;
import com.boss.adapter.jobAdapater;
import com.boss.company2.CompanyInfoActiviy;
import com.boss.company2.lunboview.CycleViewPager;
import com.boss.db.Company;
import com.boss.db.Job;
import com.boss.db.User;
import com.boss.employee2.JobInfoActivity;
import com.boss.im.base.ImageLoaderFactory;
import com.boss.im.ui.ChatActivity;
import com.boss.login.BaseActivity;
import com.boss.util.BossConstants;
import com.boss.util.TurnHelp;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMTextMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 滕新科 on 2017/5/8.
 */

public class CheckCompanyInfoActivity extends BaseActivity{
    /*  @BindView(R.id.img_company_avtar)*/
    ImageView imgCompanyAvtar;
    /* @BindView(R.id.tv_company_name)*/
    TextView tvCompanyName;
    /*  @BindView(R.id.tv_company_poi)*/
    TextView tvCompanyPoi;
    /*@BindView(R.id.item_company1_companyfragment1_state)*/
    TextView itemCompany1Companyfragment1State;
    /*@BindView(R.id.item_company1_companyfragment1_company)*/
    TextView itemCompany1Companyfragment1Company;
    /*@BindView(R.id.tv_company_flag)*/
    TextView tvCompanyFlag;
    /*  @BindView(R.id.tv_company_mem_num)*/
    TextView tvCompanyMemNum;
    /*@BindView(R.id.tv_job_request)*/
    TextView tvJobRequest;
    /*@BindView(R.id.img_boss_avtar)*/
    ImageView imgBossAvtar;
    /* @BindView(R.id.tv_bos_name)*/
    TextView tvBosName;
    /*@BindView(R.id.tv_boss_phone)*/
    TextView tvBossPhone;
    /* @BindView(R.id.lv_job)*/
    ListView lvJob;

    jobAdapater adapter1;
    List<Job> mListShow1;

    private static final int STATE_REFRESH = 0;// 下拉刷新
    private static final int STATE_MORE = 1;// 加载更多
    @BindView(R.id.tv_is_from_spider)
    TextView tvIsFromSpider;

    private int limit = 10; // 每页的数据是10条
    private int curPage = 0; // 当前页的编号，从0开始


    boolean flag1 = false;
    boolean flag2 = false;

    String lastTime = null;

    private List<ImageView> views = new ArrayList<ImageView>();
    Company company;
    private CycleViewPager cycleViewPager;

    private String[] imageUrls = {"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2151978559,3402042074&fm=23&gp=0.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493902382&di=f5876f56276147793b2d0ce616ff2b62&imgtype=jpg&er=1&src=http%3A%2F%2Fimg610.ph.126.net%2FEpbGVyPK5kzwT7aw13Xkmw%3D%3D%2F1689975760172997901.jpg"
            , "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2669717440,3744983225&fm=23&gp=0.jpg"};


    ViewPager pager = null;
    PagerTabStrip tabStrip = null;
    ArrayList<View> viewContainter = new ArrayList<View>();
    ArrayList<String> titleContainer = new ArrayList<String>();
    public String TAG = "tag";

    View view1;
    View view2;


    CircleProgressDialog circleProgressDialog;


    public static final int REQUEST_FAIL_REASON = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_company_info);
        ButterKnife.bind(this);
        circleProgressDialog = new CircleProgressDialog(this);
        iniData();
        initPage();
        initialize();
        initAllView();
        initView1();
        initView2();
    }

    private void initAllView() {
        ButterKnife.bind(this);
        imgCompanyAvtar = ButterKnife.findById(view1, R.id.img_company_avtar);
        tvCompanyName = ButterKnife.findById(view1, R.id.tv_company_name);
        ;
        tvCompanyPoi = ButterKnife.findById(view1, R.id.tv_company_poi);
        ;
        itemCompany1Companyfragment1State = ButterKnife.findById(view1, R.id.item_company1_companyfragment1_state);
        ;
        itemCompany1Companyfragment1Company = ButterKnife.findById(view1, R.id.item_company1_companyfragment1_company);
        ;
        tvCompanyFlag = ButterKnife.findById(view1, R.id.tv_company_flag);
        ;
        tvCompanyMemNum = ButterKnife.findById(view1, R.id.tv_company_mem_num);
        ;

        tvJobRequest = ButterKnife.findById(view1, R.id.tv_job_request);
        ;

        imgBossAvtar = ButterKnife.findById(view1, R.id.img_boss_avtar);
        ;

        tvBosName = ButterKnife.findById(view1, R.id.tv_bos_name);
        ;

        tvBossPhone = ButterKnife.findById(view1, R.id.tv_boss_phone);
        ;

       // lvJob = ButterKnife.findById(view2, R.id.lv_job);
        ;

    }

    private void iniData() {
        company = (Company) getIntent().getSerializableExtra("company");
    }

    public void startActivity(Class<? extends Activity> target, Bundle bundle, boolean finish) {
        Intent intent = new Intent();
        intent.setClass(this, target);
        if (bundle != null)
            intent.putExtra(getPackageName(), bundle);
        startActivity(intent);
        if (finish)
            finish();
    }


    private void initView1() {

        if (company != null) {
            ImageLoaderFactory.getLoader().loadAvator(imgCompanyAvtar, company.getComanyAvtar(), R.drawable.ic_launcher);
            ImageLoaderFactory.getLoader().loadAvator(imgBossAvtar, company.getComanyAvtar(), R.drawable.ic_launcher);

            tvCompanyName.setText(company.getCompanyName());
            tvCompanyPoi.setText(company.getComanyPoi());
            tvCompanyFlag.setText(company.getComanyFlag());
            tvCompanyMemNum.setText(company.getComanyFlag());
            tvJobRequest.setText(company.getCompanyDetailInfo());
            tvBosName.setText(company.getCompanyBossName());
            tvBossPhone.setText(company.getCompanyBossPhone());
            if(company.getFromSpider()){
                tvIsFromSpider.setText("未注册");
            }else{
                tvIsFromSpider.setText("已注册");
            }
        }
    }

    private void initView2() {

       /* mListShow1 = new ArrayList<Job>();
        adapter1 = new jobAdapater(this, mListShow1);
        lvJob.setAdapter(adapter1);
        lvJob.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View mItemView = view;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && CheckCompanyInfoActivity.this.checkSelfPermission(Settings.ACTION_MANAGE_OVERLAY_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
                    requestAlertWindowPermission();
                } else {

                    Bundle bundle = new Bundle();

                    bundle.putSerializable("job", (Job) parent.getItemAtPosition(position));

                    TurnHelp.turn(CheckCompanyInfoActivity.this, findViewById(android.R.id.content), view, bundle, new JobInfoActivity());
                }

            }
        });
        queryData(0, STATE_REFRESH);*/
    }


    private void requestAlertWindowPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 1);
        }
    }

    @OnClick(R.id.btn_pass)
    public void onClickCompanyPass(View view){
        if(company == null){
            Toast("company is null");
            return;
        }
        circleProgressDialog.showDialog();
        //Company com = company.toCompany();
        flag1 = false;
        flag2 = false;
        company.setCompanyState(BossConstants.STATISTICS_SUCCSSS);
        company.update(this, company.getObjectId(), new UpdateListener() {
            @Override
            public void onSuccess() {
                circleProgressDialog.dismiss();
                sendAMeassageToUser(true);
               // finish();
            }

            @Override
            public void onFailure(int i, String s) {
                ToastError("通过公司失败" + s);
                circleProgressDialog.dismiss();
            }
        });

     /*   company.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                flag1 = true;
                if(flag1 && flag2){
                    circleProgressDialog.dismiss();
                    sendAMeassageToUser(true);
                    finish();
                }
            }

            @Override
            public void onFailure(int i, String s) {
                ToastError("通过公司失败" + s);
                circleProgressDialog.dismiss();

            }
        });
        company.delete(this, new DeleteListener() {
            @Override
            public void onSuccess() {
                flag2 = true;

                if(flag1 && flag2){
                    circleProgressDialog.dismiss();
                    sendAMeassageToUser(true);
                    finish();
                }

            }

            @Override
            public void onFailure(int i, String s) {
                circleProgressDialog.dismiss();
                ToastError("删除审核公司失败");
            }
        });*/
    }




    public void sendAMeassageToUser(final Boolean isSuccess){
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("mobilePhoneNumber", company.getCompanyBossPhone());
        query.findObjects(this, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
               if(circleProgressDialog.isShowing()){
                    circleProgressDialog.dismiss();
                }

                if (list.size() > 0) {
                    User user = list.get(0);
                    BmobIMUserInfo info = new BmobIMUserInfo(user.getObjectId(), "通知", user.getAvator());
                    //启动一个会话，实际上就是在本地数据库的会话列表中先创建（如果没有）与该用户的会话信息，且将用户信息存储到本地的用户表中
                    BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info, null);

                    c=BmobIMConversation.obtain(BmobIMClient.getInstance(),c);

                    String text="";
                    if(!isSuccess){
                        text = "您发布的公司-" + company.getCompanyName() + "审核失败" + "\r\n"
                                + "理由" + company.getCompanyFailReason() + "\r\n"
                                + "尝试修改后重新发布";
                    }else{
                        text = "您发布的公司-" + company.getCompanyName() + "审核通过" + "\r\n";
                    }
                    BmobIMTextMessage msg =new BmobIMTextMessage();
                    msg.setContent(text);
                    //可设置额外信息
                    Map<String,Object> map =new HashMap<>();
                    map.put("level", "1");//随意增加信息
                    msg.setExtraMap(map);
                    c.sendMessage(msg, new MessageSendListener() {
                        @Override
                        public void done(BmobIMMessage bmobIMMessage, BmobException e) {
                            finish();
                        }
                    });
                    /*c.sendMessage()*/
                    c.updateLocalCache();

                } else {
                    Toast.makeText(CheckCompanyInfoActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(int i, String s) {
                circleProgressDialog.dismiss();
                Toast.makeText(CheckCompanyInfoActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick(R.id.btn_not_pass)
    public void onClickCompanyNotPass(View view){
        Intent intent = new Intent(this, SetDataActivity.class);
        intent.putExtra("limit", 1000);
        intent.putExtra("title", "不通过的理由");
        intent.putExtra("content", company.getCompanyFailReason());
        startActivityForResult(intent, REQUEST_FAIL_REASON);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_FAIL_REASON:
                    String reason = data.getStringExtra("data_return");
                    if(TextUtils.isEmpty(reason)){
                        ToastError("请至少说明一条理由");
                        break;
                    }
                    circleProgressDialog.showDialog();
                    company.setCompanyState(BossConstants.STATISTICS_FAIL);
                    company.setCompanyFailReason(reason);
                    company.update(this, company.getObjectId(), new UpdateListener() {
                        @Override
                        public void onSuccess() {
                            sendAMeassageToUser(false);
                           // finish();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            circleProgressDialog.dismiss();
                            ToastError("理由更新失败");
                        }
                    });
                    break;
            }
        }
    }


    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    private void initPage() {
        pager = (ViewPager) this.findViewById(R.id.viewpager);
        tabStrip = (PagerTabStrip) this.findViewById(R.id.tabstrip);
        //取消tab下面的长横线
        tabStrip.setDrawFullUnderline(false);
        //设置tab的背景色
        tabStrip.setBackgroundColor(this.getResources().getColor(R.color.background));
        //设置当前tab页签的下划线颜色
        tabStrip.setTabIndicatorColor(this.getResources().getColor(R.color.app_main));
        tabStrip.setTextSpacing(200);

        view1 = LayoutInflater.from(this).inflate(R.layout.fragment_company_tab1, null);
        //view2 = LayoutInflater.from(this).inflate(R.layout.fragment_company_tab2, null);

        //viewpager开始添加view
        viewContainter.add(view1);
        //viewContainter.add(view2);
        //页签项
        titleContainer.add("公司概况");
        //titleContainer.add("招聘职位");

        pager.setAdapter(new PagerAdapter() {

            //viewpager中的组件数量
            @Override
            public int getCount() {
                return viewContainter.size();
            }

            //滑动切换的时候销毁当前的组件
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                ((ViewPager) container).removeView(viewContainter.get(position));
            }

            //每次滑动的时候生成的组件
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(viewContainter.get(position));
                return viewContainter.get(position);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleContainer.get(position);
            }
        });

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {
                Log.d(TAG, "--------changed:" + arg0);
                //ButterKnife.
                if (arg0 == 0) {
                    initView1();
                } else if (arg0 == 2) {
                    initView1();
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                Log.d(TAG, "-------scrolled arg0:" + arg0);
                Log.d(TAG, "-------scrolled arg1:" + arg1);
                Log.d(TAG, "-------scrolled arg2:" + arg2);
            }

            @Override
            public void onPageSelected(int arg0) {
                Log.d(TAG, "------selected:" + arg0);
            }


        });

    }


    @SuppressLint("NewApi")
    private void initialize() {

        cycleViewPager = (CycleViewPager) getFragmentManager()
                .findFragmentById(R.id.fragment_cycle_viewpager_content);

        if (!TextUtils.isEmpty(company.getCompanyAvatar1())) {
            imageUrls[0] = company.getCompanyAvatar1();
        }
        if (!TextUtils.isEmpty(company.getCompanyAvatar2())) {
            imageUrls[1] = company.getCompanyAvatar2();
        }
        if (!TextUtils.isEmpty(company.getCompanyAvatar3())) {
            imageUrls[2] = company.getCompanyAvatar3();
        }

/*        imageUrls[1] = company.getCompanyAvatar2();
        imageUrls[2] = company.getCompanyAvatar3();*/

        // 将最后一个ImageView添加进来
        views.add(getImageView(this, imageUrls[0]));

        for (int i = 0; i < imageUrls.length; i++) {
            views.add(getImageView(this, imageUrls[i]));
        }
        // 将第一个ImageView添加进来
        views.add(getImageView(this, imageUrls[imageUrls.length - 1]));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, company, mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(2000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(int position, View imageView) {
            if (cycleViewPager.isCycle()) {
                position = position - 1;
                Toast.makeText(CheckCompanyInfoActivity.this,
                        "position-->", Toast.LENGTH_SHORT)
                        .show();
            }

        }

    };


    /**
     * 获取ImageView视图的同时加载显示url
     *
     * @return
     */
    public static ImageView getImageView(Context context, String url) {
        ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(
                R.layout.view_banner, null);
        ImageLoader.getInstance().displayImage(url, imageView);
        return imageView;
    }

}
