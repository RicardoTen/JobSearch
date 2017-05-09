package com.boss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.boss.im.DemoMessageHandler;
import com.boss.util.BossConstants;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import cn.bmob.newim.BmobIM;
import cn.bmob.v3.Bmob;

public class BossApplication extends Application{
	//自定义全局Applcation类
	
	//实例化对象
	public static BossApplication mInstance;
	

	public static BossApplication getInstance() {
		return mInstance;
	}

	
	
	@Override
	public void onCreate() {
		super.onCreate();
		// 是否开启debug模式--默认开启状态
		mInstance = this;
		//初始化
		initImageLoader(getApplicationContext());

		Bmob.initialize(this, BossConstants.BMOB_ID);

		initIM();

	}

	private void initIM() {
		if (getApplicationInfo().packageName.equals(getMyProcessName())){
			//im初始化
			BmobIM.init(this);
			//注册消息接收器
			BmobIM.registerDefaultMessageHandler(new DemoMessageHandler(this));
		}
	}

	/** 初始化ImageLoader */
	public static void initImageLoader(Context context) {
		File cacheDir = StorageUtils.getOwnCacheDirectory(context,
				"baoku/Cache");// 获取到缓存的目录地址
		// 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 2)
				.memoryCache(new WeakMemoryCache())
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				// 将保存的时候的URI名称用MD5 加密
				.tasksProcessingOrder(QueueProcessingType.LIFO)

				// .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);// 全局初始化此配置
	}
	
	public DisplayImageOptions getOptions(int drawableId){
		return new DisplayImageOptions.Builder()
		 
		.showImageOnLoading(drawableId) //设置图片在下载期间显示的图片
		.showImageForEmptyUri(drawableId)//设置图片Uri为空或是错误的时候显示的图片
		.showImageOnFail(drawableId) //设置图片加载/解码过程中错误时候显示的图片
		.resetViewBeforeLoading(true)
		.cacheInMemory(true)//设置下载的图片是否缓存在内存中  
		.cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中 
		.imageScaleType(ImageScaleType.EXACTLY)//设置图片以如何的编码方式显示
		.bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
		.build();//构建完成
	}


	/**
	 * 获取当前运行的进程名
	 * @return
	 */
	public static String getMyProcessName() {
		try {
			File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
			BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
			String processName = mBufferedReader.readLine().trim();
			mBufferedReader.close();
			return processName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
