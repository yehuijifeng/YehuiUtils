package com.yehui.utils.bean.weather;

/**
 * Created by yehuijifeng on 2016/1/11.
 */
public class Pm25Bean {
    private String key;//城市拼音
    private int show_desc;//显示下降数量
    private Pm25TowBean pm25;//pm25信息
    private String dateTime;//时间日期
    private String cityName;//城市名称


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getShow_desc() {
        return show_desc;
    }

    public void setShow_desc(int show_desc) {
        this.show_desc = show_desc;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Pm25TowBean getPm25() {
        return pm25;
    }

    public void setPm25(Pm25TowBean pm25) {
        this.pm25 = pm25;
    }

    public Pm25Bean(String key, int show_desc, String dateTime, String cityName, Pm25TowBean pm25) {
        this.key = key;
        this.show_desc = show_desc;
        this.dateTime = dateTime;
        this.cityName = cityName;
        this.pm25 = pm25;

    }
}
