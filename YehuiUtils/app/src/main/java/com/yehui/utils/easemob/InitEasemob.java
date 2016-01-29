package com.yehui.utils.easemob;

import android.content.Context;

import com.alsfox.chatlibrary.utils.HelpDeskPreferenceUtils;
import com.easemob.chat.EMChat;
import com.yehui.utils.easemob.helper.EaseHelper;

/**
 * Created by luhao
 * on 2016/1/26.
 */
public class InitEasemob {
    private static volatile InitEasemob instance = null;

    /**
     * 初始化环信
     */
    private InitEasemob(Context applicationContext) {
        //代码中设置环信IM的Appkey
        String appkey = HelpDeskPreferenceUtils.getInstance(applicationContext).getSettingCustomerAppkey();
        EMChat.getInstance().setAppkey(appkey);
        // init demo helper
        EaseHelper.getInstance().init(applicationContext);
    }

    public synchronized static InitEasemob getInstance(Context applicationContext) {
        if (instance == null) {
            synchronized (InitEasemob.class) {
                if (instance == null) {
                    instance = new InitEasemob(applicationContext);
                }
            }
        }
        return instance;
    }
}
