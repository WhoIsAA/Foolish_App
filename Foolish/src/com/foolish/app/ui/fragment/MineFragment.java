package com.foolish.app.ui.fragment;

import com.foolish.app.R;
import com.foolish.app.ui.activity.SettingActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 个人中心
 * @author AA
 * @date 2014-10-8
 */
public class MineFragment extends Fragment implements OnClickListener{

	private static final String TAG = "个人中心";
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;
	
	private View mMineView;
	private ImageView mMineInfoBg;
	
	private RelativeLayout mMyTalkOpt;
	private RelativeLayout mMyQuestionOpt;
	private RelativeLayout mMyAnswerOpt;
	private RelativeLayout mMyCommentOpt;
	
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
		initTitle();
		mMyTalkOpt = (RelativeLayout)mMineView.findViewById(R.id.mine_option_my_talk);
		mMyTalkOpt.setOnClickListener(this);
		mMyQuestionOpt = (RelativeLayout)mMineView.findViewById(R.id.mine_option_my_question);
		mMyQuestionOpt.setOnClickListener(this);
		mMyAnswerOpt = (RelativeLayout)mMineView.findViewById(R.id.mine_option_my_answer);
		mMyAnswerOpt.setOnClickListener(this);
		mMyCommentOpt = (RelativeLayout)mMineView.findViewById(R.id.mine_option_my_comment);
		mMyCommentOpt.setOnClickListener(this);
		
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int bgWidth = dm.widthPixels;
		int bgHeight = (bgWidth * 6)/13;
		mMineInfoBg = (ImageView)mMineView.findViewById(R.id.iv_mine_info_bg);		
		LayoutParams params = mMineInfoBg.getLayoutParams();
		params.width = bgWidth;
		params.height = bgHeight;
		mMineInfoBg.setLayoutParams(params);
		
	}

	
	/**
	 * 初始化标题栏
	 */
	private void initTitle() {
		//左边
		mTitleBackImg = (ImageButton) mMineView.findViewById(R.id.title_bar_left_img);
		mTitleBackImg.setVisibility(View.GONE);
		mTitleBack = (TextView)mMineView.findViewById(R.id.title_bar_left);
		mTitleBack.setVisibility(View.GONE);
		//右边
		mTitleNext = (TextView) mMineView.findViewById(R.id.title_bar_right);
		mTitleNext.setVisibility(View.GONE);
		mTitleNextImg = (ImageButton)mMineView.findViewById(R.id.title_bar_right_img);
		mTitleNextImg.setVisibility(View.VISIBLE);
		mTitleNextImg.setImageResource(R.drawable.header_setting_btn);
		mTitleNextImg.setOnClickListener(this);
		//中间
		mTitleText = (TextView) mMineView.findViewById(R.id.title_bar_center);
		mTitleText.setText(TAG);
	}
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_right_img:
			Intent intent = new Intent(getActivity(), SettingActivity.class);
			startActivity(intent);
			getActivity().overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;

		
		}
		
	}
	
}
