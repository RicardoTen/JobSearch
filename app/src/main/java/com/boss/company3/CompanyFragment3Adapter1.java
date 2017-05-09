package com.boss.company3;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.boss.R;

public class CompanyFragment3Adapter1 extends BaseAdapter {
	public int getCount() {
		return listdata.size();
	}

//	private Context context;
	private LayoutInflater inflater;
	CompanyFragment3Adapter1 adap;
	List<Show1> listdata;
	private Show1 mShow1;

	public CompanyFragment3Adapter1(Context context, List<Show1> hfListItems) {
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
					R.layout.item_company3_companyfragment3adapter1, parent,
					false);
			holder.mNumber = (TextView) convertView
					.findViewById(R.id.company3_companyfragment3adapter1_number);
			holder.mContent = (TextView) convertView
					.findViewById(R.id.company3_companyfragment3adapter1_content);
			// 最后setTag,也就是类似保存convertView，也就是保存了item
			convertView.setTag(holder);
		} else {
			// 如果convertView前面保存过，这里就不为空，那么我们把item拿过来用
			holder = (ViewHolder) convertView.getTag();
		}
		mShow1 = listdata.get(position);
		// 评论的内容
		int number = mShow1.getData_int();
		holder.mNumber.setText(number + "");
		String content = mShow1.getData_string();
		holder.mContent.setText(content);

		return convertView;
	}

	class ViewHolder {

		TextView mNumber;
		TextView mContent;
	}

}