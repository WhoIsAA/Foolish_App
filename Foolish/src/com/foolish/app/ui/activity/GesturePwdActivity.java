package com.foolish.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.foolish.app.R;
import com.foolish.app.ui.BaseActivity;
import com.foolish.app.ui.widget.GesturePwdView;
import com.foolish.app.ui.widget.GesturePwdView.OnCompleteListener;
import com.foolish.app.utils.StringUtils;
import com.foolish.app.utils.ToastUtils;

/**
 * 手势密码界面
 * 
 * @author AA
 * @date 2014-10-28
 *
 */
public class GesturePwdActivity extends BaseActivity implements OnClickListener{

	private static final String TAG = "绘制手势密码";
	private static final String AGAIN = "再次绘制手势密码";
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;

	private Button mResetBtn;
	private GesturePwdView mGesturePwdView;
	private String mGesturePwd;
	private int mCurTimes = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_gesture_pwd);

		initView();
	}

	
	private void initView() {
		initTitle();
		
		mResetBtn = (Button)findViewById(R.id.register_gesture_pwd_reset);
		mResetBtn.setOnClickListener(this);
		mGesturePwdView = (GesturePwdView)findViewById(R.id.register_gesture_pwd);
		mGesturePwdView.setOnCompleteListener(new OnCompleteListener() {
			String fstPwd = "";
			
			@Override
			public void onComplete(String password) {
				if(StringUtils.isNotEmpty(password)) {
					//首次绘制
					if(mCurTimes == 0) {
						//设置新标题
						mTitleText.setText(AGAIN);
						//记录本次密码并清除View
						fstPwd = password;
						mGesturePwdView.clearPassword();
						mCurTimes = mCurTimes + 1;
					//再次绘制
					} else if(mCurTimes == 1) {
						if(fstPwd.equals(password)) {
							mTitleNext.setVisibility(View.VISIBLE);
							mGesturePwd = password;
						} else {
							mTitleText.setText(TAG);
							mTitleNext.setVisibility(View.GONE);
							//两次手势密码不一致
							mCurTimes = 0;
							mGesturePwdView.clearPassword();
							ToastUtils.show(GesturePwdActivity.this, R.string.register_gesture_pwd_dif);
						}
					}
				}
			}
		});
	}

	
	/**
	 * 初始化标题栏
	 */
	private void initTitle() {
		// 左边
		mTitleBackImg = (ImageButton) findViewById(R.id.title_bar_left_img);
		mTitleBackImg.setVisibility(View.VISIBLE);
		mTitleBackImg.setOnClickListener(this);
		mTitleBack = (TextView) findViewById(R.id.title_bar_left);
		mTitleBack.setVisibility(View.GONE);
		// 右边
		mTitleNext = (TextView) findViewById(R.id.title_bar_right);
		mTitleNext.setText(getResources().getString(R.string.register_finish));
		mTitleNext.setOnClickListener(this);
		mTitleNext.setVisibility(View.GONE);
		mTitleNextImg = (ImageButton) findViewById(R.id.title_bar_right_img);
		mTitleNextImg.setVisibility(View.GONE);
		// 中间
		mTitleText = (TextView) findViewById(R.id.title_bar_center);
		mTitleText.setText(TAG);
	}

	
	@Override
	public void onClick(View v) {
		Intent intent = null;
		
		switch (v.getId()) {
		case R.id.register_gesture_pwd_reset:
			resetGesturePwd();
			break;
		
		case R.id.title_bar_left_img:
			intent = new Intent(GesturePwdActivity.this, RegisterActivity.class);
			startActivity(intent);
			finish();
			break;

		case R.id.title_bar_right:
			finishGesturePwd();
			break;
		
		}
		
	}

	
	/**
	 * 重绘按钮：还原标题栏和清除View中的手势密码
	 */
	private void resetGesturePwd() {
		mGesturePwdView.clearPassword();
		mTitleText.setText(TAG);
		mTitleNext.setVisibility(View.GONE);
		mCurTimes = mCurTimes - 1;
		if(mCurTimes < 0) {
			mCurTimes = 0;
		}
	}
	
	
	/**
	 * 完成按钮
	 */
	private void finishGesturePwd() {
		//记录密码
		mGesturePwdView.resetPassWord(mGesturePwd);
		//关闭此页面，跳转至登录界面
		Intent intent = new Intent(GesturePwdActivity.this, LoginActivity.class);
		startActivity(intent);
		finish();
	}
}
