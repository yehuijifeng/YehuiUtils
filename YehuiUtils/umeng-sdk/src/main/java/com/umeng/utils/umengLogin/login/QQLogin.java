package com.umeng.utils.umengLogin.login;

import android.app.Activity;

import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by yehuijifeng
 * on 2015/11/21.
 */
public class QQLogin extends UmengLogin {

    private UmengLogin umengLogin;
    public QQLogin(Activity activity, SHARE_MEDIA platform) {
        super(activity, platform);
        umengLogin=new UmengLogin(activity,platform);
    }

    //授权登陆
    public void getLogin() {
        umengLogin.login();
    }

    //获取登录用户信息
    public void getUserInfo() {
        umengLogin.userInfo();
    }

    //登出
    public void getOutLogin() {
        umengLogin.loginOut();
    }
}
