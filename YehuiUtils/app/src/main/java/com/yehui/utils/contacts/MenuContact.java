package com.yehui.utils.contacts;

import com.yehui.utils.bean.MenuBean;
import com.yehui.utils.bean.MenuTowBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yehuijifeng
 * on 2016/1/14.
 * 首页侧滑菜单数据
 */
public class MenuContact {

    private List<MenuBean> listOne;
    private List<MenuTowBean> listTow;

    public List<MenuBean> getMenuList() {
        int i = 0;
        listOne = new ArrayList<>();
        MenuBean menuBean = new MenuBean();
        menuBean.setId(i);
        menuBean.setName("activity");
        menuBean.setListTow(getActivityList());
        listOne.add(menuBean);

        MenuBean menuBean1 = new MenuBean();
        menuBean1.setId(i++);
        menuBean1.setName("fragment");
        menuBean1.setListTow(getFragmentList());
        listOne.add(menuBean1);

        MenuBean menuBean2 = new MenuBean();
        menuBean2.setId(i++);
        menuBean2.setName("数据存储");
        menuBean2.setListTow(getDBList());
        listOne.add(menuBean2);

        MenuBean menuBean4 = new MenuBean();
        menuBean4.setId(i++);
        menuBean4.setName("view");
        menuBean4.setListTow(getViewList());
        listOne.add(menuBean4);

        MenuBean menuBean5 = new MenuBean();
        menuBean5.setId(i++);
        menuBean5.setName("功能");
        menuBean5.setListTow(getUtilsList());
        listOne.add(menuBean5);

        return listOne;
    }

    private List<MenuTowBean> getActivityList() {
        int i = 0;
        listTow = new ArrayList<>();
        MenuTowBean menuBean = new MenuTowBean();
        menuBean.setId(i++);
        menuBean.setName("base");
        listTow.add(menuBean);

        MenuTowBean menuBean1 = new MenuTowBean();
        menuBean1.setId(i++);
        menuBean1.setName("list");
        listTow.add(menuBean1);

        MenuTowBean menuBean2 = new MenuTowBean();
        menuBean2.setId(i++);
        menuBean2.setName("grid");
        listTow.add(menuBean2);

        MenuTowBean menuBean3 = new MenuTowBean();
        menuBean3.setId(i++);
        menuBean3.setName("staggered");
        listTow.add(menuBean3);

        MenuTowBean menuBean4 = new MenuTowBean();
        menuBean4.setId(i++);
        menuBean4.setName("expandableList");
        listTow.add(menuBean4);

        return listTow;
    }

    private List<MenuTowBean> getFragmentList() {
        int i = 0;
        listTow = new ArrayList<>();
        MenuTowBean menuBean = new MenuTowBean();
        menuBean.setId(i++);
        menuBean.setName("base");
        listTow.add(menuBean);

        MenuTowBean menuBean5 = new MenuTowBean();
        menuBean5.setId(i++);
        menuBean5.setName("viewpager");
        listTow.add(menuBean5);

        MenuTowBean menuBean1 = new MenuTowBean();
        menuBean1.setId(i++);
        menuBean1.setName("list");
        listTow.add(menuBean1);

        MenuTowBean menuBean2 = new MenuTowBean();
        menuBean2.setId(i++);
        menuBean2.setName("grid");
        listTow.add(menuBean2);

        MenuTowBean menuBean3 = new MenuTowBean();
        menuBean3.setId(i++);
        menuBean3.setName("staggered");
        listTow.add(menuBean3);

        MenuTowBean menuBean4 = new MenuTowBean();
        menuBean4.setId(i++);
        menuBean4.setName("expandableList");
        listTow.add(menuBean4);

        return listTow;
    }

    private List<MenuTowBean> getViewList() {
        int i = 0;
        listTow = new ArrayList<>();
        MenuTowBean menuBean = new MenuTowBean();
        menuBean.setId(i++);
        menuBean.setName("dialog");
        listTow.add(menuBean);

        MenuTowBean menuBean5 = new MenuTowBean();
        menuBean5.setId(i++);
        menuBean5.setName("popupwindow");
        listTow.add(menuBean5);

        MenuTowBean menuBean1 = new MenuTowBean();
        menuBean1.setId(i++);
        menuBean1.setName("webview");
        listTow.add(menuBean1);

        MenuTowBean menuBean2 = new MenuTowBean();
        menuBean2.setId(i++);
        menuBean2.setName("轮番图");
        listTow.add(menuBean2);

        MenuTowBean menuBean3 = new MenuTowBean();
        menuBean3.setId(i++);
        menuBean3.setName("photoview");
        listTow.add(menuBean3);

        return listTow;
    }

    private List<MenuTowBean> getUtilsList() {
        int i = 0;
        listTow = new ArrayList<>();
        MenuTowBean menuBean = new MenuTowBean();
        menuBean.setId(i++);
        menuBean.setName("极光推送");
        listTow.add(menuBean);

        MenuTowBean menuBean5 = new MenuTowBean();
        menuBean5.setId(i++);
        menuBean5.setName("二维码扫描");
        listTow.add(menuBean5);

        return listTow;
    }

    private List<MenuTowBean> getDBList() {
        int i = 0;
        listTow = new ArrayList<>();
        MenuTowBean menuBean = new MenuTowBean();
        menuBean.setId(i++);
        menuBean.setName("ormLite操作数据库");
        listTow.add(menuBean);

        MenuTowBean menuBean5 = new MenuTowBean();
        menuBean5.setId(i++);
        menuBean5.setName("本地文件操作");
        listTow.add(menuBean5);

        MenuTowBean menuBean1 = new MenuTowBean();
        menuBean1.setId(i++);
        menuBean1.setName("okhttp");
        listTow.add(menuBean1);

        return listTow;
    }

}
