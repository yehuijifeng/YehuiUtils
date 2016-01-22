package com.yehui.utils.activity.map;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.map.utils.locations.LocationBean;
import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.baidumap.LocationUtil;

/**
 * Created by yehuijifeng
 * on 2016/1/19.
 * 百度地图api
 */
public class LocationActivity extends BaseActivity implements View.OnClickListener {
    private LocationUtil locationUtil;
    private Button map_zuobiao_btn, map_zuobiao_name_btn, map_name_zuobiao_btn;
    private TextView map_text;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_test_map_location);
    }

    @Override
    protected String setTitleText() {
        return "百度地图api";
    }

    @Override
    protected void initView() {
        map_zuobiao_btn = (Button) findViewById(R.id.map_zuobiao_btn);
        map_zuobiao_name_btn = (Button) findViewById(R.id.map_zuobiao_name_btn);
        map_name_zuobiao_btn = (Button) findViewById(R.id.map_name_zuobiao_btn);
        map_zuobiao_btn.setOnClickListener(this);
        map_zuobiao_name_btn.setOnClickListener(this);
        map_name_zuobiao_btn.setOnClickListener(this);
        map_text = (TextView) findViewById(R.id.map_text);
    }

    @Override
    protected void initData() {
        locationUtil = new LocationUtil();
        locationUtil.initLoaction(this);

    }

    public void onEventMainThread(LocationBean locationBean) {
        map_text.setText("经度：" + locationBean.getLontitude() + "\n维度：" + locationBean.getLatitude());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.map_zuobiao_btn:
                locationUtil.getLoaction();
                break;
            case R.id.map_zuobiao_name_btn:
                break;
            case R.id.map_name_zuobiao_btn:
                break;
        }
    }
}
