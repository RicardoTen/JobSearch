package com.boss.employee1;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.listener.FindListener;

import com.boss.R;
import com.boss.adapter.ConstellationAdapter;
import com.boss.adapter.GirdDropDownAdapter;
import com.boss.adapter.ListDropDownAdapter;
import com.boss.company1.AAActivity;
import com.boss.company1.UserAdapter;
import com.boss.db.User;
import com.boss.util.BossConstants;
import com.boss.util.TurnHelp;
import com.boss.view.DropDownMenu;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

//public class EmployeeFragment1 extends Fragment{
//	 
//	 
//	// 这里结束
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//
//		// 二手内容区域
//		View rootView = inflater.inflate(
//				R.layout.fragment_employee1_employeefragment1, container, false);
//
//		return rootView;
//
//	}
//
//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
//		super.onActivityCreated(savedInstanceState);
// 
//	}

public class EmployeeFragment1 extends Fragment implements OnClickListener {

	private ListView mlist;
	UserAdapter adapter1;
	List<User> mListShow1;

	View rootView;

	/** 点击重新加载 */
	private LinearLayout mReStart;
	/** 数据加载失败 */
	private TextView mDataFail;

	DropDownMenu dropDownMenu;

	private String headers[] = {"性别","学历", "期望行业", "需求"};

	private String test[] = {"全部","男", "女"};
	private String test1[] = {"全部","本科", "硕士", "博士"};
	private String test2[] =  BossConstants.CHOOSE_CHILD_HANGYE;

	/**
	 * "学历"
	 */
	private String test3[] = {"全部", "本科","硕士","博士"};
	/**
	 * "项目经验"
	 */
	private String test4[] = { "全部","有","无"};
	/**
	 * 薪资
	 */
	private String test5[] = BossConstants.CHOOSE_Money;
	/**
	 * 筛选条件
	 */
  	private String property[] = BossConstants.USER_CHOOSE_PROPERTY;
	//private String tables[] = {"1"};

	private List<View> popviews = new ArrayList<>();

	private View converView;

	private ListDropDownAdapter ageAdapter;
	GirdDropDownAdapter cityAdapter;
	ListDropDownAdapter sexAdapter;

	ConstellationAdapter constellationAdapter;
	ConstellationAdapter constellationAdapter1;
	ConstellationAdapter constellationAdapter2;

	private int constellationPosition = 0;
	private int constellationPosition1 = 0;
	private int constellationPosition2 = 0;

	private int testPotion = 0;
	private int testPotion1 = 0;
	private int testPotion2 = 0;


	/*SwipeRefreshLayout swipeRefreshLayout;
	RecyclerView recyclerView;*/

	private PullToRefreshLayout pullToRefreshLayout;

	private static final int STATE_REFRESH = 0;// 下拉刷新
	private static final int STATE_MORE = 1;// 加载更多

	private int limit = 10; // 每页的数据是10条
	private int curPage = 0; // 当前页的编号，从0开始

	String lastTime = null;



	// 这里结束
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 二手内容区域
		rootView = inflater.inflate(
				R.layout.fragment_company1_companyfragment1, container, false);
		/*mlist = (ListView) rootView
				.findViewById(R.id.company1_companyfragment1);*/
		mReStart = (LinearLayout) rootView
				.findViewById(R.id.include_shujujiazai);
		mReStart.setOnClickListener(this);
		mDataFail = (TextView) rootView
				.findViewById(R.id.include_shujujiazai_txt);

		//initSwipeView();

		initDropDownView();


		return rootView;

	}



/*	private void initSwipeView() {
		swipeRefreshLayout = new SwipeRefreshLayout(getActivity());
		recyclerView = new RecyclerView(getActivity());
		LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		para.setMargins(10, 10, 10, 10);
		recyclerView.setLayoutParams(para);
		LinearLayout.LayoutParams paraSwipeView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		swipeRefreshLayout.setLayoutParams(paraSwipeView);
		swipeRefreshLayout.addView(recyclerView);



	}*/


	private void initDropDownView() {

		dropDownMenu = ButterKnife.findById(rootView, R.id.dropDownMenu);
		//init age menu
		final ListView ageView = new ListView(getActivity());
		ageView.setDividerHeight(0);
		ageAdapter = new ListDropDownAdapter(getActivity(), Arrays.asList(test));
		ageView.setAdapter(ageAdapter);



		//init city menu
		final ListView cityView = new ListView(getActivity());
		 cityAdapter = new GirdDropDownAdapter(getActivity(), Arrays.asList(test1));
		cityView.setDividerHeight(0);
		cityView.setAdapter(cityAdapter);

		//init sex menu
		final ListView sexView = new ListView(getActivity());
		sexView.setDividerHeight(0);
		 sexAdapter = new ListDropDownAdapter(getActivity(), Arrays.asList(test2));
		sexView.setAdapter(sexAdapter);

		//init constellation

		final View constellationView = getActivity().getLayoutInflater().inflate(R.layout.custom_layout, null);
		Button btnReset = (Button) constellationView.findViewById(R.id.ok);
		Button btnConfirm = (Button) constellationView.findViewById(R.id.ok1);

		TextView tvTitle = (TextView) constellationView.findViewById(R.id.tv_choose_title_0);
		TextView tvTitle1 = (TextView) constellationView.findViewById(R.id.tv_choose_title_1);
		TextView tvTitle2 = (TextView) constellationView.findViewById(R.id.tv_choose_title_2);
		tvTitle.setText("学历");
		tvTitle1.setText("项目经验");
		tvTitle2.setText("薪资");
		//GridView constellation = ButterKnife.findById(constellationView, R.id.constellation);
		GridView constellation = (GridView) constellationView.findViewById(R.id.constellation);
		GridView constellation1 = (GridView) constellationView.findViewById(R.id.constellation1);
		GridView constellation2 = (GridView) constellationView.findViewById(R.id.constellation2);

		constellationAdapter = new ConstellationAdapter(getActivity(), Arrays.asList(test3));
		constellation.setAdapter(constellationAdapter);

		constellationAdapter1= new ConstellationAdapter(getActivity(), Arrays.asList(test4));
		constellation1.setAdapter(constellationAdapter1);

		constellationAdapter2= new ConstellationAdapter(getActivity(), Arrays.asList(test5));
		constellation2.setAdapter(constellationAdapter2);

		//add item click event
		cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				cityAdapter.setCheckItem(position);
				dropDownMenu.setTabText(position == 0 ? headers[1] : test1[position]);
				testPotion1 = position;
				dropDownMenu.closeMenu();
				queryData(0, STATE_REFRESH);
			}
		});

		ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ageAdapter.setCheckItem(position);
				dropDownMenu.setTabText(position == 0 ? headers[0] : test[position]);
				testPotion = position;
				dropDownMenu.closeMenu();
				queryData(0, STATE_REFRESH);
			}
		});

		sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				sexAdapter.setCheckItem(position);
				dropDownMenu.setTabText(position == 0 ? headers[2] : test2[position]);
				testPotion2 = position;
				dropDownMenu.closeMenu();
				queryData(0, STATE_REFRESH);
			}
		});

		constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				constellationAdapter.setCheckItem(position);
				constellationPosition = position;
			}
		});


		constellation1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				constellationAdapter1.setCheckItem(position);
				constellationPosition1 = position;
			}
		});

		constellation2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				constellationAdapter2.setCheckItem(position);
				constellationPosition2 = position;
			}
		});


		btnReset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				constellationPosition2 = 0;
				constellationPosition1 = 0;
				constellationPosition = 0;

				constellationAdapter2.setCheckItem(constellationPosition2);
				constellationAdapter1.setCheckItem(constellationPosition1);
				constellationAdapter.setCheckItem(constellationPosition);
			}
		});

		btnConfirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dropDownMenu.closeMenu();
				queryData(0, STATE_REFRESH);
			}
		});


		popviews.add(ageView);
		popviews.add(cityView);
		popviews.add(sexView);
		popviews.add(constellationView);



		mlist = new ListView(getActivity());
		LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		para.setMargins(10, 10, 10, 10);
		mlist.setLayoutParams(para);

		mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				View mItemView = view;
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
						&& getActivity().checkSelfPermission(Settings.ACTION_MANAGE_OVERLAY_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
					requestAlertWindowPermission();
				} else {

					/*Intent intent = new Intent(context, AAActivity.class);
					intent.putExtra("us", (Serializable) listdata);
					intent.putExtra("position", position);
					context.startActivity(intent);*/

					Bundle bundle = new Bundle();
					bundle.putInt("position", position);

					List<User> users = new ArrayList<>();
					users.add((User)parent.getItemAtPosition(position));
					bundle.putSerializable("us", (Serializable)users );

					TurnHelp.turn(getActivity(), getActivity().findViewById(android.R.id.content), view, bundle, new AAActivity());
				}

			}
		});

		LinearLayout.LayoutParams paraRefleshView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		pullToRefreshLayout = new PullToRefreshLayout(getActivity());
		pullToRefreshLayout.setLayoutParams(paraRefleshView);

		pullToRefreshLayout.addView(mlist);

		pullToRefreshLayout.setRefreshListener(new BaseRefreshListener() {
			@Override
			public void refresh() {
				queryData(0, STATE_REFRESH);
			}

			@Override
			public void loadMore() {
				queryData(curPage, STATE_MORE);
			}
		});

		dropDownMenu.setDropDownMenu(Arrays.asList(headers), popviews, pullToRefreshLayout);


	}



	private void requestAlertWindowPermission() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
			intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
			startActivityForResult(intent, 1);
		}
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

		queryData(0, STATE_REFRESH);

		//mlist.setVisibility(View.GONE);

	}

	/*private void query() {
		mDataFail.setText("数据加载中");

		BmobQuery<User> query = new BmobQuery<User>();
		query.findObjects(getActivity(), new FindListener<User>() {
			@Override
			public void onSuccess(List<User> list) {
				mListShow1.addAll(list);
				adapter1.notifyDataSetChanged();
				mlist.setVisibility(View.VISIBLE);
				mReStart.setVisibility(View.GONE);

				pullToRefreshLayout.finishRefresh();
				pullToRefreshLayout.finishLoadMore();
			}

			@Override
			public void onError(int i, String s) {
				mDataFail.setText("数据加载失败，点击重试");
				pullToRefreshLayout.finishRefresh();
				pullToRefreshLayout.finishLoadMore();
			}
	*//*		@Override
			public void done(List<User> object, BmobException e) {
				if (e == null) {
					mListShow1.addAll(object);
					adapter1.notifyDataSetChanged();
					mlist.setVisibility(View.VISIBLE);
					mReStart.setVisibility(View.GONE);
				} else {
					mDataFail.setText("数据加载失败，点击重试");
				}
			}*//*


		});
	}*/

	public void refleshComplete(){
		pullToRefreshLayout.finishRefresh();
		pullToRefreshLayout.finishLoadMore();
	}


	/**
	 * 分页获取数据
	 *
	 * @param page
	 *            页码
	 * @param actionType
	 *            ListView的操作类型（下拉刷新、上拉加载更多）
	 */
	private void queryData(int page, final int actionType) {

		mDataFail.setText("数据加载中");

		BmobQuery<User> query = new BmobQuery<>();
		// 按时间降序查询
		query.order("-createdAt");
		int count = limit;

		if(!test[testPotion].equals("全部")){
			query.addWhereEqualTo(property[0], test[testPotion].equals("男")?true:false);
		}

		if(!test1[testPotion1].equals("全部")){
			query.addWhereEqualTo(property[1], test1[testPotion1]);
		}

		if(!test2[testPotion2].equals("全部")){
			query.addWhereEqualTo(property[2], test2[testPotion2]);
		}

		if(!test3[constellationPosition].equals("全部")){
			query.addWhereEqualTo(property[3], test3[constellationPosition]);
		}

/*		if(!test5[constellationPosition].equals("全部")){
			query.addWhereEqualTo(property[3], test3[constellationPosition]);
		}*/


		/*query = addQuery(query, property[0], test[testPotion]);
		query = addQuery(query, property[1], test1[testPotion1]);
		query = addQuery(query, property[2], test2[testPotion2]);
		query = addQuery(query, property[3], test3[constellationPosition]);
		query = addQuery(query, property[5], test4[constellationPosition1]);
		query = addQuery(query, property[6], test4[constellationPosition2]);*/

		// 如果是加载更多
		if (actionType == STATE_MORE) {
			// 处理时间查询
			Date date = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				date = sdf.parse(lastTime);
				//Log.i("0414", date.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// 只查询小于等于最后一个item发表时间的数据
			//query.addWhereLessThanOrEqualTo("createdAt", new BmobDate(date));
			// 跳过之前页数并去掉重复数据
			query.setSkip(page * count + 1);
		} else {
			// 下拉刷新
			page = 0;
			query.setSkip(page);
		}
		// 设置每页数据个数
		query.setLimit(limit);
		// 查找数据
		query.findObjects(getActivity(), new FindListener<User>() {
			@Override
			public void onSuccess(List<User> list) {
				if (list.size() > 0) {
					if (actionType == STATE_REFRESH) {
						// 当是下拉刷新操作时，将当前页的编号重置为0，并把bankCards清空，重新添加
						curPage = 0;
						mListShow1.clear();
						// 获取最后时间
						lastTime = list.get(list.size() - 1).getCreatedAt();
					}

					// 将本次查询的数据添加到bankCards中
					for (User td : list) {
						mListShow1.add(td);
					}

					// 这里在每次加载完数据后，将当前页码+1，这样在上拉刷新的onPullUpToRefresh方法中就不需要操作curPage了
					curPage++;
//					 showToast("第"+(page+1)+"页数据加载完成");
				} else if (actionType == STATE_MORE) {

					showToast("没有更多数据了");
				} else if (actionType == STATE_REFRESH) {
					mListShow1.clear();
					showToast("没有数据");
				}
				adapter1.notifyDataSetChanged();
				mlist.setVisibility(View.VISIBLE);
				mReStart.setVisibility(View.GONE);
				refleshComplete();
			}

			@Override
			public void onError(int arg0, String arg1) {
				showToast("查询失败:" + arg1);
				mDataFail.setText("数据加载失败，点击重试");
				refleshComplete();
			}
		});

	}

	private BmobQuery addQuery(BmobQuery query, String property, String content){
		if(!content.equals("全部")){
			query.addWhereEqualTo(property,content);
		}
		return query;
	}


	private void showToast(String msg) {
		Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
	}


	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.include_shujujiazai:
			if (mDataFail.getText().toString() == "数据加载失败，点击重试") {
				queryData(0, STATE_REFRESH);
			} else {

			}
			break;

		default:
			break;
		}
	}
}
