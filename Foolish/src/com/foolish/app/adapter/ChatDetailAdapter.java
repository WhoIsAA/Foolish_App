package com.foolish.app.adapter;

import java.util.HashMap;
import java.util.List;

import com.foolish.app.R;
import com.foolish.app.common.Consts;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChatDetailAdapter extends BaseAdapter {

	private static final int TYPE_COUNT = 2;
	private List<HashMap<String, Object>> mList;
	private LayoutInflater mInflater;
	
	public ChatDetailAdapter(Context context, List<HashMap<String, Object>> list) {
		this.mList = list;
		mInflater = LayoutInflater.from(context);
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
	public int getItemViewType(int position) {
		return (Integer) mList.get(position).get(Consts.CHAT_DETAIL_TYPE);
	}

	@Override
	public int getViewTypeCount() {
		return TYPE_COUNT;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		
		
		if(convertView == null) {
			viewHolder = new ViewHolder();
			if(getItemViewType(position) == Consts.TYPE_CHAT_MESSAGE_FROM) {
				convertView = mInflater.inflate(R.layout.listitem_chat_detail_from, parent, false);
				viewHolder.msgDate = (TextView)convertView.findViewById(R.id.id_tv_chat_detail_from_date);
				viewHolder.headImage = (ImageButton)convertView.findViewById(R.id.id_ib_chat_detail_from_head);
				viewHolder.msgContent = (TextView)convertView.findViewById(R.id.id_tv_chat_detail_from_msg);
			} else if(getItemViewType(position) == Consts.TYPE_CHAT_MESSAGE_TO) {
				convertView = mInflater.inflate(R.layout.listitem_chat_detail_to, parent, false);
				viewHolder.msgDate = (TextView)convertView.findViewById(R.id.id_tv_chat_detail_to_date);
				viewHolder.headImage = (ImageButton)convertView.findViewById(R.id.id_ib_chat_detail_to_head);
				viewHolder.msgContent = (TextView)convertView.findViewById(R.id.id_tv_chat_detail_to_msg);
			}
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		String date = mList.get(position).get(Consts.CHAT_DETAIL_DATE).toString();
		viewHolder.msgDate.setText(date);
		int head = (Integer) mList.get(position).get(Consts.CHAT_DETAIL_HEAD);
		viewHolder.headImage.setImageResource(head);
		String msg = mList.get(position).get(Consts.CHAT_DETAIL_MSG).toString();
		viewHolder.msgContent.setText(msg);		
		return convertView;
	}

	
	private static class ViewHolder {
		TextView msgDate;
		ImageButton headImage;
		TextView msgContent;
	}
	
}
