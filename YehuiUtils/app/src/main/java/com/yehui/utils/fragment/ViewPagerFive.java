package com.yehui.utils.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yehui.utils.R;
import com.yehui.utils.fragment.base.BaseFragment;

/**
 * Created by yehuijifeng
 * on 2015/12/3.
 * 测试fragment
 */
public class ViewPagerFive extends BaseFragment {

    private Button button_text;

    @Override
    protected int setFragmentLayoutId() {
        return R.layout.layout_test_viewpager_item;
    }

    @Override
    protected String setTitleText() {
        return "baseFragment应用";
    }

    @Override
    protected void initView(View parentView) {
        button_text = (Button) parentView.findViewById(R.id.button_text);
        button_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "fragment中的点击事件", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void initData() {

    }

}
