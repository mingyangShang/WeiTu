package com.smy.weitu.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import com.huewu.pla.lib.MultiColumnListView;
import com.smy.weitu.R;
import com.smy.weitu.adapter.WaterFallAdapter;
import com.smy.weitu.adapter.WaterFallAdapter.OnWeiTuClickListener;
import com.smy.weitu.base.BaseActivity;
import com.smy.weitu.model.WeiTuRecord;
import com.smy.weitu.utils.SystemInfoUtil;

/**图库界面*/
public class MainActivity extends BaseActivity implements OnWeiTuClickListener{
	
	private MultiColumnListView waterfallList;
	private List<WeiTuRecord> records;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState,R.layout.activity_main);
		
	}

	@Override
	protected void initData(){
		records = new ArrayList<WeiTuRecord>();
		records.add(new WeiTuRecord("drawable://"+R.drawable.ic_launcher));
		records.add(new WeiTuRecord("drawable://"+R.drawable.image06));
		records.add(new WeiTuRecord("drawable://"+R.drawable.img1));
		records.add(new WeiTuRecord("drawable://"+R.drawable.image06));
		records.add(new WeiTuRecord("drawable://"+R.drawable.deathnote));
	}
	
	@Override
	protected void initView() {
		waterfallList = (MultiColumnListView) getView(R.id.list_waterfall);
		WaterFallAdapter adapter = new WaterFallAdapter(records, this);
		waterfallList.setAdapter(adapter);
	}

	//点击瀑布流图片事件
	@Override
	public void onWeiTuClick(int position) {
		Bundle data = new Bundle();
//		data.putParcelable("record", records.get(position));
		data.putString("imgUri", records.get(position).getUri());
		goTo(PhotoActivity.class,data);
	}
	
	
	
		
}
