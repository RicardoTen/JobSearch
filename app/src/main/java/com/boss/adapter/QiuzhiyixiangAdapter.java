package com.boss.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.boss.R;
import com.boss.db.User;
import com.boss.employee4.in.editdata.qiuzhiyixiang.AddExpectWorkChangeActivity;

public class QiuzhiyixiangAdapter extends BaseAdapter {
	public int getCount() {
		return listdata.size();
	}

	private Context context;
	private LayoutInflater inflater;
	QiuzhiyixiangAdapter adap;
	List<User> listdata;
	private User mQiuzhiyixiangList;

	public QiuzhiyixiangAdapter(Context context, List<User> hfListItems) {
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
			// 实例化ViewHolder
			holder = new ViewHolder();
			// 获得listview的item条目xml布局文件
			convertView = inflater.inflate(R.layout.item_adapter_qiuzhiyixiang,
					parent, false);
			holder.mCity = (TextView) convertView
					.findViewById(R.id.item_adapter_qiuzhiyixiang_city);
			holder.mZhiwei = (TextView) convertView
					.findViewById(R.id.item_adapter_qiuzhiyixiang_zhiwei);
			holder.mXinzi = (TextView) convertView
					.findViewById(R.id.item_adapter_qiuzhiyixiang_xinzi);
			holder.mHangye = (TextView) convertView
					.findViewById(R.id.item_adapter_qiuzhiyixiang_hangye);
			// 最后setTag,也就是类似保存convertView，也就是保存了item
			convertView.setTag(holder);
			convertView.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					Intent intent = new Intent(context,
							AddExpectWorkChangeActivity.class);
					intent.putExtra("begin", listdata.get(position).getXinziBegin());
					intent.putExtra("done", listdata.get(position).getXinziDone());
					intent.putExtra("hangye", listdata.get(position).getHangye());
					intent.putExtra("zhiwei", listdata.get(position).getZhiwei());
					intent.putExtra("city", listdata.get(position).getCity());
					intent.putExtra("objectid", listdata.get(position).getObjectId());
					context.startActivity(intent);
				 

				}
			});

		} else {
			// 如果convertView前面保存过，这里就不为空，那么我们把item拿过来用
			holder = (ViewHolder) convertView.getTag();
		}
		mQiuzhiyixiangList = listdata.get(position);
		// 评论的内容
		String city = mQiuzhiyixiangList.getCity();
		holder.mCity.setText("[" + city + "]");
		String hangye = mQiuzhiyixiangList.getHangye();
		holder.mHangye.setText(hangye);
		String xinzi = mQiuzhiyixiangList.getXinziBegin() + "-"
				+ mQiuzhiyixiangList.getXinziDone();
		holder.mXinzi.setText(xinzi);
		String zhiwei = mQiuzhiyixiangList.getZhiwei();
		holder.mZhiwei.setText(zhiwei);
		return convertView;
	}

	class ViewHolder {

		TextView mCity;
		TextView mZhiwei;
		TextView mXinzi;
		TextView mHangye;
	}

}