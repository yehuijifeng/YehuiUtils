package com.pay.utils;

import com.pay.utils.alipay.AliPayService;

/**
 * Created by yehuijifeng
 * on 2015/11/26.
 * 支付服务类型
 */
public class PayService {

    /**
     * 支付宝支付信息
     */

    private static AliPayService payByZhifubao;

    public static AliPayService payByAli() {
        payByZhifubao = AliPayService.getPayService();
        return payByZhifubao;
    }

    /**
     * ---------------------------------------------------------------------------------------
     */
    private void payByWeixin() {

    }

}
