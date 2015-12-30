package com.yehui.utils.activity;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;

public class TestActivity extends BaseActivity {
    /**
     * PullToRefreshListView在使用上有一个BUG，
     * 在你的xml layout中，
     * 不能一开始将它的visiablity设置为GONE，
     * 否则，
     * 在代码中设置visiablity为VISIABLE也没有作用。
     */

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_test_default_list);
    }

    @Override
    protected String setTitleText() {
        return "pull-to-refresh-master测试";
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }
}
