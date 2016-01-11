package com.yehui.utils.bean.weather;

import java.util.List;

/**
 * Created by yehuijifeng on 2016/1/11.
 */
public class InfoBean {

    private List<String> chuanyi;//穿衣指数
    private List<String> ganmao;//感冒指数
    private List<String> kongtiao;//空调指数
    private List<String> wuran;//污染指数
    private List<String> xiche;//洗车指数
    private List<String> yundong;//运动指数
    private List<String> ziwaixian;//紫外线指数

    public InfoBean(List<String> chuanyi, List<String> ganmao, List<String> kongtiao, List<String> wuran, List<String> xiche, List<String> yundong, List<String> ziwaixian) {
        this.chuanyi = chuanyi;
        this.ganmao = ganmao;
        this.kongtiao = kongtiao;
        this.wuran = wuran;
        this.xiche = xiche;
        this.yundong = yundong;
        this.ziwaixian = ziwaixian;
    }

    public List<String> getChuanyi() {
        return chuanyi;
    }

    public void setChuanyi(List<String> chuanyi) {
        this.chuanyi = chuanyi;
    }

    public List<String> getGanmao() {
        return ganmao;
    }

    public void setGanmao(List<String> ganmao) {
        this.ganmao = ganmao;
    }

    public List<String> getKongtiao() {
        return kongtiao;
    }

    public void setKongtiao(List<String> kongtiao) {
        this.kongtiao = kongtiao;
    }

    public List<String> getWuran() {
        return wuran;
    }

    public void setWuran(List<String> wuran) {
        this.wuran = wuran;
    }

    public List<String> getXiche() {
        return xiche;
    }

    public void setXiche(List<String> xiche) {
        this.xiche = xiche;
    }

    public List<String> getYundong() {
        return yundong;
    }

    public void setYundong(List<String> yundong) {
        this.yundong = yundong;
    }

    public List<String> getZiwaixian() {
        return ziwaixian;
    }

    public void setZiwaixian(List<String> ziwaixian) {
        this.ziwaixian = ziwaixian;
    }
}
