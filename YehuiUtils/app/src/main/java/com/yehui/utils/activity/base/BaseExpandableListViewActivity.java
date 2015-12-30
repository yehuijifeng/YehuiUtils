package com.yehui.utils.activity.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.yehui.utils.R;
import com.yehui.utils.adapter.base.BaseExpandableAdapter;
import com.yehui.utils.adapter.base.BaseViewHolder;
import com.yehui.utils.view.recyclerview.HeaderView;
import com.yehui.utils.view.recyclerview.MyExpandableView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by yehuijifeng
 * on 2015/12/30.
 * 多级列表代理类
 */
public abstract class BaseExpandableListViewActivity extends BaseActivity {

    /**
     * 需要放入父类的viewholder
     *
     * @return
     */
    protected abstract BaseViewHolder getGroupViewHolder(ViewGroup parent, int groupPosition, boolean isExpanded);

    /**
     * 父类中旋转的方向箭头图片，若为空则使用默认的
     */
    public abstract ImageView groupImageView();

    /**
     * 父itemview
     */
    public abstract int groupViewByLayout();

    /**
     * 父view每一行的数据
     */
    public abstract void groupItemData(BaseViewHolder baseViewHolder, int groupPosition, boolean isExpanded);

    /**
     * 父view的点击事件
     */
    public abstract void onGroupItemClick(ExpandableListView expandableListView, View itemView, int groupPosition, boolean isExpanded);

    /**
     * 需要放入子viewholder
     */
    protected abstract BaseViewHolder getChildViewHolder(ViewGroup parent, int groupPosition, int childPosition);

    /**
     * 子itemview
     */
    public abstract int childViewByLayout();

    /**
     * 子view每一行的数据
     */
    public abstract void childItemData(BaseViewHolder baseViewHolder, int groupPosition, int childPosition);

    /**
     * 子view的点击事件
     */
    public abstract void onChildItemClick(ExpandableListView expandableListView, View itemView, int groupPosition, int childPosition);

    private MyExpandableView mExpandableView;
    private ExpandableListView expandableListView;
    protected List<Object> groupData = new ArrayList<>();
    protected List<List<Object>> childData = new ArrayList<>();
    private ExpandableAdapter mExpandableAdapter;

    @Override
    protected void initView() {
        mExpandableView = (MyExpandableView) findViewById(R.id.my_expandable_view);
        expandableListView = mExpandableView.expandableListView;
        mExpandableAdapter = new ExpandableAdapter(this, groupData, childData);
        expandableListView.setAdapter(mExpandableAdapter);
        expandableListView.setGroupIndicator(null); //隐藏ExpandableListView自带的图标
        mExpandableView.setIsRefresh(isRefresh());
        defaultRefresh();
    }

    @Override
    protected void initData() {

    }

    /**
     * 下拉刷新监听事件
     */
    private void defaultRefresh() {
        mExpandableView.setRefreshListener(new HeaderView.RefreshListener() {
            @Override
            public void onRefreshPrepare(boolean bl, PtrFrameLayout frame) {
                //准备刷新
            }

            @Override
            public void onRefreshBegin(boolean bl, PtrFrameLayout frame) {
                //刷新中
                refresh();
            }

            @Override
            public void onRefreshComplete(boolean bl, PtrFrameLayout frame) {
                //刷新完成
            }
        });
    }

    /**
     * 是否下拉刷新
     */
    protected boolean isRefresh() {
        return true;
    }

    /**
     * 下拉刷新方法,子类需要重写
     */
    protected void refresh() {
    }

    /**
     * 刷新成功
     */
    protected void refreshSuccess() {
        if (isRefresh()) {
            mExpandableView.closeRefreshView();
        }
    }

    /**
     * 刷新数据
     */
    protected void notifyDataChange() {
        mExpandableAdapter.notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param data
     */
    protected void addGroupAll(List<?> data) {
        if (data == null) return;
        this.groupData.addAll(data);
    }

    protected <T> void addChildAll(int position, List<T> data) {
        if (data == null) return;
        this.childData.add(position, (List<Object>) data);
        //this.childData.addAll((Collection<? extends List<Object>>) data);
    }

    /**
     * 删除数据
     */
    protected void clearGroupAll() {
        if (groupData == null) return;
        mExpandableAdapter.clearGroupAll(groupData);
    }

    protected void clearChildAll(int position) {
        if (childData == null) return;
        mExpandableAdapter.clearChildAll(childData.get(position));
    }

    /**
     * 添加一条数据数据
     */
    protected void addGroupOne(Object obj, int position) {
        if (groupData == null) return;
        mExpandableAdapter.addGroupOne(groupData, obj, position);
    }
    protected void addChildOne(List<?> listobj, int position) {
        if (childData == null) return;
        mExpandableAdapter.addCilndOne(childData, listobj, position);
    }

    /**
     * 删除某条数据
     */
    protected void clearGroupOne(int position) {
        if (groupData == null) return;
        mExpandableAdapter.clearOne(groupData, position);
    }
    protected void clearChildOne(int position) {
        if (childData == null) return;
        mExpandableAdapter.clearOne(childData, position);
    }

    /**
     * 获取数据长度
     * @return
     */
    protected int getGroupCount() {
        return groupData.size();
    }
    protected int getClindCount(int position) {
        return childData.get(position).size();
    }


    /**
     * 多级列表的适配器
     */
    class ExpandableAdapter extends BaseExpandableAdapter {

        public ExpandableAdapter(Activity activity, List<Object> groupData, List<List<Object>> childData) {
            super(activity, groupData, childData);
        }

        @Override
        public BaseViewHolder groupViewHolder(ViewGroup parent, int groupPosition, boolean isExpanded) {
                       return BaseExpandableListViewActivity.this.getGroupViewHolder(parent, groupPosition, isExpanded);
        }

        @Override
        public ImageView groupImageView() {
            return BaseExpandableListViewActivity.this.groupImageView();
        }

        @Override
        public int groupViewByLayout() {
            return BaseExpandableListViewActivity.this.groupViewByLayout();
        }

        @Override
        public void groupItemData(BaseViewHolder baseViewHolder, int groupPosition, boolean isExpanded) {
            //baseViewHolder.itemView.setOnClickListener(new onGroupClickListener(groupPosition, isExpanded));
            BaseExpandableListViewActivity.this.groupItemData(baseViewHolder, groupPosition, isExpanded);
        }

        @Override
        public BaseViewHolder childViewHolder(ViewGroup parent, int groupPosition, int childPosition) {
            return BaseExpandableListViewActivity.this.getChildViewHolder(parent, groupPosition, childPosition);
        }


        @Override
        public int childViewByLayout() {
            return BaseExpandableListViewActivity.this.childViewByLayout();
        }

        @Override
        public void childItemData(BaseViewHolder baseViewHolder, int groupPosition, int childPosition) {
            //baseViewHolder.itemView.setOnClickListener(new onChildClickListener(groupPosition, childPosition));
            BaseExpandableListViewActivity.this.childItemData(baseViewHolder, groupPosition, childPosition);
        }

    }

    /**
     * 父item的点击事件
     */
    class onGroupClickListener implements View.OnClickListener {

        private int groupPosition;
        private boolean isExpanded;

        public onGroupClickListener(int groupPosition, boolean isExpanded) {
            this.groupPosition = groupPosition;
            this.isExpanded = isExpanded;
        }

        @Override
        public void onClick(View v) {
            onGroupItemClick(expandableListView, v, groupPosition, isExpanded);
        }
    }

    /**
     * 子item的点击事件
     */
    class onChildClickListener implements View.OnClickListener {

        private int groupPosition;
        private int childPosition;

        public onChildClickListener(int groupPosition, int childPosition) {
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
        }

        @Override
        public void onClick(View v) {
            onChildItemClick(expandableListView, v, groupPosition, childPosition);
        }
    }


}
