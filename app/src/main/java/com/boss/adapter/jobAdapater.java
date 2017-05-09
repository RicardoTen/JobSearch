package com.boss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.boss.R;
import com.boss.db.Job;

import java.util.List;

/**
 * Created by 滕新科 on 2017/4/22.
 */

public class jobAdapater extends BaseAdapter {

    public int getCount() {
        return listdata.size();
    }

    private Context context;
    private LayoutInflater inflater;
    jobAdapater adap;
    List<Job> listdata;
    Job job;

    public jobAdapater(Context context, List<Job> hfListItems) {
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
        final jobAdapater.ViewHolder holder;
        if (convertView == null) {
            // 实例化ViewHolder
            holder = new jobAdapater.ViewHolder();
            // 获得listview的item条目xml布局文件oww
            convertView = inflater.inflate(
                    R.layout.item_job, parent, false);
            holder.jobBossName = (TextView) convertView
                    .findViewById(R.id.tv_job_boss_name);
            holder.jobCompanyName = (TextView) convertView
                    .findViewById(R.id.tv_job_compny_name);
            holder.jobCompanyPoi = (TextView) convertView
                    .findViewById(R.id.tv_job_company_poi);
            holder.jobName = (TextView) convertView
                    .findViewById(R.id.tv_job_name);
            holder.jobOfferMoney = (TextView) convertView
                    .findViewById(R.id.tv_job_offer);
            holder.jobRequestEdu = (TextView) convertView
                    .findViewById(R.id.tv_job_request_edu);
            holder.jobRequestExp = (TextView) convertView
                    .findViewById(R.id.tv_job_request_exp);


            // 最后setTag,也就是类似保存convertView，也就是保存了item
            convertView.setTag(holder);

        } else {
            // 如果convertView前面保存过，这里就不为空，那么我们把item拿过来用
            holder = (jobAdapater.ViewHolder) convertView.getTag();
        }
        job = listdata.get(position);

        setValue( holder.jobName, job.getJobName());
        setValue( holder.jobOfferMoney, job.getJobOfferMoney());
        setValue( holder.jobCompanyName, job.getJobCompany());
        setValue( holder.jobCompanyPoi, job.getJobCompanyPoi());
        setValue( holder.jobRequestExp, job.getJobRequestExp());
        setValue( holder.jobRequestEdu, job.getJobRequestGraduate());
        setValue( holder.jobBossName, job.getJobBossName());

        return convertView;
    }

    private void setValue(TextView tv, String content){
        if(content != null){
            tv.setText(content);
        }
    }

    class ViewHolder {
        /** 职位名称 */
        TextView jobName;
        /** 职位薪资 */
        TextView jobOfferMoney;
        /**公司名字*/
        TextView jobCompanyName;
        /**公司地点*/
        TextView jobCompanyPoi;
        /**职位要求经验*/
        TextView jobRequestExp;
        /**位置要求学历*/
        TextView jobRequestEdu;
        /**BOSS名字*/
        TextView jobBossName;

    }

}
