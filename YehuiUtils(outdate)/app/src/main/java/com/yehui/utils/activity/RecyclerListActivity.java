package com.yehui.utils.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseListActivity;
import com.yehui.utils.adapter.base.BaseViewHolder;
import com.yehui.utils.utils.LogUtil;
import com.yehui.utils.view.DefaultTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yehuijifeng
 * on 2015/12/9.
 * recycler代替listview
 */
public class RecyclerListActivity extends BaseListActivity {

    /**
     * 自定义toolbar的优先级高于toolbarmodel
     */
    @Override
    protected View setCustomToolbar() {

        return inflate(R.layout.item_test_recycler, null);
    }

    @Override
    protected DefaultTitleView.TitleMode setTypeByTitleMode() {
        return DefaultTitleView.TitleMode.NO_BACK_IMAGE;
    }

    @Override
    protected int isHorizaontalOrVertical() {
        return 0;
    }

    @Override
    protected float itemDecoration() {
        return 0.5f;
    }

    @Override
    protected void initView() {
        super.initView();
//        defaultTitleView.setImageButtonOnClick(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showShortToast("编辑图片");
//            }
//        });
    }

    @Override
    protected void initData() {
        super.initData();
        loadingView("玩命加载中");
        for (int i = 0; i < 15; i++) {
            data.add("确定" + i);
        }
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    protected int getItemLayoutResId(int type) {
        if (type == 2015) {
            return R.layout.item_test_recycler;
        }
        return R.layout.item_test_recycler;
    }

    /**
     * 测试
     */
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    clearDataAll();
                    List<String> list2=new ArrayList<>();
                    list2.add("加载成功后添加的数据1");
                    list2.add("加载成功后添加的数据2");
                    list2.add("加载成功后添加的数据3");
                    list2.add("加载成功后添加的数据4");
                    list2.add("加载成功后添加的数据1");
                    list2.add("加载成功后添加的数据2");
                    list2.add("加载成功后添加的数据3");
                    list2.add("加载成功后添加的数据4");
                    list2.add("加载成功后添加的数据1");
                    list2.add("加载成功后添加的数据2");
                    list2.add("加载成功后添加的数据3");
                    list2.add("加载成功后添加的数据4");
                    list2.add("加载成功后添加的数据1");
                    list2.add("加载成功后添加的数据2");
                    list2.add("加载成功后添加的数据3");
                    list2.add("加载成功后添加的数据4");
                    list2.add("加载成功后添加的数据1");
                    list2.add("加载成功后添加的数据2");
                    list2.add("加载成功后添加的数据3");
                    list2.add("加载成功后添加的数据4");
                    addAll(list2);
                    LogUtil.i("加载完成");
                    notifyDataChange();
                    loadMoreSuccess();
                    loadingEmpty(null,null);
                    break;
                case 1:
                    LogUtil.i("刷新成功");
                    clearDataAll();
                    List<String> list1=new ArrayList<>();
                    list1.add("刷新成功后添加的数据1");
                    list1.add("刷新成功后添加的数据2");
                    list1.add("刷新成功后添加的数据3");
                    list1.add("刷新成功后添加的数据4");
                    list1.add("加载成功后添加的数据1");
                    list1.add("加载成功后添加的数据2");
                    list1.add("加载成功后添加的数据3");
                    list1.add("加载成功后添加的数据4");
                    list1.add("加载成功后添加的数据1");
                    list1.add("加载成功后添加的数据2");
                    list1.add("加载成功后添加的数据3");
                    list1.add("加载成功后添加的数据4");
                    list1.add("加载成功后添加的数据1");
                    list1.add("加载成功后添加的数据2");
                    list1.add("加载成功后添加的数据3");
                    list1.add("加载成功后添加的数据4");
                    list1.add("加载成功后添加的数据1");
                    list1.add("加载成功后添加的数据2");
                    list1.add("加载成功后添加的数据3");
                    list1.add("加载成功后添加的数据4");
                    addAll(list1);
                    notifyDataChange();
                    refreshSuccess();
                    loadingFail(null,null);
                default:
                    break;
            }

        }
    };

    /**
     * 是否下拉加载更多
     */
    @Override
    protected boolean isLoadMore() {
        return true;
    }

    /**
     * 是否下拉刷新
     */
    @Override
    protected boolean isRefresh() {
        return true;
    }

    /**
     * 重新加载
     */
    @Override
    protected void reLoad() {
        handler.sendEmptyMessageDelayed(0, 2000);
    }

    /**
     * 下拉刷新
     */
    @Override
    protected void refresh() {
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    /**
     * 加载更多
     */
    @Override
    protected void loadMore() {
         handler.sendEmptyMessageDelayed(0, 2000);
    }


    @Override
    protected void initItemData(BaseViewHolder holder, int position) {
        if (holder instanceof DefaultTowViewHolder) {
            DefaultTowViewHolder defaultTowViewHolder = (DefaultTowViewHolder) holder;
            defaultTowViewHolder.textViewTest.setText("第" + position + "行,这是另外一个viewholder");
        } else if (holder instanceof DefaultViewHolder) {
            DefaultViewHolder defaultViewHolder = (DefaultViewHolder) holder;
            defaultViewHolder.textViewTest.setText("第" + position + "行");
            if (position == 0) defaultViewHolder.buttonTest.setText(data.get(position) + "特定");
            else
                defaultViewHolder.buttonTest.setText(data.get(position)+"");
        }
    }

    @Override
    protected int getItemType(int position) {
        if (("确定1").equals(data.get(position))) {
            return 2015;
        }
        return super.getItemType(position);
    }

    @Override
    protected BaseViewHolder getViewHolder(View itemView, int type) {
        if (type == 2015) {
            return new DefaultTowViewHolder(itemView);
        }
        return new DefaultViewHolder(itemView);
    }

    @Override
    protected void onItemClick(RecyclerView parent, View itemView, int position) {
        showShortToast("点击了第" + position + "行");
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_default_list);
    }


    @Override
    protected String setTitleText() {
        return "recycler测试";
    }




    /**
     * 如果需要多个适配器，则在具体实现类中创建多个
     */
    class DefaultViewHolder extends BaseViewHolder {

        private RadioButton radioButtonTest;
        private TextView textViewTest;
        private Button buttonTest;

        public DefaultViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initItemView(View itemView) {
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
            radioButtonTest = (RadioButton) itemView.findViewById(R.id.radioButtonTest);
            textViewTest = (TextView) itemView.findViewById(R.id.textViewTest);
            buttonTest = (Button) itemView.findViewById(R.id.buttonTest);
        }
    }
}
