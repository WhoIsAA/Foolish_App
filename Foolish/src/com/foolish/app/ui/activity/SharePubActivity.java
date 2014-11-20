package com.foolish.app.ui.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.foolish.app.R;
import com.foolish.app.ui.BaseActivity;
import com.foolish.app.utils.SystemUtils;
import com.foolish.app.utils.ToastUtils;


/**
 * 发表分享界面
 * @author AA
 * @date 2014-11-07
 * 
 */
public class SharePubActivity extends BaseActivity implements OnClickListener{

	private static final String TAG = "与你分享";
	private ImageButton mTitleBackImg;
	private ImageButton mTitleNextImg;
	private TextView mTitleText;
	private TextView mTitleBack;
	private TextView mTitleNext;
	
	private EditText mContentEdit;
	private ImageButton mFaceBtn;
	private ImageButton mPictureBtn;
	private ImageButton mCameraBtn;
	private TextView mPublicBtn;
	private TextView mCommentBtn;
	
	private PopupWindow mPopupWindow;
	private ListView mPopupWindowListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_share_pub);

		initView();

	}

	private void initView() {
		initTitle();
		mContentEdit = (EditText)findViewById(R.id.share_content_edit);
		mContentEdit.setOnFocusChangeListener(onFocusChangeListener);
		mFaceBtn = (ImageButton)findViewById(R.id.share_face_btn);
		mFaceBtn.setOnClickListener(this);
		mPictureBtn = (ImageButton)findViewById(R.id.share_picture_btn);
		mPictureBtn.setOnClickListener(this);
		mCameraBtn = (ImageButton)findViewById(R.id.share_camera_btn);
		mCameraBtn.setOnClickListener(this);
		mPublicBtn = (TextView)findViewById(R.id.share_public_btn);
		mPublicBtn.setOnClickListener(this);
		mCommentBtn = (TextView)findViewById(R.id.share_comment_btn);
		mCommentBtn.setOnClickListener(this);
	}

	/**
	 * 设置标题栏
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
		mTitleNext.setText(getResources().getString(R.string.share));
		mTitleNext.setOnClickListener(this);
		mTitleNextImg = (ImageButton) findViewById(R.id.title_bar_right_img);
		mTitleNextImg.setVisibility(View.GONE);
		// 中间
		mTitleText = (TextView) findViewById(R.id.title_bar_center);
		mTitleText.setText(TAG);
	}
	
	
	private OnFocusChangeListener onFocusChangeListener = new OnFocusChangeListener() {
		
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if(hasFocus) {
				SystemUtils.showSoftInput(SharePubActivity.this, v);
			} else {
				SystemUtils.hideSoftInput(SharePubActivity.this, v);
			}
		}
	};
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_left:
			finish();
			break;
			
		case R.id.title_bar_right:
			ToastUtils.show(SharePubActivity.this, R.string.share);
			break;
			
		case R.id.share_face_btn:
			ToastUtils.show(SharePubActivity.this, "表情按钮");
			break;
			
		case R.id.share_picture_btn:
			ToastUtils.show(SharePubActivity.this, "图片按钮");
			break;
			
		case R.id.share_camera_btn:
			ToastUtils.show(SharePubActivity.this, "相机按钮");
			break;
			
		case R.id.share_public_btn:
			showPopupWindow(getData(new String[]{"公开", "私密"}), v);
			break;

		case R.id.share_comment_btn:
			showPopupWindow(getData(new String[]{"允许评论", "禁止评论"}), v);
			break;
		}
		
	}
	
	
	/**
	 * 显示对话框
	 * @param list
	 * @param upwardView
	 */
	private void showPopupWindow(final List<HashMap<String, String>> list, final View upwardView) {
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.pw_listview_layout, null);
		mPopupWindowListView = (ListView)layout.findViewById(R.id.pw_detail_listview);
		mPopupWindow = new PopupWindow(layout);
		//加上这个PopupWindow中的ListView才可以接收点击事件
		mPopupWindow.setFocusable(true);
		//设置适配器
		mPopupWindowListView.setAdapter(new SimpleAdapter(this, list, R.layout.pw_item_share_pub, new String[]{"key"}, new int[]{R.id.pw_detail_listview_item}));
		//设置滚动条
		mPopupWindowListView.setDivider(getResources().getDrawable(R.drawable.pw_list_divider));
		mPopupWindowListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(upwardView.getId() == R.id.share_public_btn) {
					mPublicBtn.setText(list.get(position).get("key").toString());
					mPopupWindow.dismiss();
				} else if(upwardView.getId() == R.id.share_comment_btn) {
					mCommentBtn.setText(list.get(position).get("key").toString());
					mPopupWindow.dismiss();
				}
			}
		});
		//控制PopupWindow的宽度和高度自适应
		mPopupWindowListView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		mPopupWindow.setWidth(upwardView.getWidth());
		mPopupWindow.setHeight((mPopupWindowListView.getMeasuredHeight() * 2) + 4);
		//设置背景图片，不能再布局中设置，要通过代码来设置
		mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.pw_share_pub_bg_selector));
		//点屏幕其他地方对话框消失
		mPopupWindow.setOutsideTouchable(true);
		//设置对话框位置
		int[] location = new int[2];  
		upwardView.getLocationOnScreen(location);
		mPopupWindow.showAtLocation(upwardView, Gravity.NO_GRAVITY, location[0], location[1]-mPopupWindow.getHeight()+4);
	}
	
	
	/**
	 * 获得数据
	 * @param data
	 * @return
	 */
	private List<HashMap<String, String>> getData(String[] data) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		for(int i=0; i<data.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("key", data[i]);
			list.add(map);
		}
		
		return list;
	}

}
