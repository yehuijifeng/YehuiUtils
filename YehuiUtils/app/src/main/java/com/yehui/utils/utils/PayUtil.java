package com.yehui.utils.utils;

import android.app.Activity;

import com.pay.utils.alipay.AliPayService;
import com.yehui.utils.R;

/**
 * Created by yehuijifeng
 * on 2016/1/15.
 * 支付的工具类
 */
public class PayUtil {

    private static AliPayService payByAli;

    public static AliPayService aliPay(Activity activity) {
        payByAli = AliPayService.getPayService();
          return payByAli;
    }
}
