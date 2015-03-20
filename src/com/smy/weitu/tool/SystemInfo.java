package com.smy.weitu.tool;

import java.io.File;

import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;

public class SystemInfo {
	/**
	 * 判断是否有sd卡
	 * @return true有，false无
	 */
	public static boolean isSDExists(){
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}
	
	/**
	 * 取得sd卡总空间
	 * @return	sd卡总空间(MB)
	 */
	public static long getSDAllSize(){
		if(isSDExists()){
		      File path = Environment.getExternalStorageDirectory(); 
		      StatFs sf = new StatFs(path.getPath()); 
		      //获取单个数据块的大小(Byte)
		      long blockSize = sf.getBlockSize(); 
		      //获取所有数据块数
		      long allBlocks = sf.getBlockCount();
		      //return allBlocks * blockSize; //单位Byte
		      //return (allBlocks * blockSize)/1024; //单位KB
		      return (allBlocks * blockSize)/1024/1024; //单位MB
		}else{
			return 0L;
		}
	}
	
	/**
	 * 取得sd卡可用空间
	 * @return	sd卡可用空间(MB)
	 */
	public static long getSDAvailableSize(){
		if(isSDExists()){
		      File path = Environment.getExternalStorageDirectory(); 
		      StatFs sf = new StatFs(path.getPath()); 
		      //获取单个数据块的大小(Byte)
		      long blockSize = sf.getBlockSize(); 
		      //空闲的数据块的数量
		      long freeBlocks = sf.getAvailableBlocks();
		      //return freeBlocks * blockSize;  //单位Byte
		      //return (freeBlocks * blockSize)/1024;   //单位KB
		      return (freeBlocks * blockSize)/1024 /1024; //单位MB
		}else{
			return 0L;
		}
	}
	
	/**
	 * 获取系统存储相片的Uri
	 * @return 系统存储相片的Uri
	 */
	public static Uri getDCIMUri(){
		return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
	}
}
