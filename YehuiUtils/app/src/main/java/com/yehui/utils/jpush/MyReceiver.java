package com.yehui.utils.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yehui.utils.activity.YehuiHomeActivity;

import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by yehuijifeng
 * on 2016/1/14.
 * 重写极光推送的消息广播
 */
public class MyReceiver extends BroadcastReceiver {

    private Class cla;//用户点击通知后跳转的目标activity

    public Class getCla() {
        return cla;
    }

    public void setCla(Class cla) {
        this.cla = cla;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            //用户唯一注册id
            String id = JPushReceiver.getRegistrationId(intent);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
            String[] strings=JPushReceiver.getMessageReceived(intent);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            // 收到了通知,在这里可以做些统计，或者做些其他工作
            List<Object> list=JPushReceiver.getNotificationReceived(intent);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            // 用户点击打开了通知,在这里可以自己写代码去定义用户点击后的行为
            //Intent i = new Intent(context, getCla());  //自定义打开的界面
            Intent i = new Intent(context, YehuiHomeActivity.class);  //自定义打开的界面
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            //不能处理的意图，initent
        }
    }
}
