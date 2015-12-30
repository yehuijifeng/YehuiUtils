package com.yehui.utils.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;

/**
 * Created by yehuijifeng
 * on 2015/12/1.
 * toolbar的测试页面
 */
public class ToolbarActivity extends BaseActivity {
    private Toolbar toolbar;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_toolbar);
    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initData() {
        toolbar.setNavigationIcon(R.drawable.ic_menu_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected String setTitleText() {
        return "toolbar示例";
    }
}
