package com.boss.employee2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.boss.R;
import com.boss.company2.CompanyInfoActiviy;
import com.boss.company3.in.release.ReleseJobActivity;
import com.boss.db.Company;
import com.boss.db.Job;
import com.boss.db.PostJianLi;
import com.boss.db.RecieveJianLi;
import com.boss.db.User;
import com.boss.im.base.ImageLoaderFactory;
import com.boss.im.ui.ChatActivity;
import com.boss.login.BaseActivity;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 滕新科 on 2017/4/22.
 */

public class JobInfoActivity extends BaseActivity {
    @BindView(R.id.txt_num)
    TextView txtNum;
    @BindView(R.id.tv_job_name)
    TextView tvJobName;
    @BindView(R.id.tv_jo_offer)
    TextView tvJoOffer;
    @BindView(R.id.tv_job_company_poi)
    TextView tvJobCompanyPoi;
    @BindView(R.id.tv_job_request_exp)
    TextView tvJobRequestExp;
    @BindView(R.id.tv_job_request_edu)
    TextView tvJobRequestEdu;
    @BindView(R.id.img_company_avtar)
    ImageView imgCompanyAvtar;
    @BindView(R.id.tv_job_comany_name)
    TextView tvJobComanyName;
    @BindView(R.id.tv_job_company_flag)
    TextView tvJobCompanyFlag;
    @BindView(R.id.employee4_in_editdata_yulanjianli_educate)
    TextView employee4InEditdataYulanjianliEducate;
    @BindView(R.id.tv_job_flag)
    TextView tvJobFlag;
    @BindView(R.id.tv_job_request)
    TextView tvJobRequest;
    @BindView(R.id.tv_job_request_skill)
    TextView tvJobRequestSkill;
    @BindView(R.id.img_boss_avtar)
    ImageView imgBossAvtar;
    @BindView(R.id.tv_bos_name)
    TextView tvBosName;
    @BindView(R.id.employee4_in_editdata_yulanjianli_all)
    LinearLayout employee4InEditdataYulanjianliAll;
    @BindView(R.id.btn_begin_chat)
    Button btnBeginChat;

    Job job = null;
    @BindView(R.id.tv_boss_phone)
    TextView tvBossPhone;
    @BindView(R.id.btn_begin_review)
    Button btnBeginReview;
    @BindView(R.id.btn_post_jianli)
    Button btnPostJianli;

    CircleProgressDialog circleProgressDialog;

    boolean flag1 = false;
    boolean flag2 = false;
    @BindView(R.id.tv_is_from_spider)
    TextView tvIsFromSpider;
    @BindView(R.id.tv_request_num)
    TextView tvRequestNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);
        ButterKnife.bind(this);
        circleProgressDialog = new CircleProgressDialog(this);
        iniData();
    }

    @Override
    protected void onResume() {
        queryIsPostJianli();
        super.onResume();
    }

    @OnClick(R.id.layout_company_info)
    public void onCompanyInfoClick(View view){
        if(job == null){
            Toast("职位信息为空");
            return;
        }
        circleProgressDialog.showDialog();
        BmobQuery<Company> query = new BmobQuery<>();
        query.addWhereEqualTo("companyName",job.getJobCompany());
        query.findObjects(this, new FindListener<Company>() {
            @Override
            public void onSuccess(List<Company> list) {
                circleProgressDialog.dismiss();
                if(list.size() > 0){
                    Intent intent = new Intent(JobInfoActivity.this, CompanyInfoActiviy.class);
                    Company com = list.get(0);
                    intent.putExtra("company", com);
                    startActivity(intent);
                }else{
                    Toast("未收录该公司信息");
                }
            }

            @Override
            public void onError(int i, String s) {
                circleProgressDialog.dismiss();
                Toast("请重试");
            }
        });



    }

    private boolean queryIsPostJianli() {
        User user = User.getCurrentUser(this, User.class);
        BmobQuery<PostJianLi> query = new BmobQuery<>();
        // 按时间降序查询
        query.order("-createdAt");
        query.addWhereEqualTo("postJianliUserPhone", user.getMobilePhoneNumber());
        query.addWhereEqualTo("recieveCompanyJob", job.getJobName());
        query.addWhereEqualTo("recieveCompanyName", job.getJobCompany());

        query.count(this, PostJianLi.class, new CountListener() {
            @Override
            public void onSuccess(int i) {
                if (i > 0) {
                    btnPostJianli.setText("已投递");
                    btnPostJianli.setClickable(false);
                } else {
                    btnPostJianli.setText("投递简历");
                    btnPostJianli.setClickable(true);
                }
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(JobInfoActivity.this, "查询简历" + s, Toast.LENGTH_SHORT).show();
            }
        });
        return false;
    }


    @OnClick(R.id.btn_begin_review)
    public void onClickReview(View view) {
        Intent intent = new Intent(this, ReleseJobActivity.class);
        intent.putExtra("job", job);
        startActivity(intent);
    }

    @OnClick(R.id.btn_post_jianli)
    public void onClickPostJianli() {
        if(job != null){
            if(job.getFromSpider() != null && job.getFromSpider()){
                Toast("该公司/职位未注册");
                return;
            }
        }

        flag1 = false;
        flag2 = false;
        circleProgressDialog.showDialog();
        User user = User.getCurrentUser(this, User.class);
        PostJianLi postJianLi = new PostJianLi();
        postJianLi.setJianliName("");
        postJianLi.setPostJianliUserNme(user.getNick());
        postJianLi.setPostJianliUserPhone(user.getMobilePhoneNumber());
        postJianLi.setRecieveCompanyJob(job.getJobName());
        postJianLi.setRecieveCompanyName(job.getJobCompany());
        postJianLi.setReCompanyBossName(job.getJobBossName());
        postJianLi.setReCompanyBossPhone(job.getJobBossPhoneNumber());
        //postJianLi.set
        postJianLi.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                flag1 = true;
                if (flag1 && flag2) {
                    circleProgressDialog.dismiss();
                    Toast("投递成功");
                    btnPostJianli.setText("已投递");
                    btnPostJianli.setClickable(false);
                }
            }

            @Override
            public void onFailure(int i, String s) {
                circleProgressDialog.dismiss();
                Toast("投递失败");
            }
        });


        RecieveJianLi recieveJianLi = new RecieveJianLi();
        recieveJianLi.setJianliName("");
        recieveJianLi.setPostJianliUserNme(user.getNick());
        recieveJianLi.setPostJianliUserPhone(user.getMobilePhoneNumber());
        recieveJianLi.setRecieveCompanyJob(job.getJobName());
        recieveJianLi.setRecieveCompanyName(job.getJobCompany());
        recieveJianLi.setReCompanyBossName(job.getJobBossName());
        recieveJianLi.setReCompanyBossPhone(job.getJobBossPhoneNumber());
        recieveJianLi.setInterst(false);
        recieveJianLi.setPostJianliUserAv(user.getAvator());

        recieveJianLi.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                flag2 = true;
                if (flag1 && flag2) {
                    circleProgressDialog.dismiss();
                    Toast("投递成功");
                    btnPostJianli.setText("已投递");
                    btnPostJianli.setClickable(false);
                }
            }

            @Override
            public void onFailure(int i, String s) {
                circleProgressDialog.dismiss();
                Toast("投递失败");
            }
        });


    }

    private void iniData() {
        job = (Job) getIntent().getSerializableExtra("job");
        boolean isReview = getIntent().getBooleanExtra("review", false);
        if (isReview) {
            btnBeginReview.setVisibility(View.VISIBLE);
            btnPostJianli.setVisibility(View.GONE);
            btnBeginChat.setVisibility(View.GONE);
        } else {
            btnBeginReview.setVisibility(View.GONE);
            btnBeginChat.setVisibility(View.VISIBLE);
            btnPostJianli.setVisibility(View.VISIBLE);
        }

        if (job == null) {
            return;
        }

        setValue(tvJobName, job.getJobName());
        setValue(tvJoOffer, job.getJobOfferMoney());
        setValue(tvJobCompanyPoi, job.getJobCompanyPoi());
        setValue(tvJobRequestExp, job.getJobRequestExp());
        setValue(tvJobRequestEdu, job.getJobRequestGraduate());
        setValue(tvJobComanyName, job.getJobCompany());
        setValue(tvJobCompanyFlag, job.getJobCompanyFlag());
        setValue(tvJobFlag, job.getJobFlag());
        setValue(tvJobRequest, job.getJobRequestInfo());
        setValue(tvJobRequestSkill, job.getJobRequestSkill());
        setValue(tvBosName, job.getJobBossName());
        setValue(tvBossPhone, job.getJobBossPhoneNumber());
        setValue(tvRequestNum, job.getJobRequestum());


        if (job.getFromSpider()) {
            tvIsFromSpider.setText("未注册");
        } else {
            tvIsFromSpider.setText("已注册");
        }

        ImageLoaderFactory.getLoader().loadAvator(imgCompanyAvtar, job.getJobCompanyAvatar(), R.drawable.ic_launcher);
        ImageLoaderFactory.getLoader().loadAvator(imgBossAvtar, job.getJobBossAvtar(), R.drawable.ic_launcher);
    }


    private void setValue(TextView tv, String content) {
        if (content != null) {
            tv.setText(content);
        }
    }

    @OnClick(R.id.btn_begin_chat)
    public void beginChat(View view) {
        if (job == null) {
            return;
        }
        circleProgressDialog.showDialog();
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("mobilePhoneNumber", job.getJobBossPhoneNumber());
        query.findObjects(this, new FindListener<User>() {

            @Override
            public void onSuccess(List<User> list) {
                circleProgressDialog.dismiss();
                if (list.size() > 0) {
                    User user = list.get(0);
                    BmobIMUserInfo info = new BmobIMUserInfo(user.getObjectId(), user.getNick(), user.getAvator());
                    //启动一个会话，实际上就是在本地数据库的会话列表中先创建（如果没有）与该用户的会话信息，且将用户信息存储到本地的用户表中
                    BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info, null);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("c", c);
                    //this.startActi
                    //startActivity(new Intent(this, ChatActivity.class), bundle);
                    startActivity(ChatActivity.class, bundle, false);
                } else {
                    Toast.makeText(JobInfoActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(int i, String s) {
                circleProgressDialog.dismiss();
                Toast.makeText(JobInfoActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
            }
        });


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

}
