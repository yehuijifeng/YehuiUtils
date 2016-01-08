package com.yehui.utils.activity;

import android.widget.LinearLayout;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;

/**
 * Created by yehuijifeng
 * on 2016/1/8.
 * 可操作图片的activity
 */
public class TouchImageActivity extends BaseActivity {
    private LinearLayout touch_layout;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_touch_image);
    }

    @Override
    protected String setTitleText() {
        return "操作图片";
    }

    @Override
    protected void initView() {
//        img = new TouchImageView(this, R.drawable.test);
        //touch_image=new TestView(this);
        //touch_image= (TestView) findViewById(R.id.touch_image);
        touch_layout = (LinearLayout) findViewById(R.id.touch_layout);
        //touch_image = (TouchImageView) findViewById(R.id.touch_image);
    }

    @Override
    protected void initData() {
//      touch_image.setImage(bitmap);
        //touch_layout.addView(img);

    }

}
