package com.yehui.utils.utils;

import android.content.Context;

import com.yehui.utils.contacts.SettingContact;

import java.util.Locale;

/**
 * Created by yehuijifeng
 * on 2016/1/22.
 * 适配语言
 */
public class LanguageUtil {

    public final static String CHINA = "CN";
    public final static String ENGLISH = "GB";

    public static void adapterLanguage(Context context, SharedPreferencesUtil sharedPreferences) {
        if (EmptyUtil.isStringEmpty(sharedPreferences.getString(SettingContact.APP_LANGUAGE)))
            AppUtil.setUserLanguage(context, AppUtil.getUserLanguage(context));
        else {
            if (sharedPreferences.getString(SettingContact.APP_LANGUAGE).equalsIgnoreCase(CHINA)) {
                AppUtil.setUserLanguage(context, Locale.CHINESE);
            } else if (sharedPreferences.getString(SettingContact.APP_LANGUAGE).equalsIgnoreCase(ENGLISH)) {
                AppUtil.setUserLanguage(context, Locale.ENGLISH);
            }
        }
    }
}
