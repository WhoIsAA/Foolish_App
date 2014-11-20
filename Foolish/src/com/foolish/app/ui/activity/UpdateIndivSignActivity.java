package com.foolish.app.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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




public class UpdateIndivSignActivity extends BaseActivity implements OnClickListener{

	private static final String TAG = "更新个性签名";
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;
	
	private final int INDIV_SIGN_MAX_WORDS = 100;
	private EditText mIndivSignEdit;
	private TextView mRemainWordsView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_update_indiv_sign);
		
		initView();
		
	}
	
	
	private void initView() {
		initTitle();
		//获得当前使用的昵称
		Bundle bundle = getIntent().getExtras();
		String curIndivSign = StringUtils.checkNull(bundle.getString(Consts.BASIC_INFO_INDIV_SIGN));
		mIndivSignEdit = (EditText)findViewById(R.id.id_et_update_indiv_sign);
		mRemainWordsView = (TextView)findViewById(R.id.id_tv_remain_words);
		setRemainWords(curIndivSign);
		mIndivSignEdit.setText(curIndivSign);
		//设置光标位置
		mIndivSignEdit.setSelection(curIndivSign.length());
		//监听输入
		mIndivSignEdit.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				setRemainWords(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
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


	/**
	 * 设置还可以输入的内容字数
	 * @param src
	 */
	private void setRemainWords(String src) {
		int remain = INDIV_SIGN_MAX_WORDS - src.length();
		if(remain < 0) {
			mRemainWordsView.setTextColor(Color.RED);
		} else {
			mRemainWordsView.setTextColor(Color.parseColor("#666666"));
		}
		mRemainWordsView.setText(String.format("%d", remain));
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_left_img:
			finish();
			break;

		case R.id.title_bar_right:
			String username = mIndivSignEdit.getText().toString();
			if(username.length() > INDIV_SIGN_MAX_WORDS) {
				ToastUtils.show(UpdateIndivSignActivity.this, getResources().getString(R.string.update_indiv_sign_wrong));
				break;
			}
			ToastUtils.show(UpdateIndivSignActivity.this, "保存");
			break;
		}
		
	}
	
	
	
	
}
