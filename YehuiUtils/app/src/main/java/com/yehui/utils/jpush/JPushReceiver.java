package com.yehui.utils.jpush;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by yehuijifeng
 * on 2016/1/14.
 * 极光推送的广播接收类
 */
public class JPushReceiver {

    /**
     * 接受广播
     如果全部类型的广播都接收，则需要在 AndroidManifest.xml 里添加如下的配置信息：
     <receiver
     android:name="cn.jpush.android.service.PushReceiver"
     android:enabled="true">
     <intent-filter>
     <action android:name="cn.jpush.android.intent.REGISTRATION" />
     <action android:name="cn.jpush.android.intent.MESSAGE_RECEIVED" />
     <action android:name="cn.jpush.android.intent.NOTIFICATION_RECEIVED" />
     <action android:name="cn.jpush.android.intent.NOTIFICATION_OPENED" />
     <category android:name="com.yehui.utils" />
     </intent-filter>
     </receiver>

     */

    /**
     * 需要添加配置:cn.jpush.android.intent.REGISTRATION
     * SDK 向 JPush Server 注册所得到的注册 全局唯一的 ID ，可以通过此 ID 向对应的客户端发送消息和通知。
     */
    public static String getRegistrationId(Intent intent){
        return intent.getExtras().getString(JPushInterface.EXTRA_REGISTRATION_ID);//全局唯一的 ID
    }

    /**
     * 需要添加配置:cn.jpush.android.intent.MESSAGE_RECEIVED
     *SDK 对自定义消息，只是传递，不会有任何界面上的展示。
     */
    public static String[] getMessageReceived(Intent intent){
        Bundle bundle = intent.getExtras();
        String title = bundle.getString(JPushInterface.EXTRA_TITLE);//保存服务器推送下来的消息的标题。
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);//保存服务器推送下来的消息内容。
        String extrasJson = bundle.getString(JPushInterface.EXTRA_EXTRA);//保存服务器推送下来的附加字段。这是个 JSON 字符串。
        String type = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);//对应 API 消息内容的 content_type 字段。
        String file = bundle.getString(JPushInterface.EXTRA_RICHPUSH_FILE_PATH);//SDK 1.4.0 以上版本支持。,富媒体通消息推送下载后的文件路径和文件名。
        String id = bundle.getString(JPushInterface.EXTRA_MSG_ID);//SDK 1.6.1 以上版本支持。唯一标识消息的 ID, 可用于上报统计等。
        return new String[]{title,message,extrasJson,type,file,id};
    }

    /**
     * 需要添加配置:cn.jpush.android.intent.NOTIFICATION_RECEIVED
     * 收到了通知 Push。如果通知的内容为空，则在通知栏上不会展示通知。但是，这个广播 Intent 还是会有。开发者可以取到通知内容外的其他信息。
     */
    public static List<Object> getNotificationReceived(Intent intent){
        List<Object> list=new ArrayList<>();
        Bundle bundle = intent.getExtras();
        String title = bundle.getString(JPushInterface.EXTRA_TITLE);//保存服务器推送下来的消息的标题。
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);//保存服务器推送下来的消息内容。
        String extrasJson = bundle.getString(JPushInterface.EXTRA_EXTRA);//保存服务器推送下来的附加字段。这是个 JSON 字符串。
        String type = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);//对应 API 消息内容的 content_type 字段。
        String fileHtml = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_PATH);//SDK 1.4.0 以上版本支持。,富媒体通消息推送下载后的文件路径和文件名。
        //String id = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_ID);//SDK 1.6.1 以上版本支持。唯一标识消息的 ID, 可用于上报统计等。
        String fileStr = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_RES);//SDK 1.4.0 以上版本支持。富媒体通知推送下载的图片资源的文件名,多个文件名用 “，” 分开。 与
        list.add(title);
        list.add(message);
        list.add(extrasJson);
        list.add(type);
        list.add(fileHtml);
        //list.add(id);
        list.add(fileStr);
        return list;
    }

    /**
     * 需要添加配置:cn.jpush.android.intent.NOTIFICATION_OPENED
     * 用户点击了通知。一般情况下，用户不需要配置此 receiver action。
        如果开发者在 AndroidManifest.xml 里未配置此 receiver action，
        那么，SDK 会默认打开应用程序的主 Activity，相当于用户点击桌面图标的效果。

        如果开发者在 AndroidManifest.xml 里配置了此 receiver action，那么，当用户点击通知时，SDK 不会做动作。
        开发者应该在自己写的 BroadcastReceiver 类里处理，比如打开某 Activity 。
     */
    public static List<Object> getNotificationOpened(Intent intent){
        List<Object> list=new ArrayList<>();
        Bundle bundle = intent.getExtras();
        String title = bundle.getString(JPushInterface.EXTRA_TITLE);//保存服务器推送下来的消息的标题。
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);//保存服务器推送下来的消息内容。
        String extrasJson = bundle.getString(JPushInterface.EXTRA_EXTRA);//保存服务器推送下来的附加字段。这是个 JSON 字符串。
        String type = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);//对应 API 消息内容的 content_type 字段。
        String id = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_ID);//SDK 1.6.1 以上版本支持。唯一标识消息的 ID, 可用于上报统计等。
        String msgId = bundle.getString(JPushInterface.EXTRA_MSG_ID);//SDK 1.6.1 以上版本支持。唯一标识调整消息的 ID, 可用于上报统计等。
        list.add(title);
        list.add(message);
        list.add(extrasJson);
        list.add(type);
        list.add(msgId);
        list.add(id);
        return list;
    }


}
