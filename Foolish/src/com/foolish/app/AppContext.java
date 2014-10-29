package com.foolish.app;

import com.foolish.app.utils.LockPatternUtils;
import android.app.Application;


public class AppContext extends Application {

	private static AppContext mInstance;
	private LockPatternUtils mLockPatternUtils;

	
	public static AppContext getInstance() {
		return mInstance;
	}

	
	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		mLockPatternUtils = new LockPatternUtils(this);
	}

	
	public LockPatternUtils getLockPatternUtils() {
		return mLockPatternUtils;
	}
}
