package com.foolish.app.ui.activity;

import java.util.ArrayList;
import com.foolish.app.R;
import com.foolish.app.adapter.ViewPagerAdapter;
import com.foolish.app.ui.BaseActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 发提问界面
 * @author AA
 *
 */
public class QuestionPub extends BaseActivity implements OnClickListener{

	private static final String TAG = "发提问";
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;
	
	private static final int QUESTION_TITLE = 0;
	private static final int QUESTION_DESC = 1;
	private static final int QUESTION_TYPE = 2;
	
	private ViewPager mViewPager;
	private TextView mQuestionTitle;
	private TextView mQuestionDesc;
	private TextView mQuestionType;
	
	private ViewPagerAdapter mAdapter;
	private ArrayList<View> mViewList;
	private LayoutInflater mInflater;
	private View mQuestionTitleView;
	private View mQuestionDescView;
	private View mQuestionTypeView;
	
	private TextView mHeaderBack;
	private TextView mHeaderSubmit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_question_pub);
		
		init();
	}
	
	
	private void init() {
		mInflater = getLayoutInflater();
		mQuestionTitleView = mInflater.inflate(R.layout.question_pub_edit, null);
		mQuestionDescView = mInflater.inflate(R.layout.question_pub_edit, null);
		mQuestionTypeView = mInflater.inflate(R.layout.question_pub_edit, null);
		
		mViewList = new ArrayList<View>();
		mViewList.add(mQuestionTitleView);
		mViewList.add(mQuestionDescView);
		mViewList.add(mQuestionTypeView);
		
		initView();
	}
	
	
	private void initView() {
		//初始化标题栏
		initTitle();
		//初始化问题栏
		mQuestionTitle = (TextView)findViewById(R.id.question_pub_title_left);
		mQuestionTitle.setTextColor(getResources().getColor(R.color.question_title_sel));
		mQuestionTitle.setOnClickListener(this);
		mQuestionDesc = (TextView)findViewById(R.id.question_pub_title_center);
		mQuestionDesc.setTextColor(getResources().getColor(R.color.question_title_nor));
		mQuestionDesc.setOnClickListener(this);
		mQuestionType = (TextView)findViewById(R.id.question_pub_title_right);
		mQuestionType.setTextColor(getResources().getColor(R.color.question_title_nor));
		mQuestionType.setOnClickListener(this);
		//初始化ViewPager
		mViewPager = (ViewPager)findViewById(R.id.question_pub_viewpager);
		mAdapter = new ViewPagerAdapter(mViewList);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setCurrentItem(0);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				//根据当前位置修改TextView颜色
				View v = mViewList.get(position);
				EditText editText = (EditText)v.findViewById(R.id.question_pub_edit);
				changeViewByPosition(position, editText);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
				
			}
		});
		
	}
	
	
	/**
	 * 初始化标题栏
	 */
	private void initTitle() {
		// 左边
		mTitleBackImg = (ImageButton) findViewById(R.id.title_bar_left_img);
		mTitleBackImg.setVisibility(View.GONE);
		mTitleBack = (TextView) findViewById(R.id.title_bar_left);
		mTitleBack.setVisibility(View.VISIBLE);
		mTitleBack.setText(getResources().getString(R.string.cancel));
		mTitleBack.setOnClickListener(this);
		// 右边
		mTitleNext = (TextView) findViewById(R.id.title_bar_right);
		mTitleNext.setVisibility(View.VISIBLE);
		mTitleNext.setText(getResources().getString(R.string.submit));
		mTitleNext.setOnClickListener(this);
		mTitleNextImg = (ImageButton) findViewById(R.id.title_bar_right_img);
		mTitleNextImg.setVisibility(View.GONE);
		// 中间
		mTitleText = (TextView) findViewById(R.id.title_bar_center);
		mTitleText.setText(TAG);
	}

	/**
	 * 根据当前位置修改TextView颜色
	 * @param position
	 */
	private void changeViewByPosition(int position, EditText editText) {
		switch (position) {
		case QUESTION_TITLE:
			mQuestionTitle.setTextColor(getResources().getColor(R.color.question_title_sel));
			mQuestionDesc.setTextColor(getResources().getColor(R.color.question_title_nor));
			mQuestionType.setTextColor(getResources().getColor(R.color.question_title_nor));
			editText.setHint(getResources().getString(R.string.question_pub_title_hint));
			break;

		case QUESTION_DESC:
			mQuestionTitle.setTextColor(getResources().getColor(R.color.question_title_nor));
			mQuestionDesc.setTextColor(getResources().getColor(R.color.question_title_sel));
			mQuestionType.setTextColor(getResources().getColor(R.color.question_title_nor));
			editText.setHint(getResources().getString(R.string.question_pub_desc_hint));
			break;
			
		case QUESTION_TYPE:
			mQuestionTitle.setTextColor(getResources().getColor(R.color.question_title_nor));
			mQuestionDesc.setTextColor(getResources().getColor(R.color.question_title_nor));
			mQuestionType.setTextColor(getResources().getColor(R.color.question_title_sel));
			editText.setHint(getResources().getString(R.string.question_pub_type_hint));
			break;
		}
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_left:
			finish();
			break;
			
		case R.id.title_bar_right:
			Toast.makeText(QuestionPub.this, "发布", Toast.LENGTH_SHORT).show();
			
			break;
		
		case R.id.question_pub_title_left:
			mViewPager.setCurrentItem(QUESTION_TITLE);
			break;

		case R.id.question_pub_title_center:
			mViewPager.setCurrentItem(QUESTION_DESC);
			break;
			
		case R.id.question_pub_title_right:
			mViewPager.setCurrentItem(QUESTION_TYPE);
			break;

		}
	}
	
}
