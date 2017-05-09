package com.boss.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.boss.R;
import com.boss.adapter.SetWorkTypeAdapter;
import com.boss.ddb.WorkType;
import com.boss.login.BaseActivity;
import com.boss.util.BossConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 滕新科 on 2017/4/24.
 */

public class setCompanyTypeActivity  extends BaseActivity implements
        View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_company_flag);
        findViewById();
        iniyListView1();
        initActionBar();
    }



    // 记录用户选择的职业类型
    private String mWorkType = "";
    private ListView mWorkTypeList;

    private void findViewById() {
        mWorkTypeList = (ListView) findViewById(R.id.register_setworktype_listview);

    }

    private void initActionBar() {
        TextView left = (TextView) findViewById(R.id.actionbar_left);
        left.setText(R.string.zuojiantou);
        left.setOnClickListener(this);
        TextView center = (TextView) findViewById(R.id.actionbar_center);
        center.setText("选择公司类型");
    }

    /**
     * 初始化第一个List
     */
    private void iniyListView1() {
        List<WorkType> mListShow3 = new ArrayList<WorkType>();
        for (int i = 0; i < BossConstants.HANG_YE.length; i++) {
            WorkType workType = new WorkType();
            workType.setWorkType(BossConstants.HANG_YE[i]);
            mListShow3.add(workType);
        }

        SetWorkTypeAdapter adapter1 = new SetWorkTypeAdapter(this, mListShow3);
        mWorkTypeList.setAdapter(adapter1);

        mWorkTypeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                mWorkType = BossConstants.HANG_YE[arg2];
                Intent intent = new Intent();
                intent.putExtra("data_return", mWorkType);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.actionbar_left:// 返回键
                finish();
                break;
            default:
                break;
        }

    }
}
