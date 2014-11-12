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

public class ConcernAdapter extends BaseAdapter {

	private Context mContext;
	private List<HashMap<String, Object>> mList;
	private LayoutInflater mInflater;
	
	public ConcernAdapter(Context context, List<HashMap<String, Object>> list) {
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
			convertView = mInflater.inflate(R.layout.listitem_concern, null);
			viewHolder = new ViewHolder();
			viewHolder.head = (ImageView)convertView.findViewById(R.id.concern_listitem_head);
			viewHolder.username = (TextView)convertView.findViewById(R.id.concern_listitem_username);
			viewHolder.intro = (TextView)convertView.findViewById(R.id.concern_listitem_intro);
			convertView.setTag(viewHolder);
			
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		viewHolder.head.setImageResource(R.drawable.icon_temp1);
		String username = mList.get(position).get(Consts.MINE_CONCERN_USERNAME).toString();
		viewHolder.username.setText(StringUtils.checkNull(username));
		String intro = mList.get(position).get(Consts.MINE_CONCERN_INTRO).toString();
		viewHolder.intro.setText(StringUtils.checkNull(intro));
		
		return convertView;
	}

	
	private class ViewHolder {
		ImageView head;
		TextView username;
		TextView intro;
	}
	
}
