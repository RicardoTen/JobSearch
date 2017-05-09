package com.boss.search.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.boss.R;
import com.boss.db.User;
import com.boss.im.holder.OnRecyclerViewListener;
import com.boss.im.holder.SearchUserAdapter;
import com.boss.im.model.BaseModel;
import com.boss.im.model.UserModel;
import com.boss.im.ui.UserInfoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.listener.FindListener;

public class SearchFragment3 extends Fragment{

	View rootView;

	@BindView(R.id.et_find_name)
	EditText et_find_name;
	@BindView(R.id.sw_refresh)
	SwipeRefreshLayout sw_refresh;
	@BindView(R.id.btn_search)
	Button btn_search;
	@BindView(R.id.rc_view)
	RecyclerView rc_view;
	@BindView(R.id.layout_serach_box)
	LinearLayout layoutSearchBox;
	LinearLayoutManager layoutManager;
	SearchUserAdapter adapter;

	String name;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(
				R.layout.activity_search_user, container, false);
		ButterKnife.bind(this, rootView);

		adapter =new SearchUserAdapter();
		layoutManager = new LinearLayoutManager(getActivity());
		rc_view.setLayoutManager(layoutManager);
		rc_view.setAdapter(adapter);
		sw_refresh.setEnabled(true);
		sw_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				query();
			}
		});
		adapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {
			@Override
			public void onItemClick(int position) {
				Bundle bundle = new Bundle();
				User user = adapter.getItem(position);
				bundle.putSerializable("u", user);
				startActivity(UserInfoActivity.class, bundle, false);
			}

			@Override
			public boolean onItemLongClick(int position) {
				return true;
			}

			@Override
			public void onAvatarClick(int position) {

			}
		});

		layoutSearchBox.setVisibility(View.GONE);
		name = getActivity().getIntent().getStringExtra("content");
		query();


		return rootView;
	}


	@OnClick(R.id.btn_search)
	public void onSearchClick(View view){
		sw_refresh.setRefreshing(true);
		query();
	}

	public void query(){
/*		String name =et_find_name.getText().toString();
		if(TextUtils.isEmpty(name)){
			toast("请填写用户名");
			sw_refresh.setRefreshing(false);
			return;
		}*/
		UserModel.getInstance().queryUsers(name, BaseModel.DEFAULT_LIMIT, new FindListener<User>() {
			@Override
			public void onSuccess(List<User> list) {
				sw_refresh.setRefreshing(false);
				adapter.setDatas(list);
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onError(int i, String s) {
				sw_refresh.setRefreshing(false);
				adapter.setDatas(null);
				adapter.notifyDataSetChanged();
				Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
			}
		});
	}



	public void startActivity(Class<? extends Activity> target, Bundle bundle, boolean finish) {
		Intent intent = new Intent();
		intent.setClass(getActivity(), target);
		if (bundle != null)
			intent.putExtra(getActivity().getPackageName(), bundle);
		startActivity(intent);
		if (finish)
			getActivity().finish();
	}
 

}
