package com.foolish.app.ui;

import com.foolish.app.R;
import com.foolish.app.utils.AppManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class BaseActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState, int layoutResID) {
		super.onCreate(savedInstanceState);
		setContentView(layoutResID);
		
		//入栈
		AppManager.getInstance().pushActivity(this);
	}
	
	
	@Override
	protected void onDestroy() {
		//退栈
		AppManager.getInstance().popActivity(this);
		
		super.onDestroy();
	}
	
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			if(event.getAction() == KeyEvent.ACTION_UP) {
				finish();
				overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
				
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}
}
