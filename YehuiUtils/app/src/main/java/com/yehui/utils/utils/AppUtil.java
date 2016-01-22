package com.yehui.utils.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Handler;
import android.util.DisplayMetrics;

import com.yehui.utils.activity.YehuiHomeActivity;
import com.yehui.utils.application.ActivityCollector;
import com.yehui.utils.contacts.SettingContact;

import java.util.List;
import java.util.Locale;

/**
 * Created by yehuijifeng
 * on 2016/1/5.
 */
public class AppUtil {
    /**
     * 防止被实例化
     */
    private AppUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 判断是否第一次进入应用
     * @param context
     * @return
     */
    public static boolean isOneStart(Context context) {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(context, SettingContact.YEHUI_SHARE);
        Boolean isOneStart = sharedPreferencesUtil.getBoolean(SettingContact.IS_ONE_START, true);//获取这个值，如果没有这个值则去第二个参数，即取默认值
        if (isOneStart) {//第一次
            sharedPreferencesUtil.saveBoolean(SettingContact.IS_ONE_START, false);
            return true;
        }
        return false;
    }

    /**
     * 判断该activity是否处于栈顶
     * android 5.0以后弃用，有时候判断不准确，慎用！
     *
     * @param activty
     * @return
     */
    public static boolean isTopActivity(Activity activty) {
        ActivityManager activityManager = (ActivityManager) activty.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfoList = activityManager.getRunningTasks(1);
        if (runningTaskInfoList != null) {
            String topActivity = runningTaskInfoList.get(0).topActivity.toString();
            return topActivity.equals(activty.getComponentName().toString());
        } else
            return false;
    }

    /**
     * 获取手机设置的语言
     */
    public static Locale getUserLanguage(Context context) {
        return context.getResources().getConfiguration().locale;
    }

    /**
     * 修改当前语言
     */
    public static String setUserLanguage(Context context, Locale locale) {
        //选择语言
        Configuration config = context.getResources().getConfiguration();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        config.locale = locale;
        context.getResources().updateConfiguration(config, dm);
        return context.getResources().getConfiguration().locale.getCountry();
    }

    private static Handler handler = new Handler();

    /**
     * 重启app
     */
    public static void reStartApp(final Context context) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(context, YehuiHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent restartIntent = PendingIntent.getActivity(
                        context, 0, intent, 0);
                //退出程序
                AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
                        restartIntent); // 1秒钟后重启应用
                ActivityCollector.finishAll();
            }
        }, 1000);
    }
}
