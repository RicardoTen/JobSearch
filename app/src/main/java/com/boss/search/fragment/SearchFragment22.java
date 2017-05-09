package com.boss.search.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boss.R;

/**
 * Created by 滕新科 on 2017/4/22.
 */

public class SearchFragment22 extends Fragment{

    // 这里结束
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 二手内容区域
        View rootView = inflater.inflate(
                R.layout.fragment_employee2_employeefragment2, container, false);

        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
