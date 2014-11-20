package com.foolish.app.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.foolish.app.R;
import com.foolish.app.adapter.ShareAdapter;
import com.foolish.app.common.Consts;
import com.foolish.app.ui.activity.ShareDetailActivity;
import com.foolish.app.ui.activity.SharePubActivity;
import com.foolish.app.ui.widget.XListView;
import com.foolish.app.ui.widget.XListView.IXListViewListener;

import android.R.anim;
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


public class ShareFragment extends Fragment implements OnClickListener, OnItemClickListener, IXListViewListener{

	private static final String TAG = "首页";
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;
	
	private View mShareView;
	private XListView mShareListView;
	private ShareAdapter mAdapter;
	private List<HashMap<String, Object>> mShareList;
	
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
		if(mShareView == null) {
			mShareView = inflater.inflate(R.layout.fragment_share, container, false);
			initView();
		}
		
		ViewGroup parent = (ViewGroup)mShareView.getParent();
		if(parent != null) {
			parent.removeView(mShareView);
		}
		
		return mShareView;
	}
	
	
	private void initView() {
		initListView();
		
	}
	
	
	/**
	 * 初始化列表项
	 */
	private void initListView() {
		mShareListView = (XListView)mShareView.findViewById(R.id.id_lv_share_listview);
		mShareListView.setPullLoadEnable(true);
		mShareListView.setPullRefreshEnable(true);
		mShareListView.setXListViewListener(this,1);
		mShareListView.setAdapter(mAdapter);
		mShareListView.setRefreshTime();
		
		TextView emptyTextView = (TextView)mShareView.findViewById(R.id.id_tv_share_listview_empty);
		mShareList = getData();
		mAdapter = new ShareAdapter(getActivity(), mShareList);
		mShareListView.setAdapter(mAdapter);
		mShareListView.setEmptyView(emptyTextView);
		mShareListView.setOnItemClickListener(this);
		//去除行与行之间的黑线
//		mShareListView.setDivider(null);
		
		
	}
	
	
	private List<HashMap<String, Object>> getData() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		for(int i=0; i<total; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(Consts.SHARE_USERNAME, "用户" + i);
			map.put(Consts.SHARE_CONTENT, contents[i%contents.length]);
			map.put(Consts.SHARE_DATE, (i+1)*2 + "小时前");
			list.add(map);
		}
		return list;
	}
	
	
	private void onLoad() {
		mShareListView.stopRefresh();
		mShareListView.stopLoadMore();
		mShareListView.setRefreshTime();
	}
	

	@Override
	public void onClick(View v) {
		Intent intent = null;
		
		switch (v.getId()) {
		case R.id.title_bar_right_img:
			intent = new Intent(getActivity(), SharePubActivity.class);
			startActivity(intent);
			
			break;

		
		}
		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(getActivity(), ShareDetailActivity.class);
		startActivity(intent);
		
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
			mShareList.clear();
			mShareList.addAll(getData());
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
			mShareList.clear();
			mShareList.addAll(getData());
			mAdapter.notifyDataSetChanged();
			onLoad();
		}
	}
	

	@Override
	public void onRefresh(int id) {
		new RefreshTask().execute(0);
	}


	@Override
	public void onLoadMore(int id) {
		new LoadTask().execute(0);
	}
}
