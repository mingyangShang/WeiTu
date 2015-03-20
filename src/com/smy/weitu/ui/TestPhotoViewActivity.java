package com.smy.weitu.ui;

import uk.co.senab.photoview.PhotoViewAttacher;

import com.smy.weitu.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class TestPhotoViewActivity extends Activity {

	ImageView img;
	PhotoViewAttacher attacher;
	@Override
	public void onCreate(Bundle savedInstance){
		super.onCreate(savedInstance);
		setContentView(R.layout.photoview_test);
		img = (ImageView) findViewById(R.id.img);
		attacher = new PhotoViewAttacher(img);
	}

}
