package com.umeng.utils.umengLogin.login;

/**
 * Created by yehuijifeng
 * on 2015/11/21.
 * 登录信息返回结果实体类
 */
public class LoginBean {

    private int loginStatus;//登录状态
    private String loginContent;//登录信息
    private String userInfo;//用户信息
    private int loginOutStatus;//登出状态
    private String loginOutContent;//登出信息

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getLoginContent() {
        return loginContent;
    }

    public void setLoginContent(String loginContent) {
        this.loginContent = loginContent;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public int getLoginOutStatus() {
        return loginOutStatus;
    }

    public void setLoginOutStatus(int loginOutStatus) {
        this.loginOutStatus = loginOutStatus;
    }

    public String getLoginOutContent() {
        return loginOutContent;
    }

    public void setLoginOutContent(String loginOutContent) {
        this.loginOutContent = loginOutContent;
    }

}
