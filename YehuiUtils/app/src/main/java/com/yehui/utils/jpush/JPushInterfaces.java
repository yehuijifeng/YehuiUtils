package com.yehui.utils.jpush;

import android.content.Context;

import java.util.Set;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by yehuijifeng
 * on 2016/1/14.
 * 极光推送的所有功能接口
 */
public class JPushInterfaces {


    /**
     * SDK 初次注册成功后，开发者通过在自定义的 Receiver 里监听
     * Action - cn.jpush.android.intent.REGISTRATION
     * 来获取对应的 RegistrationID。注册成功后，也可以通过此函数获取
     * <p/>
     * 调用此 API 来取得应用程序对应的 RegistrationID。
     * 只有当应用程序成功注册到 JPush 的服务器时才返回对应的值，否则返回空字符串。
     * <p/>
     * 开始支持的版本：1.6.0。
     * <p/>
     * 通过 RegistrationID 进行点对点推送
     * <p/>
     * 点对点推送参考：http://docs.jpush.cn/display/dev/Push+API+v2
     */
    public static String getJPushRegistrationID(Context context) {
        String RegistrationID = JPushInterface.getRegistrationID(context);
        return RegistrationID;
    }

    /**
     * 初始化，整个app只需要调用一次
     *
     * @param context
     */
    public static void initJPush(Context context) {
        JPushInterface.init(context);
    }

    /**
     * 设置调试模式，设置开启日志,发布时请关闭日志
     *
     * @param debugEnalbed
     */
    public static void setJPushDebugMode(boolean debugEnalbed) {
        JPushInterface.setDebugMode(debugEnalbed);
    }

    /**
     * JPush 推送服务完全被停止。具体表现为：
     * JPush Service 不在后台运行
     * 收不到推送消息
     * 不能通过 JPushInterface.init 恢复，需要调用resumePush恢复。
     * 极光推送所有的其他 API 调用都无效
     *
     * @param context
     */
    public static void stopJPush(Context context) {
        JPushInterface.stopPush(context);
    }

    /**
     * 调用了此 API 后，极光推送完全恢复正常工作。
     *
     * @param context
     */
    public static void resumeJPush(Context context) {
        JPushInterface.resumePush(context);
    }


    /**
     * 用来检查 Push Service 是否已经被停止
     * SDK 1.5.2 以上版本支持。
     *
     * @param context
     * @return
     */
    public static boolean isJPushStopped(Context context) {
        return JPushInterface.isPushStopped(context);
    }


    /**
     * 设置允许推送时间 API
     *默认情况下用户在任何时间都允许推送。即任何时候有推送下来，客户端都会收到，并展示。

     开发者可以调用此 API 来设置允许推送的时间。

     如果不在该时间段内收到消息，当前的行为是：推送到的通知会被扔掉。
     * @param context
     * @param weekDays 0表示星期天，1表示星期一，以此类推。 （7天制，Set集合里面的int范围为0到6）
     * @param startHour 允许推送的开始时间 （24小时制：startHour的范围为0到23）
     * @param endHour  允许推送的结束时间 （24小时制：endHour的范围为0到23）
     *
     * 代码示例：
    Set<Integer> days = new HashSet<Integer>();
    days.add(1);
    days.add(2);
    days.add(3);
    days.add(4);
    days.add(5);
    JPushInterface.setPushTime(getApplicationContext(), days, 10, 23);
    此代码表示周一到周五、上午10点到晚上23点，都可以推送。
     */
    public static void setJPushTime(Context context, Set<Integer> weekDays, int startHour, int endHour) {
        JPushInterface.setPushTime(context, weekDays, startHour, endHour);
    }


    /**设置某编号的通知栏样式构建类（setPushNotificationBuilder 可以在 JPushInterface.init() 之后任何地方调用）
     * 1，当开发者需要为不同的通知，指定不同的通知栏样式（行为）时，则需要调用此方法设置多个通知栏构建类。
     * 2，设置时，开发者自己维护 notificationBuilderId 这个编号，下发通知时使用 n_builder_id 指定该编号，
     *      从而 Push SDK 会调用开发者应用程序里设置过的指定编号的通知栏构建类，来定制通知栏样式。
     * 3，开发者不自定义通知栏样式时，则此编号默认为 0。
     * 4，开发者自定义的通知栏样式编号应大于 0，小于 1000。
     * @param notificationBuilderId
     * @param builder
     */
    public static void setJPushNotificationBuilder(Integer notificationBuilderId, BasicPushNotificationBuilder builder){
        JPushInterface.setPushNotificationBuilder(notificationBuilderId,builder);
    }

    /**定制通知栏
     * BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(MainActivity.this);
     builder.statusBarDrawable = R.drawable.jpush_notification_icon;//图标
     builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为自动消失
     builder.notificationDefaults = Notification.DEFAULT_SOUND ｜ Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS;  // 设置为铃声与震动都要
 ushInterface.setPushNotificationBuilder(1, builder);//给这个通知栏设定一个编号1
     */

    /** 进一步定制通知栏
     * CustomPushNotificationBuilder builder = new CustomPushNotificationBuilder(MainActivity.this,
     R.layout.customer_notitfication_layout, R.id.icon, R.id.title, R.id.text);  // 指定定制的 Notification Layout
     builder.statusBarDrawable = R.drawable.your_notification_icon;      // 指定最顶层状态栏小图标
     builder.layoutIconDrawable = R.drawable.your_2_notification_icon;   // 指定下拉状态栏时显示的通知图标
     JPushInterface.setPushNotificationBuilder(2, builder);
     */
}
