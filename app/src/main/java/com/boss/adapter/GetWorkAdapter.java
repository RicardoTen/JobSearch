package com.boss.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.boss.R;
import com.boss.ddb.GetWorkYear;

public class GetWorkAdapter extends BaseAdapter {
	public int getCount() {
		return listdata.size();
	}

//	private Context context;
	private LayoutInflater inflater;
	GetWorkAdapter adap;
	List<GetWorkYear> listdata;
	private GetWorkYear mIncon;

	public GetWorkAdapter(Context context, List<GetWorkYear> hfListItems) {
//		this.context = context;
		listdata = hfListItems;
		this.inflater = LayoutInflater.from(context);
	}

	public Object getItem(int position) {
		return listdata.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			// 实例化ViewHolder
			holder = new ViewHolder();
			// 获得listview的item条目xml布局文件
			convertView = inflater.inflate(
					R.layout.item_adapter_getwork,
					parent, false);
			holder.mTxt = (TextView) convertView
					.findViewById(R.id.adapter_getwork_txt);
			// 最后setTag,也就是类似保存convertView，也就是保存了item
			convertView.setTag(holder);
			 
			 

		} else {
			// 如果convertView前面保存过，这里就不为空，那么我们把item拿过来用
			holder = (ViewHolder) convertView.getTag();
		}
		mIncon = listdata.get(position);
		// 评论的内容
		String content = mIncon.getYear();
		holder.mTxt.setText(content);
		return convertView;
	}

	class ViewHolder {

		TextView mTxt;
	}

}