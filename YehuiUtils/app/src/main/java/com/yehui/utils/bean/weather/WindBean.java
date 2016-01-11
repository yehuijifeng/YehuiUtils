package com.yehui.utils.bean.weather;

/**
 * Created by yehuijifeng on 2016/1/11.
 */
public class WindBean {
    private String direct;//风向
    private String power;//风级
    private String offset;//补偿字段
    private String windspeed;//风速

    public WindBean(String direct, String power, String offset, String windspeed) {
        this.direct = direct;
        this.power = power;
        this.offset = offset;
        this.windspeed = windspeed;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }
}
