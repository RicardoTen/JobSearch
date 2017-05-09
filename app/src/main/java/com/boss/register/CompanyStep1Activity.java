package com.boss.register;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import com.boss.BossApplication;
import com.boss.R;
import com.boss.activity.SetDataActivity;
import com.boss.adapter.DefaultPictureAdapter;
import com.boss.company3.in.release.setListDataActivity;
import com.boss.db.Company;
import com.boss.db.User;
import com.boss.ddb.DefaultPicture;
import com.boss.im.base.ImageLoaderFactory;
import com.boss.login.BaseActivity;
import com.boss.login.MainCompanyActivity;
import com.boss.util.BossConstants;
import com.boss.util.PhotoUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

public class CompanyStep1Activity extends BaseActivity implements
		OnClickListener {

	// 界面布局
	private View parentView;
	// 底部
	private PopupWindow pop = null;
	LayoutInflater inflater;
	// 底部
	private PopupWindow mpop = null;

	Company saveCompany = new Company();

	TextView tvHintCompFlag;
	TextView tvHintCompPoi;
	TextView tvHintCompMember;

	ImageView imgCompanyAvtar;
	ImageView imgCompanyPic1;
	ImageView imgCompanyPic2;
	ImageView imgCompanyPic3;
	/**
	 * 当前是选择什么图片
	 */
	private static int requestAvatarImg;

	boolean flagUser = false;
	boolean flagCompany = false;


	boolean isReview = false;


	Bitmap bitCompanyAvatar;
	Bitmap bitCompanyPic1;
	Bitmap bitCompanyPic2;
	Bitmap bitCompanyPic3;

	public static final int REQUEST_SET_COMPANY_FLAG = 101;
	public static final int REQUEST_SET_COMPANY_AVTAR= 102;
	public static final int REQUEST_SET_COMPANY_PIC1= 103;
	public static final int REQUEST_SET_COMPANY_PIC2 = 104;
	public static final int REQUEST_SET_COMPANY_PIC3 = 105;

	public static final int REQUEST_SET_COMPANY_POI = 106;

	public static final int REQUEST_SET_COMPANY_MEMBER = 107;

	public static final int REQEST_SET_COMPANY_DETAIL = 108;

	CircleProgressDialog circleProgressDialog;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inflater = LayoutInflater.from(this);
		parentView = inflater.inflate(R.layout.activity_register_companystep,
				new LinearLayout(this), false);
		setContentView(parentView);
		initActionBar();
		findViewById();
		InitPop1();
		InitPop2();
		initAvatar();// 初始化用户头像

		isReview = false;
		saveCompany = (Company) getIntent().getSerializableExtra("company");
		if(saveCompany != null){
			isReview = true;
			initData();
		}else{
			saveCompany = new Company();
		}

	}

	private void initData() {

		User user = User.getCurrentUser(this, User.class);
		ImageLoaderFactory.getLoader().loadAvator(mAvatar, user.getAvator(), R.drawable.ic_launcher);
		mNick.setText(user.getNick());
		mEmployee.setText(saveCompany.getCompanyBossJob());
		mEmail.setText(saveCompany.getCompanyEmail());
		mCompany.setText(saveCompany.getCompanyName());
		tvHintCompFlag.setText(saveCompany.getComanyFlag());
		tvHintCompPoi.setText(saveCompany.getComanyPoi());
		tvHintCompMember.setText(saveCompany.getCompanyMemberNum());

		ImageLoaderFactory.getLoader().loadAvator(imgCompanyAvtar, saveCompany.getComanyAvtar(), R.drawable.ic_launcher);
		ImageLoaderFactory.getLoader().loadAvator(imgCompanyPic1, saveCompany.getCompanyAvatar1(), R.drawable.ic_launcher);
		ImageLoaderFactory.getLoader().loadAvator(imgCompanyPic2, saveCompany.getCompanyAvatar2(), R.drawable.ic_launcher);
		ImageLoaderFactory.getLoader().loadAvator(imgCompanyPic3, saveCompany.getCompanyAvatar3(), R.drawable.ic_launcher);
	}

	private void initActionBar() {

		TextView center = (TextView) findViewById(R.id.actionbar_center);
		center.setText("个人信息");
		TextView right = (TextView) findViewById(R.id.actionbar_right);
		right.setText(R.string.gou);
		right.setOnClickListener(this);
	}

	private ImageView mAvatar;// 头像
	private TextView mNick;// 昵称
	private TextView mEmployee;// 我的职位
	private TextView mEmail; // 接收邮件邮箱
	private TextView mCompany;// 在职公司

	private void findViewById() {

		 tvHintCompFlag = (TextView) findViewById(R.id.tv_hint_company_flag);
		 tvHintCompPoi = (TextView) findViewById(R.id.tv_hint_company_poi);
		 tvHintCompMember = (TextView) findViewById(R.id.tv_hint_company_member);

		mAvatar = (ImageView) findViewById(R.id.register_companystep1_avator);
		imgCompanyAvtar = (ImageView) findViewById(R.id.img_company_avtar);
		imgCompanyAvtar.setOnClickListener(this);
		imgCompanyPic1 = (ImageView) findViewById(R.id.img_company_pic1);
		imgCompanyPic1.setOnClickListener(this);
		imgCompanyPic2 = (ImageView) findViewById(R.id.img_company_pic2);
		imgCompanyPic2.setOnClickListener(this);
		imgCompanyPic3 = (ImageView) findViewById(R.id.img_company_pic3);
		imgCompanyPic3.setOnClickListener(this);

		findViewById(R.id.layout_company_detail).setOnClickListener(this);

		findViewById(R.id.layout_company_poi).setOnClickListener(
				this);
		findViewById(R.id.layout_compang_member).setOnClickListener(
				this);
		findViewById(R.id.register_companystep1_myAvator).setOnClickListener(
				this);
		findViewById(R.id.register_companystep1_Nick).setOnClickListener(this);
		findViewById(R.id.register_companystep1_mEmployee).setOnClickListener(
				this);
		findViewById(R.id.register_companystep1_companyEmail)
				.setOnClickListener(this);
		findViewById(R.id.register_companystep1_companyName)
				.setOnClickListener(this);
		findViewById(R.id.register_companystep1_goCompanyMain)
				.setOnClickListener(this);
		findViewById(R.id.layout_company_flag)
				.setOnClickListener(this);
		mNick = (TextView) findViewById(R.id.register_companystep1_Nicktxt);
		mEmployee = (TextView) findViewById(R.id.register_companystep1_mEmployeetxt);
		mEmail = (TextView) findViewById(R.id.register_companystep1_companyEmailtxt);
		mCompany = (TextView) findViewById(R.id.register_companystep1_companyNametxt);

		circleProgressDialog = new CircleProgressDialog(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {

			case R.id.layout_company_detail:
				Intent detail = new Intent(this, SetDataActivity.class);
				detail.putExtra("title", "公司介绍");// 传标题
				detail.putExtra("limit", 1000);// 传字数限制
				detail.putExtra("content", saveCompany.getCompanyDetailInfo());
				startActivityForResult(detail, REQEST_SET_COMPANY_DETAIL);
				break;

		case R.id.layout_compang_member:
			Intent memberIntent = new Intent(this, setCompanyMemberNum.class);
			startActivityForResult(memberIntent,REQUEST_SET_COMPANY_MEMBER);
			break;

		case R.id.layout_company_poi:
			/*Intent setPoi = new Intent(this, SetDataActivity.class);
			setPoi.putExtra("title", "公司地址");// 传标题
			setPoi.putExtra("limit", 100);// 传字数限制
			setPoi.putExtra("content", saveCompany.getComanyPoi());*/
			Intent setPoi = new Intent(this, setListDataActivity.class);
			setPoi.putExtra("data", BossConstants.CITY);// 传标题
			startActivityForResult(setPoi, REQUEST_SET_COMPANY_POI);
			break;

		case R.id.img_company_avtar:
			requestAvatarImg = REQUEST_SET_COMPANY_AVTAR;
			pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			break;
		case R.id.img_company_pic1:
			requestAvatarImg = REQUEST_SET_COMPANY_PIC1;
			pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			break;
			case R.id.img_company_pic2:
				requestAvatarImg = REQUEST_SET_COMPANY_PIC2;
				pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
				break;
			case R.id.img_company_pic3:
				requestAvatarImg = REQUEST_SET_COMPANY_PIC3;
				pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
				break;

		case R.id.layout_company_flag:
				Intent companyType = new Intent(this, setCompanyTypeActivity.class);
				companyType.putExtra("content", saveCompany.getComanyFlag());
				startActivityForResult(companyType, REQUEST_SET_COMPANY_FLAG);
				break;
		case R.id.register_companystep1_myAvator:// 设置头像
			requestAvatarImg = 2;
			pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			break;
		case R.id.register_companystep1_Nick:// 设置名字
			Intent setData = new Intent(this, SetDataActivity.class);
			setData.putExtra("title", "姓名");// 传标题
			setData.putExtra("limit", 4);// 传字数限制
			startActivityForResult(setData, 10);
			break;
		case R.id.register_companystep1_mEmployee:// 设置我的职位
			Intent setEmployee = new Intent(this, SetDataActivity.class);
			setEmployee.putExtra("title", "我的职位");// 传标题
			setEmployee.putExtra("limit", 7);// 传字数限制
			startActivityForResult(setEmployee, 11);
			break;
		case R.id.register_companystep1_companyEmail:// 设置接收简历邮箱
			Intent email = new Intent(this, SetDataActivity.class);
			email.putExtra("title", "接收简历邮箱");// 传标题
			email.putExtra("limit", 64);// 传字数限制
			startActivityForResult(email, 12);
			break;

		case R.id.register_companystep1_companyName:// 设置公司名字
			Intent name = new Intent(this, SetDataActivity.class);
			name.putExtra("title", "Boss个人信息");// 传标题
			name.putExtra("limit", 46);// 传字数限制
			startActivityForResult(name, 13);
			break;

		// pop中的点击事件
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
		case R.id.pop_register_companystep1_goNormalPicture:// 默认头像
			pop.dismiss();
			mpop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			break;
		case R.id.pop_register_companystep1_cancel:// 取消
			pop.dismiss();
			break;
		// 选择默认头像pop中的点击事件
		case R.id.pop_register_companystep1_normalpicture_all:// 点击其他地方
			mpop.dismiss();
			break;
		case R.id.pop_register_companystep1_normalpicture_cancel:// 取消
			mpop.dismiss();
			break;

		case R.id.actionbar_right:
		case R.id.register_companystep1_goCompanyMain:// 完成注册签名主界面
			goCompanyMain();
			break;

		}

	}

	/**
	 * 下一步按钮，提交数据并且跳转到企业界面
	 */
	private void goCompanyMain() {
		String nick = mNick.getText().toString();
		if (nick == "") {
			ToastNoMessage("姓名");
			return;
		}
		String company = mCompany.getText().toString();
		if (company == "") {
			ToastNoMessage("公司名称");
			return;
		}
		String email = mEmail.getText().toString();
		if (email == "") {
			ToastNoMessage("接收简历邮箱");
			return;
		}
		String employee = mEmployee.getText().toString();
		if (employee == "") {
			ToastNoMessage("职位");
			return;
		}

		circleProgressDialog.showDialog();

		User newUser = new User();
		/*newUser.setCompanyEmail(email);
		newUser.setCompanyEmployee(employee);*/
		newUser.setNick(nick);
		/*newUser.setCompanyAllName(company);
		newUser.setRegisterCompany(true);*/
		newUser.setMainLayout(true);
		newUser.setRegisterCompany(true);

		flagCompany = false;
		flagUser = false;

		User bmobUser = User.getCurrentUser(this, User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				circleProgressDialog.dismiss();
				flagUser = true;
				saveComplete(flagCompany, flagUser);
			}

			@Override
			public void onFailure(int i, String s) {
				circleProgressDialog.dismiss();
				Toast("请重试" + s);
			}
		});


		saveCompany.setCompanyBossJob(employee);
		saveCompany.setCompanyName(company);
		saveCompany.setCompanyBossPhone(bmobUser.getMobilePhoneNumber());
		saveCompany.setCompanyBossName(nick);
		saveCompany.setCompanyEmail(email);
		saveCompany.setCompanyState(BossConstants.STATISTICS_ING);
		saveCompany.setCompanyFailReason("");

	if(isReview){

	saveCompany.update(this, saveCompany.getObjectId(), new UpdateListener() {
		@Override
		public void onSuccess() {
			flagCompany = true;
			saveComplete(flagCompany, flagUser);
		}

		@Override
		public void onFailure(int i, String s) {
			Toast("请重试" + s);
			}
		});
	}else{
		saveCompany.save(this, new SaveListener() {
			@Override
			public void onSuccess() {
				flagCompany = true;
				saveComplete(flagCompany, flagUser);
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
		});
	}



		//saveCompany.set
	}


	private void saveComplete(boolean flag1, boolean flag2){
		if(flag1 && flag2){
			sendBroadcast(new Intent(
					BossConstants.ACTION_REGISTER_SUCCESS_COMPANY_FINISH));
			if(!isReview){
				startActivity(new Intent(CompanyStep1Activity.this,
						MainCompanyActivity.class));
			}
			CompanyStep1Activity.this.finish();
		}
	}


	@SuppressWarnings("deprecation")
	public void InitPop1() {

		View view = inflater.inflate(R.layout.pop_register_companystep1,
				new LinearLayout(this), false);
		// 设置popup的属性
		// 初始化popup
		pop = new PopupWindow(this);
		// 宽
		pop.setWidth(LayoutParams.MATCH_PARENT);
		// 高
		pop.setHeight(LayoutParams.WRAP_CONTENT);
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
		mpop.setWidth(LayoutParams.MATCH_PARENT);
		// 高
		mpop.setHeight(LayoutParams.WRAP_CONTENT);
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
		for (int i = 0; i < BossConstants.DEFAULT_PICTURE.length; i++) {
			DefaultPicture pic = new DefaultPicture();
			pic.setPic(BossConstants.DEFAULT_PICTURE[i]);
			mPic.add(pic);
		}

		GridView mDefaultPicture = (GridView) view
				.findViewById(R.id.pop_register_companystep1_normalpicture_picture);

		DefaultPictureAdapter adapter2 = new DefaultPictureAdapter(this, mPic);
		mDefaultPicture.setAdapter(adapter2);

		mDefaultPicture.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(requestAvatarImg == 2){
					changeavator(arg2);
				}else if(requestAvatarImg == REQUEST_SET_COMPANY_PIC1){
					imgCompanyPic1.setImageResource(BossConstants.DEFAULT_PICTURE[arg2]);
				}else if(requestAvatarImg == REQUEST_SET_COMPANY_PIC2){
					imgCompanyPic2.setImageResource(BossConstants.DEFAULT_PICTURE[arg2]);
				}else if(requestAvatarImg == REQUEST_SET_COMPANY_PIC3){
					imgCompanyPic3.setImageResource(BossConstants.DEFAULT_PICTURE[arg2]);
				}else if(requestAvatarImg == REQUEST_SET_COMPANY_AVTAR){
					imgCompanyAvtar.setImageResource(BossConstants.DEFAULT_PICTURE[arg2]);
				}

			}

		});
	}

	/**
	 */

	private void changeavator(final int arg2) {
		User newUser = new User();
		newUser.setNormalavator(true);
		newUser.setNormalavatorindex(arg2);
		User bmobUser = User.getCurrentUser(this,User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				mAvatar.setImageResource(BossConstants.DEFAULT_PICTURE[arg2]);
				mpop.dismiss();
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("头像更新失败，请重试" + s);
			}
			/*@Override
			public void done(BmobException e) {
				if (e == null) {
					mAvatar.setImageResource(BossConstants.DEFAULT_PICTURE[arg2]);
					mpop.dismiss();
				} else {
					Toast("头像更新失败，请重试" + e.getMessage());
				}
			}*/

		});

	}

	/**
	 * 初始化头像，若用户有头像则显示，否则显示默认
	 */
	private void initAvatar() {
		// 放置图片
		User user = User.getCurrentUser(this, User.class);
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

	/** 头像地址 */
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
		CompanyStep1Activity.this.startActivityForResult(intent, requestCode);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case 2 :
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
					Toast.makeText(CompanyStep1Activity.this, "取消选择",
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

			case 11:// 提取返回的我的职位
				String employee = data.getStringExtra("data_return");
				mEmployee.setText(employee);
				break;
			// 提取
			case 12:// 提取返回的接收简历邮箱
				String email = data.getStringExtra("data_return");
				mEmail.setText(email);
				break;
			case 13:// Boss个人信息
				String company = data.getStringExtra("data_return");
				mCompany.setText(company);
				break;
			case REQUEST_SET_COMPANY_FLAG:
				String companyFlag = data.getStringExtra("data_return");
				saveCompany.setComanyFlag(companyFlag);
				tvHintCompFlag.setText(companyFlag);
				break;

			case REQUEST_SET_COMPANY_POI:
				String companyPoi = data.getStringExtra("data_return");
				saveCompany.setComanyPoi(companyPoi);
				tvHintCompPoi.setText(companyPoi);
					break;

			case REQUEST_SET_COMPANY_MEMBER:
				String companyMember = data.getStringExtra("data_return");
				saveCompany.setCompanyMemberNum(companyMember);
				tvHintCompMember.setText(companyMember);
				break;

			case REQEST_SET_COMPANY_DETAIL:
					String detailCom = data.getStringExtra("data_return");
					saveCompany.setCompanyDetailInfo(detailCom);
					break;


			}
		}
	}

	/**
	 * @param data
	 *            保存裁剪的图片到本地，并给图片的本地地址path赋值
	 */
	public void saveCropAvator(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
			Bitmap bitmap = extras.getParcelable("data");
			if (bitmap != null) {
				bitmap = PhotoUtil.toRoundCorner(bitmap, 10);
				if(requestAvatarImg == REQUEST_SET_COMPANY_AVTAR){
					imgCompanyAvtar.setImageBitmap(bitmap);
					bitCompanyAvatar = bitmap;
				}else if(requestAvatarImg == 2){
					mAvatar.setImageBitmap(bitmap);
					// 保存图片
					User user = User.getCurrentUser(this ,User.class);
					String u = user.getUsername();
					String filename = u + ".png";
					path = BossConstants.MyAvatarDir + filename;
					PhotoUtil.saveBitmap(BossConstants.MyAvatarDir, filename,
							bitmap, true);
				}else if(requestAvatarImg == REQUEST_SET_COMPANY_PIC1){
					imgCompanyPic1.setImageBitmap(bitmap);
					bitCompanyPic1 = bitmap;
				}else if(requestAvatarImg == REQUEST_SET_COMPANY_PIC2){
					imgCompanyPic2.setImageBitmap(bitmap);
					bitCompanyPic2 = bitmap;
				}else if(requestAvatarImg == REQUEST_SET_COMPANY_PIC3){
					imgCompanyPic3.setImageBitmap(bitmap);
					bitCompanyPic3 = bitmap;
				}

/*				// 回收bitmap
				if (bitmap != null && bitmap.isRecycled()) {
					bitmap.recycle();
				}*/
			}
		}
	}

	/** 通过图片本地地址上传图片 */
	private void uploadAvatar() {
		String picPath = path;
		final BmobFile bmobFile = new BmobFile(new File(picPath));
		bmobFile.uploadblock(this, new UploadFileListener() {
			@Override
			public void onProgress(Integer value) {
				// 返回的上传进度（百分比）
			}

			@Override
			public void onSuccess() {
				// bmobFile.getFileUrl()--返回的上传文件的完整地址
				updataUserAvatar(bmobFile.getFileUrl(CompanyStep1Activity.this));
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("上传文件失败：" + s);
			}
		});

	}

	/**
	 * @param fileUrl
	 *            bmob服务器中返回的图片地址 将图片地址与用户头像绑定
	 */
	private void updataUserAvatar(String fileUrl) {
		User newUser = new User();
		newUser.setAvator(fileUrl);
		newUser.setNormalavator(false);
		User bmobUser = User.getCurrentUser(this, User.class);
		newUser.update(this,bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("头像更新成功");
				// sendBroadcast(new Intent(
				// BossConstants.ACTION_REGISTER_SUCCESS_COMPANY_FINISH));
				// startActivity(new Intent(CompanyStep2Activity.this,
				// MainCompanyActivity.class));
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
			/*@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("头像更新成功");
					// sendBroadcast(new Intent(
					// BossConstants.ACTION_REGISTER_SUCCESS_COMPANY_FINISH));
					// startActivity(new Intent(CompanyStep2Activity.this,
					// MainCompanyActivity.class));
				} else {
					Toast("请重试" + e.getMessage());
				}
			}*/

		});
	}
}
