package com.umeng.utils.umengLogin.login;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;

import java.util.Map;

/**
 * Created by yehuijifeng
 * on 2015/11/21.
 */
public class UmengLogin {

    public static UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");
    private Activity activity;
    private final SHARE_MEDIA platform;//授权登陆类型

    private LoginBean loginBean = new LoginBean();

    public UmengLogin(Activity activity, SHARE_MEDIA platform) {
        this.activity = activity;
        this.platform = platform;
    }


    /**
     * 授权。如果授权成功，则获取用户信息</br>
     */
    protected void login() {
        //umeng身份验证
        mController.doOauthVerify(
                activity,
                platform,
                new SocializeListeners.UMAuthListener() {

                    @Override
                    public void onStart(SHARE_MEDIA platform) {
                        Toast.makeText(activity, "start", Toast.LENGTH_LONG).show();
                        loginBean.setLoginContent("开始登录");
                        loginBean.setLoginStatus(0);
                    }

                    @Override
                    public void onError(SocializeException e, SHARE_MEDIA platform) {
                        Toast.makeText(activity, "登录失败错误信息：" + e.getMessage(), Toast.LENGTH_LONG).show();
                        loginBean.setLoginContent("登录失败，错误信息：" + e.getMessage());
                        loginBean.setLoginStatus(-1);
                    }

                    @Override
                    public void onComplete(Bundle value, SHARE_MEDIA platform) {
                        String uid = value.getString("uid");
                        if (!TextUtils.isEmpty(uid)) {
                            loginBean.setLoginStatus(1);
                            loginBean.setLoginContent("登录成功");
                            userInfo();
                        } else {
                            loginBean.setLoginStatus(-1);
                            loginBean.setLoginContent("授权失败");
                            Toast.makeText(activity, "授权失败...", Toast.LENGTH_SHORT).show();
                        }
                    }

                    /**
                     * @param platform 取消登录
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA platform) {
                        loginBean.setLoginStatus(2);
                        loginBean.setLoginContent("用户取消了登录");
                        Toast.makeText(activity, "用户取消了登录", Toast.LENGTH_LONG).show();

                    }
                });
    }

    /**
     * 获取授权平台的用户信息</br>
     */
    protected String userInfo() {
        final String[] userinfo = {""};
        mController.getPlatformInfo(
                activity, platform,
                new SocializeListeners.UMDataListener() {

                    @Override
                    public void onStart() {
                        Toast.makeText(activity, "开始获取授权登陆用户信息", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete(int status, Map<String, Object> info) {
                        if (info != null) {
                            userinfo[0] = info.toString();
                        }
                    }
                });
        return userinfo[0];
    }

    /**
     * 注销本次登录</br>
     */
    protected void loginOut() {
        mController.deleteOauth(activity, platform, new SocializeListeners.SocializeClientListener() {

            @Override
            public void onStart() {
                loginBean.setLoginOutContent("开始登出");
                loginBean.setLoginOutStatus(0);
                Toast.makeText(activity, "开始注销", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete(int status, SocializeEntity entity) {
                String showText;
                if (status != StatusCode.ST_CODE_SUCCESSED) {
                    loginBean.setLoginOutContent("解除" + platform.toString() + "平台授权失败[" + status + "]");
                    loginBean.setLoginOutStatus(-1);
                    showText = "解除" + platform.toString() + "平台授权失败[" + status + "]";
                } else {
                    loginBean.setLoginOutContent("解除" + platform.toString() + "平台授权成功");
                    loginBean.setLoginOutStatus(-1);
                    showText = "解除" + platform.toString() + "平台授权成功";
                }
                Toast.makeText(activity, showText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
