package com.smy.weitu.base;

import android.app.Application;
import android.graphics.Bitmap;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.smy.weitu.model.DaoMaster;
import com.smy.weitu.model.DaoSession;
import com.smy.weitu.model.DaoMaster.OpenHelper;

public class WeiTuApplication extends Application {
	private static WeiTuApplication mInstance;
	
	private static DaoMaster daoMaster;
	private static DaoSession daoSession;
	
	@Override
	public void onCreate(){
		if(mInstance==null){
			mInstance = this;
		}
		//初始化图片加载库
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
			.cacheOnDisc()
			.cacheInMemory()
			.displayer(new FadeInBitmapDisplayer(50))
			.bitmapConfig(Bitmap.Config.RGB_565)
			.imageScaleType(ImageScaleType.EXACTLY)
			.build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
			.memoryCache(new UsingFreqLimitedMemoryCache(16*1024*1024))
			.defaultDisplayImageOptions(defaultOptions)
			.build();
		ImageLoader.getInstance().init(config);
		//初始化百度地图
//		SDKInitializer.initialize(this);
	}
	public static WeiTuApplication getInstance(){
		return mInstance;
	}
	
	//取得DaoMaster
	public static DaoMaster getDaoMaster(String dbname){
		if(daoMaster==null){
			OpenHelper helper = new DaoMaster.DevOpenHelper(getInstance(), dbname, null);
			daoMaster = new DaoMaster(helper.getWritableDatabase());
		}
		return daoMaster;
	}
	
	//取得DaoSession
	public static DaoSession getDaoSession(String dbname){
		if(daoSession==null){
			if(daoMaster==null){
				daoMaster = getDaoMaster(dbname);
			}
			daoSession = daoMaster.newSession();
		}
		return daoSession;
	}

}
