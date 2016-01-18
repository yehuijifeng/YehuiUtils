package com.yehui.utils.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

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

    private static final String YEHUI_SHARE = "yehui_utils_share";
    private static final String IS_ONE_START = "is_one_start_app";

    public static boolean isOneStart(Context context) {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(context, YEHUI_SHARE);
        Boolean isOneStart = sharedPreferencesUtil.getBoolean(IS_ONE_START, true);//获取这个值，如果没有这个值则去第二个参数，即取默认值
        if (isOneStart) {//第一次
            sharedPreferencesUtil.saveBoolean(IS_ONE_START, false);
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
    public boolean isTopActivity(Activity activty) {
        ActivityManager activityManager = (ActivityManager) activty.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfoList = activityManager.getRunningTasks(1);
        if (runningTaskInfoList != null) {
            String topActivity = runningTaskInfoList.get(0).topActivity.toString();
            return topActivity.equals(activty.getComponentName().toString());
        } else
            return false;
    }
}
