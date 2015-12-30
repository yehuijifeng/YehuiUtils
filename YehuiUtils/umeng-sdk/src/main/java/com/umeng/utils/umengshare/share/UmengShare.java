package com.umeng.utils.umengshare.share;

import android.app.Activity;

import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.BaseShareContent;
import com.umeng.socialize.media.UMImage;

/**
 * Created by yehuijifeng
 * on 2015/11/14.
 * 分享的公有类
 */
public  class UmengShare {
    //private final UMSocialService mController = UMServiceFactory.getUMSocialService(Constants.DESCRIPTOR);
    //友盟社会化服务的url
    protected UMSocialService mController;
    //哪个activiyt设置的分享
    protected Activity activity;

    private UMImage umImage;

    public UmengShare(UMSocialService mController, Activity activity){
        this.mController=mController;
        this.activity=activity;
    }

    /**
     * qq的分享
     * @param title
     *            标题
     * @param iconUrl
     *            图标
     * @param content
     *            内容
     * @param targetUrl
     *            链接地址
     */
    public void setShareContent(String title, String iconUrl, String content,
                           String targetUrl){
        umImage = new UMImage(activity, iconUrl);

        BaseShareContent[] shareContents = getShareContents(title, umImage,
                content, targetUrl);
        if (shareContents != null && shareContents.length > 0) {
            for (int i = 0; i < shareContents.length; i++) {
                mController.setShareMedia(shareContents[i]);
            }
        }
    }


    //让子类完成具体分享内容的方法
    protected BaseShareContent[] getShareContents(String title, UMImage umImage, String content, String targetUrl) {
        return null;
    }
}
