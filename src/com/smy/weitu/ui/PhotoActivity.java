package com.smy.weitu.ui;

import uk.co.senab.photoview.PhotoViewAttacher;
import android.os.Bundle;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.smy.weitu.R;
import com.smy.weitu.base.BaseActivity;
import com.smy.weitu.model.WeiTuRecord;

/**展示图片的Activity*/
public class PhotoActivity extends BaseActivity {
	private ImageView img;
	private PhotoViewAttacher attacher;
	
	private WeiTuRecord record;
	private String imgUri;
	@Override
	public void onCreate(Bundle savedInstance){
		super.onCreate(savedInstance);
		setContentView(R.layout.activity_photo);
		init();
	}
	
	@Override
	protected void initData() {
		Bundle data = getBundleFromUp();
		if(data!=null){
//			record = data.getParcelable("record");
			imgUri = data.getString("imgUri");
		}
	}

	@Override
	protected void initView() {
		img = (ImageView) getView(R.id.img_show);
		attacher = new PhotoViewAttacher(img);
//		ImageLoader.getInstance().displayImage(record.getUri(), img);
		ImageLoader.getInstance().displayImage(imgUri, img);
	}

}
