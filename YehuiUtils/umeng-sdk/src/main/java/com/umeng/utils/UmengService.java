package com.umeng.utils;

import android.app.Activity;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.utils.umengshare.share.QQShare;

/**
 * Created by yehuijifeng
 * on 2015/11/25.
 * 友盟分享
 */
public class UmengService {

    //qq
    public static final String QQappId = "1104967462";
    public static final String QQappKey = "8SKqUN5ap6zc5GtK";

    //分享的地址来源
    public static final String DESCRIPTOR = "http://www.yehuijifeng.icoc.in/";

    public static UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");

    /**
     * 通用app授权
     */
    public static void doAccredit(Activity activity) {
        mController.getConfig().setPlatforms(
                SHARE_MEDIA.WEIXIN,
                SHARE_MEDIA.WEIXIN_CIRCLE,
                SHARE_MEDIA.QQ,
                SHARE_MEDIA.QZONE,
                SHARE_MEDIA.SMS,
                SHARE_MEDIA.SINA);
    }

    /**
     * 添加QQ、QZone平台的授权
     */
    public static QQShare qqShare;

    public static void byQQShare(Activity activity) {
        // 添加QQ、QZone平台
        qqShare = QQShare.getQQSHare(mController, activity);//调用分享服务
        qqShare.addQQPlatform(QQappId, QQappKey, DESCRIPTOR);//配置key和id
    }

}
