package com.pay.utils;

import com.pay.utils.alipay.AliPayService;

/**
 * Created by yehuijifeng
 * on 2015/11/26.
 * 支付服务类型
 */
public class payService {

    /**   * 支付宝支付信息
     */

    private static AliPayService payByZhifubao;

    //商户PID
    public static final String PARTNER = "2088512600464774";
    //商户收款账号
    public static final String SELLER = "alsfox@163.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALS3D2e4cAJOGCGkgCGgot3WKQ9btyppnEd8dE4y0Vb8Chh4yb59ZHh503E8ciEO8uQyOsDyTbndugK7EWVTCYPsucO/u6rRAeTOrvUbgvyPfVuM5/N85y/WKUo6NMkGKoPlziYIANFVFoMiyv+J2IYNFCXxjwvd6YO5GH1zdO2fAgMBAAECgYEAtEB3vDQKfTIZ/cyQBXqtmOnZcLlBnKtUtzUa6Tjk94dUJo6sZDW8djLFxYfOKs03VogHdJb7Ei5oL/sZYeCOdnr5Kwve1hPHVsZX2LfrznsgiRPa9qNI7LKlN/R5lqRY/X2Hx8rvNid9Xn6VrD4MvBJty1tnkVd1xr7PESEgfSkCQQDnaV/c6tVAYG7wBAbDeXx0Qv8d0nBXwR2XyDkFZt5jfUR6VObgP4gaqWJGq1GGhhB9ix/QsiUSu7nViQSO0X+1AkEAx+qwhcVBECQ5nq6Rp84C5Z1e0T+CDC85Oz4azF4+q49pHieJh47HMOVjU4dn2dRqNvhh/JZP/8f8RdfGHtLEgwJAEJwfpVu1ssk5LUEcuysdI7srVJimN6ZE2YAll52qmLmFEMLHT5E/06oUw73IPD+jL1+tCO3Ae3e9/vW2vgmLgQJBAKQ3qm0YDUv/Fb29VDRtS3/l918XNfJgUDjT2RuLRxuSIBz/CT/rDsChjSeuEKakj+M7wPHFX02Sry2IA1yKcZUCQQCs8szRemzCCrTTOHqkYZGiq+BzQznXo7xpZvXIMi27kkRF3piGDwctgz2lD82XM2UeUIlyGv7gHNNDZi9HGtLu";
    //异步回调
    private static final String BACK_ASYNC_URL = "http://115.29.224.175:9999/alsfoxShop/site/order/notifyUrl.action";

    public static AliPayService payByZhifubao() {
        payByZhifubao = AliPayService.getPayService();
        payByZhifubao.setBckAsyncRul(PARTNER, SELLER, RSA_PRIVATE, BACK_ASYNC_URL);
        return payByZhifubao;
    }

    /**
     * ---------------------------------------------------------------------------------------
     */
    private void payByWeixin() {

    }

}
