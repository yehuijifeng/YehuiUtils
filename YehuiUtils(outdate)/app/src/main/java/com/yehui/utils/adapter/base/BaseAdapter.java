package com.yehui.utils.adapter.base;

import android.view.View;

import java.util.List;

/**
 * Created by yehuijifeng
 * on 2015/12/8.
 * recyclerview的主要适配器
 */
public abstract class BaseAdapter<T> extends UltimateViewAdapter<BaseViewHolder> {

    protected List<T> data;

    public BaseAdapter(List<T> data) {
        this.data = data;
    }

    /**
     * 绑定每一行的数据
     *
     * @param holder
     * @param position
     */
    public abstract void onBindDataForItem(BaseViewHolder holder, int position);


    /**
     * 绑定viewholder
     *
     * @param holder   ，viewholder
     * @param position 标识
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        // && !holder.itemView.equals(customHeaderView)
        //判断绑定的该viewholder是否属于尾部view，如果是，则不绑定
        if (!holder.itemView.equals(customLoadMoreView)) {
            if (customHeaderView != null) position--;
            onBindDataForItem(holder, position);
        } else if (holder.itemView.equals(customLoadMoreView)&&data.size()==0) {
            if (customHeaderView != null)position--;
            holder.itemView.setVisibility(View.GONE);
        }
    }

    /**
     * 获取适配器item的数量，抽象类来自于Ultimateviewadapter
     */
    @Override
    public int getAdapterItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if ((position == getItemCount() - 1 && customLoadMoreView != null) || (position == 0 && customHeaderView != null)) {
            return super.getItemViewType(position);
        }
        return getItemType(position);
    }


    /**
     * 每一行item的类型
     *
     * @param position，如果返回1001，1002，1003则表示头，尾，尾
     */
    protected int getItemType(int position) {
        return position;
    }

    /**
     * 清除数据
     */
    public void clearData() {
        if (data == null) return;
        this.clearAll(data);
    }

    /**
     * 添加数据具体到某个位置
     *
     * @param datum
     * @param position
     */
    public void addOneData(T datum, int position) {
        this.insert(data, datum, position);
    }

    /**
     * 添加全部数据
     *
     * @param dataList
     */
    public void addAllData(List<T> dataList) {
        this.data.addAll(dataList);
    }

    /**
     * 删除某条数据
     *
     * @param position
     */
    public void removeOne(int position) {
        this.remove(data, position);
    }
}
