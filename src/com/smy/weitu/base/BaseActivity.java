package com.smy.weitu.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public abstract class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	protected abstract void initData(); //初始化数据
	protected abstract void initView(); //初始化视图
	protected void init() {
		initData();
		initView();
	}
	// ==findViewById()
	protected View getView(int resId){
		return findViewById(resId);
	}
	
	protected void goTo(Class<? extends Activity> destActivity,Bundle bundle) {
		Intent intent = new Intent(this,destActivity);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	protected void goTo(Class<? extends Activity> destActivity){
		goTo(destActivity,null);
	}
	protected Bundle getBundleFromUp(){
		return getIntent().getExtras();
	}
	

}
