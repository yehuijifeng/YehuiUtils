package com.yehui.utils.http.request;

import com.yehui.utils.contacts.WeatherKey;

/**
 * Created by yehuijifeng
 * on 2016/1/10.
 * 网络请求的所有url
 */
public class RequestUrls {

    public static final String IP = "http://op.juhe.cn/";
    public static final String ROOT_URL = IP + "onebox/weather/";

    /**
     * 天气预报
     */
    public static final String GET_WEATHER_URL = ROOT_URL + "query?cityname=上海&key="+ WeatherKey.WEATHER_KEY;

    /**
     * 天气预报
     */
    public static final String POST_WEATHER_URL = ROOT_URL + "query";

}
