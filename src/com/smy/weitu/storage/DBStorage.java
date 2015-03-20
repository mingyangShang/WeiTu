package com.smy.weitu.storage;

import java.util.List;

import android.net.Uri;

import com.smy.weitu.base.WeiTuApplication;
import com.smy.weitu.model.DaoSession;
import com.smy.weitu.model.WeiTuRecord;
import com.smy.weitu.model.WeiTuRecordDao;

public class DBStorage {
	public static final String DBNAME="SMYWeiTu"; //数据库名称
	
	private volatile static DBStorage singleton;
	
	private DaoSession daoSession;
	private WeiTuRecordDao recordDao;
	
	//获取单例
	public static DBStorage getSingleton(){
		if(singleton==null){
			synchronized (DBStorage.class) {
				if(singleton==null){
					singleton = new DBStorage();
					singleton.daoSession = WeiTuApplication.getDaoSession(DBNAME);
					singleton.recordDao = singleton.daoSession.getWeiTuRecordDao();
				}
			}
		}
		return singleton;
	}
	
	/**
	 * save a weiturecord
	 * @param record 
	 * @return insert or update record id
	 */
	public long saveRecord(final WeiTuRecord record){
		return recordDao.insertOrReplace(record);
	}
	
	/** save weiturecord list
	 * @param recordList
	 * @return succeed to save the list,return true is allSucceed
	 */
	public boolean saveNoteLists(final List<WeiTuRecord> recordList){
		if(recordList==null || recordList.isEmpty()){
			//此时应记录下日志，尝试存储
			return false;
		}else{
			recordDao.getSession().runInTx(new Runnable() {
				
				@Override
				public void run() {
					for(int i=0,size=recordList.size();i<size;++i){
						WeiTuRecord record = recordList.get(i);
						recordDao.insertOrReplace(record);
					}
				}
			});
		}
		return true;
	}
	
	/**
	 *delete a weiturecord 
	 * @param uri the uri of the img to be deleted
	 */
	public void deleteRecord(final Uri uri){
		if(uri==null){
			throw new IllegalArgumentException("the to bedeleted img uri must not be null");
		}
		recordDao.deleteByKey(uri.toString());
	}
	
	public void deleteRecord(String img){
		recordDao.deleteByKey(img);
	}
	
	/**
	 * delete weiturecord list
	 * @param uriList
	 */
	public void deleteRecordList(final List<Uri> uriList){
		if(uriList==null || uriList.isEmpty()){
			throw new IllegalArgumentException("the to be deleted img list must has at least 1 item");
		}
		recordDao.getSession().runInTx(new Runnable() {
			@Override
			public void run() {
				for(int i=0,size=uriList.size();i<size;++i){
					Uri uri = uriList.get(i);
					if(uri!=null){
						recordDao.deleteByKey(uri.toString());
					}
				}
			}
		});
	}
	
	/**
	 * query weiturecord with where clause
	 * eg:queryRecord("exists=?","true")
	 * @param where clause,include 'where' word
	 * @param params query parameters
	 * @return
	 */
	public List<WeiTuRecord> queryRecord(String where,String... params){
		return recordDao.queryRaw(where, params);
	}
	
	/**
	 * load the img with the specified uri
	 * @param uri 
	 * @return the weiturecord
	 */
	public WeiTuRecord loadRecord(Uri uri){
		if(uri==null){
			throw new IllegalArgumentException("the img to be loaded can't be null");
		}
		return recordDao.load(uri.toString());
	}
	
}
