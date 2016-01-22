package com.yehui.utils.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.activity.animation.CustomAnimActivity;
import com.yehui.utils.activity.animation.ValueAnimActivity;
import com.yehui.utils.activity.animation.ViewAnimActivity;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.activity.function.FileActivity;
import com.yehui.utils.activity.function.JPushActivity;
import com.yehui.utils.activity.function.OkHttpActivity;
import com.yehui.utils.activity.function.OrmLiteActivity;
import com.yehui.utils.activity.function.PayActivity;
import com.yehui.utils.activity.function.UMengActivity;
import com.yehui.utils.activity.function.ZxingActivity;
import com.yehui.utils.activity.map.LocationActivity;
import com.yehui.utils.activity.view.DialogActivity;
import com.yehui.utils.activity.view.PhotoViewActivity;
import com.yehui.utils.activity.view.PopupwindowActivity;
import com.yehui.utils.activity.view.ViewFlipperActivity;
import com.yehui.utils.activity.view.WebViewActivity;
import com.yehui.utils.bean.MenuBean;
import com.yehui.utils.bean.MenuTowBean;
import com.yehui.utils.contacts.MenuContact;
import com.yehui.utils.contacts.SettingContact;
import com.yehui.utils.utils.AppUtil;
import com.yehui.utils.utils.LanguageUtil;
import com.yehui.utils.utils.ResourcesUtil;
import com.yehui.utils.utils.UmengUtil;
import com.yehui.utils.view.dialog.PromptDialog;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by yehuijifeng
 * on 2016/1/4.
 * 夜辉宝典首页
 */
public class YehuiHomeActivity extends BaseActivity implements OnClickListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private List<MenuBean> list;
    private View homeMenuView;
    private LinearLayout layout_drawer;
    private TextView home_menu_text;
    private ImageView home_menu_image, home_menu_go;
    private TextView home_text;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_yehui_main);
    }

    @Override
    protected String setTitleText() {
        return null;
    }

    @Override
    protected void initView() {
        overridePendingTransition(R.anim.activity_start_anim, 0);
        toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setLogo(R.mipmap.ic_launcher);//标题栏的logo图标
        //toolbar.setSubtitle(R.string.loading_refresh);//二级标题
        toolbar.setTitle(getResourceString(R.string.app_name));//设置Toolbar标题
        toolbar.setTitleTextColor(getResources().getColor(R.color.white)); //设置标题颜色
        //如果设置了actionBarDrawerToggle，则该设置无效
        toolbar.setNavigationIcon(R.drawable.ic_launcher);//左菜单图标
        drawerLayout = (DrawerLayout) findViewById(R.id.yehui_drawer_home);
        layout_drawer = (LinearLayout) findViewById(R.id.layout_drawer);
        home_text = (TextView) findViewById(R.id.home_text);
        //创建返回键，并实现打开关/闭监听
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer image to replace 'Up' caret */
                0,  /* "open drawer" description for accessibility */
                0  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.activity_start_anim, 0);
        root.invalidate();
    }

    @Override
    protected void initData() {
        MenuContact menuContact = new MenuContact(this);
        list = menuContact.getMenuList();
        for (MenuBean menuBean : list) {
            homeMenuView = inflate(R.layout.item_home_menu, null);
            home_menu_text = (TextView) homeMenuView.findViewById(R.id.home_menu_text);
            home_menu_image = (ImageView) homeMenuView.findViewById(R.id.home_menu_image);
            home_menu_go = (ImageView) homeMenuView.findViewById(R.id.home_menu_go);
            home_menu_image.setVisibility(View.GONE);
            home_menu_go.setVisibility(View.GONE);
            layout_drawer.addView(homeMenuView);
            home_menu_text.setText(menuBean.getName());
            home_menu_text.setTextColor(getResourceColor(R.color.black));
            for (MenuTowBean menuTowBean : menuBean.getListTow()) {
                homeMenuView = inflate(R.layout.item_home_menu, null);
                homeMenuView.setOnClickListener(this);
                home_menu_text = (TextView) homeMenuView.findViewById(R.id.home_menu_text);
                home_menu_image = (ImageView) homeMenuView.findViewById(R.id.home_menu_image);
                home_menu_go = (ImageView) homeMenuView.findViewById(R.id.home_menu_go);
                home_menu_image.setVisibility(View.VISIBLE);
                home_menu_go.setVisibility(View.VISIBLE);
                layout_drawer.addView(homeMenuView);
                home_menu_text.setText(menuTowBean.getName());
            }
        }
        String explain;
        try {
            InputStream in;
            if (sharedPreferences.getString(SettingContact.APP_LANGUAGE).equalsIgnoreCase(LanguageUtil.CHINA)) {
                in = getResources().getAssets().open("yehui-explain-zh.txt");
            } else if (sharedPreferences.getString(SettingContact.APP_LANGUAGE).equalsIgnoreCase(LanguageUtil.ENGLISH)) {
                in = getResources().getAssets().open("yehui-explain-en.txt");
            } else {
                in = getResources().getAssets().open("yehui-explain-zh.txt");
            }
            explain = ResourcesUtil.getFromRaw(in);
        } catch (IOException e) {
            e.printStackTrace();
            explain = "获取说明文档失败！";
        }
        home_text.setText(Html.fromHtml(explain));

        if (AppUtil.isOneStart(this))
            showShortToast(getResourceString(R.string.welcome_to_use));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.action_edit:
                UmengUtil.shareDefault(this);
                break;
            case R.id.action_share:
                PromptDialog promptDialog = new PromptDialog(this);
                promptDialog.showPromptDialog(getResourceString(R.string.welcome_learning), getResourceString(R.string.learning_content), new PromptDialog.PromptOnClickListener() {
                    @Override
                    public void onDetermine() {

                    }

                    @Override
                    public void onCancel() {

                    }
                });
                break;
            case R.id.action_settings:
                startActivity(SettingsActivity.class);
                break;
            case R.id.action_exit:
                finishAll();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showShortToast(getResourceString(R.string.exit_app));
                exitTime = System.currentTimeMillis();
            } else {
                finishAll();
            }
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onPause() {
        super.onPause();
        drawerLayout.closeDrawers();
    }


    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        home_menu_text = (TextView) v.findViewById(R.id.home_menu_text);
        String menuStr = home_menu_text.getText().toString().trim();
        switch (menuStr) {

            case MenuContact.activityBase://baseactivity
                startActivity(CartAnimationActivity.class);
                break;
            case MenuContact.activityList://activityList
                startActivity(ListActivity.class);
                break;
            case MenuContact.activityGrid://activityGrid
                startActivity(GridActivity.class);
                break;
            case MenuContact.activityExpandable://两级列表
                startActivity(ExpandableListActivity.class);
                break;
            case MenuContact.activityStaggered://瀑布流
                startActivity(StaggeredActivity.class);
                break;
            case MenuContact.fragmentBase://fragmentBase
                bundle.putInt(MenuContact.viewpagerPage, 0);
                startActivity(ViewpagerActivity.class, bundle);
                break;
            case MenuContact.fragmentList://fragmentList
                bundle.putInt(MenuContact.viewpagerPage, 1);
                startActivity(ViewpagerActivity.class, bundle);
                break;
            case MenuContact.fragmentGrid://fragmentGrid
                bundle.putInt(MenuContact.viewpagerPage, 2);
                startActivity(ViewpagerActivity.class, bundle);
                break;
            case MenuContact.fragmentExpandable://两级列表
                bundle.putInt(MenuContact.viewpagerPage, 4);
                startActivity(ViewpagerActivity.class, bundle);
                break;
            case MenuContact.fragmentStaggered://瀑布流
                bundle.putInt(MenuContact.viewpagerPage, 3);
                startActivity(ViewpagerActivity.class, bundle);
                break;
            case MenuContact.viewpager://viewpager
                bundle.putInt(MenuContact.viewpagerPage, 0);
                startActivity(ViewpagerActivity.class, bundle);
                break;
            case MenuContact.sqllite://ormLite的数据库存储
                startActivity(OrmLiteActivity.class);
                break;
            case MenuContact.okhttp://okhttp网络请求
                startActivity(OkHttpActivity.class);
                break;
            case MenuContact.dialog://dialog
                startActivity(DialogActivity.class);
                break;
            case MenuContact.popupwindow://popupwindow
                startActivity(PopupwindowActivity.class);
                break;
            case MenuContact.photoview://方法旋转图片三方库
                startActivity(PhotoViewActivity.class);
                break;
            case MenuContact.webview://webview
                startActivity(WebViewActivity.class);
                break;
            case MenuContact.zxing://扫码google官方库
                startActivity(ZxingActivity.class);
                break;
            case MenuContact.layoutAnim://父动画
                startActivity(ViewAnimActivity.class);
                break;
        }

        if (menuStr == getResources().getString(R.string.file_storage)) {
            startActivity(FileActivity.class);
        } else if (menuStr == getResources().getString(R.string.turns_image)) {
            startActivity(ViewFlipperActivity.class);
        } else if (menuStr == getResources().getString(R.string.jpush_propelling)) {
            startActivity(JPushActivity.class);
        } else if (menuStr == getResources().getString(R.string.umeng)) {
            startActivity(UMengActivity.class);
        } else if (menuStr == getResources().getString(R.string.pay)) {
            startActivity(PayActivity.class);
        } else if (menuStr == getResources().getString(R.string.view_anim)) {
            startActivity(ViewAnimActivity.class);
            overridePendingTransition(R.anim.activity_start_anim, 0);
        } else if (menuStr == getResources().getString(R.string.value_anim)) {
            startActivity(ValueAnimActivity.class);
        } else if (menuStr == getResources().getString(R.string.custom_anim)) {
            startActivity(CustomAnimActivity.class);
        }else if (menuStr == getResources().getString(R.string.map_location)) {
            startActivity(LocationActivity.class);
        }
    }
}
