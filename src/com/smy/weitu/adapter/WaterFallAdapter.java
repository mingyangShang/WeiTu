package com.smy.weitu.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.smy.weitu.R;
import com.smy.weitu.model.WeiTuRecord;

public class WaterFallAdapter extends BaseAdapter {
	
	private List<WeiTuRecord> records; //记录
	
	private final OnWeiTuClickListener weiTuClickListener;
	
	private final LayoutInflater inflater;
	
	private final Drawable defaultImg; //默认图片
	
	public WaterFallAdapter(List<WeiTuRecord> records, Context context) {
		super();
		try{
			weiTuClickListener = (OnWeiTuClickListener) context;
		}catch(ClassCastException exception){
			throw new IllegalArgumentException("the activity class must implements the interface OnWeiTuClickListener");
		}
		this.records = records;
		inflater = LayoutInflater.from(context);
		defaultImg = context.getResources().getDrawable(R.drawable.ic_launcher);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return records!=null?records.size():0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if(convertView==null){
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.adapter_waterfall, null);
			viewHolder.ivImg = (ImageView) convertView.findViewById(R.id.iv_img);
			viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
			viewHolder.layoutIntro = (RelativeLayout) convertView.findViewById(R.id.layout_intro);
			viewHolder.tvMsg = (TextView) convertView.findViewById(R.id.tv_msg);
			viewHolder.pbLoading = (ProgressBar) convertView.findViewById(R.id.pb_loading);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		initItem(position,viewHolder);
		return convertView;
	}
	
	private void initItem(final int position,final ViewHolder viewHolder) {
		WeiTuRecord record = records.get(position);
		final String imgUri = record.getUri();
		final RelativeLayout layoutIntro = viewHolder.layoutIntro;
		final ImageView ivImg = viewHolder.ivImg;
		final TextView tvTime = viewHolder.tvTime;
		final TextView tvMsg = viewHolder.tvMsg;
		ivImg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				weiTuClickListener.onWeiTuClick(position);
			}
		});
		ImageLoader.getInstance().displayImage(imgUri, viewHolder.ivImg, new ImageLoadingListener() {
			
			@Override
			public void onLoadingStarted(String arg0, View arg1) {
				// TODO Auto-generated method stub
				Log.d("imageLoader", "loading start");
			}
			
			@Override
			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
				// TODO Auto-generated method stub
				Log.d("imageLoader", "loading failed");
			}
			
			@Override
			public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
				// TODO Auto-generated method stub
				Log.d("imageLoader", "loading complete");
				if(arg2==null){
					System.out.println("null");
				}else{
					System.out.println("not null");
				}
				generateProminentIntro(arg2); 
			}
			
			@Override
			public void onLoadingCancelled(String arg0, View arg1) {
				// TODO Auto-generated method stub
				Log.d("imageLoader", "loading canceled");
			}
			
			private void generateProminentIntro(Bitmap bmp){
				Palette.Swatch vibrantSwatch = Palette.generate(bmp).getVibrantSwatch();
				if(vibrantSwatch!=null){
					layoutIntro.setBackgroundColor(vibrantSwatch.getRgb());
					tvTime.setTextColor(vibrantSwatch.getTitleTextColor());
					tvMsg.setTextColor(vibrantSwatch.getBodyTextColor());
					System.out.println("swatch is not null");
				}
			}
		});
		
		/*tvTime.setText(record.getTime()!=null?record.getTime().toString():"");
		tvMsg.setText(record.getMsg()!=null?record.getMsg():"");*/
	}

	protected class ViewHolder{
		public ImageView ivImg;
		public TextView tvTime;
		private RelativeLayout layoutIntro;
		public TextView tvMsg;
		public ProgressBar pbLoading;
	}
	
	/**瀑布流的图片点击事件*/
	public interface OnWeiTuClickListener{
		void onWeiTuClick(int position);
	}
	
}
