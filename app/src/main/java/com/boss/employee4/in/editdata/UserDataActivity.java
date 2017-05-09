package com.boss.employee4.in.editdata;

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
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import com.boss.BossApplication;
import com.boss.R;
import com.boss.activity.SetDataActivity;
import com.boss.adapter.DefaultPictureAdapter;
import com.boss.adapter.GetWorkAdapter;
import com.boss.db.User;
import com.boss.ddb.DefaultPicture;
import com.boss.ddb.GetWorkYear;
import com.boss.login.BaseActivity;
import com.boss.util.BossConstants;
import com.boss.util.PhotoUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

public class UserDataActivity extends BaseActivity implements OnClickListener {
	// 界面布局
	private View parentView;
	LayoutInflater inflater;
	// 底部
	private PopupWindow pop = null;
	// 底部
	private PopupWindow mpop = null;
	// 底部
	private PopupWindow mmpop = null;

	CircleProgressDialog circleProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inflater = LayoutInflater.from(this);
		parentView = inflater.inflate(R.layout.activity_employee4_in_editdata_userdata,
				new LinearLayout(this), false);
		setContentView(parentView);
		findViewById();
		InitPop1();// 初始化选择照片模式
		InitPop2();// 初始化本地头像模式
		InitPop3();// 初始化参加工作
		initAvatar();// 初始化头像
		initData();// 初始化信息数据
		initActionBar();

	}

	private void initActionBar() {
		TextView left = (TextView) findViewById(R.id.actionbar_left);
		left.setText(R.string.zuojiantou);
		left.setOnClickListener(this);
		TextView center = (TextView) findViewById(R.id.actionbar_center);
		center.setText("个人信息");
		 
	}

	private void initData() {
		User user = User.getCurrentUser(this, User.class);
		if (user.getNick() != null) {
			mNick.setText(user.getNick());
		}
		if (user.getEmployeeWorkyear() != null) {
			mYear.setText(user.getEmployeeWorkyear());
		}
		if(user.getWeChatNumber()!=null){
			mWeChat.setText(user.getWeChatNumber());
		}
	}

	/** 用户头像 */
	private ImageView mAvatar;
	/** 用户昵称 */
	private TextView mNick;
	/** 参加工作年份 */
	private TextView mYear;
	/**微信号*/
	private TextView mWeChat;

	private void findViewById() {
		findViewById(R.id.employee4_in_editdata_userdata_avatar).setOnClickListener(this);
		mAvatar = (ImageView) findViewById(R.id.employee4_in_editdata_userdata_Myavatar);
		findViewById(R.id.employee4_in_editdata_userdata_nick).setOnClickListener(this);
		mNick = (TextView) findViewById(R.id.employee4_in_editdata_userdata_nickTxt);
		findViewById(R.id.employee4_in_editdata_userdata_workYear).setOnClickListener(
				this);
		mYear = (TextView) findViewById(R.id.employee4_in_editdata_userdata_workYearTxt);
		 
		findViewById(R.id.employee4_in_editdata_userdata_weChat).setOnClickListener(this);
		mWeChat = (TextView)findViewById(R.id.employee4_in_editdata_userdata_weChatTxt);

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
		for (int i = 0; i < 8; i++) {
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
				// 更新用户头像信息
				changeavator(arg2);
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

	/*	new UpdateListener() {
			@Override
			public void onSuccess() {

			}

			@Override
			public void onFailure(int i, String s) {

			}
		};*/
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
/*			@Override
			public void done(BmobException e) {
				if (e == null) {
					mAvatar.setImageResource(BossConstants.DEFAULT_PICTURE[arg2]);
					mpop.dismiss();
				} else {
					Toast("头像更新失败，请重试" + e.getMessage());
				}
			}*/

			@Override
			public void onSuccess() {
				mAvatar.setImageResource(BossConstants.DEFAULT_PICTURE[arg2]);
				mpop.dismiss();
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("头像更新失败，请重试" + s);
			}

		});

	}

	/**
	 * 初始化参加工作年份
	 */
	@SuppressWarnings("deprecation")
	public void InitPop3() {

		View view = inflater.inflate(
				R.layout.pop_register_employeestep1_workyear, new LinearLayout(
						this), false);
		// 设置popup的属性
		// 初始化popup
		mmpop = new PopupWindow(this);
		// 宽
		mmpop.setWidth(LayoutParams.MATCH_PARENT);
		// 高
		mmpop.setHeight(LayoutParams.WRAP_CONTENT);
		// 背景位空
		// ColorDrawable dw = new ColorDrawable(0xb0000000);
		mmpop.setBackgroundDrawable(new BitmapDrawable());
		// 设置焦点点击事件
		mmpop.setFocusable(true);
		// 设置外边可以被点击
		mmpop.setOutsideTouchable(true);
		// 设置视图
		mmpop.setContentView(view);
		view.findViewById(R.id.pop_register_employeestep1_all)
				.setOnClickListener(this);
		view.findViewById(R.id.pop_register_employeestep1_cancel)
				.setOnClickListener(this);

		List<GetWorkYear> mListYear = new ArrayList<GetWorkYear>();
		for (int i = 0; i < BossConstants.GET_WORK_YEAR.length; i++) {
			GetWorkYear year = new GetWorkYear();
			year.setYear(BossConstants.GET_WORK_YEAR[i]);
			mListYear.add(year);
		}

		ListView mGetWorkYearList = (ListView) view
				.findViewById(R.id.pop_register_employeestep1_workyear_list);
		GetWorkAdapter adapter2 = new GetWorkAdapter(this, mListYear);
		mGetWorkYearList.setAdapter(adapter2);
		mGetWorkYearList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mYear.setText(BossConstants.GET_WORK_YEAR[arg2]);
				updataUserWorkYear(BossConstants.GET_WORK_YEAR[arg2]);
				mmpop.dismiss();

			}

		});
	}

	/**
	 * 初始化头像
	 */

	private void initAvatar() {
		// 放置图片
		User user = User.getCurrentUser(this,User.class);
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

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.actionbar_left:// 返回
			finish();
			break;
		case R.id.employee4_in_editdata_userdata_nick:// 设置姓名
			Intent setData = new Intent(this, SetDataActivity.class);
			setData.putExtra("title", "姓名");// 传标题
			setData.putExtra("limit", 4);// 传字数限制
			startActivityForResult(setData, 10);
			break;
			
		case R.id.employee4_in_editdata_userdata_weChat://设置微信号
			Intent wechat = new Intent(this, SetDataActivity.class);
			wechat.putExtra("title", "微信号");// 传标题
			wechat.putExtra("limit", 40);// 传字数限制
			startActivityForResult(wechat, 11);
			break;

		case R.id.employee4_in_editdata_userdata_avatar:// 选择头像
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

		case R.id.employee4_in_editdata_userdata_workYear:// 设置参加工作年份
			mmpop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			break;

		case R.id.pop_register_employeestep1_all:// 点击外面
			mmpop.dismiss();
			break;
		case R.id.pop_register_employeestep1_cancel:// 取消按钮
			mmpop.dismiss();
			break;
		case R.id.pop_register_employeestep1_ok:// 确认按钮
			mmpop.dismiss();
			break;
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
		UserDataActivity.this.startActivityForResult(intent, requestCode);
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
					Toast.makeText(UserDataActivity.this, "取消选择",
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
				updataUserNick(nick);//更新服务器用户姓名
				
				break;
			case 11://提取返回的微信号
				String wechat = data.getStringExtra("data_return");
				mWeChat.setText(wechat);
				updataUserWechat(wechat);
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
				mAvatar.setImageBitmap(bitmap);
				// 保存图片
				User user = User.getCurrentUser(this,User.class);
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

	/** 通过图片本地地址上传图片 */
	private void uploadAvatar() {
		String picPath = path;
		final BmobFile bmobFile = new BmobFile(new File(picPath));

/*		new UploadFileListener() {



		};*/
		bmobFile.uploadblock(UserDataActivity.this, new UploadFileListener() {

			/*@Override
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
				updataUserAvatar(bmobFile.getFileUrl(UserDataActivity.this));
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
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
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
	private void updataUserNick(String nick) {
		User newUser = new User();
		newUser.setNick(nick);
		User bmobUser = User.getCurrentUser(this, User.class);
		newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("姓名更新成功");
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
			/*@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("姓名更新成功");
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
	private void updataUserWorkYear(String year) {
		User newUser = new User();
		newUser.setEmployeeWorkyear(year);
		User bmobUser = User.getCurrentUser(this, User.class);
		newUser.update(this,bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("参加工作年份更新成功");
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
					Toast("参加工作年份更新成功");
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
	
	private void updataUserWechat(String chat) {
		User newUser = new User();
		newUser.setWeChatNumber(chat);
		User bmobUser = User.getCurrentUser(this ,User.class);
		newUser.update(this,bmobUser.getObjectId(), new UpdateListener() {
			@Override
			public void onSuccess() {
				Toast("微信号更新成功");
				// sendBroadcast(new Intent(
				// BossConstants.ACTION_REGISTER_SUCCESS_COMPANY_FINISH));
				// startActivity(new Intent(CompanyStep2Activity.this,
				// MainCompanyActivity.class));
			}

			@Override
			public void onFailure(int i, String s) {
				Toast("请重试" + s);
			}
		/*	@Override
			public void done(BmobException e) {
				if (e == null) {
					Toast("微信号更新成功");
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
