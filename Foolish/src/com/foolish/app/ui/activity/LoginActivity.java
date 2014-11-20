package com.foolish.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.foolish.app.R;
import com.foolish.app.ui.BaseActivity;

/**
 * 登录界面
 * @author AA
 * @date 2014-10-27
 *
 */
public class LoginActivity extends BaseActivity implements OnClickListener {

	private static final String TAG = "登录";
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;
	
	private Button mLoginBtn;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_login);
		
		initView();
		
	}
	
	
	private void initView() {
		initTitle();
		
		mLoginBtn = (Button)findViewById(R.id.login_btn);
		mLoginBtn.setOnClickListener(this);
		
	}
	
	
	/**
	 * 初始化标题栏
	 */
	private void initTitle() {
		//左边
		mTitleBackImg = (ImageButton) findViewById(R.id.title_bar_left_img);
		mTitleBackImg.setVisibility(View.VISIBLE);
		mTitleBackImg.setOnClickListener(this);
		mTitleBack = (TextView)findViewById(R.id.title_bar_left);
		mTitleBack.setVisibility(View.GONE);
		//右边
		mTitleNext = (TextView) findViewById(R.id.title_bar_right);
		mTitleNext.setVisibility(View.VISIBLE);
		mTitleNext.setText(getResources().getString(R.string.login_retrieve_pwd));
		mTitleNext.setOnClickListener(this);
		mTitleNextImg = (ImageButton)findViewById(R.id.title_bar_right_img);
		mTitleNextImg.setVisibility(View.GONE);
		//中间
		mTitleText = (TextView) findViewById(R.id.title_bar_center);
		mTitleText.setText(TAG);
	}

	
	@Override
	public void onClick(View v) {
		Intent intent = null;
		
		switch (v.getId()) {
		case R.id.title_bar_left_img:
			finish();
			
			break;

		case R.id.title_bar_right:
			
			break;
			
		case R.id.login_btn:
			intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
			
			break;
		}

	}
	
}
