package com.yehui.utils.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import com.yehui.utils.contacts.SettingContact;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by yehuijifeng
 * on 2016/1/22.
 * 适配语言
 */
public class LanguageUtil {

    public final static String CHINA = "CN";
    public final static String ENGLISH = "GB";
    public static Map<String, Locale> localeMap = new HashMap<>();

    static {
        localeMap.put(CHINA, Locale.CHINA);
        localeMap.put(ENGLISH, Locale.UK);
    }

    public static void adapterLanguage(Context context, SharedPreferencesUtil sharedPreferences) {
        if (EmptyUtil.isStringEmpty(sharedPreferences.getString(SettingContact.APP_LANGUAGE)))
            setUserLanguage(context, getUserLanguage(context));
        else {
            setUserLanguage(context, localeMap.get(sharedPreferences.getString(SettingContact.APP_LANGUAGE, SettingContact.DEFAULT_LANGUAGE)));
        }
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
}
