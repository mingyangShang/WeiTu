package com.smy.weitu.test;

import uk.co.senab.photoview.PhotoViewAttacher;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

import com.smy.weitu.R;

public class TestPhotoViewActivity extends Activity {

	ImageView img;
	PhotoViewAttacher attacher;
	
	Button button;
	@Override
	public void onCreate(Bundle savedInstance){
		super.onCreate(savedInstance);
		setContentView(R.layout.photoview_test);
		img = (ImageView) findViewById(R.id.img);
		attacher = new PhotoViewAttacher(img);
		
	}

}
