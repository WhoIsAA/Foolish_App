package com.foolish.app.ui;

import com.foolish.app.R;
import com.foolish.app.ui.activity.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;

public class Start extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final View view = View.inflate(this, R.layout.activity_start, null);
		LinearLayout layout = (LinearLayout)view.findViewById(R.id.ll_start);
		setContentView(layout);
		
		//启动界面渐变效果
		AlphaAnimation aa = new AlphaAnimation(0.5f, 1f);
		aa.setDuration(2000);
		view.setAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {}
			
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				startMain();
			}
		});
		
		
	}

	
	/**
	 * 跳转到主界面
	 */
	private void startMain() {
		Intent intent = new Intent(Start.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
