package com.foolish.app.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.foolish.app.R;
import com.foolish.app.adapter.CommentAdapter;
import com.foolish.app.adapter.ConcernAdapter;
import com.foolish.app.adapter.MineShareAdapter;
import com.foolish.app.adapter.ViewPagerAdapter;
import com.foolish.app.common.Consts;
import com.foolish.app.ui.activity.SettingActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * 个人中心
 * @author AA
 * @date 2014-10-8
 */
public class MineFragment extends Fragment implements OnClickListener{

	private static final String TAG = "个人中心";
	
	private LinearLayout mShareLayout;
	private LinearLayout mCommentLayout;
	private LinearLayout mConcernLayout;
	
	private ViewPager mContentPager;
	private ViewPagerAdapter mAdapter;
	private ArrayList<View> mViewList; 
	private LayoutInflater mInflater;
	private View mShareView;
	private View mCommentView;
	private View mConcernView;
	private View mShareLine;
	private View mCommentLine;
	private View mConcernLine;
	private ListView mShareListView;
	private ListView mCommentListView;
	private ListView mConcernListView;
	private List<HashMap<String, Object>> mShareList;
	private List<HashMap<String, Object>> mCommentList;
	private List<HashMap<String, Object>> mConcernList;
	private MineShareAdapter mShareAdapter;
	private CommentAdapter mCommentAdapter;
	private ConcernAdapter mConcernAdapter;
	
	private static final int MINE_MY_SHARE = 0;
	private static final int MINE_MY_COMMENT = 1;
	private static final int MINE_MY_CONCERN = 2;
	
	private View mMineView;
	private RelativeLayout mMineHeadBg;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(mMineView == null) {
			mMineView = inflater.inflate(R.layout.fragment_mine, container, false);
			initView();
			
		}
		ViewGroup parent = (ViewGroup)mMineView.getParent();
		if(parent != null) {
			parent.removeView(mMineView);
		}
		
		return mMineView;
	}
	
	
	private void initView() {
		
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int bgWidth = dm.widthPixels;
//		int bgHeight = (bgWidth * 6)/13;
		int bgHeight = (bgWidth * 8)/13;
		mMineHeadBg = (RelativeLayout)mMineView.findViewById(R.id.id_ll_mine_head_bg);		
		LayoutParams params = mMineHeadBg.getLayoutParams();
		params.width = bgWidth;
		params.height = bgHeight;
		mMineHeadBg.setLayoutParams(params);
		
		mShareLayout = (LinearLayout)mMineView.findViewById(R.id.id_ll_mine_head_bottom_one);
		mShareLayout.setOnClickListener(this);
		mCommentLayout = (LinearLayout)mMineView.findViewById(R.id.id_ll_mine_head_bottom_two);
		mCommentLayout.setOnClickListener(this);
		mConcernLayout = (LinearLayout)mMineView.findViewById(R.id.id_ll_mine_head_bottom_three);
		mConcernLayout.setOnClickListener(this);
		
		initPager();
	}

	
	private void initPager() {
		mInflater = getActivity().getLayoutInflater();
		mShareView = mInflater.inflate(R.layout.pager_mine_content, null);
		mCommentView = mInflater.inflate(R.layout.pager_mine_content, null);
		mConcernView = mInflater.inflate(R.layout.pager_mine_content, null);
		
		mShareList = getData(MINE_MY_SHARE);
		mCommentList = getData(MINE_MY_COMMENT);
		mConcernList = getData(MINE_MY_CONCERN);
		
		//初始化线条
		mShareLine = mMineView.findViewById(R.id.id_line_mine_head_bottom_one);
		mShareLine.setBackgroundColor(getActivity().getResources().getColor(R.color.mine_head_line_checkd));
		mCommentLine = mMineView.findViewById(R.id.id_line_mine_head_bottom_two);
		mConcernLine = mMineView.findViewById(R.id.id_line_mine_head_bottom_three);
		
		//初始化我的分享列表
		mShareListView = (ListView)mShareView.findViewById(R.id.id_lv_pager_mine_content_listview);
		mShareAdapter = new MineShareAdapter(getActivity(), mShareList);
		mShareListView.setAdapter(mShareAdapter);
		//初始化我的评论列表
		mCommentListView = (ListView)mCommentView.findViewById(R.id.id_lv_pager_mine_content_listview);
		mCommentAdapter = new CommentAdapter(getActivity(), mCommentList);
		mCommentListView.setAdapter(mCommentAdapter);
		//初始化我的关注列表
		mConcernListView = (ListView)mConcernView.findViewById(R.id.id_lv_pager_mine_content_listview);
		mConcernAdapter = new ConcernAdapter(getActivity(), mConcernList);
		mConcernListView.setAdapter(mConcernAdapter);
		
		//初始化数据
		mViewList = new ArrayList<View>();
		mViewList.add(mShareView);
		mViewList.add(mCommentView);
		mViewList.add(mConcernView);
		
		mContentPager = (ViewPager)mMineView.findViewById(R.id.mine_content_page);
		mAdapter = new ViewPagerAdapter(mViewList);
		mContentPager.setAdapter(mAdapter);
		mContentPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				changeViewByPosition(position);
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		
	}
	
	
	/**
	 * 根据用户所选去更新视图
	 * @param position
	 */
	private void changeViewByPosition(int position) {
		
		switch (position) {
		case MINE_MY_SHARE:
			mShareLine.setBackgroundColor(getActivity().getResources().getColor(R.color.mine_head_line_checkd));
			mCommentLine.setBackgroundColor(getActivity().getResources().getColor(R.color.mine_head_line_nor));
			mConcernLine.setBackgroundColor(getActivity().getResources().getColor(R.color.mine_head_line_nor));
			break;

		case MINE_MY_COMMENT:
			mShareLine.setBackgroundColor(getActivity().getResources().getColor(R.color.mine_head_line_nor));
			mCommentLine.setBackgroundColor(getActivity().getResources().getColor(R.color.mine_head_line_checkd));
			mConcernLine.setBackgroundColor(getActivity().getResources().getColor(R.color.mine_head_line_nor));
			break;
			
		case MINE_MY_CONCERN:
			mShareLine.setBackgroundColor(getActivity().getResources().getColor(R.color.mine_head_line_nor));
			mCommentLine.setBackgroundColor(getActivity().getResources().getColor(R.color.mine_head_line_nor));
			mConcernLine.setBackgroundColor(getActivity().getResources().getColor(R.color.mine_head_line_checkd));
			break;
		}
	}
	
	
	private String[] contents = {"有点点失落，吃点安眠药吧..", "今天是令人蛋疼的大周一", "这是我的第一条分享"};
	private int[] contentImgs = {R.drawable.listitem_empty, 0, 0};
	
	
	/**
	 * 获得数据
	 * @param type
	 * @return
	 */
	private List<HashMap<String, Object>> getData(int type) {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		switch (type) {
		case MINE_MY_SHARE:
		{
			for (int i = 0; i < 3; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put(Consts.MINE_SHARE_USERNAME, "AA");
				map.put(Consts.MINE_SHARE_CONTENT, contents[i]);
				map.put(Consts.MINE_SHARE_CONTENT_IMG, contentImgs[i]);
				map.put(Consts.MINE_SHARE_DATE, (i+1)*3 + "小时前");
				list.add(map);
			}
		}
			break;

		case MINE_MY_COMMENT:
		{
			for (int i = 0; i < 3; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put(Consts.COMMENT_USERNAME, "AA");
				map.put(Consts.COMMENT_CONTENT, contents[i]);
				map.put(Consts.COMMENT_DATE, (i+2)*2 + "小时前");
				list.add(map);
			}
		}
			break;
		
		case MINE_MY_CONCERN:
		{
			for (int i = 0; i < 1; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put(Consts.MINE_CONCERN_USERNAME, "新浪健康");
				map.put(Consts.MINE_CONCERN_INTRO, contents[i]);
				list.add(map);
			}
		}
			break;
		}
		
		
		return list;
	}
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_right_img:
			Intent intent = new Intent(getActivity(), SettingActivity.class);
			startActivity(intent);
			getActivity().overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;

		case R.id.id_ll_mine_head_bottom_one:
			mContentPager.setCurrentItem(0);
			
			break;
			
		case R.id.id_ll_mine_head_bottom_two:
			mContentPager.setCurrentItem(1);
			
			break;
			
		case R.id.id_ll_mine_head_bottom_three:
			mContentPager.setCurrentItem(2);
			
			break;
		
		}
		
	}
	
}
