package com.yehui.utils.bean.weather;

/**
 * Created by yehuijifeng
 * on 2016/1/11.
 */
public class RealtimeBean {
    private String city_code;//城市编号
    private String city_name;//城市名
    private String date;//日期
    private String time;//更新时间
    private int week;//星期
    private String moon;//农历日期
    private int dataUptime;//数据创建时间
    private WeatherBean weather;//当前实况天气
    private WindBean wind;//风向情况

    public RealtimeBean(String city_code, String city_name, String date, String time, String moon, int week, int dataUptime, WeatherBean weather, WindBean wind) {
        this.city_code = city_code;
        this.city_name = city_name;
        this.date = date;
        this.time = time;
        this.moon = moon;
        this.week = week;
        this.dataUptime = dataUptime;
        this.weather = weather;
        this.wind = wind;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoon() {
        return moon;
    }

    public void setMoon(String moon) {
        this.moon = moon;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDataUptime() {
        return dataUptime;
    }

    public void setDataUptime(int dataUptime) {
        this.dataUptime = dataUptime;
    }

    public WeatherBean getWeather() {
        return weather;
    }

    public void setWeather(WeatherBean weather) {
        this.weather = weather;
    }

    public WindBean getWind() {
        return wind;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }
}
