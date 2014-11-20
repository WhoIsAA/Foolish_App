package com.foolish.app.common;

import java.util.HashMap;
import java.util.List;

import com.foolish.app.R;
import com.foolish.app.utils.ToastUtils;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


/**
 * UI处理类
 * @author AA
 * @date 2014-11-4
 *
 */
public class UIHelper {

	
	public static final String POPUPWINDOW_KEY = "pop_key";
	public static final int POPUPWINDOW_TOP = 0;
	public static final int POPUPWINDOW_RIGHT = 1;
	public static final int POPUPWINDOW_BOTTOM = 2;
	public static final int POPUPWINDOW_LEFT = 3;
	
	
	private static PopupWindow mPopupWindow;
	private static ListView mPopupWindowListView;
	
	
	
	
	/**
	 * 在指定位置显示对话框
	 * @param context
	 * @param list
	 * @param view
	 * @param direction
	 */
	public static void showPopupWindowNearView(final Activity context, final List<HashMap<String, String>> list, final View view, int direction) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.pw_listview_layout, null);
		mPopupWindowListView = (ListView)layout.findViewById(R.id.pw_detail_listview);
		mPopupWindow = new PopupWindow(layout);
		//加上这个PopupWindow中的ListView才可以接收点击事件
		mPopupWindow.setFocusable(true);
		//设置适配器
		mPopupWindowListView.setAdapter(new SimpleAdapter(context, list, R.layout.pw_item_share_pub, new String[]{POPUPWINDOW_KEY}, new int[]{R.id.pw_detail_listview_item}));
		//设置滚动条
		mPopupWindowListView.setDivider(context.getResources().getDrawable(R.drawable.pw_list_divider));
		mPopupWindowListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ToastUtils.show(context, list.get(position).get(POPUPWINDOW_KEY));
				mPopupWindow.dismiss();
			}
		});
		//控制PopupWindow的宽度和高度自适应
		mPopupWindowListView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		mPopupWindow.setWidth(view.getWidth());
		mPopupWindow.setHeight((mPopupWindowListView.getMeasuredHeight() * list.size()));
		//设置背景图片，不能再布局中设置，要通过代码来设置
		mPopupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.pw_share_pub_bg_selector));
		//点屏幕其他地方对话框消失
		mPopupWindow.setOutsideTouchable(true);
		//设置对话框位置
		int[] location = new int[2];  
		view.getLocationOnScreen(location);
		//显示对话框
		switch (direction) {
		case POPUPWINDOW_TOP:
			mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1]-mPopupWindow.getHeight());
			break;

		case POPUPWINDOW_RIGHT:
			mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0]+view.getWidth(), location[1]);
			break;
			
		case POPUPWINDOW_BOTTOM:
			mPopupWindow.showAsDropDown(view);
			break;
			
		case POPUPWINDOW_LEFT:
			mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0]-view.getWidth(), location[1]);
			break;
		}
	}
	
	
	/**
	 * 显示对话框
	 * @param context
	 * @param width
	 * @param list
	 * @param direction
	 * @param onClickListener
	 */
	public static void showPopupWindow(final Activity context, final List<HashMap<String, String>> list, OnClickListener onClickListener) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.pw_common_layout, null);
		TextView textOne = (TextView)layout.findViewById(R.id.id_tv_pw_common_item_one);
		textOne.setText(list.get(0).get(POPUPWINDOW_KEY));
		textOne.setOnClickListener(onClickListener);
		TextView textTwo = (TextView)layout.findViewById(R.id.id_tv_pw_common_item_two);
		textTwo.setText(list.get(1).get(POPUPWINDOW_KEY));
		textTwo.setOnClickListener(onClickListener);
		TextView textThree = (TextView)layout.findViewById(R.id.id_tv_pw_common_item_three);
		textThree.setText(context.getResources().getString(R.string.cancel));
		textThree.setOnClickListener(onClickListener);
		
		mPopupWindow = new PopupWindow(layout, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
		//设置背景图片，不能再布局中设置，要通过代码来设置
		mPopupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.color.white));
		//点屏幕其他地方对话框消失
		mPopupWindow.setOutsideTouchable(true);
		//设置对话框显示和消失动画
		mPopupWindow.setAnimationStyle(R.style.popupwindow_anim_style);
		//显示对话框
		mPopupWindow.showAtLocation(context.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
	}
	
	
	/**
	 * 关闭对话框
	 */
	public static void hidePopupWindow() {
		if(mPopupWindow != null && mPopupWindow.isShowing()) {
			mPopupWindow.dismiss();
		}
	}
}
