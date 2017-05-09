package com.boss.admin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.boss.BossApplication;
import com.boss.R;
import com.boss.activity.SetDataActivity;
import com.boss.adapter.DefaultPictureAdapter;
import com.boss.adapter.GetWorkAdapter;
import com.boss.db.User;
import com.boss.ddb.DefaultPicture;
import com.boss.ddb.GetWorkYear;
import com.boss.login.BaseActivity;
import com.boss.support.IsPhone;
import com.boss.util.BossConstants;
import com.boss.util.PhotoUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by 滕新科 on 2017/5/8.
 */

public class CreateAdminActivity extends BaseActivity implements
        View.OnClickListener {
    @BindView(R.id.hint_sex)
    TextView hintSex;
    @BindView(R.id.layout_sex)
    LinearLayout layoutSex;
    @BindView(R.id.tv_phone_hint)
    TextView tvPhoneHint;
    @BindView(R.id.hint_password)
    TextView hintPassword;
    // 界面布局
    private View parentView;
    LayoutInflater inflater;
    // 底部
    private PopupWindow pop = null;
    // 底部
    private PopupWindow mpop = null;
    // 底部
    // private PopupWindow mmpop = null;

    CircleProgressDialog circleProgressDialog;

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = LayoutInflater.from(this);
        parentView = inflater.inflate(R.layout.activity_create_admin_user,
                new LinearLayout(this), false);
        setContentView(parentView);
        ButterKnife.bind(this);
        findViewById();
        InitPop1();// 初始化选择照片模式
        InitPop2();// 初始化本地头像模式
        initAvatar();// 初始化头像
        initData();//初始化信息数据
        initPop();
        initActionBar();

    }

    // 底部
    private PopupWindow pop3 = null;

    private void initPop() {


        View view = inflater.inflate(
                R.layout.pop_register_employeestep1_workyear, new LinearLayout(
                        this), false);
        // 设置popup的属性
        // 初始化popup
        pop3 = new PopupWindow(this);
        // 宽
        pop3.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 高
        pop3.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 背景位空
        // ColorDrawable dw = new ColorDrawable(0xb0000000);
        pop3.setBackgroundDrawable(new BitmapDrawable());
        // 设置焦点点击事件
        pop3.setFocusable(true);
        // 设置外边可以被点击
        pop3.setOutsideTouchable(true);
        // 设置视图
        pop3.setContentView(view);
        view.findViewById(R.id.pop_register_employeestep1_all)
                .setOnClickListener(this);
        view.findViewById(R.id.pop_register_employeestep1_cancel)
                .setOnClickListener(this);

        List<GetWorkYear> mListYear = new ArrayList<GetWorkYear>();
        for (int i = 0; i < 2; i++) {
            GetWorkYear year = new GetWorkYear();
            year.setYear(BossConstants.sex[i]);
            mListYear.add(year);
        }

        ListView mGetWorkYearList = (ListView) view
                .findViewById(R.id.pop_register_employeestep1_workyear_list);
        GetWorkAdapter adapter2 = new GetWorkAdapter(this, mListYear);
        mGetWorkYearList.setAdapter(adapter2);
        mGetWorkYearList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                hintSex.setText(BossConstants.sex[arg2]);
                pop3.dismiss();
            }

        });

    }

    private void initActionBar() {
        TextView left = (TextView) findViewById(R.id.actionbar_left);
        left.setText(R.string.zuojiantou);
        left.setOnClickListener(this);
        TextView center = (TextView) findViewById(R.id.actionbar_center);
        center.setText("创建管理员");
        TextView right = (TextView) findViewById(R.id.actionbar_right);
      /*  right.setText("下一步");
        right.setOnClickListener(this);*/
    }

    private void initData() {
        //  User user = User.getCurrentUser(this, User.class);
        if (user.getNick() != null) {
            mNick.setText(user.getNick());
        }
        if (user.getEmployeeWorkyear() != null) {
            mYear.setText(user.getEmployeeWorkyear());
        }
        if (user.getSex()) {
            hintSex.setText("男");
        } else {
            hintSex.setText("女");
        }
    }


    @OnClick(R.id.layout_sex)
    public void onClickSex() {
        pop3.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 用户头像
     */
    private ImageView mAvatar;
    /**
     * 用户昵称
     */
    private TextView mNick;
    /**
     * 参加工作年份
     */
    private TextView mYear;

    private void findViewById() {
        mAvatar = (ImageView) findViewById(R.id.register_employeestep1_avatar);
        mAvatar.setOnClickListener(this);
        findViewById(R.id.register_employeestep1_Nick).setOnClickListener(this);
        mNick = (TextView) findViewById(R.id.register_employeestep1_Nicktxt);
        findViewById(R.id.register_employeestep1_workYear).setOnClickListener(
                this);
        mYear = (TextView) findViewById(R.id.register_employeestep1_workYeartxt);
        findViewById(R.id.register_employeestep1_goStep2).setOnClickListener(
                this);

        circleProgressDialog = new CircleProgressDialog(this);

    }

    @SuppressWarnings("deprecation")
    public void InitPop1() {

        View view = inflater.inflate(R.layout.pop_register_companystep1,
                new LinearLayout(this), false);
        // 设置popup的属性
        // 初始化popup
        pop = new PopupWindow(this);
        // 宽
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 高
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 背景位空
        // ColorDrawable dw = new ColorDrawable(0xb0000000);
        pop.setBackgroundDrawable(new BitmapDrawable());
        // 设置焦点点击事件
        pop.setFocusable(true);
        // 设置外边可以被点击
        pop.setOutsideTouchable(true);
        // 设置视图
        pop.setContentView(view);

        view.findViewById(R.id.pop_register_companystep1_all)
                .setOnClickListener(this);
        view.findViewById(R.id.pop_register_companystep1_goPhoto)
                .setOnClickListener(this);
        view.findViewById(R.id.pop_register_companystep1_goPicture)
                .setOnClickListener(this);
        view.findViewById(R.id.pop_register_companystep1_goNormalPicture)
                .setOnClickListener(this);
        view.findViewById(R.id.pop_register_companystep1_cancel)
                .setOnClickListener(this);

    }

    @SuppressWarnings("deprecation")
    public void InitPop2() {

        View view = inflater.inflate(
                R.layout.pop_register_companystep1_normalpicture,
                new LinearLayout(this), false);
        // 设置popup的属性
        // 初始化popup
        mpop = new PopupWindow(this);
        // 宽
        mpop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 高
        mpop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 背景位空
        // ColorDrawable dw = new ColorDrawable(0xb0000000);
        mpop.setBackgroundDrawable(new BitmapDrawable());
        // 设置焦点点击事件
        mpop.setFocusable(true);
        // 设置外边可以被点击
        mpop.setOutsideTouchable(true);
        // 设置视图
        mpop.setContentView(view);
        view.findViewById(R.id.pop_register_companystep1_normalpicture_all)
                .setOnClickListener(this);
        view.findViewById(R.id.pop_register_companystep1_normalpicture_cancel)
                .setOnClickListener(this);

        List<DefaultPicture> mPic = new ArrayList<DefaultPicture>();
        for (int i = 0; i < 8; i++) {
            DefaultPicture pic = new DefaultPicture();
            pic.setPic(BossConstants.DEFAULT_PICTURE[i]);
            mPic.add(pic);
        }

        GridView mDefaultPicture = (GridView) view
                .findViewById(R.id.pop_register_companystep1_normalpicture_picture);

        DefaultPictureAdapter adapter2 = new DefaultPictureAdapter(this, mPic);
        mDefaultPicture.setAdapter(adapter2);

        mDefaultPicture.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // 更新用户头像信息
                changeavator(arg2);
            }

        });
    }

    /**
     */

    private void changeavator(final int arg2) {
        // User newUser = new User();
        user.setNormalavator(true);
        user.setNormalavatorindex(arg2);
        mAvatar.setImageResource(BossConstants.DEFAULT_PICTURE[arg2]);
        mpop.dismiss();

    }

    /**
     * 初始化头像
     */

    private void initAvatar() {
        // 放置图片
      /*  User user = User.getCurrentUser(this, User.class);*/
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
    }

    @OnClick(R.id.layout_password)
    public void oClickGoToSetPwd(View view) {
        Intent setData = new Intent(this, SetDataActivity.class);
        setData.putExtra("title", "设置密码");// 传标题
        setData.putExtra("limit", 20);// 传字数限制
        startActivityForResult(setData, 102);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionbar_left://返回
                finish();
                break;
            case R.id.register_employeestep1_Nick:// 设置姓名
                Intent setData = new Intent(this, SetDataActivity.class);
                setData.putExtra("title", "姓名");// 传标题
                setData.putExtra("limit", 4);// 传字数限制
                startActivityForResult(setData, 10);
                break;

            case R.id.register_employeestep1_avatar:// 选择头像
                pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
                break;
            // 选择头像pop中的点击事件
            case R.id.pop_register_companystep1_all:// 点击其他地方
                pop.dismiss();
                break;
            case R.id.pop_register_companystep1_goPhoto:// 拍照
            case R.id.pop_register_companystep1_goPicture:// 相册
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        "image/*");
                startActivityForResult(intent, 2);
                pop.dismiss();
                break;
            case R.id.pop_register_companystep1_cancel:// 取消
                pop.dismiss();
                break;
            // 选择默认头像pop中的点击事件
            case R.id.pop_register_companystep1_goNormalPicture:// 默认头像
                pop.dismiss();
                mpop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.pop_register_companystep1_normalpicture_all:// 点击其他地方
                mpop.dismiss();
                break;
            case R.id.pop_register_companystep1_normalpicture_cancel:// 取消
                mpop.dismiss();
                break;

            case R.id.register_employeestep1_workYear:// 设置参加工作年份
                Intent phoneNumSet = new Intent(this, SetDataActivity.class);
                phoneNumSet.putExtra("title", "电话号码");// 传标题
                phoneNumSet.putExtra("limit", 11);// 传字数限制
                startActivityForResult(phoneNumSet, 101);
                break;

/*            case R.id.pop_register_employeestep1_all:// 点击外面
                mmpop.dismiss();
                break;
            case R.id.pop_register_employeestep1_cancel:// 取消按钮
                mmpop.dismiss();
                break;
            case R.id.pop_register_employeestep1_ok:// 确认按钮
                mmpop.dismiss();
                break;*/

            case R.id.actionbar_right:
            case R.id.register_employeestep1_goStep2:// 前往下一个活动，创建微简历
                goStep2();
                break;

        }
    }

    private void goStep2() {
        String nick = mNick.getText().toString();
        if (nick == "") {
            ToastNoMessage("姓名");
            return;
        }
        String phone = mYear.getText().toString();
        if (!IsPhone.isPhone(phone)) {
            Toast("电话号码格式错误");
            return;
        }
        String pwd = hintPassword.getText().toString();
        if(TextUtils.isEmpty(hintPassword.getText().toString())){
            ToastNoMessage("密码");
            return;
        }

        circleProgressDialog.showDialog();

        String sexString = hintSex.getText().toString();
        boolean sex = sexString.equals("男") ? true : false;


        user.setNick(nick);
        user.setUsername(phone);
        user.setPassword(pwd);
        user.setSex(sex);
        user.setMobilePhoneNumber(phone);
        user.setMobilePhoneNumberVerified(true);
        user.setAdMin(true);
        user.setWorkState("管理员");



        user.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                circleProgressDialog.dismiss();
                Toast("创建成功");
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                circleProgressDialog.dismiss();
                Toast("创建失败" + s);
            }
        });

    }

    /**
     * 头像地址
     */
    String path;

    // 裁剪头像
    public void startImageAction(Uri uri, int outputX, int outputY,
                                 int requestCode, boolean isCrop) {
        Intent intent = null;
        if (isCrop) {
            intent = new Intent("com.android.camera.action.CROP");
        } else {
            intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, BossConstants.MyAvatarDir);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        this.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 2:
                    // 拍照
                    if (data == null) {
                        return;
                    }
                    if (resultCode == RESULT_OK) {
                        if (!Environment.getExternalStorageState().equals(
                                Environment.MEDIA_MOUNTED)) {
                            return;
                        }
                        Uri uri = null;
                        uri = data.getData();
                        startImageAction(uri, 200, 200, 3, true);
                    } else {
                    }
                    break;
                case 3:// 裁剪头像返回

                    if (data == null) {
                        Toast.makeText(CreateAdminActivity.this, "取消选择",
                                Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        saveCropAvator(data);
                    }
                    // 上传头像
                    uploadAvatar();

                    break;

                case 10:// 提取返回的姓名
                    String nick = data.getStringExtra("data_return");
                    mNick.setText(nick);
                    break;

                case 101:
                    String phone = data.getStringExtra("data_return");
                    mYear.setText(phone);
                    break;

                case 102:
                    String pwd = data.getStringExtra("data_return");
                    hintPassword.setText(pwd);
                    break;

            }
        }
    }

    /**
     * @param data 保存裁剪的图片到本地，并给图片的本地地址path赋值
     */
    public void saveCropAvator(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap bitmap = extras.getParcelable("data");
            if (bitmap != null) {
                bitmap = PhotoUtil.toRoundCorner(bitmap, 10);
                mAvatar.setImageBitmap(bitmap);
                // 保存图片
                User user = User.getCurrentUser(this, User.class);
                String u = user.getUsername();
                String filename = u + ".png";
                path = BossConstants.MyAvatarDir + filename;
                PhotoUtil.saveBitmap(BossConstants.MyAvatarDir, filename,
                        bitmap, true);
                // 回收bitmap
                if (bitmap != null && bitmap.isRecycled()) {
                    bitmap.recycle();
                }
            }
        }
    }

    /**
     * 通过图片本地地址上传图片
     */
    private void uploadAvatar() {
        String picPath = path;
        final BmobFile bmobFile = new BmobFile(new File(picPath));
        bmobFile.uploadblock(this, new UploadFileListener() {

		/*	@Override
            public void done(BmobException e) {
				if (e == null) {
					// bmobFile.getFileUrl()--返回的上传文件的完整地址
					updataUserAvatar(bmobFile.getFileUrl());

				} else {
					Toast("上传文件失败：" + e.getMessage());
				}

			}*/

            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }

            @Override
            public void onSuccess() {
                // bmobFile.getFileUrl()--返回的上传文件的完整地址
                updataUserAvatar(bmobFile.getFileUrl(CreateAdminActivity.this));
            }

            @Override
            public void onFailure(int i, String s) {
                Toast("上传文件失败：" + s);
            }
        });

    }

    /**
     * @param fileUrl bmob服务器中返回的图片地址 将图片地址与用户头像绑定
     */
    private void updataUserAvatar(String fileUrl) {

        user.setAvator(fileUrl);
        user.setNormalavator(false);
    }


    @Override
    protected void onDestroy() {
        //
        super.onDestroy();
    }
}
