package com.foolish.app.ui.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.foolish.app.R;
import com.foolish.app.adapter.ChatDetailAdapter;
import com.foolish.app.common.Consts;
import com.foolish.app.ui.BaseActivity;
import com.foolish.app.utils.StringUtils;
import com.foolish.app.utils.SystemUtils;
import com.foolish.app.utils.ToastUtils;

public class ChatDetailActivity extends BaseActivity implements OnClickListener {

	private String mTitleStr;
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;
	
	private ListView mListView;
	private ChatDetailAdapter mAdapter;
	private List<HashMap<String, Object>> mList;
	
	private String[] msgs = {"在吗？", "嗯嗯，什么事？", "你的APP做得怎样？", "还在弄呢，完成了一半左右吧", "几时能做出成品啊，我很想看看最后长什么样，做好发给我看下哈", "哈哈，会的"};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_chat_detail);

		initData();
		initView();
	}

	
	private void initData() {
		//获得传来的标题
		Intent intent = this.getIntent();
		mTitleStr = StringUtils.checkNull(intent.getExtras().getString(Consts.CHAT_USERNAME));
		
		
	}
	
	
	private void initView() {
		initTitle();
		mListView = (ListView)findViewById(R.id.id_lv_chat_detail);
		mList = getData();
		mAdapter = new ChatDetailAdapter(ChatDetailActivity.this, mList);
		mListView.setAdapter(mAdapter);
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
		mTitleNextImg.setImageResource(R.drawable.icon_head);
		mTitleNextImg.setOnClickListener(this);
		// 中间
		mTitleText = (TextView) findViewById(R.id.title_bar_center);
		mTitleText.setText(mTitleStr);
	}
	
	
	private List<HashMap<String, Object>> getData() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		for(int i=0; i<6; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if(i%2 == 0) {
				map.put(Consts.CHAT_DETAIL_TYPE, Consts.TYPE_CHAT_MESSAGE_FROM);
				map.put(Consts.CHAT_DETAIL_HEAD, R.drawable.icon_temp1);
			} else {
				map.put(Consts.CHAT_DETAIL_TYPE, Consts.TYPE_CHAT_MESSAGE_TO);
				map.put(Consts.CHAT_DETAIL_HEAD, R.drawable.icon_temp2);
			}
			map.put(Consts.CHAT_DETAIL_DATE, "晚上 20:"+((i+10)%50));
			map.put(Consts.CHAT_DETAIL_MSG, msgs[i]);
			list.add(map);
		}
		
		return list;
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_left:
			finish();
			break;
			
		case R.id.title_bar_right:
			ToastUtils.show(ChatDetailActivity.this, R.string.submit);
			
			break;

		}
		
		
	}

}
