package com.foolish.app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;

import com.foolish.app.R;
import com.foolish.app.ui.BaseActivity;
import com.foolish.app.utils.SystemUtils;
import com.foolish.app.utils.ToastUtils;

public class SearchActivity extends BaseActivity implements OnClickListener,
		OnFocusChangeListener {

	private ImageButton mBackBtn;
	private ImageButton mSearchBtn;
	private EditText mSearchContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_search);

		initView();

	}

	private void initView() {
		mBackBtn = (ImageButton) findViewById(R.id.id_ib_search_bar_back);
		mBackBtn.setOnClickListener(this);
		mSearchBtn = (ImageButton) findViewById(R.id.id_ib_search_bar_submit);
		mSearchBtn.setOnClickListener(this);
		mSearchContent = (EditText) findViewById(R.id.id_et_search_bar_content);
		mSearchContent.setOnFocusChangeListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_ib_search_bar_back:
			finish();
			break;

		case R.id.id_ib_search_bar_submit:
			ToastUtils.show(SearchActivity.this, "正在搜索");
			break;
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			SystemUtils.showSoftInput(SearchActivity.this, v);
		} else {
			SystemUtils.hideSoftInput(SearchActivity.this, v);
		}

	}

}
