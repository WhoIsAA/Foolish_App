package com.foolish.app.ui.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foolish.app.R;
import com.foolish.app.adapter.MenuAdapter;
import com.foolish.app.common.Consts;
import com.foolish.app.common.UIHelper;
import com.foolish.app.ui.BaseFragmentActivity;
import com.foolish.app.ui.fragment.ChatFragment;
import com.foolish.app.ui.fragment.MineFragment;
import com.foolish.app.ui.fragment.ShareFragment;
import com.foolish.app.utils.AppManager;
import com.foolish.app.utils.ToastUtils;
import com.ptr.folding.FoldingPaneLayout;


public class MainActivity extends BaseFragmentActivity implements OnClickListener{
	
	private ImageButton mTitleLeftImg;
	private ImageButton mTitleRightImg;
	private TextView mTitleText;

	private FoldingPaneLayout mPaneLayout;
	private ListView mPaneListView;
	private MenuAdapter mMenuAdapter;
	private List<HashMap<String, Object>> mMenuData = new ArrayList<HashMap<String,Object>>();
	private Fragment[] mFragments = new Fragment[3];
	
	private TextView mSettingBtn;
	private int mCurFragmentPage;

	private int[] menu_icons = {R.drawable.icon_home, R.drawable.icon_msg, R.drawable.icon_user};
	private String[] menu_texts = {"我的首页", "我的消息", "个人中心"};
	private String[] titles = {"首页", "聊聊", "个人中心"};
	private int[] rightImgs = {R.drawable.header_home_btn, 0, 0};
	
	private RelativeLayout mMyInfoLayout;
	
	
	private Resources mResources;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_main);
		mResources = getResources();
		setMainTitle(0);
		initMenu();
		if (savedInstanceState == null) {
			selectItem(0);
		}
	}
	
	
	/**
	 * 设置标题栏
	 * @param index
	 */
	private void setMainTitle(int index) {
		//左边
		mTitleLeftImg = (ImageButton)findViewById(R.id.id_ib_title_bar_main_left);
		mTitleLeftImg.setOnClickListener(this);
		//中间
		mTitleText = (TextView)findViewById(R.id.id_tv_title_bar_main_title);
		mTitleText.setText(titles[index]);
		//右边
		mTitleRightImg = (ImageButton)findViewById(R.id.id_ib_title_bar_main_right);
		mTitleRightImg.setImageResource(rightImgs[index]);
		mTitleRightImg.setOnClickListener(this);
	}
	
	
	/**
	 * 初始化菜单栏
	 */
	private void initMenu() {
		mMyInfoLayout = (RelativeLayout)findViewById(R.id.id_rl_menu_head_layout);
		mMyInfoLayout.setOnClickListener(this);
		
		mFragments[0] = new ShareFragment();
		mFragments[1] = new ChatFragment();
		mFragments[2] = new MineFragment();
		
		for(int i=0; i<3; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(Consts.MENU_ICON, menu_icons[i]);
			map.put(Consts.MENU_TEXT, menu_texts[i]);
			mMenuData.add(map);
		}
		
		mPaneLayout = (FoldingPaneLayout)findViewById(R.id.id_fpl_main_drawer);
		mPaneListView = (ListView)findViewById(R.id.id_lv_menu_item_list);
		mMenuAdapter = new MenuAdapter(this, mMenuData);
		//去除顶部和底部阴影效果
		mPaneListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
		//去除行与行之间的黑线
		mPaneListView.setDivider(null);
		mPaneListView.setAdapter(mMenuAdapter);
		mPaneListView.setOnItemClickListener(new DrawerItemClickListener());
		
		mSettingBtn = (TextView)findViewById(R.id.id_tv_menu_setting_btn);
		mSettingBtn.setOnClickListener(this);
	}
	
	
	/**
	 * 延迟关闭侧滑菜单栏
	 * @param ms
	 */
	private void closePaneDelay(long ms) {
		new ClosePaneTask().execute(ms);
			
	}
	
	
	class ClosePaneTask extends AsyncTask<Long, Long, Long> {

		@Override
		protected Long doInBackground(Long... params) {
			try {
				Thread.sleep(params[0]);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Long result) {
			if(mPaneLayout != null && mPaneLayout.isOpen()) {
				mPaneLayout.closePane();
			}
			super.onPostExecute(result);
		}
	}
	
	
	/**
	 * 获得对话框要显示的数据
	 * @param data
	 * @return
	 */
	private List<HashMap<String, String>> getPopupWindowData(String[] data) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		for(int i=0; i<data.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(UIHelper.POPUPWINDOW_KEY, data[i]);
			list.add(map);
		}
		
		return list;
	}
	
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		//用户按下菜单键
		if(event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
			if(event.getAction() == KeyEvent.ACTION_DOWN) {
				if(mPaneLayout.isOpen()) {
					mPaneLayout.closePane();
				} else {
					mPaneLayout.openPane();
				}
				return true;
			}
		//用户按下返回键
		} else if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
			if(event.getAction() == KeyEvent.ACTION_DOWN) {
				//如果左侧菜单被打开，则关闭
				if(mPaneLayout.isOpen()) {
					mPaneLayout.closePane();
					return true;
				}
				//显示对话框
				String back = mResources.getString(R.string.main_pw_back_to_desktop);
				String exit = mResources.getString(R.string.main_pw_exit_app);
				UIHelper.showPopupWindow(MainActivity.this, getPopupWindowData(new String[]{back, exit}), new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						switch (v.getId()) {
						//返回到桌面
						case R.id.id_tv_pw_common_item_one:
							Intent intent = new Intent(Intent.ACTION_MAIN);
							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.addCategory(Intent.CATEGORY_HOME);
							startActivity(intent);
							break;
						//退出应用
						case R.id.id_tv_pw_common_item_two:
							AppManager.getInstance().exitApp();
							break;
						}
						//关闭对话框
						UIHelper.hidePopupWindow();
					}
				});
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
	
	
	/**
	 * 监听菜单按钮
	 * @author AA
	 *
	 */
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (mPaneLayout.isOpen()) {
				selectItem(position);
			}
		}
	}
	
	
	/**
	 * 跳转到指定页面
	 * @param position
	 */
	private void selectItem(int position) {
		mCurFragmentPage = position;
		setMainTitle(position);
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.id_fl_main_layout, mFragments[position]).commit();
		mPaneLayout.closePane();
	}
		

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_ib_title_bar_main_left:
			if(mPaneLayout != null) {
				if(mPaneLayout.isOpen()) 
					mPaneLayout.closePane();
				else 
					mPaneLayout.openPane();
			}
			break;
			
		case R.id.id_ib_title_bar_main_right:
			if(mCurFragmentPage == 0) {
				Intent sharePubIntent = new Intent(MainActivity.this, SharePubActivity.class);
				startActivity(sharePubIntent);
			}
			break;

		case R.id.id_tv_menu_setting_btn:
			Intent settingIntent = new Intent(MainActivity.this, SettingActivity.class);
			startActivity(settingIntent);
			closePaneDelay(1000);
			break;
			
		case R.id.id_rl_menu_head_layout:
			Intent basicInfoIntent = new Intent(MainActivity.this, BasicInfoActivity.class);
			startActivity(basicInfoIntent);
			closePaneDelay(1000);
			break;
		}
	}
}
