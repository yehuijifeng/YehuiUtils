package com.umeng.utils.weixinpay;

public class Constants {
    /** appid
     *请同时修改 androidmanifest.xml里面，.PayActivityd里的属性
     * <data android:scheme="wxb4ba3c02aa476ea1"/>为新设置的appid
     */
    public static final String WEIXIN_APP_ID = "wxd85bc7b9299628c9";

    /**
     * 管理中心/应用详情中的值
     * AppID：wxd85bc7b9299628c9
     AppSecret：7f73a3ce016a86300685c597d3c6b919
     */
    public static final String WEIXIN_APP_SECRET ="7f73a3ce016a86300685c597d3c6b919";


    /**
     * 商家向财付通申请的商家id
     */
    public static final String WEIXIN_PARTNER_ID = "1284981701";



    /**
     * appkey是开发者平台中提交的key，是用gen工具通过包名生成的
     * 注意：全部字幕大写！
     */
    public static final String WEIXIN_APP_KEY = "98D9133B22B23D460B4ED3BBFE88ECD1";



    /**
     * 微信公众平台商户模块和商户约定的密钥 注意：不能hardcode在客户端，建议genPackage这个过程由服务器端完成
     * 服务端自己生成的，给我们
     */
    public static final String WEIXIN_PARTNER_KEY = "F76EF2E2D8333BCE6B23B4C392EE17F2";


}
