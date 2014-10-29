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

public class ListViewFaqAdapter extends BaseAdapter {

	private List<HashMap<String, Object>> mList;
	private Context mContext;
	private LayoutInflater mInflater;
	
	public ListViewFaqAdapter(Context context, List<HashMap<String, Object>> list) {
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
			convertView = mInflater.inflate(R.layout.listitem_faq, null);
			viewHolder = new ViewHolder();
			viewHolder.headImage = (ImageView)convertView.findViewById(R.id.question_listitem_head);
			viewHolder.questionTitle = (TextView)convertView.findViewById(R.id.question_listitem_title);
			viewHolder.questionAuthor = (TextView)convertView.findViewById(R.id.question_listitem_author);
			viewHolder.questionDate = (TextView)convertView.findViewById(R.id.question_listitem_date);
			viewHolder.questionCount = (TextView)convertView.findViewById(R.id.question_listitem_count);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		String title = mList.get(position).get(Consts.LISTVIEW_FAQ_TITLE).toString();
		viewHolder.questionTitle.setText(StringUtils.checkNull(title));
		String author = mList.get(position).get(Consts.LISTVIEW_FAQ_AUTHOR).toString();
		viewHolder.questionAuthor.setText(StringUtils.checkNull(author));
		String date = mList.get(position).get(Consts.LISTVIEW_FAQ_DATE).toString();
		viewHolder.questionDate.setText(StringUtils.checkNull(date));
		String count = mList.get(position).get(Consts.LISTVIEW_FAQ_COUNT).toString();
		viewHolder.questionCount.setText(StringUtils.emptyConvertZero(count));
		
		
		return convertView;
	}

	
	private static class ViewHolder {
		ImageView headImage;
		TextView questionTitle;
		TextView questionAuthor;
		TextView questionDate;
		TextView questionCount;
	}
	
}
