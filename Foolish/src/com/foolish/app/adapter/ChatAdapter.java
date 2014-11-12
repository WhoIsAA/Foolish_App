package com.foolish.app.adapter;

import java.util.HashMap;
import java.util.List;

import com.foolish.app.R;
import com.foolish.app.common.Consts;
import com.foolish.app.utils.StringUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatAdapter extends BaseAdapter {

	private List<HashMap<String, Object>> mList;
	private LayoutInflater mInflater;
	
	public ChatAdapter(Context context, List<HashMap<String, Object>> list) {
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
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.listitem_chat, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.headImage = (ImageView)convertView.findViewById(R.id.id_ib_chat_head);
			viewHolder.userName = (TextView)convertView.findViewById(R.id.id_tv_chat_username);
			viewHolder.latestDate = (TextView)convertView.findViewById(R.id.id_tv_chat_latest_date);
			viewHolder.latestMessage = (TextView)convertView.findViewById(R.id.id_tv_chat_latest_msg);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		int head = (Integer) mList.get(position).get(Consts.CHAT_HEAD);
		viewHolder.headImage.setImageResource(head);
		String username = mList.get(position).get(Consts.CHAT_USERNAME).toString();
		viewHolder.userName.setText(StringUtils.checkNull(username));
		String date = mList.get(position).get(Consts.CHAT_LATEST_DATE).toString();
		viewHolder.latestDate.setText(StringUtils.checkNull(date));
		String msg = mList.get(position).get(Consts.CHAT_LATEST_MSG).toString();
		viewHolder.latestMessage.setText(StringUtils.checkNull(msg));
		
		return convertView;
	}

	
	private static class ViewHolder {
		ImageView headImage;
		TextView userName;
		TextView latestDate;
		TextView latestMessage;
	}
	
}
