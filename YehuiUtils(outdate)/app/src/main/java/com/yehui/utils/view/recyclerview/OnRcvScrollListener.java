//package com.yehui.utils.view.recyclerview;
//
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//
//
///**
// * Created by yehuijifeng
// * on 2015/12/15.
// * recyclerview的滑动监听事件
// */
//public class OnRcvScrollListener extends RecyclerView.OnScrollListener {
//
//    private LoadRecyclerListeners loadRecyclerListeners;//接口
//    private boolean isLast = false, isShowFoot = false, isShowHeader = false;//是否处于下拉状态
//    public LinearLayoutManager linearLayoutManager;//布局管理者
//
//    public OnRcvScrollListener(LoadRecyclerListeners loadMoreListeners) {
//        loadRecyclerListeners = loadMoreListeners;
//    }
//
//    /**
//     * 监听下拉事件
//     *
//     * @param recyclerView 刷新的view
//     * @param newState     刷新的状态码，0，停止下拉；1，拖动的时候；2，固定，回弹的时候
//     */
//    @Override
//    public void onScrollStateChanged(RecyclerView recyclerView,
//                                     int newState) {
//        super.onScrollStateChanged(recyclerView, newState);
//        linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//            //LogUtil.i("下拉中");
//            //获取最后一个完全显示的item，下标
//            int lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
//            //获取总行数
//            int totalItemCount = linearLayoutManager.getItemCount();
//            // 判断是否滚动到底部，并且是向右滚动
//            if (lastVisibleItem == (totalItemCount - 1) && isLast) {
//                //LogUtil.i("这是最后一个item，下标：" + linearLayoutManager.findLastCompletelyVisibleItemPosition());
//                isShowFoot = loadRecyclerListeners.isShowFootView(true);
//            } else {
//                isShowFoot = loadRecyclerListeners.isShowFootView(false);
//            }
//        } else if (newState == 1) {
//            //LogUtil.i("滑动中");
//linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//@Override
//public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//        switch (newState) {
//        // 当不滚动时
//        case NumberPicker.OnScrollListener.SCROLL_STATE_IDLE:
//        // 判断滚动到底部
//        if (linearLayoutManager.findViewByPosition(linearLayoutManager.findFirstVisibleItemPosition()).getTop() == 0 && linearLayoutManager.findFirstVisibleItemPosition() == 0) {// 判断滚动到底部
//        }
//
//        if (linearLayoutManager.findViewByPosition(linearLayoutManager.findFirstVisibleItemPosition()).getTop() == linearLayoutManager.getItemCount() && linearLayoutManager.findFirstVisibleItemPosition() == linearLayoutManager.getItemCount()) {// 判断滚动到顶部
//        }
//        break;
//        }
//        }
//
//@Override
//public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//        super.onScrolled(recyclerView, dx, dy);
//        }
//        });
//            if (linearLayoutManager.findViewByPosition(linearLayoutManager.findFirstVisibleItemPosition()).getTop() == 0
//                    && linearLayoutManager.findFirstVisibleItemPosition() == 0
//                    && isShowHeader) {
//                //LogUtil.i("在第一行");
//                //LogUtil.i("第一个显示的item为0");
//                //LogUtil.i("滑动状态一直保持");
//                loadRecyclerListeners.isShowHeaderView(true);
//            } else {
//                loadRecyclerListeners.isShowHeaderView(false);
//            }
//        } else if (newState == 2) {
//            //LogUtil.i("上弹中");
//        }
//    }
//
//    /**
//     * 下拉事件
//     *
//     * @param recyclerView
//     * @param dx
//     * @param dy
//     */
//    @Override
//    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//        super.onScrolled(recyclerView, dx, dy);
//
//        loadRecyclerListeners.scrollListener(dx, dy);
//        if (dx > 0) {
//            //正在向右滚动
//            isLast = true;
//        } else if (dy > 0) {
//            //正在向下滚动
//            //LogUtil.i("向右：" + dx + "向下：" + dy);
//            //LogUtil.i("滑动状态停止");
//            isLast = true;
//            isShowHeader = false;
//        } else if (dy < 0) {
//            //LogUtil.i("向右：" + dx + "向下：" + dy);
//            //LogUtil.i("正在滑动状态");
//            //正在往下滑动
//            isShowHeader = true;
//            isLast = false;
//        }
//    }
//
//}
