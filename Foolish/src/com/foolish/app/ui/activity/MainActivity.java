package com.foolish.app.ui.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.foolish.app.R;
import com.foolish.app.adapter.FragmentAdapter;
import com.foolish.app.adapter.MenuAdapter;
import com.foolish.app.ui.BaseFragmentActivity;
import com.foolish.app.ui.widget.SlidingMenu;
import com.foolish.app.utils.AppManager;
import com.foolish.app.utils.ScreenUtils;
import com.foolish.app.utils.ToastUtils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

public class MainActivity extends BaseFragmentActivity implements OnClickListener{

	public static final int TAB_HOME = 0;
	public static final int TAB_TALK = 1;
	public static final int TAB_FAQ = 2;
	public static final int TAB_MINE = 3;
	private ViewPager mViewPager;
	private RadioButton mHomeBtn, mChatBtn, mFaqBtn, mMineBtn;
	private SlidingMenu mMenuView;
	private RelativeLayout mContentLayout;
	private ListView mFriendsListView;
	private MenuAdapter mAdapter;
	private List<HashMap<String, Object>> mData = new ArrayList<HashMap<String,Object>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_main);
		
		initView();
		addListener();
		
	}
	
	
	private void initView() {
		//初始化底部按钮
		mViewPager = (ViewPager)findViewById(R.id.vp_main);
		mHomeBtn = (RadioButton)findViewById(R.id.rb_main_tab_home);
		mHomeBtn.setOnClickListener(this);
		mChatBtn = (RadioButton)findViewById(R.id.rb_main_tab_talk);
		mChatBtn.setOnClickListener(this);
		mFaqBtn = (RadioButton)findViewById(R.id.rb_main_tab_faq);
		mFaqBtn.setOnClickListener(this);
		mMineBtn = (RadioButton)findViewById(R.id.rb_main_tab_mine);
		mMineBtn.setOnClickListener(this);
		//初始化内容区域
		FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(adapter);
		//初始化菜单栏
		mMenuView = (SlidingMenu)findViewById(R.id.main_menu);
		mContentLayout = (RelativeLayout)findViewById(R.id.main_content_layout);
		//初始化菜单栏中的好友列表
		mFriendsListView = (ListView)findViewById(R.id.menu_friends_list);
		for(int i=0; i<20; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("menu_friends_head", R.drawable.ic_launcher);
			map.put("menu_friends_name", "用户"+ (i+1) +"号");
			mData.add(map);
		}
		mAdapter = new MenuAdapter(this, mData);
		//去除顶部和底部阴影效果
		mFriendsListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
		//去除行与行之间的黑线
        mFriendsListView.setDivider(null);
		mFriendsListView.setAdapter(mAdapter);
	}
	
	
	private void addListener() {
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case TAB_HOME:
					mHomeBtn.setChecked(true);
					break;

				case TAB_TALK:
					mChatBtn.setChecked(true);
					break;
					
				case TAB_FAQ:
					mFaqBtn.setChecked(true);
					break;
	
				case TAB_MINE:
					mMineBtn.setChecked(true);
					break;
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});
		
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rb_main_tab_home:
			mViewPager.setCurrentItem(TAB_HOME);
			break;

		case R.id.rb_main_tab_talk:
			mViewPager.setCurrentItem(TAB_TALK);
			break;
			
		case R.id.rb_main_tab_faq:
			mViewPager.setCurrentItem(TAB_FAQ);
			break;
			
		case R.id.rb_main_tab_mine:
			mViewPager.setCurrentItem(TAB_MINE);
			break;
			
		}
	}
	
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			if(event.getAction() == KeyEvent.ACTION_UP) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("提示")
					.setMessage("确定 要退出吗？")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							AppManager.getInstance().exitApp();
							dialog.dismiss();
						}
					})
					.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					}).create();
				builder.show();
				
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
}
