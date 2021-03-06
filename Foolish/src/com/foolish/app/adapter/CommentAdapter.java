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

public class CommentAdapter extends BaseAdapter {

	private List<HashMap<String, Object>> mList;
	private Context mContext;
	private LayoutInflater mInflater;
	
	public CommentAdapter(Context context, List<HashMap<String, Object>> list) {
		this.mContext = context;
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
			convertView = mInflater.inflate(R.layout.listitem_comment, null);
			viewHolder = new ViewHolder();
			viewHolder.headImage = (ImageView)convertView.findViewById(R.id.comment_listitem_head);
			viewHolder.username = (TextView)convertView.findViewById(R.id.comment_listitem_username);
			viewHolder.commentContent = (TextView)convertView.findViewById(R.id.comment_listitem_content);
			viewHolder.commentDate = (TextView)convertView.findViewById(R.id.comment_listitem_date);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		String username = mList.get(position).get(Consts.COMMENT_USERNAME).toString();
		viewHolder.username.setText(StringUtils.checkNull(username));
		String content = mList.get(position).get(Consts.COMMENT_CONTENT).toString();
		viewHolder.commentContent.setText(StringUtils.checkNull(content));
		String date = mList.get(position).get(Consts.COMMENT_DATE).toString();
		viewHolder.commentDate.setText(StringUtils.checkNull(date));
		
		return convertView;
	}

	
	private static class ViewHolder {
		ImageView headImage;
		TextView username;
		TextView commentContent;
		TextView commentDate;
	}
}
