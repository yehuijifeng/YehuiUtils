/**
 * @file XListViewHeader.java
 * @create Apr 18, 2012 5:22:27 PM
 * @author Maxwin
 * @description XListView's header
 */
package com.yehui.utils.testdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yehui.utils.R;

public class XListViewHeader extends LinearLayout {
	private LinearLayout mContainer;//下拉刷新的layout
	private ImageView mArrowImageView;//下拉提示图片
	private ProgressBar mProgressBar;//转动的圆圈
	private TextView mHintTextView;//提示的text
	private int mState = STATE_NORMAL;//默认正常状态

	private Animation mRotateUpAnim;//上拉动画
	private Animation mRotateDownAnim;//下拉动画
	
	private final int ROTATE_ANIM_DURATION = 180;//动画旋转的时间
	
	public final static int STATE_NORMAL = 0;//状态，正常
	public final static int STATE_READY = 1;//迅速滑动，拉动到了一定位置
	public final static int STATE_REFRESHING = 2;//手指松开，listview回弹，正在刷新

	public XListViewHeader(Context context) {
		super(context);
		initView(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public XListViewHeader(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	//初始化的情况下，设置好了头的控件和旋转动画
	private void initView(Context context) {
		// 初始情况，设置下拉刷新view高度为0,初始化状态，将头视图的高度设置为0
		LayoutParams lp = new LayoutParams(
				LayoutParams.MATCH_PARENT, 0);
		mContainer = (LinearLayout) LayoutInflater.from(context).inflate(
				R.layout.xlistview_header, null);
		addView(mContainer, lp);

		setGravity(Gravity.BOTTOM);//并且位于父布局的下方

		//实例化下拉刷新控件
		mArrowImageView = (ImageView)findViewById(R.id.xlistview_header_arrow);
		mHintTextView = (TextView)findViewById(R.id.xlistview_header_hint_textview);
		mProgressBar = (ProgressBar)findViewById(R.id.xlistview_header_progressbar);

		//设置旋转动画
		mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);//旋转所用时间
		mRotateUpAnim.setFillAfter(true);//动画结束之后填充
		//向下的旋转动画
		mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);

		mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateDownAnim.setFillAfter(true);
	}

	/**传入当前list的运动状态
	 * @param state
	 */
	public void setState(int state) {
		if (state == mState) return ;//正常情况下则不用处理
		
		if (state == STATE_REFRESHING) {	// 显示进度
			mArrowImageView.clearAnimation();//图片清理掉动画
			mArrowImageView.setVisibility(View.INVISIBLE);//隐藏图片但不清除内存
			mProgressBar.setVisibility(View.VISIBLE);//旋转进度条显示
		} else {	// 显示箭头图片
			mArrowImageView.setVisibility(View.VISIBLE);//箭头显示
			mProgressBar.setVisibility(View.INVISIBLE);
		}
		
		switch(state){
		case STATE_NORMAL:
			if (mState == STATE_READY) {
				mArrowImageView.startAnimation(mRotateDownAnim);
			}
			if (mState == STATE_REFRESHING) {
				mArrowImageView.clearAnimation();
			}
			mHintTextView.setText(R.string.header_hint_normal);
			break;
		case STATE_READY:
			if (mState != STATE_READY) {
				mArrowImageView.clearAnimation();
				mArrowImageView.startAnimation(mRotateUpAnim);
				mHintTextView.setText(R.string.header_hint_ready);
			}
			break;
		case STATE_REFRESHING:
			mHintTextView.setText(R.string.header_hint_loading);
			break;
		}
		
		mState = state;
	}

	/**
	 * 添加控件需要显示的高度，实时改变
	 * @param height
	 */
	public void setVisiableHeight(int height) {
		if (height < 0)
			height = 0;
		LayoutParams lp = (LayoutParams) mContainer
				.getLayoutParams();
		lp.height = height;
		mContainer.setLayoutParams(lp);
	}

	/**
	 * 获得当前下拉刷新的高度
	 * @return
	 */
	public int getVisiableHeight() {
		return mContainer.getHeight();
	}

}
