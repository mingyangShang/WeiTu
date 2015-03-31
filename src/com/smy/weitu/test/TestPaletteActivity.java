package com.smy.weitu.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.Palette.PaletteAsyncListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.smy.weitu.R;

public class TestPaletteActivity extends Activity {
	ImageView img;
	TextView tv;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_palette);
		img = (ImageView) findViewById(R.id.img);
		tv = (TextView) findViewById(R.id.tv);
		
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.image06);
		Palette.generateAsync(bmp, new PaletteAsyncListener() {
			@Override
			public void onGenerated(Palette arg0) {
				//尝试取得鲜明的颜色和柔和的颜色
				Palette.Swatch swatch = arg0.getVibrantSwatch();
				swatch = swatch==null?arg0.getMutedSwatch():swatch;
				if(swatch!=null){
					tv.setBackgroundColor(swatch.getRgb());
					tv.setText("shangmingyang");
					System.out.println(swatch.getPopulation());
					tv.setTextColor(swatch.getTitleTextColor());
				}
			}
		});
		
	}
}
