package com.umeng.utils.umengshare.share;

import android.app.Activity;

import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.BaseShareContent;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;

/**
 * Created by yehuijifeng
 * on 2015/11/14.
 * qq分享
 */
public class QQShare extends UmengShare{

    private QZoneSsoHandler qZoneSsoHandler;

    private UMQQSsoHandler qqSsoHandler;

    private static QQShare mQQShare;


    private QQShare(UMSocialService mController, Activity activity) {
        super(mController,activity);
    }

    /**
     * 单例模式，实例化qq分享
     * @param mController 友盟社会化服务
     * @param activity 活动
     */
    public static QQShare getQQSHare(UMSocialService mController, Activity activity){
        if (mQQShare == null) {
            synchronized (QQShare.class) {
                if (mQQShare == null) {
                    mQQShare = new QQShare(mController, activity);
                }
            }
        }
        return mQQShare;
    }

    /**
     * @功能描述 : 添加QQ平台支持 QQ分享的内容， 包含四种类型， 即单纯的文字、图片、音乐、视频.
     * 参数说明 : title, summary,image url中必须至少设置一个,
     *          targetUrl必须设置,网页地址必须以"http://"开头 .
     *       title :要分享标题
     *       summary : 要分享的文字概述
     *       image url : 图片地址 [以上三个参数至少填写一个]
     *       targetUrl: 用户点击该分享时跳转到的目标地址 [必填] ( 若不填写则默认设置为友盟主页 )
     */
    public void addQQPlatform(String qq_app_id, String qq_app_key,
                                  String shareTargetUrl) {
        // 添加QQ支持, 并且设置QQ分享内容的target url
        qqSsoHandler = new UMQQSsoHandler(activity, qq_app_id, qq_app_key);
        qqSsoHandler.setTargetUrl(shareTargetUrl);
        qqSsoHandler.addToSocialSDK();

        // 添加QZone平台
        qZoneSsoHandler = new QZoneSsoHandler(activity, qq_app_id, qq_app_key);
        qZoneSsoHandler.setTargetUrl(shareTargetUrl);
        qZoneSsoHandler.addToSocialSDK();
    }



    /**
     * 实现父类分享类容的方法
     * @param title 标题
     * @param umImage 图片
     * @param content 内容
     * @param targetUrl 目标url
     * @return
     */
    @Override
    protected BaseShareContent[] getShareContents(String title, UMImage umImage,
                                                  String content, String targetUrl) {
        QZoneShareContent qzone = new QZoneShareContent();
        qzone.setShareContent(content);
        qzone.setTargetUrl(targetUrl);
        qzone.setTitle(title);
        qzone.setShareImage(umImage);

        QQShareContent qqShareContent = new QQShareContent();
        qqShareContent.setShareContent(content);
        qqShareContent.setTitle(title);
        qqShareContent.setTargetUrl(targetUrl);
        qqShareContent.setShareImage(umImage);

        BaseShareContent[] shareContents = new BaseShareContent[] { qzone,
                qqShareContent };
        return shareContents;
    }

}
