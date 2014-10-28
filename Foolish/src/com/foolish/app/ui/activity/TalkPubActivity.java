package com.foolish.app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.foolish.app.R;
import com.foolish.app.ui.BaseActivity;
import com.foolish.app.utils.ToastUtils;

public class TalkPubActivity extends BaseActivity implements OnClickListener{

	private static final String TAG = "与你分享";
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_talk_pub);

		initView();

	}

	private void initView() {
		initTitle();

	}

	private void initTitle() {
		// 左边
		mTitleBackImg = (ImageButton) findViewById(R.id.title_bar_left_img);
		mTitleBackImg.setVisibility(View.GONE);
		mTitleBack = (TextView) findViewById(R.id.title_bar_left);
		mTitleBack.setVisibility(View.VISIBLE);
		mTitleBack.setText(getResources().getString(R.string.cancel));
		mTitleBack.setOnClickListener(this);
		// 右边
		mTitleNext = (TextView) findViewById(R.id.title_bar_right);
		mTitleNext.setVisibility(View.VISIBLE);
		mTitleNext.setText(getResources().getString(R.string.share));
		mTitleNext.setOnClickListener(this);
		mTitleNextImg = (ImageButton) findViewById(R.id.title_bar_right_img);
		mTitleNextImg.setVisibility(View.GONE);
		// 中间
		mTitleText = (TextView) findViewById(R.id.title_bar_center);
		mTitleText.setText(TAG);
	}
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_left:
			finish();
			break;
			
		case R.id.title_bar_right:
			ToastUtils.show(TalkPubActivity.this, R.string.share);
			
			break;

		}
		
	}
}
