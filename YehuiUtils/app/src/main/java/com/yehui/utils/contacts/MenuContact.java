package com.yehui.utils.contacts;

import android.content.Context;

import com.yehui.utils.R;
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

    private Context context;

    public MenuContact(Context context) {
        this.context = context;
    }

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
    public static final String viewpagerPage = "viewpagerPage";
    /*视图样式区*/
    public static final String view = "view";
    public static final String dialog = "dialog";
    public static final String webview = "webview";
    public static final String photoview = "photoview";
    public static final String popupwindow = "popupwindow";

    /*数据存储区*/
    public static final String sqllite = "ormLite";
    public static final String okhttp = "okhttp";

    /*功能区*/
    public static final String zxing = "zxing.jar";

    /*动画区*/
    public static final String layoutAnim = "LayoutAnimation";

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
        menuBean2.setName(context.getResources().getString(R.string.db_storage));
        menuBean2.setListTow(getDBList());
        listOne.add(menuBean2);

        MenuBean menuBean4 = new MenuBean();
        menuBean4.setId(i++);
        menuBean4.setName(view);
        menuBean4.setListTow(getViewList());
        listOne.add(menuBean4);

        MenuBean menuBean5 = new MenuBean();
        menuBean5.setId(i++);
        menuBean5.setName(context.getResources().getString(R.string.functions));
        menuBean5.setListTow(getUtilsList());
        listOne.add(menuBean5);

        MenuBean menuBean6 = new MenuBean();
        menuBean6.setId(i++);
        menuBean6.setName(context.getResources().getString(R.string.animation));
        menuBean6.setListTow(getAnimList());
        listOne.add(menuBean6);

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
        menuBean2.setName(context.getResources().getString(R.string.turns_image));
        listTow.add(menuBean2);

        MenuTowBean menuBean3 = new MenuTowBean();
        menuBean3.setId(i++);
        menuBean3.setName(photoview);
        listTow.add(menuBean3);

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
        menuBean1.setName(context.getResources().getString(R.string.file_storage));
        listTow.add(menuBean1);

        MenuTowBean menuBean2 = new MenuTowBean();
        menuBean2.setId(i++);
        menuBean2.setName(okhttp);
        listTow.add(menuBean2);
        return listTow;
    }

    private List<MenuTowBean> getUtilsList() {
        int i = 0;
        listTow = new ArrayList<>();
        MenuTowBean menuBean = new MenuTowBean();
        menuBean.setId(i++);
        menuBean.setName(context.getResources().getString(R.string.jpush_propelling));
        listTow.add(menuBean);

        MenuTowBean menuBean3 = new MenuTowBean();
        menuBean3.setId(i++);
        menuBean3.setName(context.getResources().getString(R.string.umeng));
        listTow.add(menuBean3);

        MenuTowBean menuBean4 = new MenuTowBean();
        menuBean4.setId(i++);
        menuBean4.setName(context.getResources().getString(R.string.pay));
        listTow.add(menuBean4);

        MenuTowBean menuBean5 = new MenuTowBean();
        menuBean5.setId(i++);
        menuBean5.setName(zxing);
        listTow.add(menuBean5);

        return listTow;
    }

    private List<MenuTowBean> getAnimList() {
        int i = 0;
        listTow = new ArrayList<>();

        MenuTowBean menuBean = new MenuTowBean();
        menuBean.setId(i++);
        menuBean.setName(context.getResources().getString(R.string.view_anim));
        listTow.add(menuBean);

        MenuTowBean menuBean1 = new MenuTowBean();
        menuBean1.setId(i++);
        menuBean1.setName(context.getResources().getString(R.string.custom_anim));
        listTow.add(menuBean1);

        MenuTowBean menuBean2 = new MenuTowBean();
        menuBean2.setId(i++);
        menuBean2.setName(context.getResources().getString(R.string.layout_anim));
        listTow.add(menuBean2);

        MenuTowBean menuBean3 = new MenuTowBean();
        menuBean3.setId(i++);
        menuBean3.setName(context.getResources().getString(R.string.value_anim));
        listTow.add(menuBean3);

        return listTow;
    }


}
