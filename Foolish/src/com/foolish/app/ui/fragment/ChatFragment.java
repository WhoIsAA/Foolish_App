package com.foolish.app.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.foolish.app.R;
import com.foolish.app.adapter.ChatAdapter;
import com.foolish.app.common.Consts;
import com.foolish.app.ui.activity.ChatDetailActivity;
import com.foolish.app.ui.activity.SearchActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 聊聊
 * 
 * @author AA
 *
 */
public class ChatFragment extends Fragment implements OnClickListener, OnItemClickListener{

	private static final String TAG = "聊聊";
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;

	private View mChatView;
	private ListView mChatListView;
	private ChatAdapter mAdapter;
	private List<HashMap<String, Object>> mChatList;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mChatView == null) {
			mChatView = inflater.inflate(R.layout.fragment_chat, container, false);
			initView();
		}

		ViewGroup parent = (ViewGroup) mChatView.getParent();
		if (parent != null) {
			parent.removeView(mChatView);
		}

		return mChatView;
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
		mTitleBackImg = (ImageButton) mChatView.findViewById(R.id.title_bar_left_img);
		mTitleBackImg.setVisibility(View.GONE);
		mTitleBack = (TextView) mChatView.findViewById(R.id.title_bar_left);
		mTitleBack.setVisibility(View.GONE);
		// 右边
		mTitleNext = (TextView) mChatView.findViewById(R.id.title_bar_right);
		mTitleNext.setVisibility(View.GONE);
		mTitleNextImg = (ImageButton) mChatView.findViewById(R.id.title_bar_right_img);
		mTitleNextImg.setVisibility(View.VISIBLE);
		mTitleNextImg.setImageResource(R.drawable.header_search_btn);
		mTitleNextImg.setOnClickListener(this);
		// 中间
		mTitleText = (TextView) mChatView.findViewById(R.id.title_bar_center);
		mTitleText.setText(TAG);
	}

	/**
	 * 初始化列表
	 */
	private void initListView() {
		mChatListView = (ListView) mChatView.findViewById(R.id.id_lv_chat_main);
		mChatList = getData();
		mAdapter = new ChatAdapter(getActivity(), mChatList);
		mChatListView.setAdapter(mAdapter);
		mChatListView.setFocusable(true);
		mChatListView.setOnItemClickListener(this);
		
	}

	/**
	 * 获得列表要显示的数据
	 * 
	 * @return
	 */
	private List<HashMap<String, Object>> getData() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		int[] head = {R.drawable.ic_launcher, R.drawable.icon_temp1, R.drawable.icon_temp2, R.drawable.user_head_loading, R.drawable.header_title_img};
		String[] username = {"小伙伴们", "有一群疯子", "叽叽喳喳", "Big Friends", "啦啦啦"};
		String[] date = {"中午 12:47", "早上 08:46", "凌晨 00:09", "昨天", "10月31日"};
		String[] msg = {"全世界晚安", "帮我买早餐", "在吗", "有事找你", "刚吃完饭，你吃了没？"};
		
		for(int i=0; i<5; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(Consts.CHAT_HEAD, head[i]);
			map.put(Consts.CHAT_USERNAME, username[i]);
			map.put(Consts.CHAT_LATEST_DATE, date[i]);
			map.put(Consts.CHAT_LATEST_MSG, msg[i]);
			list.add(map);
		}
		
		return list;
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_right_img:
			Intent intent = new Intent(getActivity(), SearchActivity.class);
			startActivity(intent);
			break;

		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(getActivity(), ChatDetailActivity.class);
		intent.putExtra(Consts.CHAT_USERNAME, mChatList.get(position).get(Consts.CHAT_USERNAME).toString());
		startActivity(intent);

	}
}
