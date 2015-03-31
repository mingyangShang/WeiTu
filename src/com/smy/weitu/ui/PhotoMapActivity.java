package com.smy.weitu.ui;

import android.content.Intent;
import android.os.Bundle;

import com.baidu.mapapi.map.MapView;
import com.smy.weitu.R;
import com.smy.weitu.base.BaiduMapActivity;

public class PhotoMapActivity extends BaiduMapActivity {
	
	@Override
	public void onCreate(Bundle savedIntanceState) {
		super.onCreate(savedIntanceState, R.layout.activity_photo_map);
	}

	@Override
	protected void initData() {
		super.initData();
		Intent intent = getIntent();
		final int pos = intent.getIntExtra("pos", 0);
		System.out.println("pos:"+pos);
	}

	@Override
	protected void initView() {
		super.initView();
		mMapView = (MapView) findViewById(R.id.map_view); //get mapview
		map = mMapView.getMap();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

}
