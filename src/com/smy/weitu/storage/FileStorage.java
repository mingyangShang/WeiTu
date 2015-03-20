package com.smy.weitu.storage;

import com.smy.weitu.base.WeiTuApplication;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

public class FileStorage extends AbsStorage{
	private static volatile FileStorage singleton;
	private FileStorage(){}
	
	//获取单例
	public static FileStorage getSingleton(){
		if(singleton==null){
			synchronized (DBStorage.class) {
				if(singleton==null){
					singleton = new FileStorage();
				}
			}
		}
		return singleton;
	}
	
	/**
	 * 存储图片到某系统文件路径
	 * @param path 存储路径
	 * @param imgBitmap 要存储的图片
	 * @return 是否存储成功
	 */
	public boolean saveImg(String dirPath,String imgName,Bitmap imgBitmap){
		return true;
	}
	
	/**
	 * 保存图片到系统图库
	 * @return 存储图片的地址
	 */
	public String saveImg(ContentResolver cr,String imgName,Bitmap imgBitmap){
		return MediaStore.Images.Media.insertImage(cr, imgBitmap, imgName, "");
	}

/*	@Override
	public AbsStorage getSingleInstance() {
		if(this.mInstance==null){
			synchronized (FileStorage.class) {
				if(this.mInstance==null){
					this.mInstance = new FileStorage();
				}
			}
			
		}
		return this.mInstance;
	}*/
	
}
