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

public class ShareAdapter extends BaseAdapter {

	private Context mContext;
	private List<HashMap<String, Object>> mList;
	private LayoutInflater mInflater;
	
	public ShareAdapter(Context context, List<HashMap<String, Object>> list) {
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
			viewHolder.headImage = (ImageView)convertView.findViewById(R.id.id_iv_share_head);
			viewHolder.shareUsername = (TextView)convertView.findViewById(R.id.id_tv_share_username);
			viewHolder.shareDate = (TextView)convertView.findViewById(R.id.id_tv_share_date);
			viewHolder.shareContent = (TextView)convertView.findViewById(R.id.id_tv_share_content);
			viewHolder.shareContentImage = (ImageView)convertView.findViewById(R.id.id_iv_share_content_img);
			convertView.setTag(viewHolder);
			
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		String username = mList.get(position).get(Consts.SHARE_USERNAME).toString();
		viewHolder.shareUsername.setText(StringUtils.checkNull(username));
		String content = mList.get(position).get(Consts.SHARE_CONTENT).toString();
		viewHolder.shareContent.setText(StringUtils.checkNull(content));
		String date = mList.get(position).get(Consts.SHARE_DATE).toString();
		viewHolder.shareDate.setText(StringUtils.checkNull(date));
		
		return convertView;
	}

	
	private class ViewHolder {
		ImageView headImage;
		TextView shareUsername;
		TextView shareDate;
		TextView shareContent;
		ImageView shareContentImage;
		TextView shareZanCount;
		TextView shareCommentCount;
	}
	
}
