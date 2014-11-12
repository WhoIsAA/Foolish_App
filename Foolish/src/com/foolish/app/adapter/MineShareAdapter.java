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

public class MineShareAdapter extends BaseAdapter {

	private Context mContext;
	private List<HashMap<String, Object>> mList;
	private LayoutInflater mInflater;
	
	public MineShareAdapter(Context context, List<HashMap<String, Object>> list) {
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
			convertView = mInflater.inflate(R.layout.listitem_share, null);
			viewHolder = new ViewHolder();
			viewHolder.head = (ImageView)convertView.findViewById(R.id.talk_listitem_head);
			viewHolder.username = (TextView)convertView.findViewById(R.id.talk_listitem_username);
			viewHolder.content = (TextView)convertView.findViewById(R.id.talk_listitem_content);
			viewHolder.contentImage = (ImageView)convertView.findViewById(R.id.talk_listitem_image);
			viewHolder.date = (TextView)convertView.findViewById(R.id.talk_listitem_date);
			convertView.setTag(viewHolder);
			
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		viewHolder.head.setImageResource(R.drawable.icon_temp2);
		String username = mList.get(position).get(Consts.MINE_SHARE_USERNAME).toString();
		viewHolder.username.setText(StringUtils.checkNull(username));
		String content = mList.get(position).get(Consts.MINE_SHARE_CONTENT).toString();
		viewHolder.content.setText(StringUtils.checkNull(content));
		viewHolder.contentImage.setBackgroundResource((Integer)mList.get(position).get(Consts.MINE_SHARE_CONTENT_IMG));
		String date = mList.get(position).get(Consts.MINE_SHARE_DATE).toString();
		viewHolder.date.setText(StringUtils.checkNull(date));
		
		return convertView;
	}

	
	private class ViewHolder {
		ImageView head;
		TextView username;
		TextView content;
		ImageView contentImage;
		TextView date;
	}
	
}
