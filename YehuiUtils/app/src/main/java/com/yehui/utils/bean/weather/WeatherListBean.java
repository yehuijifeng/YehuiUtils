package com.yehui.utils.bean.weather;

/**
 * Created by yehuijifeng on 2016/1/11.
 */
public class WeatherListBean {

    private String date;//日期
    private InfoByWeatherBean info;//七天的信息
    private String week;//星期
    private String nongli;//农历日期

    public WeatherListBean(String date, InfoByWeatherBean info, String week, String nongli) {
        this.date = date;
        this.info = info;
        this.week = week;
        this.nongli = nongli;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public InfoByWeatherBean getInfo() {
        return info;
    }

    public void setInfo(InfoByWeatherBean info) {
        this.info = info;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getNongli() {
        return nongli;
    }

    public void setNongli(String nongli) {
        this.nongli = nongli;
    }
}
