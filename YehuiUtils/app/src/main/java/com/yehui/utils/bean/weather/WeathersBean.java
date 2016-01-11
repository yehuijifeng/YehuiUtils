package com.yehui.utils.bean.weather;

import java.util.List;

/**
 * Created by yehuijifeng
 * on 2016/1/11.
 * 天气预报
 */
public class WeathersBean {
    private RealtimeBean realtime;//实时数据
    private LifeBean life;//生活指数
    private List<WeatherListBean> weather;//七天的天气情况
    private Pm25Bean pm25;//pm2.5
    private String date;//时间
    private int isForeign;//是否是外国数据，默认0，不是

    public WeathersBean(RealtimeBean realtime, LifeBean life, List<WeatherListBean> weather, Pm25Bean pm25, String date, int isForeign) {
        this.realtime = realtime;
        this.life = life;
        this.weather = weather;
        this.pm25 = pm25;
        this.date = date;
        this.isForeign = isForeign;
    }

    public RealtimeBean getRealtime() {
        return realtime;
    }

    public void setRealtime(RealtimeBean realtime) {
        this.realtime = realtime;
    }

    public LifeBean getLife() {
        return life;
    }

    public void setLife(LifeBean life) {
        this.life = life;
    }

    public List<WeatherListBean> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherListBean> weather) {
        this.weather = weather;
    }

    public Pm25Bean getPm25() {
        return pm25;
    }

    public void setPm25(Pm25Bean pm25) {
        this.pm25 = pm25;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(int isForeign) {
        this.isForeign = isForeign;
    }
}
