package com.boss.admin;

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
import com.boss.company2.CompanyInfoActiviy;
import com.boss.company3.in.release.ReleseJobActivity;
import com.boss.db.Company;
import com.boss.db.Job;
import com.boss.db.PostJianLi;
import com.boss.db.RecieveJianLi;
import com.boss.db.User;
import com.boss.employee2.JobInfoActivity;
import com.boss.im.base.ImageLoaderFactory;
import com.boss.im.ui.ChatActivity;
import com.boss.login.BaseActivity;
import com.boss.util.BossConstants;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

import org.w3c.dom.Text;

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
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 滕新科 on 2017/5/8.
 */

public class CheckJobInfoActivity extends BaseActivity{

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

    Job job = null;
    @BindView(R.id.tv_boss_phone)
    TextView tvBossPhone;

    CircleProgressDialog circleProgressDialog;

    boolean flag1 = false;
    boolean flag2 = false;
    @BindView(R.id.tv_is_from_spider)
    TextView tvIsFromSpider;
    @BindView(R.id.tv_request_num)
    TextView tvRequestNum;


    public static final int REQUEST_FAIL_REASON = 101;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_job_info);
        ButterKnife.bind(this);
        circleProgressDialog = new CircleProgressDialog(this);
        iniData();

    }

    @Override
    protected void onResume() {
       // queryIsPostJianli();
        super.onResume();
    }

    @OnClick(R.id.btn_pass)
    public void onClickPass(View view){
        if(job == null){
            ToastError("请重试");
        }
        circleProgressDialog.showDialog();
         flag1 = false;
         flag2 = false;
         job.setJobState(BossConstants.STATISTICS_SUCCSSS);
         job.update(this, job.getObjectId(), new UpdateListener() {
             @Override
             public void onSuccess() {
                 circleProgressDialog.dismiss();
                 sendAMeassageToUser(true);
                // finish();
             }

             @Override
             public void onFailure(int i, String s) {
                 ToastError("通过职位失败" + s);
                 circleProgressDialog.dismiss();
             }
         });
       /* job.save(this, new SaveListener() {
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
                ToastError("通过职位失败" + s);
                circleProgressDialog.dismiss();

            }
        });
        job.delete(this, new DeleteListener() {
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
                ToastError("删除审核职位失败");
            }
        });*/

    }


    @OnClick(R.id.btn_not_pass)
    public void onClickNotPass(View view){
        Intent intent = new Intent(this, SetDataActivity.class);
        intent.putExtra("limit", 1000);
        intent.putExtra("title", "不通过的理由");
        intent.putExtra("content", job.getJobFailReason());
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
                    job.setJobState(BossConstants.STATISTICS_FAIL);
                    job.setJobFailReason(reason);
                    job.update(this, job.getObjectId(), new UpdateListener() {
                        @Override
                        public void onSuccess() {
                            sendAMeassageToUser(false);
                            //finish();
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

    public void sendAMeassageToUser(final Boolean isSuccess){
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("mobilePhoneNumber", job.getJobBossPhoneNumber());
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
                        text = "您发布的职位-" + job.getJobName() + "审核失败" + "\r\n"
                                + "理由" + job.getJobFailReason() + "\r\n"
                                + "尝试修改后重新发布";
                    }else{
                        text = "您发布的职位-" + job.getJobName() + "审核通过" + "\r\n";
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
                    c.updateLocalCache();

                } else {
                    Toast.makeText(CheckJobInfoActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(int i, String s) {
                circleProgressDialog.dismiss();
                Toast.makeText(CheckJobInfoActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void iniData() {
        job = (Job) getIntent().getSerializableExtra("job");
        boolean isReview = getIntent().getBooleanExtra("review", false);

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
