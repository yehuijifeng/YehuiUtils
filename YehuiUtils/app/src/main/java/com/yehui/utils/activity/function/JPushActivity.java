package com.yehui.utils.activity.function;

import android.app.Notification;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.jpush.JPushInterfaces;
import com.yehui.utils.jpush.JPushUtil;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by yehuijifeng
 * on 2016/1/14.
 * 极光推送
 */
public class JPushActivity extends BaseActivity implements View.OnClickListener {
    private Button init_btn, stop_btn, restart_btn, restart_id_btn;
    private TextView jpush_id_text, jpush_version_text, jpush_key_text, jpush_text;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_test_jpush);
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
        restart_id_btn = (Button) findViewById(R.id.restart_id_btn);
        init_btn.setOnClickListener(this);
        stop_btn.setOnClickListener(this);
        restart_btn.setOnClickListener(this);
        restart_id_btn.setOnClickListener(this);
        jpush_id_text = (TextView) findViewById(R.id.jpush_id_text);
        jpush_version_text = (TextView) findViewById(R.id.jpush_version_text);
        jpush_key_text = (TextView) findViewById(R.id.jpush_key_text);
        jpush_text = (TextView) findViewById(R.id.jpush_text);
    }

    @Override
    protected void initData() {

        jpush_key_text.append(JPushUtil.getAppKey(this));
        jpush_version_text.append(JPushUtil.getVersion(getApplicationContext()));
        JPushInterfaces.setLatestNotificationNumber(this, 3);

        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);
        builder.statusBarDrawable = R.drawable.ic_viewpager_bar;//图标
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为自动消失
        builder.notificationDefaults = Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS;  // 设置为铃声与震动都要
        JPushInterfaces.setJPushNotificationBuilder(1,builder);//给这个通知栏设定一个编号1

        CustomPushNotificationBuilder builder2 = new CustomPushNotificationBuilder(this,
                R.layout.item_test_recycler,
                R.drawable.ic_drop_right,
                R.string.app_name,
                R.string.app_name);
        builder2.statusBarDrawable = R.drawable.ic_launcher;
        // 指定最顶层状态栏小图标
        builder2.layoutIconDrawable = R.drawable.ic_launcher;
        // 指定下拉状态栏时显示的通知图标
        JPushInterface.setPushNotificationBuilder(2, builder2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.init_btn:
                JPushInterfaces.initJPush(this);
                break;
            case R.id.restart_btn:
                JPushInterfaces.resumeJPush(this);
                break;
            case R.id.stop_btn:
                JPushInterfaces.stopJPush(this);
                break;
            case R.id.restart_id_btn:
                jpush_id_text.append(JPushInterfaces.getJPushRegistrationID(this));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterfaces.resumeJPush(this);
    }
}
