package com.yehui.utils.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.yehui.utils.R;
import com.yehui.utils.activity.YehuiHomeActivity;

/**
 * Created by yehuijifeng
 * on 2016/1/7.
 * 任务栏管理服务类
 */
public class StatusService extends Service {

    /**
     *调用方法：
     Intent intent = new Intent(this, ServiceStatus.class);
     Bundle bundle = new Bundle();
     bundle.putInt(ServiceContact.SERVICE_REMIND, 1);
     intent.putExtra("serviceBundle", bundle);
     startService(intent);
     */


    /**
     * 任务栏管理类
     */
    private NotificationManager notificationManager;

    /**
     * 服务的类型
     */
    private int type = 4;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //声明任务栏通知消息服务
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    }

    /**
     * 开始执行
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getBundleExtra("serviceBundle");
        type = bundle.getInt(ServiceContact.SERVICE_REMIND);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000);
                    Notification notification;
                    switch (type) {
                        /**
                         * 清除通知
                         */
                        case 0:
                            // 清除id为NOTIFICATION_FLAG的通知
                            notificationManager.cancel(0);
                            // 清除所有的通知
                            // notificationManager.cancelAll();
                            break;

                        /**
                         * 自定义通知栏
                         */
                        case 1:
                            // Notification myNotify = new Notification(R.drawable.message,
                            // "自定义通知：您有新短信息了，请注意查收！", System.currentTimeMillis());
                            notification = new Notification();
                            notification.icon = R.drawable.ic_lockscreen_google_activated;
                            notification.tickerText = getResources().getString(R.string.service_remind);
                            notification.when = System.currentTimeMillis();
                            notification.flags = Notification.FLAG_NO_CLEAR;// 不能够自动清除
                            RemoteViews rv = new RemoteViews(getPackageName(),
                                    R.layout.item_test_recycler);
                            rv.setTextViewText(R.id.textViewTest, getResources().getString(R.string.service_remind));
                            notification.contentView = rv;
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            PendingIntent contentIntent = PendingIntent.getActivity(StatusService.this, 1,
                                    intent, 0);
                            notification.contentIntent = contentIntent;
                            notificationManager.notify(0, notification);

                            break;
                        /**
                         * api 16以后支持的通知栏
                         */
                        case 2:
                            PendingIntent pendingIntent3 = PendingIntent
                                    .getActivity(StatusService.this, 0, new Intent(StatusService.this, YehuiHomeActivity.class), 0);
                            // 通过Notification.Builder来创建通知，注意API Level
                            // API16之后才支持
                            notification = new Notification
                                    .Builder(StatusService.this)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setTicker(getResources().getString(R.string.service_remind))
                                    .setContentTitle("Notification Title")
                                    .setContentText("This is the notification message")
                                    .setContentIntent(pendingIntent3)
                                    .setNumber(1)
                                    .getNotification();
                                    //.build(); // 需要注意build()是在API

                            // level16及之后增加的，API11可以使用getNotification()来替代

                            notification.flags |= Notification.FLAG_AUTO_CANCEL; // FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。

                            notificationManager.notify(0, notification);// 步骤4：通过通知管理器来发起通知。如果id不同，则每click，在status哪里增加一个提示

                            break;
                        /**
                         * api 11以后支持的通知栏
                         */
                        case 3:
                            //设置点击当前页面后跳转到的具体到包名的某个类中
                            Intent serviceintents = new Intent();

                            //广播
                            //serviceintents.setClassName(ServiceStatus.this,"com.yehui.utils.activity.YehuiActivity");

                            //跳转
                            serviceintents.setClass(StatusService.this, YehuiHomeActivity.class);

                            //这里设置以新任务打开而不是跳转页面
                            serviceintents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            serviceintents.putExtra("yehuiNotification", "这是service从通知栏获得的消息");

                            //定义一个PendingIntent，当用户点击通知时，跳转到某个Activity(也可以发送广播等)
                            //当前service跳转activity的信息配置,等待intent
                            PendingIntent pendingIntent = PendingIntent.getActivity(
                                    StatusService.this, 0, serviceintents, 0);

                            notification = new Notification
                                    .Builder(StatusService.this)
                                    .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示，如果在那里需要更换更大的图片，可以使用setLargeIcon(Bitmap
                                            // icon)
                                    .setTicker(getResources().getString(R.string.service_remind))// 设置在status
                                            // bar上显示的提示文字
                                    .setContentTitle("标题文字（下拉后）")// 设置在下拉status
                                            // bar后Activity，本例子中的NotififyMessage的TextView中显示的标题
                                    .setContentText(getResources().getString(R.string.service_remind))// TextView中显示的详细内容
                                    .setContentIntent(pendingIntent) // 关联PendingIntent
                                    .setNumber(1) // 在TextView的右方显示的数字，可放大图片看，在最右侧。这个number同时也起到一个序列号的左右，如果多个触发多个通知（同一ID），可以指定显示哪一个。
                                    .getNotification(); // 需要注意build()是在API level
                            // 16及之后增加的，在API11中可以使用getNotificatin()来代替
                            notification.flags |= Notification.FLAG_AUTO_CANCEL;
                            notificationManager.notify(0, notification);
                            break;
                        /**
                         *以下是通用的显示方法，但是已经不建议使用
                         */
                        case 4:
                            // 在系统状态栏显示更新的天气
                            Notification n = new Notification(
                                    R.mipmap.ic_launcher, getResources().getString(R.string.service_remind),
                                    System.currentTimeMillis());


                            //设置点击当前页面后跳转到的具体到包名的某个类中
                            Intent serviceintentss = new Intent();
                            serviceintentss.setClassName(StatusService.this,
                                    "activity.MainActivity");

                            //这里设置以新任务打开而不是跳转页面
                            serviceintentss.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            serviceintentss.putExtra("yehuiNotification", "这是service从通知栏获得的消息");


                            PendingIntent pi = PendingIntent.getActivity(
                                    StatusService.this, 0, serviceintentss, 0);

                            n.setLatestEventInfo(StatusService.this, getResources().getString(R.string.service_news),
                                    getResources().getString(R.string.service_click_look), pi);

                            notificationManager.notify(0, n);

                            break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    // 自动停止服务
                    stopSelf();
                }
            }

        }).start();

        return 0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 自动停止服务
        stopSelf();
    }
}
