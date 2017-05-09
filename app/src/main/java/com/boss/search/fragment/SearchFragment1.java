package com.boss.search.fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import com.boss.R;
import com.boss.adapter.ConstellationAdapter;
import com.boss.adapter.GirdDropDownAdapter;
import com.boss.adapter.ListDropDownAdapter;
import com.boss.company1.AAActivity;
import com.boss.company1.UserAdapter;
import com.boss.db.User;
import com.boss.util.TurnHelp;
import com.boss.view.DropDownMenu;

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

public class SearchFragment1 extends Fragment implements OnClickListener {

    private ListView mlist;
    UserAdapter adapter1;
    List<User> mListShow1;

    View rootView;

    /** 点击重新加载 */
    private LinearLayout mReStart;
    /** 数据加载失败 */
    private TextView mDataFail;




    DropDownMenu dropDownMenu;

    private String headers[] = {"选择年龄","选择地区", "测试1", "测试3"};

    private String ages[] = {"选择年龄", "20", "18-22", "23-26", "27-33", "35"};
    private String test1[] = {"选择年龄", "20", "18-22", "23-26", "27-33", "35"};
    private String test2[] = {"选择年龄", "20", "18-22", "23-26", "27-33", "35"};
    private String test3[] = {"选择年龄", "20", "18-22", "23-26", "27-33", "35"};
    //private String tables[] = {"1"};

    private List<View> popviews = new ArrayList<>();

    private View converView;

    private ListDropDownAdapter ageAdapter;
    GirdDropDownAdapter cityAdapter;
    ListDropDownAdapter sexAdapter;
    ConstellationAdapter constellationAdapter;

    private int constellationPosition = 0;

    String content = "";



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

        content = getActivity().getIntent().getStringExtra("content");

        initDropDownView();
        return rootView;

    }



    private void initDropDownView() {

        dropDownMenu = ButterKnife.findById(rootView, R.id.dropDownMenu);
        //init age menu
        final ListView ageView = new ListView(getActivity());
        ageView.setDividerHeight(0);
        ageAdapter = new ListDropDownAdapter(getActivity(), Arrays.asList(ages));
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
        //GridView constellation = ButterKnife.findById(constellationView, R.id.constellation);
        GridView constellation = (GridView) constellationView.findViewById(R.id.constellation);

        constellationAdapter = new ConstellationAdapter(getActivity(), Arrays.asList(test3));
        constellation.setAdapter(constellationAdapter);



        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[0] : test1[position]);
                dropDownMenu.closeMenu();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[1] : ages[position]);
                dropDownMenu.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[2] : test2[position]);
                dropDownMenu.closeMenu();
            }
        });

        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                constellationAdapter.setCheckItem(position);
                constellationPosition = position;
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

        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popviews, mlist);

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
        query();
        //mlist.setVisibility(View.GONE);

    }

    private void query() {
        mDataFail.setText("数据加载中");

        BmobQuery<User> query = new BmobQuery<User>();
       if(!TextUtils.isEmpty(content)){
            BmobQuery<User> eq1 = new BmobQuery<User>();
            eq1.addWhereEqualTo("nick",content);
            BmobQuery<User> eq2 = new BmobQuery<User>();
            eq2.addWhereEqualTo("username",content);
            List<BmobQuery<User>> queries = new ArrayList<BmobQuery<User>>();
            queries.add(eq1);
            queries.add(eq2);
            query.or(queries);
        }

        //query.addWhereContains("username","152");

        query.findObjects(getActivity(), new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                mListShow1.addAll(list);
                adapter1.notifyDataSetChanged();
                mlist.setVisibility(View.VISIBLE);
                mReStart.setVisibility(View.GONE);
            }

            @Override
            public void onError(int i, String s) {
                mDataFail.setText("数据加载失败，点击重试");
                Toast.makeText(getActivity(),"s", Toast.LENGTH_SHORT).show();
            }
	/*		@Override
			public void done(List<User> object, BmobException e) {
				if (e == null) {
					mListShow1.addAll(object);
					adapter1.notifyDataSetChanged();
					mlist.setVisibility(View.VISIBLE);
					mReStart.setVisibility(View.GONE);
				} else {
					mDataFail.setText("数据加载失败，点击重试");
				}
			}*/


        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.include_shujujiazai:
                if (mDataFail.getText().toString() == "数据加载失败，点击重试") {
                    query();
                } else {

                }
                break;

            default:
                break;
        }
    }
}
