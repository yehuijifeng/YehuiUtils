package com.yehui.utils.view.recyclerview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.yehui.utils.R;

/**
 * Created by yehuijifeng
 * on 2015/12/9.
 * 默认list的布局
 */
public class MyRecyclerView extends UltimateRecyclerView{

    public MyRecyclerView(Context context) {
        super(context, null);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setFocusable(true);
//        setFocusableInTouchMode(true);
    }

    /**
     * 默认的下拉刷新
     * @param swipeRefreshLayout
     */
    public final void defaultRefresh(SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setColorSchemeResources(
                R.color.default_load_more_color_one,
                R.color.default_load_more_color_tow,
                R.color.default_load_more_color_three,
                R.color.default_load_more_color_four);
    }

    /**
     * 手势滑动监听处理
     * @param ev
     * @return
     */
//    private float downY, moveY;
//    private boolean isTouch=false;
//    private boolean onTouchEventListener(MotionEvent ev) {
//        if (isOnePosition) {
//            downY = ev.getRawY();//float DownX
//            LogUtil.i("起始位置y:" + downY);
//            isOnePosition = false;
//        }
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN://按下
//                isTouch=false;
//                break;
//            case MotionEvent.ACTION_UP://抬起
//                LogUtil.i("抬起手指了");
//                isShowHeader = false;
//                isOnePosition = true;
//                isTouch=false;
//                downY = 0;
//                moveY = 0;
//                LayoutParams lp = (LayoutParams) headerView
//                        .getLayoutParams();
//                lp.height = 0;
//                headerView.setLayoutParams(lp);
//                break;
//            case MotionEvent.ACTION_MOVE://手指滑动
//                moveY = ev.getRawY() - downY;//y
//                if(!isDefaultRefresh())
//                headerView.alterRefrechCustomView(moveY);
//                isTouch=true;
//                LogUtil.i("滑动y:" + moveY);
//                break;
//        }
//        return isTouch;
//    }



    //    public boolean onTouchEv(View v, MotionEvent event) {
//        if (isShowHeader)
//            return onTouchEventListener(event);
//        return false;
//    }
}
