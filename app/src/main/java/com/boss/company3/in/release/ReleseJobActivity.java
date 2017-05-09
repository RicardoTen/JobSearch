package com.boss.company3.in.release;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.boss.R;
import com.boss.activity.SetDataActivity;
import com.boss.db.Company;
import com.boss.db.Job;
import com.boss.util.BossConstants;
import com.boss.view.IconFontTextView;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 滕新科 on 2017/4/24.
 */

public class ReleseJobActivity extends Activity {

    @BindView(R.id.actionbar_left)
    IconFontTextView actionbarLeft;
    @BindView(R.id.actionbar_center)
    TextView actionbarCenter;
    @BindView(R.id.actionbar_right)
    IconFontTextView actionbarRight;
    @BindView(R.id.img_serch)
    ImageView imgSerch;
    @BindView(R.id.tv_devider)
    TextView tvDevider;
    @BindView(R.id.tv_job_name)
    TextView tvJobName;
    @BindView(R.id.layout_job_name)
    LinearLayout layoutJobName;
    @BindView(R.id.tv_job_exp)
    TextView tvJobExp;
    @BindView(R.id.layout_job_exp)
    LinearLayout layoutJobExp;
    @BindView(R.id.tv_job_edu)
    TextView tvJobEdu;
    @BindView(R.id.layout_job_edu)
    LinearLayout layoutJobEdu;
    @BindView(R.id.tv_job_money)
    TextView tvJobMoney;
    @BindView(R.id.layout_job_money)
    LinearLayout layoutJobMoney;
    @BindView(R.id.tv_hint_company_poi)
    TextView tvHintCompanyPoi;
    @BindView(R.id.layout_job_request)
    LinearLayout layoutJobRequest;
    @BindView(R.id.tv_hint_company_member)
    TextView tvHintCompanyMember;
    @BindView(R.id.laout_job_skill)
    LinearLayout laoutJobSkill;
    @BindView(R.id.layout_job_detail_poi)
    LinearLayout layoutJobDetailPoi;
    @BindView(R.id.layout_job_type)
    LinearLayout layoutJobType;
    @BindView(R.id.register_companystep1_goCompanyMain)
    Button registerCompanystep1GoCompanyMain;

    CircleProgressDialog circleProgressDialog;


    public static final int REQUEST_SET_JOB_EXP = 101;
    public static final int REQUEST_SET_JOB__NAME = 102;
    public static final int REQUEST_SET_JOB__REQUEST = 103;
    public static final int REQUEST_SET_JOB__SKILL_REQUEST = 104;
    public static final int REQUEST_SET_JOB__POI_DE = 105;

    public static final int REQUEST_SET_JOB_EDU = 106;

    public static final int REQUEST_SET_JOB_MONEY = 107;

    public static final int REQEST_SET_JOB_TYPE = 108;

    public static final int REQEST_SET_JOB_REQUEST_NUM = 109;


    Company company = new Company();
    Job job;

    boolean isReview = false;
    @BindView(R.id.tv_job_flag)
    TextView tvJobFlag;
    @BindView(R.id.tv_job_member_num)
    TextView tvJobMemberNum;
    @BindView(R.id.layout_job_member_num)
    LinearLayout layoutJobMemberNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relese_job);
        ButterKnife.bind(this);
        circleProgressDialog = new CircleProgressDialog(this);
        company = (Company) getIntent().getSerializableExtra("company");
        job = (Job) getIntent().getSerializableExtra("job");

        if (company == null) {
            company = new Company();
        }

        if (job != null) {
            isReview = true;
            initData();
        } else {
            isReview = false;
            job = new Job();
        }
    }

    private void initData() {
        tvJobMoney.setText(job.getJobOfferMoney());
        tvJobExp.setText(job.getJobRequestExp());
        tvJobEdu.setText(job.getJobRequestGraduate());
        tvJobName.setText(job.getJobName());
        tvJobFlag.setText(job.getJobFlag());
    }

    @OnClick(R.id.layout_job_member_num)
    public void onJobmemerClick(View view){
        Intent intent = new Intent(this, SetDataActivity.class);
        intent.putExtra("title", "需求人数");// 传标题
        intent.putExtra("limit", 10);// 传字数限制
        intent.putExtra("content", job.getJobRequestum());
        startActivityForResult(intent, REQEST_SET_JOB_REQUEST_NUM);
    }


    @OnClick({R.id.layout_job_name, R.id.layout_job_exp, R.id.layout_job_edu, R.id.layout_job_money, R.id.layout_job_request, R.id.laout_job_skill, R.id.layout_job_detail_poi, R.id.layout_job_type, R.id.register_companystep1_goCompanyMain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_job_name: {
                Intent intent = new Intent(this, SetDataActivity.class);
                intent.putExtra("title", "职位名称");// 传标题
                intent.putExtra("limit", 40);// 传字数限制
                intent.putExtra("content", job.getJobName());
                startActivityForResult(intent, REQUEST_SET_JOB__NAME);
            }
            break;
            case R.id.layout_job_exp: {
                Intent intent = new Intent(this, SetDataActivity.class);
                intent.putExtra("title", "经验");// 传标题
                intent.putExtra("limit", 1000);// 传字数限制
                intent.putExtra("content", job.getJobRequestExp());
                startActivityForResult(intent, REQUEST_SET_JOB_EXP);
            }
            break;
            case R.id.layout_job_edu: {
                Intent intent = new Intent(this, setListDataActivity.class);
                intent.putExtra("data", BossConstants.GET_EDUCATION);// 传标题
                startActivityForResult(intent, REQUEST_SET_JOB_EDU);
            }
            break;
            case R.id.layout_job_money: {
                Intent intent = new Intent(this, setListDataActivity.class);
                intent.putExtra("data", BossConstants.Money);// 传标题
                startActivityForResult(intent, REQUEST_SET_JOB_MONEY);
            }
            break;
            case R.id.layout_job_request: {
                {
                    Intent intent = new Intent(this, SetDataActivity.class);
                    intent.putExtra("title", "详细要求");// 传标题
                    intent.putExtra("limit", 1000);// 传字数限制
                    intent.putExtra("content", job.getJobRequestInfo());
                    startActivityForResult(intent, REQUEST_SET_JOB__REQUEST);
                }
            }
            break;
            case R.id.laout_job_skill: {
                Intent intent = new Intent(this, SetDataActivity.class);
                intent.putExtra("title", "技能要求");// 传标题
                intent.putExtra("limit", 1000);// 传字数限制
                intent.putExtra("content", job.getJobRequestSkill());
                startActivityForResult(intent, REQUEST_SET_JOB__SKILL_REQUEST);
            }
            break;
            case R.id.layout_job_detail_poi: {
                Intent intent = new Intent(this, SetDataActivity.class);
                intent.putExtra("title", "详细位置");// 传标题
                intent.putExtra("limit", 50);// 传字数限制
                intent.putExtra("content", job.getJobCompanyPoiDe());
                startActivityForResult(intent, REQUEST_SET_JOB__POI_DE);
            }
            break;
            case R.id.layout_job_type: {
                Intent intent = new Intent(this, setListDataActivity.class);
                intent.putExtra("data", BossConstants.GET_WORK_TYPE);// 传标题
                startActivityForResult(intent, REQEST_SET_JOB_TYPE);
            }
            break;
            case R.id.register_companystep1_goCompanyMain:
                saveOrUpdate();
                break;
        }
    }

    private void saveOrUpdate() {
        if (TextUtils.isEmpty(job.getJobName())) {
            Toast.makeText(this, "职位名称未填写",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        circleProgressDialog.showDialog();

        if (isReview) {
            job.setJobState(BossConstants.STATISTICS_ING);
            job.setJobFailReason("");
            job.update(this, job.getObjectId(), new UpdateListener() {
                @Override
                public void onSuccess() {
                    circleProgressDialog.dismiss();
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    circleProgressDialog.dismiss();
                    Toast.makeText(ReleseJobActivity.this, "职位更新失败",
                            Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            job.setJobCompany(company.getCompanyName());
            job.setJobCompanyFlag(company.getComanyFlag());
            job.setJobCompanyPoi(company.getComanyPoi());
            job.setJobBossAvtar(company.getCompanyBossAvatar());
            job.setJobBossName(company.getCompanyBossName());
            job.setJobBossPhoneNumber(company.getCompanyBossPhone());
            job.setJobState(BossConstants.STATISTICS_ING);
            job.setJobFailReason("");


            job.save(this, new SaveListener() {
                @Override
                public void onSuccess() {
                    circleProgressDialog.dismiss();
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    circleProgressDialog.dismiss();
                    Toast.makeText(ReleseJobActivity.this, "职位新建失败",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQEST_SET_JOB_TYPE: {
                    String content = data.getStringExtra("data_return");
                    tvJobFlag.setText(content);
                    job.setJobFlag(content);
                }
                break;
                case REQUEST_SET_JOB__NAME: {
                    String content = data.getStringExtra("data_return");
                    tvJobName.setText(content);
                    job.setJobName(content);
                }
                break;
                case REQUEST_SET_JOB__POI_DE: {
                    String content = data.getStringExtra("data_return");
                    //tvJOB.setText(content);
                    job.setJobCompanyPoiDe(content);
                }
                break;
                case REQUEST_SET_JOB__REQUEST: {
                    String content = data.getStringExtra("data_return");
                    //tvJobName.setText(content);
                    job.setJobRequestInfo(content);
                }
                break;
                case REQUEST_SET_JOB__SKILL_REQUEST: {
                    String content = data.getStringExtra("data_return");
                    //tvJobName.setText(content);
                    job.setJobRequestSkill(content);
                }
                break;
                case REQUEST_SET_JOB_EDU: {
                    String content = data.getStringExtra("data_return");
                    tvJobEdu.setText(content);
                    job.setJobRequestGraduate(content);
                }
                break;
                case REQUEST_SET_JOB_EXP: {
                    String content = data.getStringExtra("data_return");
                    tvJobExp.setText(content);
                    job.setJobRequestExp(content);
                }
                break;
                case REQUEST_SET_JOB_MONEY: {
                    String content = data.getStringExtra("data_return");
                    tvJobMoney.setText(content);
                    job.setJobOfferMoney(content);
                }
                break;

                case REQEST_SET_JOB_REQUEST_NUM: {
                    String content = data.getStringExtra("data_return");
                    tvJobMemberNum.setText(content);
                    job.setJobRequestum(content);
                }
                break;


            }
        }
    }

}
