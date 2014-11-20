package com.foolish.app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.foolish.app.R;
import com.foolish.app.common.Consts;
import com.foolish.app.ui.BaseActivity;
import com.foolish.app.utils.StringUtils;
import com.foolish.app.utils.ToastUtils;




public class UpdateUserNameActivity extends BaseActivity implements OnClickListener{

	private static final String TAG = "修改昵称";
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;
	
	private EditText mUserNameEdit;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_update_username);
		
		initView();
		
	}
	
	
	private void initView() {
		initTitle();
		//获得当前使用的昵称
		Bundle bundle = getIntent().getExtras();
		String curUserName = StringUtils.checkNull(bundle.getString(Consts.BASIC_INFO_USERNAME));
		mUserNameEdit = (EditText)findViewById(R.id.id_et_update_username);
		mUserNameEdit.setText(curUserName);
		mUserNameEdit.setSelection(curUserName.length());
	}
	
	
	/**
	 * 初始化标题栏
	 */
	private void initTitle() {
		// 左边
		mTitleBackImg = (ImageButton) findViewById(R.id.title_bar_left_img);
		mTitleBackImg.setVisibility(View.VISIBLE);
		mTitleBackImg.setImageResource(R.drawable.title_bar_back);
		mTitleBackImg.setOnClickListener(this);
		mTitleBack = (TextView) findViewById(R.id.title_bar_left);
		mTitleBack.setVisibility(View.GONE);
		// 右边
		mTitleNext = (TextView) findViewById(R.id.title_bar_right);
		mTitleNext.setVisibility(View.VISIBLE);
		mTitleNext.setText(getResources().getString(R.string.save));
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
		case R.id.title_bar_left_img:
			finish();
			break;

		case R.id.title_bar_right:
			String username = mUserNameEdit.getText().toString();
			if(username.length() < 4 || username.length() > 23) {
				ToastUtils.show(UpdateUserNameActivity.this, getResources().getString(R.string.update_username_wrong));
				break;
			}
			ToastUtils.show(UpdateUserNameActivity.this, "保存");
			break;
		}
		
	}
	
	
	
	
	
}
