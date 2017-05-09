package com.boss.employee4.in;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.boss.R;
import com.boss.adapter.jobAdapater;
import com.boss.db.Job;
import com.boss.db.PostJianLi;
import com.boss.db.RecieveJianLi;
import com.boss.db.User;
import com.boss.employee2.JobInfoActivity;
import com.boss.login.BaseActivity;
import com.boss.view.IconFontTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 滕新科 on 2017/4/26.
 */

public class PostJobListActivity extends BaseActivity {

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
    @BindView(R.id.lv_list_job)
    ListView lvListJob;

    jobAdapater adapter1;
    List<Job> mListShow1 = new ArrayList<>();

    List<PostJianLi> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_post_job);
        ButterKnife.bind(this);
        mListShow1 = new ArrayList<Job>();
        adapter1 = new jobAdapater(this, mListShow1);
        lvListJob.setAdapter(adapter1);
        lvListJob.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Job job = (Job) parent.getItemAtPosition(position);
                Intent intent = new Intent(PostJobListActivity.this, JobInfoActivity.class);
                intent.putExtra("job", job);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        queryData();
        super.onResume();
    }

    private void queryData() {
        User user = User.getCurrentUser(this, User.class);
        BmobQuery<PostJianLi> query = new BmobQuery<>();
        // 按时间降序查询
        query.order("-createdAt");
        query.addWhereEqualTo("postJianliUserPhone", user.getMobilePhoneNumber());
        query.findObjects(this, new FindListener<PostJianLi>() {
            @Override
            public void onSuccess(List<PostJianLi> list) {
                datas.clear();
                if (list.size() > 0) {
                    for (PostJianLi td : list) {
                        datas.add(td);
                    }
                } else {
                    Toast("没有更多数据了");
                }
                queryJob();
            }
            @Override
            public void onError(int arg0, String arg1) {
                Toast("查询失败:" + arg1);
            }
        });
    }


    private void queryJob(){
        mListShow1.clear();
        for(int i = 0; i < datas.size(); i++){
            PostJianLi postJianLi = datas.get(i);
            BmobQuery<Job> query = new BmobQuery<>();
            // 按时间降序查询
            query.order("-createdAt");
            query.addWhereEqualTo("jobCompany", postJianLi.getRecieveCompanyName());
            query.addWhereEqualTo("jobName", postJianLi.getRecieveCompanyJob());
            query.findObjects(this, new FindListener<Job>() {
                @Override
                public void onSuccess(List<Job> list) {
                    if (list.size() > 0) {
                        mListShow1.add(list.get(0));
                    }
                    adapter1.notifyDataSetChanged();
                }
                @Override
                public void onError(int arg0, String arg1) {
                    Toast("查询失败:" + arg1);
                }
            });
        }

    }
}
