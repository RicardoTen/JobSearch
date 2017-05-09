package com.boss.company1;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class MyPageAdapter extends PagerAdapter {
	private List<View> list_view;

	public MyPageAdapter(List<View> list_view) {
		this.list_view = list_view;
	}

	@Override
	public int getCount() {
		return list_view.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

/*	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		int index = position % list_view.size();
		View view = list_view.get(index);
		Log.e("MyPageAdapter","list_view.size():" + list_view.size() + "position:" + position);

		if(view.getParent() != null){
			ViewParent vp =view.getParent();
			ViewGroup parent = (ViewGroup)vp;
			parent.removeView(view);
		}
		container.addView(view);
		return view;
	}*/


	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		//对ViewPager页号求模取出View列表中要显示的项
		/*position %= list_view.size();
		if (position<0){
			position = list_view.size()+position;
		}
		View view = list_view.get(position);
		//如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
		ViewParent vp =view.getParent();
		if (vp!=null){
			ViewGroup parent = (ViewGroup)vp;
			parent.removeView(view);
		}*/
/*		if(position >= list_view.size()){
			return null;
		}*/

		View view = list_view.get(position);
		container.addView(view);
		//add listeners here if necessary
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
	/*	View view = list_view.get(position % list_view.size());
		container.removeView(view);*/

		container.removeView(list_view.get(position));
	}
}