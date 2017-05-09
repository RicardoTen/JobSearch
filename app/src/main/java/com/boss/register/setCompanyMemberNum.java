package com.boss.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.boss.R;
import com.boss.adapter.SetWorkSkillAdapter;
import com.boss.ddb.SetWorkSkill;
import com.boss.util.BossConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 滕新科 on 2017/4/24.
 */

public class setCompanyMemberNum extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_company_number);
        findViewById();
        iniyListView1();
        initActionBar();

    }

    private GridView mGridView;
    private void findViewById() {
        mGridView = (GridView)findViewById(R.id.register_setworkskill);

    }

    private void initActionBar() {
        TextView left = (TextView) findViewById(R.id.actionbar_left);
        left.setText(R.string.cha);
        left.setOnClickListener(this);
        TextView center = (TextView) findViewById(R.id.actionbar_center);
        center.setText("公司规模");
        TextView right = (TextView) findViewById(R.id.actionbar_right);
        right.setText(R.string.gou);
        right.setOnClickListener(this);

    }

    /**
     * 初始化第一个List
     */
    private void iniyListView1() {
        List<SetWorkSkill> mListSkill = new ArrayList<SetWorkSkill>();
        for (int i = 0; i < BossConstants.COMPANY_MEMBER.length; i++) {
            SetWorkSkill workSkill = new SetWorkSkill();
            workSkill.setSkill(BossConstants.COMPANY_MEMBER[i]);
            mListSkill.add(workSkill);
        }


        SetWorkSkillAdapter adapter1 = new SetWorkSkillAdapter(this, mListSkill);
        mGridView.setAdapter(adapter1);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                String mWorkSkill=BossConstants.COMPANY_MEMBER[arg2];
                Intent intent = new Intent();
                intent.putExtra("data_return", mWorkSkill);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionbar_left://返回
                finish();
                break;

            default:
                break;
        }

    }
}
