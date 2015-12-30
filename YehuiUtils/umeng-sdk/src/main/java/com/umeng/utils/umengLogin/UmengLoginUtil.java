package com.umeng.utils.umengLogin;

import android.app.Activity;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.utils.UmengService;
import com.umeng.utils.umengLogin.login.QQLogin;


/**
 * Created by yehuijifeng
 * on 2015/11/21.
 * 授权登陆
 */
public class UmengLoginUtil {
    private Activity activity;
    public UmengLoginUtil(Activity activity) {
        this.activity = activity;
        /**
         * 授权
         */
        UmengService.doAccredit(activity);
        UmengService.byQQShare(activity);
    }

    //qq授权登陆
    private QQLogin qqLogin;

    public void QQByLogin() {
        qqLogin = new QQLogin(activity, SHARE_MEDIA.QQ);
        qqLogin.getLogin();
    }

    public void QQByUserInfo() {
        qqLogin.getUserInfo();
    }

    public void QQByOutLogin() {
        qqLogin.getOutLogin();
    }
}
