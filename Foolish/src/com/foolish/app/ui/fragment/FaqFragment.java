package com.foolish.app.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.foolish.app.R;
import com.foolish.app.adapter.ListViewFaqAdapter;
import com.foolish.app.common.Consts;
import com.foolish.app.ui.activity.QuestionPubActivity;
import com.foolish.app.ui.widget.PullToRefreshListView;
import com.foolish.app.ui.widget.PullToRefreshListView.FoolListViewListener;
import com.foolish.app.utils.StringUtils;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 你问我答
 * 
 * @author AA
 *
 */
public class FaqFragment extends Fragment implements OnClickListener,
		OnItemClickListener, FoolListViewListener {

	private static final String TAG = "你问我答";
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;

	private View mFaqView;
	private PullToRefreshListView mFAQListView;
	private ListViewFaqAdapter mAdapter;

	private List<HashMap<String, Object>> mFaqList;

	private int total = 10;

	private String[] titles = { "为什么牛不会吹牛？", "求解：十万个为什么为什么叫做十万个为什么？",
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mFaqView == null) {
			mFaqView = inflater
					.inflate(R.layout.fragment_faq, container, false);
			initView();
		}

		ViewGroup parent = (ViewGroup) mFaqView.getParent();
		if (parent != null) {
			parent.removeView(mFaqView);
		}

		return mFaqView;
	}

	private void initView() {
		initTitle();
		initListView();
	}

	/**
	 * 初始化标题栏
	 */
	private void initTitle() {
		// 左边
		mTitleBackImg = (ImageButton) mFaqView.findViewById(R.id.title_bar_left_img);
		mTitleBackImg.setVisibility(View.GONE);
		mTitleBack = (TextView) mFaqView.findViewById(R.id.title_bar_left);
		mTitleBack.setVisibility(View.GONE);
		// 右边
		mTitleNext = (TextView) mFaqView.findViewById(R.id.title_bar_right);
		mTitleNext.setVisibility(View.GONE);
		mTitleNextImg = (ImageButton) mFaqView.findViewById(R.id.title_bar_right_img);
		mTitleNextImg.setVisibility(View.VISIBLE);
		mTitleNextImg.setImageResource(R.drawable.header_faq_btn);
		mTitleNextImg.setOnClickListener(this);
		// 中间
		mTitleText = (TextView) mFaqView.findViewById(R.id.title_bar_center);
		mTitleText.setText(TAG);
	}

	/**
	 * 初始化列表
	 */
	private void initListView() {
		mFAQListView = (PullToRefreshListView) mFaqView.findViewById(R.id.lv_faq);
		mFAQListView.setPullRefreshEnable(true);
		mFAQListView.setPullLoadEnable(true);
		mFAQListView.setAutoLoadEnable(true);
		mFAQListView.setFoolListViewListener(this);
		mFAQListView.setRefreshTime(StringUtils.getCurTime());

		TextView emptyView = (TextView) mFaqView.findViewById(R.id.tv_faq_listview_empty);
		mFaqList = getData();
		mAdapter = new ListViewFaqAdapter(getActivity(), mFaqList);
		mFAQListView.setAdapter(mAdapter);
		mFAQListView.setEmptyView(emptyView);
		mFAQListView.setOnItemClickListener(this);
	}

	/**
	 * 获得列表要显示的数据
	 * 
	 * @return
	 */
	private List<HashMap<String, Object>> getData() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < total; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(Consts.LISTVIEW_FAQ_TITLE, titles[i % titles.length]);
			map.put(Consts.LISTVIEW_FAQ_AUTHOR, "大嘴" + i + "号");
			map.put(Consts.LISTVIEW_FAQ_DATE, (i + 1) * 2 + "小时前");
			map.put(Consts.LISTVIEW_FAQ_COUNT, i + 6 + "回|" + (i + 1) * 6 + "阅");
			list.add(map);

			if (i >= titles.length) {
				break;
			}
		}
		return list;
	}

	private void onLoad() {
		mFAQListView.stopRefresh();
		mFAQListView.stopLoadMore();
		mFAQListView.setRefreshTime(StringUtils.getCurTime());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_right_img:
			Intent intent = new Intent(getActivity(), QuestionPubActivity.class);
			startActivity(intent);
			getActivity().overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;

		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();

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
			mFaqList.clear();
			mFaqList.addAll(getData());
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
			mFaqList.clear();
			mFaqList.addAll(getData());
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
