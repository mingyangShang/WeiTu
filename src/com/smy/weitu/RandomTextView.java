package com.smy.weitu;

import android.R.integer;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.IInterface;
import android.util.AttributeSet;
import android.view.View;

public class RandomTextView extends View {

	private String text;
	private int textColor;
	
	private Paint paint;
	private Rect bound;
	
	public RandomTextView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public RandomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RandomTextView, defStyle, 0);
		int count = ta.getIndexCount(); //共定义了多少个属性
		for(int i=0;i<count;++i){
			int attr = ta.getIndex(i);
			switch(attr){
			case R.styleable.RandomTextView_text:
				text = ta.getString(attr);
				break;
			case R.styleable.RandomTextView_textColor:
				textColor = ta.getColor(attr, Color.RED);
				break;
			}
		}
		ta.recycle(); //注意要在这里释放TypedArray对象
		
		paint = new Paint();
		paint.setColor(textColor);
		
		bound = new Rect();
		paint.getTextBounds(text, 0, text.length(), bound);
		
	}

	public RandomTextView(Context context, AttributeSet attrs) {
		this(context,attrs,0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		paint.setColor(Color.YELLOW);
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);
		paint.setColor(textColor);
		canvas.drawText(text,getWidth()/2-bound.width()/2,getHeight()/2+bound.height()/2,paint);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		
		int width = widthSize,height = heightSize;
		if(widthMode != MeasureSpec.EXACTLY){
			paint.setTextSize(20f);
			paint.getTextBounds(text, 0, text.length(), bound);
			width = getPaddingLeft()+bound.width()+getPaddingRight();
		}
		if(heightMode != MeasureSpec.EXACTLY){
			paint.setTextSize(20f);
			paint.getTextBounds(text, 0, text.length(), bound);
			height = getPaddingTop()+bound.height()+getPaddingBottom();
		}
		setMeasuredDimension(width, height);
	}
	
	
	

}
