package com.smy.weitu.base;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

public class BaiduMapActivity extends BaseActivity {
	
	protected MapView mMapView = null; //地图mapview控件
	protected BaiduMap map; 
	
	protected long centerLong,centerLan; //
	
	private int markerRes; //标记物的资源id

/*	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		// 注意该方法要再setContentView方法之前实现
		setContentView(R.layout.baidumap_test);
		// 获取地图控件引用
		mMapView = (MapView) findViewById(R.id.bmapView);
	}*/

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
	}
	
	/**
	 * 在地图上添加标记物
	 * @param latitude 经度
	 * @param longitude 纬度
	 */
	public void addMarker(long latitude,long longitude){
		LatLng point = new LatLng(latitude, longitude);
		BitmapDescriptor bmp = BitmapDescriptorFactory.fromResource(markerRes);
		OverlayOptions option = new MarkerOptions().position(point).icon(bmp);
		mMapView.getMap().addOverlay(option);
	}
	public void setMarkerRes(int markerRes) {
		this.markerRes = markerRes;
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		
	}
}
