package com.yehui.utils.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseExpandableListViewActivity;
import com.yehui.utils.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yehuijifeng
 * on 2015/12/30.
 * 多级列表
 */
public class ExpandableListActivity extends BaseExpandableListViewActivity {

    private ImageView groupImage;

    @Override
    protected void initView() {
        super.initView();
        List<String> list = new ArrayList<>();
        List<List<String>> lists = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            list.add("确定" + i);

        }
        for (int i = 0; i <groupData.size() ; i++) {
            lists.add(list);
        }
        addGroupAll(list);
        for (int i = 0; i <groupData.size() ; i++) {
            addChildAll(i, list);
        }

    }

    @Override
    protected BaseViewHolder getGroupViewHolder(ViewGroup parent, int groupPosition, boolean isExpanded) {
        return new DefaultViewHolder(parent);
    }

    @Override
    protected BaseViewHolder getChildViewHolder(ViewGroup parent, int groupPosition, int childPosition) {
        return new DefaultTowViewHolder(parent);
    }

    @Override
    public ImageView groupImageView() {
        return groupImage;
    }

    @Override
    public int groupViewByLayout() {
        return R.layout.item_test_recycler;
    }

    @Override
    public int childViewByLayout() {
        return R.layout.item_test_recycler;
    }

    @Override
    public void groupItemData(BaseViewHolder baseViewHolder, int groupPosition, boolean isExpanded) {
        DefaultViewHolder defaultViewHolder = (DefaultViewHolder) baseViewHolder;

        defaultViewHolder.textViewTest.setText("第" + groupPosition + "行");
        defaultViewHolder.buttonTest.setText(groupData.get(groupPosition) + "");
    }

    @Override
    public void onGroupItemClick(ExpandableListView expandableListView, View itemView, int groupPosition, boolean isExpanded) {

    }

    @Override
    public void childItemData(BaseViewHolder baseViewHolder, int groupPosition, int childPosition) {
        DefaultTowViewHolder defaultViewHolder = (DefaultTowViewHolder) baseViewHolder;
        defaultViewHolder.textViewTest.setText("第" + childPosition + "行");
        defaultViewHolder.buttonTest.setText(childData.get(groupPosition).get(childPosition) + "");
    }

    @Override
    public void onChildItemClick(ExpandableListView expandableListView, View itemView, int groupPosition, int childPosition) {
        showShortToast("点击了子view的第" + childPosition + "行");
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_test_expandable_list_view);
    }

    @Override
    protected String setTitleText() {
        return "两级列表";
    }

    class DefaultViewHolder extends BaseViewHolder {

        private RadioButton radioButtonTest;
        private TextView textViewTest;
        private Button buttonTest;

        public DefaultViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            groupImage = (ImageView) itemView.findViewById(R.id.group_image);
            radioButtonTest = (RadioButton) itemView.findViewById(R.id.radioButtonTest);
            textViewTest = (TextView) itemView.findViewById(R.id.textViewTest);
            buttonTest = (Button) itemView.findViewById(R.id.buttonTest);
        }
    }

    class DefaultTowViewHolder extends BaseViewHolder {

        private RadioButton radioButtonTest;
        private TextView textViewTest;
        private Button buttonTest;

        public DefaultTowViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
            groupImage = (ImageView) itemView.findViewById(R.id.group_image);
            radioButtonTest = (RadioButton) itemView.findViewById(R.id.radioButtonTest);
            textViewTest = (TextView) itemView.findViewById(R.id.textViewTest);
            buttonTest = (Button) itemView.findViewById(R.id.buttonTest);
        }
    }
}
