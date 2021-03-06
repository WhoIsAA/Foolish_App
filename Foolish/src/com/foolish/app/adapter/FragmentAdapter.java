package com.foolish.app.adapter;

import com.foolish.app.ui.activity.MainActivity;
import com.foolish.app.ui.fragment.ShareFragment;
import com.foolish.app.ui.fragment.ChatFragment;
import com.foolish.app.ui.fragment.HomeFragment;
import com.foolish.app.ui.fragment.MineFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
	private final int TAB_COUNT = 4;
	
	public FragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	
	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case MainActivity.TAB_HOME:
			HomeFragment homeFragment = new HomeFragment();
			return homeFragment;
			
		case MainActivity.TAB_TALK:
			ShareFragment shareFragment = new ShareFragment();
			return shareFragment;
			
		case MainActivity.TAB_FAQ:
			ChatFragment chatFragment = new ChatFragment();
			return chatFragment;
			
		case MainActivity.TAB_MINE:
			MineFragment mineFragment = new MineFragment();
			return mineFragment;
		}
		return null;
	}
	

	@Override
	public int getCount() {
		return TAB_COUNT;
	}

}
