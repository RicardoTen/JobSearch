package com.boss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.boss.R;
import com.boss.db.Company;
import com.boss.im.base.ImageLoaderFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 滕新科 on 2017/4/23.
 */

public class CompanyAdapter extends BaseAdapter {

    public int getCount() {
        return listdata.size();
    }

    private Context context;
    private LayoutInflater inflater;
    CompanyAdapter adap;
    List<Company> listdata;
    Company company;

    public CompanyAdapter(Context context, List<Company> hfListItems) {
        this.context = context;
        listdata = hfListItems;
        this.inflater = LayoutInflater.from(context);
    }

    public Object getItem(int position) {
        return listdata.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            // 获得listview的item条目xml布局文件oww
            convertView = inflater.inflate(
                    R.layout.item_company, parent, false);
            holder = new ViewHolder(convertView);
            // 最后setTag,也就是类似保存convertView，也就是保存了item
            convertView.setTag(holder);
        } else {
            // 如果convertView前面保存过，这里就不为空，那么我们把item拿过来用
            holder = (CompanyAdapter.ViewHolder) convertView.getTag();
        }
        company = listdata.get(position);

        ImageLoaderFactory.getLoader().loadAvator(holder.imgCompanyAvtar, company.getComanyAvtar(), R.drawable.ic_launcher);

        setValue(holder.tvCompanyName, company.getCompanyName());
        setValue(holder.tvCompanyPoi, company.getComanyPoi());
        setValue(holder.tvCompanyFlag, company.getComanyFlag());
        setValue(holder.tvCompanyMemNum, company.getCompanyMemberNum());

        return convertView;
    }

    private void setValue(TextView tv, String content) {
        if (content != null) {
            tv.setText(content);
        }
    }

    static class ViewHolder {
        @BindView(R.id.img_company_avtar)
        ImageView imgCompanyAvtar;
        @BindView(R.id.tv_company_name)
        TextView tvCompanyName;
        @BindView(R.id.tv_company_poi)
        TextView tvCompanyPoi;
        @BindView(R.id.item_company1_companyfragment1_state)
        TextView itemCompany1Companyfragment1State;
        @BindView(R.id.item_company1_companyfragment1_company)
        TextView itemCompany1Companyfragment1Company;
        @BindView(R.id.tv_company_flag)
        TextView tvCompanyFlag;
        @BindView(R.id.tv_company_mem_num)
        TextView tvCompanyMemNum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
