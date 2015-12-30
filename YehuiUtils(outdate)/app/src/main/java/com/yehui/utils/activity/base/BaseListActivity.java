package com.yehui.utils.activity.base;

import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yehui.utils.R;
import com.yehui.utils.adapter.base.BaseAdapter;
import com.yehui.utils.adapter.base.BaseViewHolder;
import com.yehui.utils.utils.DisplayUtil;
import com.yehui.utils.view.recyclerview.MyRecyclerView;
import com.yehui.utils.view.recyclerview.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yehuijifeng
 * on 2015/11/25.
 * listview的activity
 */
public abstract class BaseListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    /**
     * 每一行的布局文件id
     *
     * @param type
     */
    protected abstract int getItemLayoutResId(int type);

    /**
     * 初始化每一行的数据
     *
     * @param holder
     * @param position
     */
    protected abstract void initItemData(BaseViewHolder holder, int position);

    /**
     * 需要放入viewholder
     *
     * @param itemView
     * @param type
     * @return
     */
    protected abstract BaseViewHolder getViewHolder(View itemView, int type);

    /**
     * item的点击事件
     *
     * @param parent
     * @param itemView
     * @param position
     */
    protected abstract void onItemClick(RecyclerView parent, View itemView, int position);

    /**
     * 初始化
     */
    protected MyAdapter mAdapter;
    protected List<Object> data = new ArrayList<>();
    private MyRecyclerView mRecyclerView;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    protected LinearLayoutManager layoutManager;
    protected SuperSwipeRefreshLayout superSwipeRefreshLayout;
    /**
     * 创建横向的还是纵向的recyclerview
     * 默认,0，纵向；1，横线
     */
    protected int isHorizaontalOrVertical() {
        return 0;
    }

    /**
     * 设置每个item的间距
     */
    protected float itemDecoration() {
        return 0;
    }

    @Override
    protected void initView() {
        //实例化recyclerview
        mRecyclerView = (MyRecyclerView) findViewById(R.id.recycler_list_view);
        recyclerView = (RecyclerView) mRecyclerView.findViewById(R.id.recycler_view_widget);
        // 创建一个线性布局管理器
        layoutManager = new LinearLayoutManager(this);
        // 默认是Vertical
        layoutManager.setOrientation(isHorizaontalOrVertical() == 0 ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //实例化adapter
        mAdapter = new MyAdapter(data);
        //添加适配器
        recyclerView.setAdapter(mAdapter);
        //添加item的间距
        recyclerView.addItemDecoration(new SpaceItemDecoration(DisplayUtil.dip2px(this, itemDecoration())));
        //是否下拉刷新
        defaultRefresh();
        //是否上拉加载更多
        defaultLoadMore();
    }

    /**
     * 加载失败的点击事件
     */
    class loadingFailBtnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            loadingView(null);
            reLoad();
        }
    }

    /**
     * 加载成功，隐藏遮罩层
     */
    protected void loadingSuccess() {
        mRecyclerView.loadingSuccess();
    }

    /**
     * 加载失败，提示语
     * @param failTextStr 文本提示
     * @param fialBtnStr  按钮上的文字
     */
    protected void loadingFail(String failTextStr, String fialBtnStr) {
        mRecyclerView.loadingFail(failTextStr, fialBtnStr);
        mRecyclerView.loadingFailBtnClick(new loadingFailBtnClick());
    }

    /**
     * 加载出空数据的时候
     * @param emptyStr 空数据提示语
     */
    protected void loadingEmpty(String emptyStr, String fialBtnStr) {
        mRecyclerView.loadingEmpty(emptyStr,fialBtnStr);
    }

    /**
     * 正在加载中
     * @param loadingStr 正在加载中提示语
     */
    protected void loadingView(String loadingStr) {
        mRecyclerView.loadingView(loadingStr);
    }

    @Override
    protected void initData() {

    }

    /**
     * 下拉刷新监听事件
     */
    private void defaultRefresh() {
        if (mRecyclerView.isDefaultRefresh()) {
            swipeRefreshLayout = mRecyclerView.swipeRefreshLayout;
            if (!isRefresh()) {
                swipeRefreshLayout.setRefreshing(true);
                return;
            }
            mRecyclerView.defaultRefresh(swipeRefreshLayout);
            swipeRefreshLayout.setOnRefreshListener(this);
        } else {
            superSwipeRefreshLayout = mRecyclerView.superSwipeRefreshLayout;
            superSwipeRefreshLayout.setIsRefresh(isRefresh());
            superSwipeRefreshLayout
                    .setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
                        /**
                         * 刷新中
                         */
                        @Override
                        public void onRefresh() {
                            mRecyclerView.showRefreshView();
                            refresh();
                        }

                        /**
                         * 下拉的距离
                         */
                        @Override
                        public void onPullDistance(int distance) {
                        }

                        /**
                         * 下拉是否启用
                         */
                        @Override
                        public void onPullEnable(boolean enable) {
                            mRecyclerView.changeRefreshView(enable);
                        }
                    });
        }
    }

    /**
     * 默认刷新，当松手的时候会调用此方法
     */
    @Override
    public void onRefresh() {
        if (isRefresh()) {
            refresh();
        }
    }

    /**
     * 默认加载更多的方法
     */
    private void defaultLoadMore() {
        if (superSwipeRefreshLayout == null) return;
        superSwipeRefreshLayout.setIsLoadMore(isLoadMore());
        superSwipeRefreshLayout
                .setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {

                    /**
                     * 正在加载更多
                     */
                    @Override
                    public void onLoadMore() {
                        mRecyclerView.showLoadMoreView();
                        loadMore();
                    }

                    @Override
                    public void onPushEnable(boolean enable) {
                        mRecyclerView.changeLoadMoreView(enable);
                    }

                    /**
                     * 滑动的距离
                     */
                    @Override
                    public void onPushDistance(int distance) {

                    }

                });

//        mCustomLoadMoreView.setVisibility(View.GONE);
//        if (!isLoadMore()) return;
//
//        loadMoreListeners = new LoadRecyclerListeners() {
//            /**
//             * @param isLoadSuccess，是否最后一行出现
//             * @return
//             */
//            @Override
//            public boolean isShowFootView(boolean isLoadSuccess) {
//                if (isLoadSuccess) {
//                    if (data.size() == 0) return false;
//                    //LogUtil.i("list处于停滞状态，并且最后一行显示在屏幕之内了");
//                    mCustomLoadMoreView.setVisibility(View.VISIBLE);
//                    loadMoreViewText.setText("正在加载中... ...");
//                    loadMoreViewProgressbar.setVisibility(View.VISIBLE);
//                    loadMore();
//                    return true;
//                } else {
//                    //LogUtil.i("list处于停滞状态，最后一行未显示");
//                    //mCustomLoadMoreView.setVisibility(View.GONE);
//                    return false;
//                }
//            }
//
//            /**
//             * @param isShowHeader，是否头部出现，可以下拉刷新
//             * @return
//             */
//            @Override
//            public boolean isShowHeaderView(boolean isShowHeader) {
//                if (isShowHeader) {
//                    //通过ontouch时间来显示头布局文件
//                    //mRecyclerView.isShowHeader=true;
//                }
//                return false;
//            }
//
//            /**
//             * @param dx
//             * @param dy
//             */
//            @Override
//            public void scrollListener(int dx, int dy) {
//
//            }
//        };
//        mRecyclerView.defaultLoarMoreListener(loadMoreListeners);
    }


    /**
     * 加载更多,子类需要重写
     */
    protected void reLoad() {}

    /**
     * 下拉刷新方法,子类需要重写
     */
    protected void refresh(){}
    /**
     * 是否下拉刷新
     */
    protected boolean isRefresh() {
        return true;
    }

    /**
     * 是否上拉加载更多
     */
    protected boolean isLoadMore() {
        return true;
    }

    /**
     * 上拉加载更多,根据情况子类重写
     */
    protected void loadMore() {
    }

    /**
     * 加载更多成功
     */
    protected void loadMoreSuccess() {
        if (isLoadMore()) {
            mRecyclerView.closeLoadMoreView();
        }
    }

    /**
     * 刷新成功
     */
    protected void refreshSuccess() {
        if (isRefresh()) {
            if (mRecyclerView.isDefaultRefresh())
                swipeRefreshLayout.setRefreshing(false);//刷新动画重新开始
            else
                mRecyclerView.closeRefreshView();
        }
    }

//    protected void loadSuccess() {
//        if (isLoadMore()) {
//            mRecyclerView.closeLoadMoreView();
//        }
//        if (isRefresh()) {
//            if (mRecyclerView.isDefaultRefresh())
//                swipeRefreshLayout.setRefreshing(false);//刷新动画重新开始
//            else
//                mRecyclerView.closeRefreshView();
//        }
//        notifyDataChange();
//    }




    /**
     * 给recyclerview加横线
     */
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            //设置左右的间隔如果想设置的话自行设置，我这用不到就注释掉了
            //outRect.left = space;
            //outRect.right = space;
            //outRect.bottom = space;

            //改成使用上面的间隔来设置
            if (parent.getChildAdapterPosition(view) != 0)
                outRect.top = space;
        }
    }

    /**
     * 刷新界面
     */
    protected void notifyDataChange() {
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param data
     */
    protected void addAll(List<?> data) {
        if (data == null) {
            return;
        }
        this.data.addAll(data);
    }

    /**
     * 删除数据
     */
    protected void clearDataAll() {
        if (data == null) return;
        mAdapter.clearAll(data);
    }

    /**
     * 获取数据长度
     *
     * @return
     */
    protected int getCount() {
        return data.size();
    }

    /**
     * 默认的加载更多的view
     */
    private View defaultLoadMoreView() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        View view = inflate(R.layout.layout_custom_footer, null);
        view.setLayoutParams(layoutParams);
        return view;
    }

    /**
     * 默认适配器
     */
    public class MyAdapter extends BaseAdapter<Object> {

        public MyAdapter(List<Object> data) {
            super(data);
//            //添加尾部视图
//            setLoadMoreView(defaultLoadMoreView());
//            //提取尾部视图
//            mCustomLoadMoreView = customLoadMoreView;
//            //加载图片
//            loadMoreViewProgressbar = (android.support.v4.widget.ContentLoadingProgressBar) mCustomLoadMoreView.findViewById(R.id.progressbar);
//            //加载文字
//            loadMoreViewText = (TextView) mCustomLoadMoreView.findViewById(R.id.load_more_text);

        }

        /**
         * 每一行item的view都会同时进入getViewHolder和onCreateViewHolderItem
         * getviewholder代表着头或者尾部
         */
        @Override
        public BaseViewHolder getViewHolder(View view) {
            return BaseListActivity.this.getViewHolder(view, -1);
        }

        /**
         * 每一行item数据的初始化都会在该方法回调
         *
         * @param holder   强转成继承了baseviewholder的viewholder
         * @param position item的标识
         */
        @Override
        public void onBindDataForItem(BaseViewHolder holder, int position) {
            holder.itemView.setOnClickListener(new onClickListener(position));
            initItemData(holder, position);
        }

        /**
         * 创建item的viewholder
         * itemview（可以用它实例化item中的控件）
         * viewType用于判断当前item的类型从而new的hivewholder
         */
        @Override
        public BaseViewHolder onCreateViewHolderItem(ViewGroup parent, int viewType) {
            View itemView = inflate(getItemLayoutResId(viewType), parent, false);
            return BaseListActivity.this.getViewHolder(itemView, viewType);
        }

        /**
         * item的tyoe取决于getviewholder方法中的第二个参数
         */
        @Override
        public int getItemType(int position) {
            return BaseListActivity.this.getItemType(position);
        }
    }

    /**
     * 获得当前item的类型
     *
     * @param position
     * @return
     */
    protected int getItemType(int position) {
        return position;
    }


    /**
     * item的点击事件
     */
    class onClickListener implements View.OnClickListener {

        private int position;

        public onClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            onItemClick(mRecyclerView.recyclerView, v, position);
        }
    }


}
