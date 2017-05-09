package com.boss.util;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;


public class NormalUtil {

	// 放在util
	/** 初始化ImageLoader */
		public static void initImageLoader(Context context) {
			File cacheDir = StorageUtils.getOwnCacheDirectory(context,
					"baoku/Cache");// 获取到缓存的目录地址
			// 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
					context)
					// 线程池内加载的数量
					.threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 2)
					.memoryCache(new WeakMemoryCache())
					.denyCacheImageMultipleSizesInMemory()
					.discCacheFileNameGenerator(new Md5FileNameGenerator())
					// 将保存的时候的URI名称用MD5 加密
					.tasksProcessingOrder(QueueProcessingType.LIFO)
					//.discCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径
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

		
		
		





}
