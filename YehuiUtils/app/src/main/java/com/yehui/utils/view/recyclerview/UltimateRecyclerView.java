package com.yehui.utils.view.recyclerview;

import android.content.Context;
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

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

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
    public PtrFrameLayout ptrFrameLayout;//下拉刷新，上拉加载控件
    private Context context;//实现了该布局的上下文
    public View root;//父布局
    private LayoutInflater inflater;//父容器
    public HeaderView headerView; //头文件的父布局
    public FootView footView;//为布局
    public RelativeLayout loadingLayout;//listview的遮罩层
    private ImageView loading_img;
    private ProgressBar loading_bar;
    private TextView loading_text, loading_empty_text, loading_empty_click_text;
    private Button loading_btn;
    public OnRcvScrollListener onRcvScrollListener;
    private boolean isRefresh;//是否可以下拉刷新

    private boolean isRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(boolean isRefresh) {
        if (isRefresh) {
            if (ptrFrameLayout == null)
                ptrFrameLayout = (PtrFrameLayout) root.findViewById(R.id.swipe_refresh_widget);
            ptrFrameLayout.setHeaderView(headerView);
            ptrFrameLayout.addPtrUIHandler(headerView);
        } else {
            ptrFrameLayout = null;
        }
        this.isRefresh = isRefresh;
    }

    private boolean isLoadMore;//是否可以上拉加载

    private boolean isLoadMore() {
        return isLoadMore;
    }

    public void setIsLoadMore(boolean isLoadMore) {
        if (onRcvScrollListener != null) {
            onRcvScrollListener.setIsShowFoot(isLoadMore);
        }
        this.isLoadMore = isLoadMore;
    }

    public HeaderView.RefreshListener getRefreshListener() {
        return this.headerView.getRefreshListener();
    }

    public void setRefreshListener(HeaderView.RefreshListener refreshListener) {
        this.headerView.setRefreshListener(refreshListener);
    }

    public FootView.LoadMoreListener getLoadMoreListener() {
        return this.footView.getLoadMoreListener();
    }

    public void setLoadMoreListener(FootView.LoadMoreListener loadMoreListener) {
        this.footView.setLoadMoreListener(loadMoreListener);
    }

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

    /**
     * 初始化数据
     *
     * @param context
     */
    private void initialize(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        root = inflater.inflate(R.layout.activity_custom_recycler_view, this);
        loadingLayout = (RelativeLayout) root.findViewById(R.id.loading_layout);
        ptrFrameLayout = (PtrFrameLayout) root.findViewById(R.id.swipe_refresh_widget);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_widget);
        headerView = new HeaderView(context);
        footView = new FootView(context);

        onRcvScrollListener = new OnRcvScrollListener(context, recyclerView, footView);
        recyclerView.addOnScrollListener(onRcvScrollListener);
        initLoadingView();

        /**
         * 注意！
         * 该回调方法必须写！
         * 如果不写则下拉刷新不正常！
         * ptrFrameLayout.refreshComplete();为刷新完成还原视图的方法可以在实现了该回调后自行处理
         */
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                //ptrFrameLayout.refreshComplete();//刷新完成
                //ptrFrameLayout.autoRefresh();//自动刷新
            }
        });
    }


    /**
     * 刷新完成成功后的回调
     */
    public void closeRefreshView() {
        if (isRefresh()) {
            if (ptrFrameLayout != null) {
            }
            ptrFrameLayout.refreshComplete();
        }
    }

    /**
     * 加载更多成功后的回调
     */
    public void closeLoadMoreView() {
        if (isLoadMore()) {
            if (footView != null)
                footView.onFootViewAll();
        }
    }

    /**
     * 加载更多后隐藏视图
     */
    public void onFootViewEmpty() {
        if (isLoadMore()) {
            if (footView != null)
                footView.onFootViewEmpty();
        }
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

//    /**
//     * 以下是上拉加载
//     */
//    public void setOnScrollListener(RecyclerView.OnScrollListener customOnScrollListener) {
//        recyclerView.setOnScrollListener(customOnScrollListener);
//    }
//
//    public void addOnScrollListener(RecyclerView.OnScrollListener customOnScrollListener) {
//        recyclerView.addOnScrollListener(customOnScrollListener);
//    }
//
//    public void removeOnScrollListener(RecyclerView.OnScrollListener customOnScrollListener) {
//        recyclerView.removeOnScrollListener(customOnScrollListener);
//    }

}