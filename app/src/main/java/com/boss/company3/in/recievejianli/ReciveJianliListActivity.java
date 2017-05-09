package com.boss.company3.in.recievejianli;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.boss.R;
import com.boss.company3.in.recievejianli.adapter.jianliAdapter;
import com.boss.db.Company;
import com.boss.db.RecieveJianLi;
import com.boss.db.User;
import com.boss.login.BaseActivity;
import com.boss.view.IconFontTextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 滕新科 on 2017/4/26.
 */

public class ReciveJianliListActivity extends BaseActivity {

    jianliAdapter adapter;

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
    @BindView(R.id.lv_revive_jianli)
    ListView lvReviveJianli;

    Company company = new Company();
    List<RecieveJianLi> datas = new ArrayList<>();
    String lastTime = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recive_jianli_list);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        company = (Company) getIntent().getSerializableExtra("company");
        if(company == null){
            company = new Company();
            return;
        }
        quryData();
    }

    @Override
    protected void onResume() {
        quryData();
        super.onResume();
    }



    private void initView() {
        adapter = new jianliAdapter(this, datas);
        lvReviveJianli.setAdapter(adapter);

        lvReviveJianli.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ReciveJianliListActivity.this, ReviewJianliActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("us", (Serializable)datas);
                startActivity(intent);
            }
        });
    }


    private void quryData(){
        BmobQuery<RecieveJianLi> query = new BmobQuery<>();
        // 按时间降序查询
        query.order("-createdAt");
        query.addWhereEqualTo("recieveCompanyName", company.getCompanyName());
        query.findObjects(this, new FindListener<RecieveJianLi>() {
            @Override
            public void onSuccess(List<RecieveJianLi> list) {
                if (list.size() > 0) {
                    datas.clear();
                    // 获取最后时间
                    lastTime = list.get(list.size() - 1).getCreatedAt();
                    // 将本次查询的数据添加到bankCards中
                    for (RecieveJianLi td : list) {
                        datas.add(td);
                    }
                    // 这里在每次加载完数据后，将当前页码+1，这样在上拉刷新的onPullUpToRefresh方法中就不需要操作curPage了
//					 showToast("第"+(page+1)+"页数据加载完成");
                } else {
                    Toast("没有更多数据了");
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int arg0, String arg1) {
                Toast("查询失败:" + arg1);
            }
        });
    }
}
