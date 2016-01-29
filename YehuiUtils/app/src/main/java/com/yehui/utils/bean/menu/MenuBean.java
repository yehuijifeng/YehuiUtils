package com.yehui.utils.bean.menu;

import java.util.List;

/**
 * Created by yehuijifeng
 * on 2016/1/14.
 * 菜单实体类
 */
public class MenuBean {
    private int id;
    private int drawable;
    private String name;
    private List<MenuTowBean> listTow;

    public List<MenuTowBean> getListTow() {
        return listTow;
    }

    public void setListTow(List<MenuTowBean> listTow) {
        this.listTow = listTow;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
