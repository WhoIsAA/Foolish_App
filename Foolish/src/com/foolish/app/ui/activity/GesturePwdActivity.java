package com.foolish.app.ui.activity;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.foolish.app.AppContext;
import com.foolish.app.R;
import com.foolish.app.ui.BaseActivity;
import com.foolish.app.ui.widget.LockPatternView;
import com.foolish.app.ui.widget.LockPatternView.Cell;
import com.foolish.app.ui.widget.LockPatternView.DisplayMode;
import com.foolish.app.ui.widget.LockPatternView.OnPatternListener;
import com.foolish.app.utils.LockPatternUtils;
import com.foolish.app.utils.ToastUtils;

/**
 * 手势密码界面
 * 
 * @author AA
 * @date 2014-10-28
 *
 */
public class GesturePwdActivity extends BaseActivity implements OnClickListener{

	private static final String TAG = "手势密码";
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;

	private Button mResetBtn;
	private TextView mTipsTextView;
	private LockPatternView mGesturePwdView;
	private List<LockPatternView.Cell> mChosenPattern = null;
	private int mCurStatus = Status.INTRODUCTION;
	
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_gesture_pwd);

		initView();
	}

	
	private void initView() {
		initTitle();
		mResetBtn = (Button)findViewById(R.id.register_gesture_pwd_reset);
		mResetBtn.setOnClickListener(this);
		mTipsTextView = (TextView)findViewById(R.id.register_gesture_tips);
		mGesturePwdView = (LockPatternView)findViewById(R.id.register_gesture_pwd);
		//设置监听器
		mGesturePwdView.setOnPatternListener(onPatternListener);
		//允许绘制图案时震动
		mGesturePwdView.setTactileFeedbackEnabled(true);
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
			//重置手势密码
			resetGesturePwd();
			break;
		
		case R.id.title_bar_left_img:
			//返回
			intent = new Intent(GesturePwdActivity.this, RegisterActivity.class);
			startActivity(intent);
			finish();
			break;

		case R.id.title_bar_right:
			//完成按钮
			finishGesturePwd();
			break;
		
		}
	}

	
	private Runnable mClearPatternRunnable = new Runnable() {
		
		@Override
		public void run() {
			mGesturePwdView.clearPattern();
		}
	};
	
	
	/**
	 * 指定延迟时间清除当前手势密码
	 * @param delayMillis 毫秒
	 */
	private void postClearPatternRunnable(long delayMillis) {
		mGesturePwdView.removeCallbacks(mClearPatternRunnable);
		mGesturePwdView.postDelayed(mClearPatternRunnable, delayMillis);
	}
	
	
	/**
	 * 手势图案监听器
	 */
	private LockPatternView.OnPatternListener onPatternListener = new OnPatternListener() {
		
		@Override
		public void onPatternStart() {
			mGesturePwdView.removeCallbacks(mClearPatternRunnable);
			patternInProgress();
		}
		
		@Override
		public void onPatternDetected(List<LockPatternView.Cell> pattern) {
			if(pattern == null) {
				return ;
			}
			
			mResetBtn.setEnabled(true);
			if(mCurStatus == Status.NEED_TO_CONFIRM || mCurStatus == Status.CONFIRM_WRONG) {
				if(mChosenPattern == null) {
					throw new IllegalStateException("null chosen pattern in stage 'need to confirm");
				}
				if(mChosenPattern.equals(pattern)) {
					updateStatus(Status.CONFIRMED);
				} else {
					updateStatus(Status.CONFIRM_WRONG);
				}
			} else if(mCurStatus == Status.INTRODUCTION || mCurStatus == Status.TOO_SHORT) {
				if(pattern.size() < LockPatternUtils.MIN_LOCK_PATTERN_SIZE) {
					updateStatus(Status.TOO_SHORT);
				} else {
					mChosenPattern = new ArrayList<LockPatternView.Cell>(pattern);
					updateStatus(Status.NEED_TO_CONFIRM);
				}
			} else {
				throw new IllegalStateException("Unexpected stage " + mCurStatus
						+ " when " + "entering the pattern.");
			}
		}
		
		@Override
		public void onPatternCleared() {
			mGesturePwdView.removeCallbacks(mClearPatternRunnable);
		}
		
		@Override
		public void onPatternCellAdded(List<Cell> pattern) {
						
		}
	};
	
	
	/**
	 * 用户还在绘制图案中
	 */
	private void patternInProgress() {
		mTipsTextView.setText(getResources().getString(R.string.gesture_password_tips_progress));
		mResetBtn.setEnabled(false);
	}
	
	
	/**
	 * 根据当前状态更新界面
	 * @param status
	 */
	private void updateStatus(int status) {
		mCurStatus = status;
		mGesturePwdView.setDisplayMode(DisplayMode.Correct);
		
		switch (status) {
		case Status.NEED_TO_CONFIRM:
			mTipsTextView.setText(getResources().getString(R.string.gesture_password_tips_need_to_confirm));
			postClearPatternRunnable(1000);
			break;
		case Status.CONFIRMED:
			mTitleNext.setVisibility(View.VISIBLE);
			mTipsTextView.setText(getResources().getString(R.string.gesture_password_tips_confirmed));
			ToastUtils.show(GesturePwdActivity.this, mChosenPattern.toString());
			break;
		case Status.CONFIRM_WRONG:
			mGesturePwdView.setDisplayMode(DisplayMode.Wrong);
			mTipsTextView.setText(getResources().getString(R.string.gesture_password_tips_wrong));
			postClearPatternRunnable(2000);
			break;
		case Status.TOO_SHORT:
			mGesturePwdView.setDisplayMode(DisplayMode.Wrong);
			mTipsTextView.setText(getResources().getString(R.string.gesture_password_tips_too_short));
			postClearPatternRunnable(2000);
			break;
		case Status.INTRODUCTION:
			mTipsTextView.setText(getResources().getString(R.string.gesture_password_tips_def));
			mGesturePwdView.clearPattern();
			break;
		}
	}
	
	
	
	/**
	 * 重绘按钮：还原标题栏和清除View中的手势密码
	 */
	private void resetGesturePwd() {
		initTitle();
		mTipsTextView.setText(getResources().getString(R.string.gesture_password_tips_def));
		mGesturePwdView.clearPattern();
		mCurStatus = Status.INTRODUCTION;
		mChosenPattern = null;
	}
	
	
	/**
	 * 完成按钮
	 */
	private void finishGesturePwd() {
		//记录密码
		AppContext.getInstance().getLockPatternUtils().saveLockPattern(mChosenPattern);
		//关闭此页面，跳转至登录界面
		Intent intent = new Intent(GesturePwdActivity.this, LoginActivity.class);
		startActivity(intent);
		finish();
	}
	
	
	/**
	 * 状态类
	 * @author AA
	 * @date 2014-10-29
	 *
	 */
	private static class Status {
		private static final int NEED_TO_CONFIRM = 0;
		private static final int CONFIRMED = 1;
		private static final int CONFIRM_WRONG = 2;
		private static final int TOO_SHORT = 3;
		private static final int INTRODUCTION = 4;
	}
}
