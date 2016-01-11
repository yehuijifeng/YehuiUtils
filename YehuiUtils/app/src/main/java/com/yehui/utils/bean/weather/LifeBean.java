package com.yehui.utils.bean.weather;

/**
 * Created by yehuijifeng on 2016/1/11.
 */
public class LifeBean {

    private String date;//时间
    private InfoBean info;//生活指数信息

    public LifeBean(String date, InfoBean info) {
        this.date = date;
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }
}
