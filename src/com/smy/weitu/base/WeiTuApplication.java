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
import com.smy.weitu.R;
import com.smy.weitu.model.DaoMaster;
import com.smy.weitu.model.DaoSession;
import com.smy.weitu.model.DaoMaster.OpenHelper;
import com.smy.weitu.utils.SystemInfoUtil;
import com.smy.weitu.utils.SystemInfoUtil.SizeUnit;

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
			.cacheInMemory()
			.displayer(new FadeInBitmapDisplayer(50))
			.bitmapConfig(Bitmap.Config.RGB_565)
			.imageScaleType(ImageScaleType.EXACTLY)
			.showImageForEmptyUri(R.drawable.deathnote)
			.showImageOnFail(R.drawable.deathnote)
			.build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
			.memoryCache(new UsingFreqLimitedMemoryCache((int) SystemInfoUtil.getAppMaxMemory(SizeUnit.KB)))
			.defaultDisplayImageOptions(defaultOptions)
			.build();
		ImageLoader.getInstance().init(config);
		//初始化百度地图
//		SDKInitializer.initialize(this);
		
		//test the application memory size
		System.out.println("totalSize:"+SystemInfoUtil.getTotalMemory(this,SizeUnit.MB));
		System.out.println("avaliableSize:"+SystemInfoUtil.getAvailMemory(this,SizeUnit.MB));
		System.out.println("maxAppSize:"+SystemInfoUtil.getAppMaxMemory(SizeUnit.MB));
		System.out.println("totalAppSize:"+SystemInfoUtil.getAppTotalMemory(SizeUnit.MB));
		System.out.println("availAppSize:"+SystemInfoUtil.getAppAvailMemory(SizeUnit.MB));
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
