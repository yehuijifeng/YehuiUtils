package com.umeng.utils;

import android.app.Activity;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.utils.umengLogin.login.QQLogin;


/**
 * Created by yehuijifeng
 * on 2015/11/21.
 * 授权登陆
 */
public class UmengQQLoginUtil {
    private Activity activity;
    public UmengQQLoginUtil(Activity activity) {
        this.activity = activity;
        UmengService.doAccredit();
        UmengService.byQQShare(activity);
    }

    //qq授权登陆
    private QQLogin qqLogin;

    public void qqByLogin() {
        qqLogin = new QQLogin(activity, SHARE_MEDIA.QQ);
        qqLogin.getLogin();
    }

    public String qqByUserInfo() {
        return qqLogin.getUserInfo();
    }

    public void qqByOutLogin() {
        qqLogin.getOutLogin();
    }
}
