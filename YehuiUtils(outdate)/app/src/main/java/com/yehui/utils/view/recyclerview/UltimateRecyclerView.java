package com.yehui.utils.view.recyclerview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.utils.EmptyUtil;

/**
 * Created by yehuijifeng
 * on 2015/12/12.
 * 最终的recyclerview
 */
public class UltimateRecyclerView extends LinearLayout {

    /**
     * SwipeRefreshLayout里面需要注意的Api：
     * 1、setOnRefreshListener(OnRefreshListener listener)  设置下拉监听，当用户下拉的时候会去执行回调
     * 2、setColorSchemeColors(int... colors) 设置 进度条的颜色变化，最多可以设置4种颜色
     * 3、setProgressViewOffset(boolean scale, int start, int end) 调整进度条距离屏幕顶部的距离
     * 4、setRefreshing(boolean refreshing) 设置SwipeRefreshLayout当前是否处于刷新状态，一般是在请求数据的时候设置为true，在数据被加载到View中后，设置为false。
     */

    public RecyclerView recyclerView;//代替listview和graidview
    //private LinearLayoutManager linearLayoutManager;//recyclerview的管理器
    public SwipeRefreshLayout swipeRefreshLayout;//默认下拉刷新控件
    public SuperSwipeRefreshLayout superSwipeRefreshLayout;//自定义下拉刷新和上拉加载
    private Context context;//实现了该布局的上下文
    public View root;//父布局
    public View headerView; //头文件的父布局
    public View footerView;//尾布局


    private TextView custom_header_hint_text;//下拉刷新提示
    private TextView custom_header_time;//刷新时间
    private ImageView custom_header_image;//刷新动画
    private ProgressBar custom_header_bar;//刷新进度

    private TextView custom_footer_hint_text;//下拉刷新提示
    private ProgressBar custom_footer_bar;//刷新进度

    public RelativeLayout loadingLayout;//listview的遮罩层
    private ImageView loading_img;
    private ProgressBar loading_bar;
    private TextView loading_text, loading_empty_text, loading_empty_click_text;
    private Button loading_btn;

    public UltimateRecyclerView(Context context) {
        super(context);
        initialize(context);
    }

    public UltimateRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public UltimateRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
        this.context = context;
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LayoutInflater inflater = LayoutInflater.from(context);
        if (isDefaultRefresh()) {
            root = inflater.inflate(R.layout.activity_recycler_view, this);
            swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipe_refresh_widget);
        } else {
            root = inflater.inflate(R.layout.activity_custom_recycler_view, this);
            superSwipeRefreshLayout = (SuperSwipeRefreshLayout) root.findViewById(R.id.custom_header_view);
            customSwipeRefresh();
        }

        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_widget);
        loadingLayout = (RelativeLayout) root.findViewById(R.id.loading_layout);
        initLoadingView();
    }

    /**
     * 初始化正在加载遮罩层
     */
    private void initLoadingView() {
        loading_img = (ImageView) loadingLayout.findViewById(R.id.loading_img);
        loading_bar = (ProgressBar) loadingLayout.findViewById(R.id.loading_bar);
        loading_text = (TextView) loadingLayout.findViewById(R.id.loading_text);
        loading_btn = (Button) loadingLayout.findViewById(R.id.loading_btn);
        loading_empty_click_text = (TextView) loadingLayout.findViewById(R.id.loading_empty_click_text);
        loading_empty_text = (TextView) loadingLayout.findViewById(R.id.loading_empty_text);
    }

    /**
     * 加载成功，隐藏遮罩层
     */
    public void loadingSuccess() {
//        loading_empty_text.setVisibility(GONE);
//        loading_img.setVisibility(GONE);
//        loading_bar.setVisibility(GONE);
//        loading_text.setVisibility(GONE);
//        loading_btn.setVisibility(GONE);
        loadingLayout.setVisibility(GONE);
    }

    /**
     * 加载失败的点击事件处理
     *
     * @param l
     */
    public void loadingFailBtnClick(OnClickListener l) {
        loading_btn.setOnClickListener(l);
        loadingLayout.setOnClickListener(l);
    }

    /**
     * 加载失败，提示语
     *
     * @param failTextStr 文本提示
     * @param fialBtnStr  按钮上的文字
     */
    public void loadingFail(String failTextStr, String fialBtnStr) {
        loading_empty_text.setVisibility(GONE);
        loading_img.setVisibility(GONE);
        loading_bar.setVisibility(GONE);
        loading_text.setVisibility(VISIBLE);
        loading_btn.setVisibility(VISIBLE);
        loading_empty_click_text.setVisibility(GONE);
        if (EmptyUtil.isStringEmpty(failTextStr))
            loading_text.setText(getResources().getString(R.string.lading_fail));
        else
            loading_text.setText(failTextStr + "");

        if (!EmptyUtil.isStringEmpty(fialBtnStr))
            loading_btn.setText(fialBtnStr + "");
        else
            loading_btn.setText(getResources().getString(R.string.hint_refresh));

    }


    /**
     * 加载出空数据的时候
     *
     * @param emptyStr 空数据提示语
     */
    public void loadingEmpty(String emptyStr, String fialBtnStr) {

        loading_empty_text.setVisibility(VISIBLE);
        loading_img.setVisibility(VISIBLE);
        loading_bar.setVisibility(GONE);
        loading_text.setVisibility(GONE);
        loading_btn.setVisibility(GONE);
        loading_empty_click_text.setVisibility(VISIBLE);
        if (!EmptyUtil.isStringEmpty(emptyStr))
            loading_empty_text.setText(emptyStr + "");
        else
            loading_empty_text.setText(getResources().getString(R.string.lading_empty));

        if (!EmptyUtil.isStringEmpty(fialBtnStr))
            loading_btn.setText(fialBtnStr + "");
        else
            loading_btn.setText(getResources().getString(R.string.hint_refresh));

    }

    /**
     * 正在加载中
     *
     * @param loadingStr 正在加载中提示语
     */
    public void loadingView(String loadingStr) {
        loading_empty_text.setVisibility(GONE);
        loading_img.setVisibility(GONE);
        loading_bar.setVisibility(VISIBLE);
        loading_text.setVisibility(VISIBLE);
        loading_btn.setVisibility(GONE);
        loading_empty_click_text.setVisibility(GONE);
        if (!EmptyUtil.isStringEmpty(loadingStr))
            loading_text.setText(loadingStr + "");
        else
            loading_text.setText(getResources().getString(R.string.header_hint_loading));
    }

    /**
     * 是否是默认的下拉刷新
     */
    public boolean isDefaultRefresh() {
        return false;
    }


    /**
     * 以下是下拉刷新的方法
     */
    public void setOnScrollListener(RecyclerView.OnScrollListener customOnScrollListener) {
        recyclerView.setOnScrollListener(customOnScrollListener);
    }

    public void addOnScrollListener(RecyclerView.OnScrollListener customOnScrollListener) {
        recyclerView.addOnScrollListener(customOnScrollListener);
    }

    public void removeOnScrollListener(RecyclerView.OnScrollListener customOnScrollListener) {
        recyclerView.removeOnScrollListener(customOnScrollListener);
    }

    /**
     * 关闭下拉刷新视图
     */
    public void closeRefreshView() {
        if (superSwipeRefreshLayout != null) {
            superSwipeRefreshLayout.setRefreshing(false);
            custom_header_bar.setVisibility(View.GONE);
        }
    }

    /**
     * 显示正在刷新中的视图
     */
    public void showRefreshView() {
        custom_header_hint_text.setText("正在刷新");
        custom_header_image.setVisibility(View.GONE);
        custom_header_bar.setVisibility(View.VISIBLE);
    }

    /**
     * 改变刷新状态
     */
    public void changeRefreshView(boolean enable) {
        custom_header_hint_text.setText(enable ? "松开刷新" : "下拉刷新");
        custom_header_image.setVisibility(View.VISIBLE);
        custom_header_image.setRotation(enable ? 180 : 0);
    }

    /**
     * 关闭上拉加载视图
     */
    public void closeLoadMoreView() {
        if (superSwipeRefreshLayout != null) {
            superSwipeRefreshLayout.setLoadMore(false);
            custom_footer_bar.setVisibility(View.GONE);
        }
    }

    /**
     * 已经加载完全部数据
     */
    public void successLoadAllView(String promptStr) {
        if (superSwipeRefreshLayout != null) {
            superSwipeRefreshLayout.setLoadMore(false);
            custom_footer_bar.setVisibility(View.GONE);
            custom_footer_hint_text.setText(EmptyUtil.isStringEmpty(promptStr) ? "加载完全部数据" : promptStr + "");
        }
    }

    /**
     * 显示正在加载的视图
     */
    public void showLoadMoreView() {
        custom_footer_hint_text.setText("正在加载中... ...");
        custom_footer_bar.setVisibility(View.VISIBLE);
    }

    /**
     * 改变刷新状态
     */
    public void changeLoadMoreView(boolean enable) {
        custom_footer_hint_text.setText(enable ? "松开加载更多" : "上拉加载更多");
        custom_footer_bar.setVisibility(View.GONE);
    }

    /**
     * 创建自定义的拉下刷新和上拉加载视图
     */
    private void customSwipeRefresh() {

        superSwipeRefreshLayout.setHeaderViewBackgroundColor(getResources().getColor(R.color.gray));
        superSwipeRefreshLayout.setHeaderView(createHeaderView());
        superSwipeRefreshLayout.setFooterView(createFooterView());
        superSwipeRefreshLayout.setTargetScrollWithLayout(true);//子view是否跟着下拉刷新一起滑动
    }

    /**
     * 创建自定义的头布局
     */
    private View createHeaderView() {
        headerView = LayoutInflater.from(context)
                .inflate(R.layout.layout_custom_headher, null);
        custom_header_hint_text = (TextView) headerView.findViewById(R.id.custom_header_hint_text);
        custom_header_hint_text.setText("下拉刷新");

        custom_header_time = (TextView) headerView.findViewById(R.id.custom_header_time);
        custom_header_time.setText("10:42:59");

        custom_header_image = (ImageView) headerView.findViewById(R.id.custom_header_image);
        custom_header_image.setVisibility(View.VISIBLE);
        custom_header_image.setImageResource(R.drawable.xlistview_arrow);

        custom_header_bar = (ProgressBar) headerView.findViewById(R.id.custom_header_bar);
        custom_header_bar.setVisibility(View.GONE);
        return headerView;
    }

    /**
     * 创建自定义尾布局
     */
    private View createFooterView() {
        footerView = LayoutInflater.from(context)
                .inflate(R.layout.layout_custom_footer, null);
        custom_footer_hint_text = (TextView) footerView.findViewById(R.id.custom_footer_hint_text);
        custom_footer_hint_text.setText("上拉加载更多");

        custom_footer_bar = (ProgressBar) footerView.findViewById(R.id.custom_footer_bar);
        custom_footer_bar.setVisibility(View.GONE);
        return footerView;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //if (isShowHeader)
//        //return onTouchEventListener(event);
//        //return true;
//    }
//    /**
//     * 手势滑动监听处理
//     *
//     * @param ev
//     * @return
//     */
//    public boolean isShowHeader = false, isOnePosition = true;
//    private float downY, moveY;
//    private boolean isTouch = false;
//
//    public boolean onTouchEventListener(MotionEvent ev) {
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
//                    headerView.alterRefrechCustomView(moveY);
//                isTouch=true;
//                LogUtil.i("滑动y:" + moveY);
//                break;
//        }
//        return isTouch;
//    }
//
//        superSwipeRefreshLayout
//                .setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
//
//                    @Override
//                    public void onLoadMore() {
//                        footerTextView.setText("正在加载...");
//                        footerImageView.setVisibility(View.GONE);
//                        footerProgressBar.setVisibility(View.VISIBLE);
//                        new Handler().postDelayed(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                footerImageView.setVisibility(View.VISIBLE);
//                                footerProgressBar.setVisibility(View.GONE);
//                                swipeRefreshLayout.setLoadMore(false);
//                            }
//                        }, 5000);
//                    }
//
//                    @Override
//                    public void onPushEnable(boolean enable) {
//                        footerTextView.setText(enable ? "松开加载" : "上拉加载");
//                        footerImageView.setVisibility(View.VISIBLE);
//                        footerImageView.setRotation(enable ? 0 : 180);
//                    }
//
//                    @Override
//                    public void onPushDistance(int distance) {
//                        // TODO Auto-generated method stub
//
//                    }
//
//                });
//
//
//    /**
//     * 计算滚动
//     */
//    @Override
//    public void computeScroll() {
//        super.computeScroll();
//    }
}
