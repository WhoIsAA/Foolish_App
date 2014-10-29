package com.foolish.app.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.foolish.app.R;
import com.foolish.app.adapter.ListViewTalkAdapter;
import com.foolish.app.common.Consts;
import com.foolish.app.ui.activity.TalkPubActivity;
import com.foolish.app.ui.widget.PullToRefreshListView;
import com.foolish.app.ui.widget.PullToRefreshListView.FoolListViewListener;
import com.foolish.app.utils.StringUtils;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TalkFragment extends Fragment implements OnClickListener, OnItemClickListener, FoolListViewListener{

	private static final String TAG = "唧唧歪歪";
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;
	
	private View mTalkView;
	private PullToRefreshListView mTalkListView;
	private ListViewTalkAdapter mAdapter;
	private List<HashMap<String, Object>> mTalkList;
	
	private int total = 10;
	
	private String[] contents = { "为什么牛不会吹牛？", "求解：十万个为什么为什么叫做十万个为什么？",
			"这世界上真的有外星人吗？", "我家的拖鞋哪儿去了？", "长得像长颈鹿的动物是什么？", "奥特曼住在哪个星球上？",
			"我妈让我今晚好好在家复习不能出去玩，我该怎么办？", "台湾什么小吃最出名？", "后会无期这部电影是谁导演的？",
			"我是不是很罗嗦？", "最后一项为什么不能显示？", "为什么牛不会吹牛？", "求解：十万个为什么为什么叫做十万个为什么？",
			"这世界上真的有外星人吗？", "我家的拖鞋哪儿去了？", "长得像长颈鹿的动物是什么？", "奥特曼住在哪个星球上？",
			"我妈让我今晚好好在家复习不能出去玩，我该怎么办？", "台湾什么小吃最出名？", "后会无期这部电影是谁导演的？",
			"我是不是很罗嗦？", "最后一项为什么不能显示？", "为什么牛不会吹牛？", "求解：十万个为什么为什么叫做十万个为什么？",
			"这世界上真的有外星人吗？", "我家的拖鞋哪儿去了？", "长得像长颈鹿的动物是什么？", "奥特曼住在哪个星球上？",
			"我妈让我今晚好好在家复习不能出去玩，我该怎么办？", "台湾什么小吃最出名？", "后会无期这部电影是谁导演的？",
			"我是不是很罗嗦？", "最后一项为什么不能显示？" };
	
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(mTalkView == null) {
			mTalkView = inflater.inflate(R.layout.fragment_talk, container, false);
			initView();
		}
		
		ViewGroup parent = (ViewGroup)mTalkView.getParent();
		if(parent != null) {
			parent.removeView(mTalkView);
		}
		
		return mTalkView;
	}
	
	
	private void initView() {
		initTitle();
		initListView();
		
	}
	
	
	/**
	 * 初始化标题栏
	 */
	private void initTitle() {
		//左边
		mTitleBackImg = (ImageButton) mTalkView.findViewById(R.id.title_bar_left_img);
		mTitleBackImg.setVisibility(View.GONE);
		mTitleBack = (TextView)mTalkView.findViewById(R.id.title_bar_left);
		mTitleBack.setVisibility(View.GONE);
		//右边
		mTitleNext = (TextView) mTalkView.findViewById(R.id.title_bar_right);
		mTitleNext.setVisibility(View.GONE);
		mTitleNextImg = (ImageButton)mTalkView.findViewById(R.id.title_bar_right_img);
		mTitleNextImg.setVisibility(View.VISIBLE);
		mTitleNextImg.setImageResource(R.drawable.header_talk_btn);
		mTitleNextImg.setOnClickListener(this);
		//中间
		mTitleText = (TextView) mTalkView.findViewById(R.id.title_bar_center);
		mTitleText.setText(TAG);
	}

	
	/**
	 * 初始化列表项
	 */
	private void initListView() {
		mTalkListView = (PullToRefreshListView)mTalkView.findViewById(R.id.lv_talk);
		mTalkListView.setPullLoadEnable(true);
		mTalkListView.setPullRefreshEnable(true);
		mTalkListView.setAutoLoadEnable(true);
		mTalkListView.setFoolListViewListener(this);
		mTalkListView.setRefreshTime(StringUtils.getCurTime());
		
		TextView emptyTextView = (TextView)mTalkView.findViewById(R.id.tv_talk_listview_empty);
		mTalkList = getData();
		mAdapter = new ListViewTalkAdapter(getActivity(), mTalkList);
		mTalkListView.setAdapter(mAdapter);
		mTalkListView.setEmptyView(emptyTextView);
		mTalkListView.setOnItemClickListener(this);
		
		
	}
	
	
	private List<HashMap<String, Object>> getData() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		for(int i=0; i<total; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(Consts.LISTVIEW_TALK_USERNAME, "用户" + i);
			map.put(Consts.LISTVIEW_TALK_CONTENT, contents[i%contents.length]);
			map.put(Consts.LISTVIEW_TALK_DATE, (i+1)*2 + "小时前");
			list.add(map);
		
		}
		return list;
	}
	
	
	private void onLoad() {
		mTalkListView.stopRefresh();
		mTalkListView.stopLoadMore();
		mTalkListView.setRefreshTime(StringUtils.getCurTime());
	}
	

	@Override
	public void onClick(View v) {
		Intent intent = null;
		
		switch (v.getId()) {
		case R.id.title_bar_right_img:
			intent = new Intent(getActivity(), TalkPubActivity.class);
			startActivity(intent);
			
			break;

		
		}
		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(getActivity(), position+"", Toast.LENGTH_SHORT).show();
		
	}
	
	
	private class RefreshTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... params) {
			try {
				total = 10;
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		
		@Override
		protected void onPostExecute(Integer result) {
			mTalkList.clear();
			mTalkList.addAll(getData());
			mAdapter.notifyDataSetChanged();
			onLoad();
		}
	}
	
	
	private class LoadTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... params) {
			try {
				total = total + 5;
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		
		@Override
		protected void onPostExecute(Integer result) {
			mTalkList.clear();
			mTalkList.addAll(getData());
			mAdapter.notifyDataSetChanged();
			onLoad();
		}
	}
	
	
	@Override
	public void onRefresh() {
		new RefreshTask().execute(0);
	}

	@Override
	public void onLoadMore() {
		new LoadTask().execute(0);

	}
}
