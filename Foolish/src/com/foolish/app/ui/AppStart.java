package com.foolish.app.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.foolish.app.R;
import com.foolish.app.adapter.ViewPagerAdapter;
import com.foolish.app.ui.activity.LoginActivity;
import com.foolish.app.ui.activity.RegisterActivity;

public class AppStart extends BaseActivity implements OnClickListener{

	private Button mLoginBtn;
	private Button mRegisterBtn;
	
	private ViewPager mViewPager;
	private ViewPagerAdapter mAdapter;
	private LayoutInflater mInflater;
	private ArrayList<View> mViewList;
	private View mPageOne;
	private View mPageTwo;
	private View mPageThree;
	
	private ImageView mPageOneImage;
	private ImageView mPageTwoImage;
	private ImageView mPageThreeImage;
	
	private ImageView[] mDotsView;
	
	
	private int[] bgs = {R.drawable.bg_1, R.drawable.bg_2, R.drawable.bg_3};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_start_new);
		
		initView();
		
	}
	
	
	private void initView() {
		mInflater = getLayoutInflater();
		mPageOne = mInflater.inflate(R.layout.start_bg, null);
		mPageTwo = mInflater.inflate(R.layout.start_bg, null);
		mPageThree = mInflater.inflate(R.layout.start_bg, null);
		
		mViewList = new ArrayList<View>();
		mViewList.add(mPageOne);
		mViewList.add(mPageTwo);
		mViewList.add(mPageThree);
		
		mLoginBtn = (Button)findViewById(R.id.start_login_btn);
		mLoginBtn.setOnClickListener(this);
		mRegisterBtn = (Button)findViewById(R.id.start_register_btn);
		mRegisterBtn.setOnClickListener(this);
		
		initViewPager();
		initDots();
		
	}
	
	
	private void initViewPager() {
		mPageOneImage = (ImageView)mPageOne.findViewById(R.id.start_view_pager_bg);
		mPageOneImage.setBackgroundResource(R.drawable.bg_1);
		mPageTwoImage = (ImageView)mPageTwo.findViewById(R.id.start_view_pager_bg);
		mPageTwoImage.setBackgroundResource(R.drawable.bg_2);
		mPageThreeImage = (ImageView)mPageThree.findViewById(R.id.start_view_pager_bg);
		mPageThreeImage.setBackgroundResource(R.drawable.bg_3);
		
		mViewPager = (ViewPager)findViewById(R.id.start_view_pager);
		mAdapter = new ViewPagerAdapter(mViewList);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setCurrentItem(0);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				View view = mViewList.get(position);
				ImageView imageView = (ImageView)view.findViewById(R.id.start_view_pager_bg);
				imageView.setBackgroundResource(bgs[position]);
				updateDotImages(position);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		
		
	}
	
	
	private void initDots() {
		ViewGroup group = (ViewGroup)findViewById(R.id.start_dot_group);
		mDotsView = new ImageView[mViewList.size()];
		
		for(int i=0; i<mViewList.size(); i++) {
			ImageView imageView = new ImageView(AppStart.this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT, 
					LinearLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(5, 0, 5, 0);
			imageView.setLayoutParams(params);
			mDotsView[i] = imageView;
			
			if(i == 0) {
				mDotsView[i].setBackgroundResource(R.drawable.dot_on);
			} else {
				mDotsView[i].setBackgroundResource(R.drawable.dot_off);
			}
			
			if(mDotsView.length != group.getChildCount()) {
				group.addView(mDotsView[i]);
			}
		}
	}
	
	
	private void updateDotImages(int currentPageNo)
	{
		for (int index = 0; index < mDotsView.length; index++)
		{
			if (currentPageNo == index) {
				mDotsView[index].setBackgroundResource(R.drawable.dot_on);
			}
			else 
			{
				mDotsView[index].setBackgroundResource(R.drawable.dot_off);
			}
		}
	}


	@Override
	public void onClick(View v) {
		Intent intent = null;
		
		switch (v.getId()) {
		case R.id.start_login_btn:
			intent = new Intent(AppStart.this, LoginActivity.class);
			startActivity(intent);
			
			break;

		case R.id.start_register_btn:
			intent = new Intent(AppStart.this, RegisterActivity.class);
			startActivity(intent);
			
			break;
		}
		
	}
	
}
