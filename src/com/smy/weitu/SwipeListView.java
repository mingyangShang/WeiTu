package com.smy.weitu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class SwipeListView extends ListView implements OnTouchListener,
		OnGestureListener {
	
	private boolean isDeleteShow = false; //是否显示删除按钮
	private View deleteView; //删除按钮
	private ViewGroup selectedItem; //被选中的listview的item
	
	private int selectedPosition; //被选中的listview的item的顺序号
	
	private GestureDetector detector; //手势检测
	
	private OnDeleteListener onDeleteListener; //删除按钮的监听器

	public SwipeListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		detector = new GestureDetector(this);
		this.setOnTouchListener(this);
	}
	/**设置删除按钮的监听器*/
	public void setOnDeleteListener(OnDeleteListener onDeleteListener){
		this.onDeleteListener = onDeleteListener;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		if(!isDeleteShow){
			selectedPosition = pointToPosition((int)(e.getX()),(int)(e.getY()));
		}
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		if(!isDeleteShow && Math.abs(velocityX)>Math.abs(velocityY)){
			deleteView = LayoutInflater.from(getContext()).inflate(R.layout.swipe_delete, null);
			deleteView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					selectedItem.removeView(deleteView);
					deleteView = null;
					isDeleteShow = false;
					onDeleteListener.onDelete(selectedPosition);
				}
			});
			selectedItem = (ViewGroup) getChildAt(selectedPosition-getFirstVisiblePosition());
			Log.e("selected", selectedPosition+"");
			Log.e("visiable",getFirstVisiblePosition()+"");
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
						android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			params.addRule(RelativeLayout.CENTER_VERTICAL);
			selectedItem.addView(deleteView,params);
		}
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(isDeleteShow){
			selectedItem.removeView(deleteView);
			isDeleteShow = false;
			deleteView = null;
			return false;
		}else{
			return detector.onTouchEvent(event);
		}
	}
	
	/**删除按钮监听器*/
	public static interface OnDeleteListener{
		void onDelete(int position);
	}

}
