package com.boss.company3.in.release;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.boss.R;
import com.boss.adapter.jobAdapater;
import com.boss.db.Company;
import com.boss.db.Job;
import com.boss.employee2.JobInfoActivity;
import com.boss.util.BossConstants;
import com.boss.util.TurnHelp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 滕新科 on 2017/4/24.
 */

public class ReleseJobListActivity extends Activity {

    jobAdapater adapter1;
    List<Job> mListShow1;
    @BindView(R.id.lv_job)
    ListView lvJob;
    @BindView(R.id.include_shujujiazai_txt)
    TextView includeShujujiazaiTxt;
    @BindView(R.id.include_shujujiazai)
    LinearLayout includeShujujiazai;


    private static final int STATE_REFRESH = 0;// 下拉刷新
    private static final int STATE_MORE = 1;// 加载更多

    private int limit = 50; // 每页的数据是10条
    private int curPage = 0; // 当前页的编号，从0开始

    String lastTime = null;

    Company company;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relese_list_job);
        ButterKnife.bind(this);
        company = (Company) getIntent().getSerializableExtra("company");
        initData();
    }

    private void initData() {
        // TODO 动态获取数据
        mListShow1 = new ArrayList<Job>();
        adapter1 = new jobAdapater(this, mListShow1);
        lvJob.setAdapter(adapter1);
        lvJob.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
/*                View mItemView = view;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && getActivity().checkSelfPermission(Settings.ACTION_MANAGE_OVERLAY_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
                    requestAlertWindowPermission();
                } else {
					*//*Intent intent = new Intent(context, AAActivity.class);
					intent.putExtra("us", (Serializable) listdata);
					intent.putExtra("position", position);
					context.startActivity(intent);*//*

                    Bundle bundle = new Bundle();
					*//*bundle.putInt("position", position);

					List<User> users = new ArrayList<>();
					users.add((User)parent.getItemAtPosition(position));*//*
                    bundle.putSerializable("job", (Job)parent.getItemAtPosition(position) );

                    TurnHelp.turn(getActivity(), getActivity().findViewById(android.R.id.content), view, bundle, new JobInfoActivity());
                }*/

                Bundle bundle = new Bundle();
                bundle.putSerializable("job", (Job)parent.getItemAtPosition(position) );
                bundle.putBoolean("review", true );
                Intent intent = new Intent(ReleseJobListActivity.this, JobInfoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        queryData(0, STATE_REFRESH);
    }



    /**
     * 分页获取数据
     *
     * @param page
     *            页码
     * @param actionType
     *            ListView的操作类型（下拉刷新、上拉加载更多）
     */
    private void queryData(int page, final int actionType) {

        includeShujujiazaiTxt.setText("数据加载中");

        BmobQuery<Job> query = new BmobQuery<>();
        // 按时间降序查询
        query.order("-createdAt");
        int count = 0;
        // 如果是加载更多
        if (actionType == STATE_MORE) {
            // 处理时间查询
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = sdf.parse(lastTime);
                //Log.i("0414", date.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 只查询小于等于最后一个item发表时间的数据
            //query.addWhereLessThanOrEqualTo("createdAt", new BmobDate(date));
            // 跳过之前页数并去掉重复数据
            query.setSkip(page * count + 1);
        } else {
            // 下拉刷新
            page = 0;
            query.setSkip(page);
        }
        query.addWhereEqualTo("jobCompany",company.getCompanyName());
        query.addWhereEqualTo("jobState", BossConstants.STATISTICS_SUCCSSS);
        // 设置每页数据个数
        query.setLimit(limit);
        // 查找数据
        query.findObjects(this, new FindListener<Job>() {
            @Override
            public void onSuccess(List<Job> list) {
                if (list.size() > 0) {

                    if (actionType == STATE_REFRESH) {
                        // 当是下拉刷新操作时，将当前页的编号重置为0，并把bankCards清空，重新添加
                        curPage = 0;
                        mListShow1.clear();
                        // 获取最后时间
                        lastTime = list.get(list.size() - 1).getCreatedAt();
                    }

                    // 将本次查询的数据添加到bankCards中
                    for (Job td : list) {
                        mListShow1.add(td);
                    }

                    // 这里在每次加载完数据后，将当前页码+1，这样在上拉刷新的onPullUpToRefresh方法中就不需要操作curPage了
                    curPage++;
//					 showToast("第"+(page+1)+"页数据加载完成");
                } else if (actionType == STATE_MORE) {
                    showToast("没有更多数据了");
                } else if (actionType == STATE_REFRESH) {
                    showToast("没有数据");
                }
                adapter1.notifyDataSetChanged();
                includeShujujiazai.setVisibility(View.GONE);
                lvJob.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(int arg0, String arg1) {
                showToast("查询失败:" + arg1);
                includeShujujiazaiTxt.setText("数据加载失败，点击重试");
            }
        });

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
