package com.smy.weitu.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.smy.weitu.R;
import com.smy.weitu.adapter.PhotoPagerAdapter;
import com.smy.weitu.base.BaseActivity;

/**展示图片的Activity*/
public class PhotoActivity extends BaseActivity {
	private ViewPager photoViewPager; //the viewpager to show the images
	private PagerAdapter photoAdapter; //the pageradapter for the images viewpager
	
	private List<String> imgUris = new ArrayList<String>(); //imgUri list
	
	private String firstUri ; //the uri of the first when come to the activity
	@Override
	public void onCreate(Bundle savedInstance){
		onCreate(savedInstance, R.layout.activity_photo);
	}
	
	@Override 
	protected void initData() {
		Bundle data = getBundleFromUp();
		if(data!=null){
//			record = data.getParcelable("record");
			firstUri = data.getString("imgUri");
		}
		imgUris.add("drawable://"+R.drawable.ic_launcher);
		imgUris.add("drawable://"+R.drawable.deathnote);
		
		photoAdapter = new PhotoPagerAdapter(this, imgUris);
	}

	@Override
	protected void initView() {
/*		img = (ImageView) getView(R.id.img_show);
		attacher = new PhotoViewAttacher(img);
//		ImageLoader.getInstance().displayImage(record.getUri(), img);
		ImageLoader.getInstance().displayImage(imgUri, img);*/
		photoViewPager = (ViewPager) findViewById(R.id.viewpager_photos);
		photoViewPager.setAdapter(photoAdapter);
	}
}
