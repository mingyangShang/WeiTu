package com.smy.weitu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.smy.weitu.SwipeListView.OnDeleteListener;
import com.smy.weitu.model.WeiTuRecord;
import com.smy.weitu.storage.DBStorage;
import com.smy.weitu.storage.FileStorage;
import com.smy.weitu.utils.SystemInfoUtil;


public class MainActivity extends Activity {
	
	private final String TAG = getClass().getSimpleName(); 
	
	private SwipeListView swipeListView;
	private MyAdapter adapter;
	private List<String> contentList = new ArrayList<String>(); 
	
	private static final int REQUEST_CAMERA = 0x11; //系统相机请求码
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.listview_test);
        
        Log.e(TAG, SystemInfoUtil.getDCIMUri().toString());
//        content://media/external/images/media
        
        DBStorage dbStorage = DBStorage.getSingleton();
//        long result = dbStorage.saveRecord(new WeiTuRecord("4", new Date(), 12L, 12L, true, "清河", "寒冬", "爱死你了"));
        dbStorage.deleteRecord("10");
        
        initList();  
        swipeListView = (SwipeListView) findViewById(R.id.my_list_view);  
        swipeListView.setOnDeleteListener(new OnDeleteListener() {  
            @Override  
            public void onDelete(int index) {  
                contentList.remove(index);  
                adapter.notifyDataSetChanged();  
            }  
        });  
        swipeListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				startActivityForResult(intent, REQUEST_CAMERA);
			}
		});
        adapter = new MyAdapter(this, 0, contentList);    
        swipeListView.setAdapter(adapter);  
    }

    

    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(resultCode != RESULT_OK){
			Log.e(TAG,"未成功返回数据");
			return ;
		}
		switch(requestCode){
			case REQUEST_CAMERA:
				if(data!=null){
					Uri imgUri = data.getData();
					if(imgUri==null){
						Bitmap imgBitmap = (Bitmap) data.getExtras().get("data");
						if(imgBitmap!=null){
							imgUri = Uri.parse(FileStorage.getSingleton().saveImg(getContentResolver(), "title", imgBitmap));
							Log.e(TAG, "imgUri:"+imgUri);
							Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);   
							intent.setData(imgUri);   
							sendBroadcast(intent); 
						}
					}
				}
				break;
		}
	}



	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initList() {  
        contentList.add("Content Item 1");  
        contentList.add("Content Item 2");  
        contentList.add("Content Item 3");  
        contentList.add("Content Item 4");  
        contentList.add("Content Item 5");  
        contentList.add("Content Item 6");  
        contentList.add("Content Item 7");  
        contentList.add("Content Item 8");  
        contentList.add("Content Item 9");  
        contentList.add("Content Item 10");  
        contentList.add("Content Item 11");  
        contentList.add("Content Item 12");  
        contentList.add("Content Item 13");  
        contentList.add("Content Item 14");  
        contentList.add("Content Item 15");  
        contentList.add("Content Item 16");  
        contentList.add("Content Item 17");  
        contentList.add("Content Item 18");  
        contentList.add("Content Item 19");  
        contentList.add("Content Item 20");  
    }  
}
