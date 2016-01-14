package com.yehui.utils.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.jpush.JPushInterfaces;
import com.yehui.utils.jpush.JPushUtil;

/**
 * Created by yehuijifeng
 * on 2016/1/14.
 * 极光推送
 */
public class JPushActivity extends BaseActivity implements View.OnClickListener {
    private Button init_btn, stop_btn, restart_btn;
    private TextView jpush_id_text, jpush_version_text, jpush_key_text, jpush_text;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_jpush);
    }

    @Override
    protected String setTitleText() {
        return "极光推送";
    }

    @Override
    protected void initView() {
        init_btn = (Button) findViewById(R.id.init_btn);
        stop_btn = (Button) findViewById(R.id.stop_btn);
        restart_btn = (Button) findViewById(R.id.restart_btn);
        jpush_id_text = (TextView) findViewById(R.id.jpush_id_text);
        jpush_version_text = (TextView) findViewById(R.id.jpush_version_text);
        jpush_key_text = (TextView) findViewById(R.id.jpush_key_text);
        jpush_text = (TextView) findViewById(R.id.jpush_text);
    }

    @Override
    protected void initData() {
        jpush_key_text.append(JPushUtil.getAppKey(this));
        jpush_version_text.append(JPushUtil.GetVersion(this));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.init_btn:
                JPushInterfaces.initJPush(this);
                break;
            case R.id.restart_btn:
                JPushInterfaces.resumeJPush(this);
                break;
            case R.id.stop_btn:
                JPushInterfaces.stopJPush(this);
                break;
        }
    }
}
