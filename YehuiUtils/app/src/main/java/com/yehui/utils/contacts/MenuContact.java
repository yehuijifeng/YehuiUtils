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

    /*activity功能区*/
    public static final String activity = "activity";
    public static final String activityBase = activity + "Base";
    public static final String activityList = activity + "List";
    public static final String activityGrid = activity + "Grid";
    public static final String activityStaggered = activity + "Staggered";
    public static final String activityExpandable = activity + "Expandable";

    /*fragment功能区*/
    public static final String fragment = "fragment";
    public static final String fragmentBase = fragment + "Base";
    public static final String fragmentList = fragment + "List";
    public static final String fragmentGrid = fragment + "Grid";
    public static final String fragmentStaggered = fragment + "Staggered";
    public static final String fragmentExpandable = fragment + "Expandable";
    public static final String viewpager = "viewpager";

    /*视图样式区*/
    public static final String view = "view";
    public static final String dialog = "dialog";
    public static final String webview = "webview";
    public static final String photoview = "photoview";
    public static final String popupwindow = "popupwindow";
    public static final String turnsview = "轮番图";

    /*数据存储区*/
    public static final String db = "数据存储";
    public static final String sqllite = "ormLite";
    public static final String file = "文件存储";
    public static final String okhttp = "okhttp";

    /*功能区*/
    public static final String function = "功能区";
    public static final String zxing = "zxing.jar";
    public static final String jpush = "极光推送";
    public static final String umeng = "友盟";
    public static final String pay = "支付";

    private List<MenuBean> listOne;
    private List<MenuTowBean> listTow;


    public List<MenuBean> getMenuList() {
        int i = 0;
        listOne = new ArrayList<>();
        MenuBean menuBean = new MenuBean();
        menuBean.setId(i);
        menuBean.setName(activity);
        menuBean.setListTow(getActivityList());
        listOne.add(menuBean);

        MenuBean menuBean1 = new MenuBean();
        menuBean1.setId(i++);
        menuBean1.setName(fragment);
        menuBean1.setListTow(getFragmentList());
        listOne.add(menuBean1);

        MenuBean menuBean2 = new MenuBean();
        menuBean2.setId(i++);
        menuBean2.setName(db);
        menuBean2.setListTow(getDBList());
        listOne.add(menuBean2);

        MenuBean menuBean4 = new MenuBean();
        menuBean4.setId(i++);
        menuBean4.setName(view);
        menuBean4.setListTow(getViewList());
        listOne.add(menuBean4);

        MenuBean menuBean5 = new MenuBean();
        menuBean5.setId(i++);
        menuBean5.setName(function);
        menuBean5.setListTow(getUtilsList());
        listOne.add(menuBean5);

        return listOne;
    }

    private List<MenuTowBean> getActivityList() {
        int i = 0;
        listTow = new ArrayList<>();
        MenuTowBean menuBean = new MenuTowBean();
        menuBean.setId(i++);
        menuBean.setName(activityBase);
        listTow.add(menuBean);

        MenuTowBean menuBean1 = new MenuTowBean();
        menuBean1.setId(i++);
        menuBean1.setName(activityList);
        listTow.add(menuBean1);

        MenuTowBean menuBean2 = new MenuTowBean();
        menuBean2.setId(i++);
        menuBean2.setName(activityGrid);
        listTow.add(menuBean2);

        MenuTowBean menuBean3 = new MenuTowBean();
        menuBean3.setId(i++);
        menuBean3.setName(activityStaggered);
        listTow.add(menuBean3);

        MenuTowBean menuBean4 = new MenuTowBean();
        menuBean4.setId(i++);
        menuBean4.setName(activityExpandable);
        listTow.add(menuBean4);

        return listTow;
    }

    private List<MenuTowBean> getFragmentList() {
        int i = 0;
        listTow = new ArrayList<>();
        MenuTowBean menuBean = new MenuTowBean();
        menuBean.setId(i++);
        menuBean.setName(fragmentBase);
        listTow.add(menuBean);

        MenuTowBean menuBean5 = new MenuTowBean();
        menuBean5.setId(i++);
        menuBean5.setName(viewpager);
        listTow.add(menuBean5);

        MenuTowBean menuBean1 = new MenuTowBean();
        menuBean1.setId(i++);
        menuBean1.setName(fragmentList);
        listTow.add(menuBean1);

        MenuTowBean menuBean2 = new MenuTowBean();
        menuBean2.setId(i++);
        menuBean2.setName(fragmentGrid);
        listTow.add(menuBean2);

        MenuTowBean menuBean3 = new MenuTowBean();
        menuBean3.setId(i++);
        menuBean3.setName(fragmentStaggered);
        listTow.add(menuBean3);

        MenuTowBean menuBean4 = new MenuTowBean();
        menuBean4.setId(i++);
        menuBean4.setName(fragmentExpandable);
        listTow.add(menuBean4);

        return listTow;
    }

    private List<MenuTowBean> getViewList() {
        int i = 0;
        listTow = new ArrayList<>();
        MenuTowBean menuBean = new MenuTowBean();
        menuBean.setId(i++);
        menuBean.setName(dialog);
        listTow.add(menuBean);

        MenuTowBean menuBean5 = new MenuTowBean();
        menuBean5.setId(i++);
        menuBean5.setName(popupwindow);
        listTow.add(menuBean5);

        MenuTowBean menuBean1 = new MenuTowBean();
        menuBean1.setId(i++);
        menuBean1.setName(webview);
        listTow.add(menuBean1);

        MenuTowBean menuBean2 = new MenuTowBean();
        menuBean2.setId(i++);
        menuBean2.setName(turnsview);
        listTow.add(menuBean2);

        MenuTowBean menuBean3 = new MenuTowBean();
        menuBean3.setId(i++);
        menuBean3.setName(photoview);
        listTow.add(menuBean3);

        return listTow;
    }

    private List<MenuTowBean> getUtilsList() {
        int i = 0;
        listTow = new ArrayList<>();
        MenuTowBean menuBean = new MenuTowBean();
        menuBean.setId(i++);
        menuBean.setName(jpush);
        listTow.add(menuBean);

        MenuTowBean menuBean3 = new MenuTowBean();
        menuBean3.setId(i++);
        menuBean3.setName(umeng);
        listTow.add(menuBean3);

        MenuTowBean menuBean4 = new MenuTowBean();
        menuBean4.setId(i++);
        menuBean4.setName(pay);
        listTow.add(menuBean4);

        MenuTowBean menuBean5 = new MenuTowBean();
        menuBean5.setId(i++);
        menuBean5.setName(zxing);
        listTow.add(menuBean5);

        return listTow;
    }

    private List<MenuTowBean> getDBList() {
        int i = 0;
        listTow = new ArrayList<>();
        MenuTowBean menuBean = new MenuTowBean();
        menuBean.setId(i++);
        menuBean.setName(sqllite);
        listTow.add(menuBean);

        MenuTowBean menuBean1 = new MenuTowBean();
        menuBean1.setId(i++);
        menuBean1.setName(file);
        listTow.add(menuBean1);

        MenuTowBean menuBean2 = new MenuTowBean();
        menuBean2.setId(i++);
        menuBean2.setName(okhttp);
        listTow.add(menuBean2);
        return listTow;
    }

}
