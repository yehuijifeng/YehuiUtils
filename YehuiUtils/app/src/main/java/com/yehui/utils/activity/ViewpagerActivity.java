package com.yehui.utils.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseViewPagerActivity;
import com.yehui.utils.fragment.ViewPagerFive;
import com.yehui.utils.fragment.ViewPagerFour;
import com.yehui.utils.fragment.ViewPagerOne;
import com.yehui.utils.fragment.ViewPagerThree;
import com.yehui.utils.fragment.ViewPagerTow;

/**
 * Created by yehuijifeng
 * on 2015/12/3.
 */
public class ViewpagerActivity extends BaseViewPagerActivity {

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_test_viewpager);
    }

    @Override
    protected String setTitleText() {
        return "viewpager的应用";
    }

    @Override
    protected void initView() {
        super.initView();
        mViewList.add(new ViewPagerOne());
        mViewList.add(new ViewPagerTow());
        mViewList.add(new ViewPagerThree());
        mViewList.add(new ViewPagerFour());
        mViewList.add(new ViewPagerFive());
    }

    @Override
    protected boolean isShowBar() {
        return false;
    }

    /**
     * @return 当前显示的页码
     */
    @Override
    protected int setPage() {
        return 0;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected View setTabView(ViewGroup container, int position) {
        View view = inflate(R.layout.item_default_viewpager_tab, container, false);
        TextView tabText = (TextView) view.findViewById(R.id.viewpager_text);
        switch (position) {
            case 0:
                tabText.setText("tab1");
                break;
            case 1:
                tabText.setText("tab2");
                break;
            case 2:
                tabText.setText("tab3");
                break;
            case 3:
                tabText.setText("tab4");
                break;
            case 4:
                tabText.setText("tab5");
                break;

        }
        return view;
    }
}
