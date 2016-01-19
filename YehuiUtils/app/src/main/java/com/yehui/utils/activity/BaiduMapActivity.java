package com.yehui.utils.activity;

import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.baidumap.LocationUtil;
import com.yehui.utils.baidumap.locations.LocationBean;

/**
 * Created by yehuijifeng
 * on 2016/1/19.
 * 百度地图api
 */
public class BaiduMapActivity extends BaseActivity {
    private LocationUtil locationUtil;
    @Override
    protected void setContentView() {

    }

    @Override
    protected String setTitleText() {
        return "百度地图api";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        locationUtil=new LocationUtil();
        locationUtil.initLoaction(this);
        locationUtil.getLoaction();
    }


    public void onEventMainThread(LocationBean locationBean) {

    }
}
