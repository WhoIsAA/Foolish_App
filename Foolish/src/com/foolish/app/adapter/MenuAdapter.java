package com.foolish.app.adapter;

import java.util.HashMap;
import java.util.List;

import com.foolish.app.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {

	private Context mContext;
	private List<HashMap<String, Object>> mList;
	private LayoutInflater mInflater;
	
	
	public MenuAdapter(Context context, List<HashMap<String, Object>> list) {
		this.mContext = context;
		this.mList = list;
		this.mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.menu_list_item, null);
			viewHolder = new ViewHolder();
			viewHolder.textView = (TextView)convertView.findViewById(R.id.menu_list_item_name);
			viewHolder.imageView = (ImageView)convertView.findViewById(R.id.menu_list_item_head);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		viewHolder.textView.setText(mList.get(position).get("menu_friends_name").toString());
		Drawable drawable = mContext.getResources().getDrawable((Integer)mList.get(position).get("menu_friends_head"));
		viewHolder.imageView.setBackgroundDrawable(drawable);
		
		return convertView;
	}

	
	private class ViewHolder {
		ImageView imageView;
		TextView textView;
	}
}
