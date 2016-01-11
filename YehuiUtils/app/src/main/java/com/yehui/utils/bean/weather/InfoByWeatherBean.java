package com.yehui.utils.bean.weather;

import java.util.List;

/**
 * Created by yehuijifeng on 2016/1/11.
 */
public class InfoByWeatherBean {

    private List<String> day;//白天
    private List<String> night;//晚上

    public InfoByWeatherBean(List<String> day, List<String> night) {
        this.day = day;
        this.night = night;
    }

    public List<String> getDay() {
        return day;
    }

    public void setDay(List<String> day) {
        this.day = day;
    }

    public List<String> getNight() {
        return night;
    }

    public void setNight(List<String> night) {
        this.night = night;
    }
}
