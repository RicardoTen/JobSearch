package com.boss.company1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import com.boss.R;
import com.boss.adapter.ListDropDownAdapter;
import com.boss.db.User;
import com.boss.view.DropDownMenu;

public class CompanyFragment1 extends Fragment {

	private ListView mlist;
	UserAdapter adapter1;
	List<User> mListShow1;

	@BindView(R.id.dropDownMenu)
	DropDownMenu dropDownMenu;

	private String headers[] = {"1", "2", "3", "4"};
	private String ages[] = {"10", "20�", "18-22宀�", "23-26宀�", "27-35宀�", "35宀佷互涓�"};
	private String tables[] = {"1"};

	private List<View> popviews;

	private View converView;

	private ListDropDownAdapter ageAdapter;

	// 这里结束
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// 二手内容区域
		View rootView = inflater.inflate(
				R.layout.fragment_company1_companyfragment1, container, false);
		mlist = (ListView) rootView
				.findViewById(R.id.company1_companyfragment1);

		initDropDownView();

		return rootView;

	}

	private void initDropDownView() {


		//init age menu
		final ListView ageView = new ListView(getActivity());
		ageView.setDividerHeight(0);
		ageAdapter = new ListDropDownAdapter(getActivity(), Arrays.asList(ages));
		ageView.setAdapter(ageAdapter);

		popviews.add(ageView);


		//init context view
		TextView contentView = new TextView(getActivity());
		contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		contentView.setText("这里是内容");
		contentView.setGravity(Gravity.CENTER);
		contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);


		dropDownMenu.setDropDownMenu(Arrays.asList(tables), popviews, contentView);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initGridView1();

	}

	/**
	 * 初始化第一个GridView
	 */
	private void initGridView1() {
		// 获取到GridView
		// TODO 动态获取数据
		mListShow1 = new ArrayList<User>();
		adapter1 = new UserAdapter(getActivity(), mListShow1);
		mlist.setAdapter(adapter1);
		query();

	}

	private void query(){
		BmobQuery<User> query = new BmobQuery<User>();

		query.findObjects(getActivity(), new FindListener<User>() {
/*		    @Override
		    public void done(List<User> object,BmobException e) {
		        if(e==null){
		        	mListShow1.addAll(object);
		        	adapter1.notifyDataSetChanged();
		        }else{
		        }
		    }*/
			@Override
			public void onSuccess(List<User> list) {
				mListShow1.addAll(list);
				adapter1.notifyDataSetChanged();
			}

			@Override
			public void onError(int i, String s) {

			}
		});
	}
}
