package com.yehui.utils.bean.weather;

/**
 * Created by yehuijifeng on 2016/1/11.
 */
public class Pm25TowBean {

    private String curPm;
    private String pm25;
    private String pm10;
    private int level;
    private String quality;
    private String des;

    public Pm25TowBean(String curPm, String pm25, String pm10, int level, String quality, String des) {
        this.curPm = curPm;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.level = level;
        this.quality = quality;
        this.des = des;
    }

    public String getCurPm() {
        return curPm;
    }

    public void setCurPm(String curPm) {
        this.curPm = curPm;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
