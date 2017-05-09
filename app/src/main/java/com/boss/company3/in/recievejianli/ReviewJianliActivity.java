package com.boss.company3.in.recievejianli;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.boss.BossApplication;
import com.boss.R;
import com.boss.company1.MyPageAdapter;
import com.boss.db.RecieveJianLi;
import com.boss.db.User;
import com.boss.im.ui.ChatActivity;
import com.boss.login.BaseActivity;
import com.boss.util.BossConstants;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 滕新科 on 2017/4/26.
 */

public class ReviewJianliActivity extends BaseActivity {

    private static int count = 0;

    User user = new User();
    @BindView(R.id.btn_set_interest)
    Button btnSetInterest;

    private ViewPager list_pager;

    private List<View> list_view = new ArrayList<>();

    private MyPageAdapter adpter;
    List<User> mListShow1 = new ArrayList<>();

    List<RecieveJianLi> recieveJianLis;

    @BindView(R.id.btn_begin_chat)
    Button btnChat;

    CircleProgressDialog circleProgressDialog;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        circleProgressDialog = new CircleProgressDialog(this);

        list_pager = (ViewPager) findViewById(R.id.viewpager);
        recieveJianLis = (ArrayList<RecieveJianLi>) getIntent().getSerializableExtra("us");

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

        Log.e("ReviewJianliActivity", "position" + position);

        btnSetInterest.setVisibility(View.VISIBLE);

        list_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                RecieveJianLi recieveJianLi = recieveJianLis.get(position);
                if(recieveJianLi.getInterst()){
                    btnSetInterest.setClickable(false);
                    btnSetInterest.setText("已关注");
                }else{
                    btnSetInterest.setClickable(true);
                }
            }

            @Override
            public void onPageSelected(int position) {
/*                RecieveJianLi recieveJianLi = recieveJianLis.get(position);
                if(recieveJianLi.getInterst()){
                    btnSetInterest.setClickable(false);
                }else{
                    btnSetInterest.setClickable(true);
                }*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        getUser();
        //
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    private void getUser() {

        count = 0;
        mListShow1.clear();
        list_view.clear();
        for (int i = 0; i < recieveJianLis.size(); i++) {

            RecieveJianLi jianli = recieveJianLis.get(i);

            BmobQuery<User> query = new BmobQuery<>();
            // 按时间降序查询
            query.order("-createdAt");
            query.addWhereEqualTo("mobilePhoneNumber", jianli.getPostJianliUserPhone());

            query.findObjects(this, new FindListener<User>() {
                @Override
                public void onSuccess(List<User> list) {

                    if (list.size() > 0) {
                        user = list.get(0);
                        mListShow1.add(user);
                    } else {
                        //Toast("没有更多数据了");
                    }
                    count++;
                    if (count == recieveJianLis.size()) {
                        goMain(position);
                    }
                }

                @Override
                public void onError(int arg0, String arg1) {
                    count++;
                    if (count == recieveJianLis.size()) {
                        goMain(position);
                    }
                    Toast("查询失败:" + arg1);
                }
            });
        }

    }

    private void goMain(int position) {
        list_view = new ArrayList<>();

        // 这里只设置了4.因为在实现应用中，我们在页面加载的时候，你会根据数据的多少，而知道这个页面的数量
        // 一般情况下，我们会根据list<>或是string[]这样的数组的数量来判断要有多少页
        for (int i = 0; i < mListShow1.size(); i++) {
            View view = LayoutInflater.from(this).inflate(
                    R.layout.fragment_page, null);
            Toast.makeText(this, i + "", Toast.LENGTH_SHORT).show();


            /** 用户头像 */
            final ImageView mAvatar = (ImageView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_avatar);

            /** 昵称 */
            TextView mNick = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_nick);

            /** 公司名字 */
            TextView mCompanyName2type = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_companyname);

            /** 学历 */
            TextView mEducate = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_educate);

            /** 介绍 */
            TextView mIntroduce = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_introduce);

            /** 状态 */
            TextView mState = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_state);

            /** 期望职位 */
            TextView mExpectZhiwei = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_exceptZhiwei);

            /** 期望薪资 */
            TextView mExpectMoney = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_exceptMoney);

            /** 期望行业 */
            TextView mExceptHangye = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_exceptHangye);

            /** 期望城市 */
            TextView mExceptCity = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_exceptCity);

            /** 工作时间 */
            TextView mCompanyData = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_companyData);

            /** 工作内容 */
            TextView mContent = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_companyVontent);

            /** 工作介绍 */
            TextView mCompanyIntroduce = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_companyname1);

            /** 工作技能 */
            TextView mSkill = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_companyBiaoqian);
            /** 公司名字 */
            TextView mCompanyName = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_companynamea);
            /** 公司工作职位 */
            TextView mGongzuozhiwei = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_exceptZhiwei2);
            /** 学校名字 */
            TextView mSchoolName = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_eduschool);
            /** 学校日期 */
            TextView mSchoolData = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_schoolData);
            /** 学校专业 */
            TextView mSchoolMajor = (TextView) view.findViewById(R.id.employee4_in_editdata_yulanjianli_schoolMajor);

            // mListShow1.get(i).getObjectId();
            TextView txt_num = (TextView) view.findViewById(R.id.txt_num);
            txt_num.setText(i + "" + mListShow1.get(i).getObjectId());


            User user = mListShow1.get(i);
            // 判断
            if (user.getNormalavator() == null || user.getNormalavator() == false) {// 否则先判断头像地址是否为空，为空则用默认头像，否则使用地址头像
                String imgUrl = user.getAvator();
                if (imgUrl == null || imgUrl.isEmpty()) {

                    mAvatar.setImageResource(R.drawable.ic_launcher);
                } else {

                    ImageLoader.getInstance().displayImage(
                            imgUrl,
                            mAvatar,
                            BossApplication.getInstance().getOptions(
                                    R.drawable.ic_launcher),
                            new SimpleImageLoadingListener() {

                                @Override
                                public void onLoadingComplete(String imageUri,
                                                              View view, Bitmap loadedImage) {
                                    super.onLoadingComplete(imageUri, view,
                                            loadedImage);
                                    mAvatar.setImageBitmap(loadedImage);
                                }

                            });
                }

            } else { // 使用系统默认头像
                int drawable = BossConstants.DEFAULT_PICTURE[user
                        .getNormalavatorindex()];
                mAvatar.setImageResource(drawable);
            }


            if (user.getNick() != null) {
                mNick.setText(user.getNick());
            }
            if (user.getCompanyName() != null && user.getWorkType() != null) {
                mCompanyName2type.setText("现任：" + user.getCompanyName() + ",职位："
                        + user.getWorkType());
            }
            if (user.getCompanyName() != null) {
                mCompanyName.setText(user.getCompanyName());
            }
            if (user.getSkill() != null) {
                mSkill.setText(user.getSkill());
            }
            if (user.getDoneTime() != null && user.getBeginTime() != null) {
                mCompanyData
                        .setText(user.getBeginTime() + "-" + user.getDoneTime());

            }
            if (user.getEducation() != null && user.getEmployeeWorkyear() != null
                    && user.getXinziBegin() != null && user.getXinziDone() != null) {
                mEducate.setText(user.getEmployeeWorkyear() + "  "
                        + user.getEducation() + "  " + user.getXinziBegin() + "-"
                        + user.getXinziDone());
            }

            if (user.getIntroduce() != null) {
                mIntroduce.setText(user.getIntroduce());
            }
            if (user.getWorkState() != null) {
                mState.setText(user.getWorkState());
            }
            if (user.getZhiyemingcheng() != null) {
                mGongzuozhiwei.setText(user.getZhiyemingcheng());
            }
            if (user.getZhiwei() != null) {
                mExpectZhiwei.setText(user.getZhiwei());
            }
            if (user.getXinziBegin() != null && user.getXinziDone() != null) {
                mExpectMoney.setText(user.getXinziBegin() + "-"
                        + user.getXinziDone());
            }
            if (user.getHangye() != null) {
                mExceptHangye.setText("期望行业：" + user.getHangye());
            }
            if (user.getCity() != null) {
                mExceptCity.setText("期望城市：" + user.getCity());
            }
            if (user.getContent() != null) {
                mContent.setText(user.getContent());
            }
            if (user.getSchoolName() != null) {
                mSchoolName.setText(user.getSchoolName());
            }
            if (user.getEduBeginTime() != null && user.getEduDoneTime() != null) {
                mSchoolData.setText(user.getEduBeginTime() + "-"
                        + user.getEduDoneTime());
            }
            if (user.getMajor() != null && user.getEducation() != null) {
                mSchoolMajor.setText(user.getMajor() + "|" + user.getSchoolName());
            }
            if (user.getmGongzuoyeji() != null) {
                mCompanyIntroduce.setText(user.getmGongzuoneirong());
            }
            TextView center = (TextView) view.findViewById(R.id.actionbar_center);
            if (user.getNick() != null) {

//			center.setText(user.getNick());
            }
            list_view.add(view);
        }

        adpter = new MyPageAdapter(list_view);
        list_pager.setAdapter(adpter);
        // 刚开始的时候 吧当前页面是先到最大值的一半 为了循环滑动
        int currentItem = Integer.MAX_VALUE / 2;
        // 让第一个当前页是 0
        // currentItem = currentItem - ((Integer.MAX_VALUE / 2) % 4);
        list_pager.setCurrentItem(position);

    }

    @OnClick(R.id.btn_begin_chat)
    public void beginChat(View view) {
        User user = mListShow1.get(list_pager.getCurrentItem());
        BmobIMUserInfo info = new BmobIMUserInfo(user.getObjectId(), user.getNick(), user.getAvator());
        //启动一个会话，实际上就是在本地数据库的会话列表中先创建（如果没有）与该用户的会话信息，且将用户信息存储到本地的用户表中
        BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info, null);
        Bundle bundle = new Bundle();
        bundle.putSerializable("c", c);
        //this.startActi
        //startActivity(new Intent(this, ChatActivity.class), bundle);
        startActivity(ChatActivity.class, bundle, false);
    }

    @OnClick(R.id.btn_set_interest)
    public void setInterest() {
        RecieveJianLi recieveJianLi = recieveJianLis.get(list_pager.getCurrentItem());
        if (recieveJianLi.getInterst()) {
            Toast("你已经关注过他了");
            return;
        }
        circleProgressDialog.showDialog();
        recieveJianLi.setInterst(true);

        recieveJianLi.update(ReviewJianliActivity.this, new UpdateListener() {
            @Override
            public void onSuccess() {
                circleProgressDialog.dismiss();
                Toast("关注成功");
                btnSetInterest.setClickable(false);
                btnSetInterest.setText("已关注");
            }

            @Override
            public void onFailure(int i, String s) {
                circleProgressDialog.dismiss();
                Toast("关注失败");
            }
        });

    }


    public void startActivity(Class<? extends Activity> target, Bundle bundle, boolean finish) {
        Intent intent = new Intent();
        intent.setClass(this, target);
        if (bundle != null)
            intent.putExtra(getPackageName(), bundle);
        startActivity(intent);
        if (finish)
            finish();
    }
}
