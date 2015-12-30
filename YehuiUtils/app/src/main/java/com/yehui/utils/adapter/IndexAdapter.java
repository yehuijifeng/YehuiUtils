package com.yehui.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.adapter.base.BaseAdapter;
import com.yehui.utils.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by yehuijifeng
 * on 2015/12/12.
 * 继承baseadapter的适配器
 */
public class IndexAdapter extends BaseAdapter<Object> {

    public IndexAdapter(List<Object> data) {
        super(data);
    }



    /**
     * 绑定每一行的数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindDataForItem(BaseViewHolder holder, int position) {


    }

    @Override
    public int getItemType(int position) {
        if (customHeaderView != null) position--;
        if (customLoadMoreView != null) position--;
        return position;
    }

    /**
     * viewholder
     * @param view
     * @return
     */
    @Override
    public BaseViewHolder getViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    class HeaderViewHolder extends BaseViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {

        }
    }
    /**
     * 创建每一行viewholder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateViewHolderItem(ViewGroup parent, int viewType) {
        View view;
        BaseViewHolder baseViewHolder = null;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_recycler, parent, false);
                baseViewHolder = new TestViewTowHolder(view);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_demo_cart, parent, false);
                baseViewHolder = new TestViewHolder(view);
                break;
        }
        return baseViewHolder;

    }

    class TestViewHolder extends BaseViewHolder {
        Button item_button;

        public TestViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            item_button = (Button) itemView.findViewById(R.id.item_button);
        }
    }

    class TestViewTowHolder extends BaseViewHolder {
        TextView textViewTest;

        public TestViewTowHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            textViewTest = (TextView) itemView.findViewById(R.id.textViewTest);
        }
    }
}
