package com.foolish.app.ui.fragment;

import com.foolish.app.R;
import com.foolish.app.ui.AppStart;
import com.foolish.app.ui.widget.PullToRefreshScrollView;
import com.foolish.app.ui.widget.PullToRefreshScrollView.IXScrollViewListener;
import com.foolish.app.utils.StringUtils;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment implements OnClickListener{

	private static final String TAG = "八卦八卦";
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;
	
	private View mHomeView;
	
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(mHomeView == null) {
			mHomeView = inflater.inflate(R.layout.fragment_home, container, false);
			initView();
			
			
		}
		ViewGroup parent = (ViewGroup)mHomeView.getParent();
		if(parent != null) {
			parent.removeView(mHomeView);
		}
		
		return mHomeView;
	}
	
	
	private void initView() {
		initTitle();
		
	}
	
	private void initTitle() {
		//左边
		mTitleBackImg = (ImageButton) mHomeView.findViewById(R.id.title_bar_left_img);
		mTitleBackImg.setVisibility(View.GONE);
		mTitleBack = (TextView)mHomeView.findViewById(R.id.title_bar_left);
		mTitleBack.setVisibility(View.GONE);
		//右边
		mTitleNext = (TextView) mHomeView.findViewById(R.id.title_bar_right);
		mTitleNext.setVisibility(View.GONE);
		mTitleNextImg = (ImageButton)mHomeView.findViewById(R.id.title_bar_right_img);
		mTitleNextImg.setVisibility(View.VISIBLE);
		mTitleNextImg.setImageResource(R.drawable.header_home_btn);
		mTitleNextImg.setOnClickListener(this);
		//中间
		mTitleText = (TextView) mHomeView.findViewById(R.id.title_bar_center);
		mTitleText.setText(TAG);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_right_img:
			Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
			
			break;

		
		}
		
	}

	
}
