package com.foolish.app.nouse;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.foolish.app.R;

/**
 * The footer view for {@link com.foolish.app.nouse.PullToRefreshListView.pulltorefresh.widget.XListView} and
 * {@link com.foolish.app.nouse.PullToRefreshScrollView.pulltorefresh.widget.XScrollView}
 *
 * @author markmjw
 * @date 2013-10-08
 */
public class FooterLoadingLayout extends LinearLayout {
    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_LOADING = 2;

    private final int ROTATE_ANIM_DURATION = 180;

    private View mLayout;

    private View mProgressBar;

    private TextView mTipsView;

    private int mState = STATE_NORMAL;

    public FooterLoadingLayout(Context context) {
        super(context);
        initView(context);
    }

    public FooterLoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mLayout = LayoutInflater.from(context).inflate(R.layout.footer_load_more, null);
        mLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        addView(mLayout);

        mProgressBar = mLayout.findViewById(R.id.drop_down_to_loadmore_progressBar);
        mTipsView = (TextView) mLayout.findViewById(R.id.drop_down_to_loadmore_tips);
    }

    /**
     * Set footer view state
     *
     * @see #STATE_LOADING
     * @see #STATE_NORMAL
     * @see #STATE_READY
     *
     * @param state
     */
    public void setState(int state) {
        if (state == mState) return;

        if (state == STATE_LOADING) {
            mProgressBar.setVisibility(View.VISIBLE);
            mTipsView.setText(R.string.drop_down_to_loadmore_loading);
        } else {
        	mTipsView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
        }

        switch (state) {
            case STATE_NORMAL:
            	mTipsView.setText(R.string.drop_down_to_loadmore_tips);
                break;

            case STATE_READY:
                if (mState != STATE_READY) {
                	mTipsView.setText(R.string.drop_down_to_loadmore_release);
                }
                break;

            case STATE_LOADING:
                break;
        }

        mState = state;
    }

    /**
     * Set footer view bottom margin.
     *
     * @param margin
     */
    public void setBottomMargin(int margin) {
        if (margin < 0) return;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mLayout.getLayoutParams();
        lp.bottomMargin = margin;
        mLayout.setLayoutParams(lp);
    }

    /**
     * Get footer view bottom margin.
     *
     * @return
     */
    public int getBottomMargin() {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mLayout.getLayoutParams();
        return lp.bottomMargin;
    }

    /**
     * normal status
     */
    public void normal() {
    	mTipsView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    /**
     * loading status
     */
    public void loading() {
    	mTipsView.setText(R.string.drop_down_to_loadmore_loading);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * hide footer when disable pull load more
     */
    public void hide() {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mLayout.getLayoutParams();
        lp.height = 0;
        mLayout.setLayoutParams(lp);
    }

    /**
     * show footer
     */
    public void show() {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mLayout.getLayoutParams();
        lp.height = LayoutParams.WRAP_CONTENT;
        mLayout.setLayoutParams(lp);
    }

}
