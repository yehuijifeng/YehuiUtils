package com.yehui.utils.bean.weather;

/**
 * Created by yehuijifeng
 * on 2016/1/11.
 */
public class WeatherBean {
    private String temperature;//温度
    private String humidity;//湿度
    private String info;//天气说明
    private String img;//图片

    public WeatherBean(String temperature, String humidity, String info, String img) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.info = info;
        this.img = img;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
