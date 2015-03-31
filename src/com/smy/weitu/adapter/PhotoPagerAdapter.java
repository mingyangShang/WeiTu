package com.smy.weitu.adapter;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.smy.weitu.R;
import com.smy.weitu.ui.PhotoMapActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * a pageradapter to fill the viewpager's content
 * @author smy
 */

public class PhotoPagerAdapter extends PagerAdapter{
	private List<String> imgUris; //a list for the imgUris
	
	private LayoutInflater inflater;
	private Context context;
	
	private boolean isOptionShown = true; // whether the option menu is shown
	
	public PhotoPagerAdapter(Context context,List<String> uris){
			this.context = context;
			inflater = LayoutInflater.from(this.context);
			this.imgUris = uris==null?new ArrayList<String>():uris;
	}
	
	@Override
	public int getCount() {
		return imgUris.size();
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
		System.out.println("destory item");
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		final View itemView = inflater.inflate(R.layout.item_photo, null); //inflate the item layout
		final ImageView photo = (ImageView) itemView.findViewById(R.id.img_show);
		final PhotoViewAttacher attacher = new PhotoViewAttacher(photo);
		ImageLoader.getInstance().displayImage(imgUris.get(position), photo);
		
		final ViewGroup option = (ViewGroup) itemView.findViewById(R.id.layout_msg); //the option layout
		option.setVisibility(isOptionShown?View.VISIBLE:View.GONE);
		
		final TextView msg = (TextView) itemView.findViewById(R.id.tv_msg); //the msg textview
		msg.setText(""+position);
		
		final View menu = itemView.findViewById(R.id.img_menu);
		final int pos = position;
		menu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,PhotoMapActivity.class);
				intent.putExtra("pos", pos);
				context.startActivity(intent);
			}
		});
		
		final ViewGroup containerGroup = container;
		itemView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				isOptionShown =  !isOptionShown;
				option.setVisibility(isOptionShown?View.VISIBLE:View.GONE);
				refreshOptionViews(containerGroup);
			}
		}); //set the listener for the item view
		container.addView(itemView,0); //
		return itemView; //return the item layout view
	}

	@Override
	public void finishUpdate(ViewGroup container) {
		// TODO Auto-generated method stub
		super.finishUpdate(container);
		System.out.println("end update");
	}

	@Override
	public void startUpdate(ViewGroup container) {
		// TODO Auto-generated method stub
		super.startUpdate(container);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1; 
	}

	public boolean isOptionShown() {
		return isOptionShown;
	}

	public void setOptionShown(boolean isOptionShown) {
		this.isOptionShown = isOptionShown;
	}
	
	//refresh views when options's visibility changed
	protected  void refreshOptionViews(ViewGroup container){
		final int childCount = container.getChildCount();
		for(int i=0;i<childCount;++i){
			View view = container.getChildAt(i); //item view
			view.findViewById(R.id.layout_msg).setVisibility(isOptionShown?View.VISIBLE:view.GONE);
		}
	}
}
