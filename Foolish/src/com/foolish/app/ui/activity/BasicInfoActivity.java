package com.foolish.app.ui.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foolish.app.R;
import com.foolish.app.common.Consts;
import com.foolish.app.common.UIHelper;
import com.foolish.app.ui.BaseActivity;
import com.foolish.app.utils.ScreenUtils;
import com.foolish.app.utils.ToastUtils;



public class BasicInfoActivity extends BaseActivity implements OnClickListener{

	private static final String TAG = "基本资料";
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;
	
	private RelativeLayout mHeadLayout;
	private RelativeLayout mUserNameLayout;
	private RelativeLayout mSexLayout;
	private RelativeLayout mIndivSignLayout;
	
	
	private Resources mResources;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_basic_info);
		
		initView();
	}
	
	
	private void initView() {
		initTitle();
		//初始化各菜单项点击事件
		mHeadLayout = (RelativeLayout)findViewById(R.id.id_rl_basic_info_head);
		mHeadLayout.setOnClickListener(this);
		mUserNameLayout = (RelativeLayout)findViewById(R.id.id_rl_basic_info_username);
		mUserNameLayout.setOnClickListener(this);
		mSexLayout = (RelativeLayout)findViewById(R.id.id_rl_basic_info_sex);
		mSexLayout.setOnClickListener(this);
		mIndivSignLayout = (RelativeLayout)findViewById(R.id.id_rl_basic_info_indiv_sign);
		mIndivSignLayout.setOnClickListener(this);
		
		//获得资源文件
		mResources = getResources();
	}

	
	/**
	 * 初始化标题栏
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
		mTitleNextImg.setVisibility(View.GONE);
		// 中间
		mTitleText = (TextView) findViewById(R.id.title_bar_center);
		mTitleText.setText(TAG);
	}

	
	/**
	 * 获得数据
	 * @param data
	 * @return
	 */
	private List<HashMap<String, String>> getData(String[] data) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		for(int i=0; i<data.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(UIHelper.POPUPWINDOW_KEY, data[i]);
			list.add(map);
		}
		
		return list;
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_left_img:
			finish();
			break;
			
		case R.id.id_rl_basic_info_head:
			final String fromCamera = mResources.getString(R.string.basic_info_from_camera);
			final String fromAlbum = mResources.getString(R.string.basic_info_from_album);
			UIHelper.showPopupWindow(BasicInfoActivity.this, getData(new String[]{fromCamera, fromAlbum}), new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					switch (v.getId()) {
					case R.id.id_tv_pw_common_item_one:
						ToastUtils.show(BasicInfoActivity.this, fromCamera);
						break;

					case R.id.id_tv_pw_common_item_two:
						ToastUtils.show(BasicInfoActivity.this, fromAlbum);
						break;
					}
					//关闭对话框
					UIHelper.hidePopupWindow();
				}
			});
			break;
			
		case R.id.id_rl_basic_info_username:
			String curUserName = ((TextView)findViewById(R.id.id_tv_basic_info_username)).getText().toString();
			Intent updateUserNameIntent = new Intent(BasicInfoActivity.this, UpdateUserNameActivity.class);
			updateUserNameIntent.putExtra(Consts.BASIC_INFO_USERNAME, curUserName);
			startActivity(updateUserNameIntent);
			break;
			
		case R.id.id_rl_basic_info_sex:
			final String male = mResources.getString(R.string.basic_info_sex_male);
			final String female = mResources.getString(R.string.basic_info_sex_female);
			UIHelper.showPopupWindow(BasicInfoActivity.this, getData(new String[]{male, female}), new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					TextView sex = (TextView)findViewById(R.id.id_tv_basic_info_sex);
					switch (v.getId()) {
					case R.id.id_tv_pw_common_item_one:
						sex.setText(male);
						break;

					case R.id.id_tv_pw_common_item_two:
						sex.setText(female);
						break;
					}
					//关闭对话框
					UIHelper.hidePopupWindow();
				}
			});
			break;
			
		case R.id.id_rl_basic_info_indiv_sign:
			String curIndivSign = ((TextView)findViewById(R.id.id_tv_basic_info_indiv_sign)).getText().toString();
			Intent updateIndivSignIntent = new Intent(BasicInfoActivity.this, UpdateIndivSignActivity.class);
			updateIndivSignIntent.putExtra(Consts.BASIC_INFO_INDIV_SIGN, curIndivSign);
			startActivity(updateIndivSignIntent);
			break;
			
		}
	}
}
