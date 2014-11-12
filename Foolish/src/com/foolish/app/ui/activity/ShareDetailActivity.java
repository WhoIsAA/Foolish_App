package com.foolish.app.ui.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.foolish.app.R;
import com.foolish.app.adapter.CommentAdapter;
import com.foolish.app.common.Consts;
import com.foolish.app.ui.BaseActivity;
import com.foolish.app.utils.SystemUtils;

public class ShareDetailActivity extends BaseActivity implements OnClickListener{

	private static final String TAG = "评论详情";
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;
	
	private ListView mCommentListView;
	private CommentAdapter mAdapter;
	private List<HashMap<String, Object>> mCommentList;
	private ScrollView mScrollView;
	
	private String[] contents = { "楼主说得好啊，我顶！", "完全不知道你在说什么", "沙发", "吵什么吵，唧唧歪歪", "哦噢，城管来了" };
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_share_detail);
		
		initView();
		
		
	}
	
	
	private void initView() {
		initTitle();
		mScrollView = (ScrollView)findViewById(R.id.share_detail_scrollview);
		mScrollView.smoothScrollTo(0, 0);
		mCommentListView = (ListView)findViewById(R.id.lv_share_detail_comments);
		mCommentList = getData();
		mAdapter = new CommentAdapter(ShareDetailActivity.this, mCommentList);
		mCommentListView.setAdapter(mAdapter);
	}
	
	
	/**
	 * 设置标题栏
	 */
	private void initTitle() {
		// 左边
		mTitleBackImg = (ImageButton) findViewById(R.id.title_bar_left_img);
		mTitleBackImg.setVisibility(View.VISIBLE);
		mTitleBackImg.setImageResource(R.drawable.title_bar_back);
		mTitleBackImg.setOnClickListener(this);
		mTitleBack = (TextView) findViewById(R.id.title_bar_left);
		mTitleBack.setVisibility(View.GONE);
		// 右边
		mTitleNext = (TextView) findViewById(R.id.title_bar_right);
		mTitleNext.setVisibility(View.GONE);
		mTitleNextImg = (ImageButton) findViewById(R.id.title_bar_right_img);
		mTitleNextImg.setVisibility(View.VISIBLE);
		mTitleNextImg.setImageResource(R.drawable.share_detail_refresh);
		mTitleNextImg.setOnClickListener(this);
		// 中间
		mTitleText = (TextView) findViewById(R.id.title_bar_center);
		mTitleText.setText(TAG);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_left_img:
			finish();
			break;

		case R.id.title_bar_right_img:
			RotateAnimation animation = new RotateAnimation(0, 360, 
					Animation.RELATIVE_TO_SELF, 0.5f, 
					Animation.RELATIVE_TO_SELF, 0.5f);
			animation.setDuration(1000);
			animation.setRepeatCount(3);
			mTitleNextImg.startAnimation(animation);
			break;
		}
		
	}


	/**
	 * 获得数据
	 * @return
	 */
	private List<HashMap<String, Object>> getData() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		for(int i=0; i<contents.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(Consts.COMMENT_USERNAME, "用户" + i);
			map.put(Consts.COMMENT_CONTENT, contents[i]);
			map.put(Consts.COMMENT_DATE, (i+1)*2 + "小时前");
			list.add(map);
		
		}
		return list;
	}
	
	
}
