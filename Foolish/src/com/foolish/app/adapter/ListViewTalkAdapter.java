package com.foolish.app.adapter;

import java.util.HashMap;
import java.util.List;

import com.foolish.app.R;
import com.foolish.app.R.layout;
import com.foolish.app.common.Consts;
import com.foolish.app.utils.StringUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewTalkAdapter extends BaseAdapter {

	private Context mContext;
	private List<HashMap<String, Object>> mList;
	private LayoutInflater mInflater;
	
	public ListViewTalkAdapter(Context context, List<HashMap<String, Object>> list) {
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
		ViewHolder viewHolder = null;
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.listitem_talk, null);
			viewHolder = new ViewHolder();
			viewHolder.headImage = (ImageView)convertView.findViewById(R.id.talk_listitem_head);
			viewHolder.talkUsername = (TextView)convertView.findViewById(R.id.talk_listitem_username);
			viewHolder.talkContent = (TextView)convertView.findViewById(R.id.talk_listitem_content);
			viewHolder.talkContentImage = (ImageView)convertView.findViewById(R.id.talk_listitem_image);
			viewHolder.talkDate = (TextView)convertView.findViewById(R.id.talk_listitem_date);
			convertView.setTag(viewHolder);
			
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		String username = mList.get(position).get(Consts.LISTVIEW_TALK_USERNAME).toString();
		viewHolder.talkUsername.setText(StringUtils.checkNull(username));
		String content = mList.get(position).get(Consts.LISTVIEW_TALK_CONTENT).toString();
		viewHolder.talkContent.setText(StringUtils.checkNull(content));
		String date = mList.get(position).get(Consts.LISTVIEW_TALK_DATE).toString();
		viewHolder.talkDate.setText(StringUtils.checkNull(date));
		
		return convertView;
	}

	
	private class ViewHolder {
		ImageView headImage;
		TextView talkUsername;
		TextView talkContent;
		ImageView talkContentImage;
		TextView talkDate;
	}
	
}
