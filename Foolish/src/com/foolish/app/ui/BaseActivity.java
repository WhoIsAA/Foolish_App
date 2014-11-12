package com.foolish.app.ui;

import com.foolish.app.utils.AppManager;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;


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
	

	
	
	
}
