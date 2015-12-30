package com.umeng.utils.umengshare;

import android.app.Activity;

import com.umeng.utils.UmengService;


/**
 * Created by yehuijifeng
 * on 2015/11/14.
 */
public class UmengShareUtil {

    private static final String DESCRIPTOR = "http://www.alsfox.com/";
    private static String title = "来自夜辉宝典的分享";
    private static String content = "这是分享的内容，夜辉宝典来给您android最新5.0工具类的集成操作和demo";
    private static String iconUrl = "http://d.hiphotos.baidu.com/album/w%3D2048/sign=9644b9d5d0c8a786be2a4d0e5331c83d/d1160924ab18972b675c19e5e7cd7b899e510abe.jpg";

    private Activity activity;

    public UmengShareUtil(Activity activity) {
        this.activity = activity;
    }

    public void defaultShare() {
        byQQShare();
        UmengService.doAccredit(activity);
        UmengService.mController.openShare(activity, false);
        UmengService.byQQShare(activity);
    }



    public static void byQQShare() {
        UmengService.qqShare.setShareContent(title, iconUrl, content, DESCRIPTOR);//填写分享详情
    }

}
