package com.yehui.utils.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.bean.MenuBean;
import com.yehui.utils.bean.MenuTowBean;
import com.yehui.utils.contacts.MenuContact;

import java.util.List;

/**
 * Created by yehuijifeng on 2016/1/4.
 */
public class YehuiHomeActivity extends BaseActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private List<MenuBean> list;
    private View homeMenuView;
    private LinearLayout layout_drawer;
    private TextView home_menu_text;
    private ImageView home_menu_image, home_menu_go;

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
    protected void initData() {
        MenuContact menuContact = new MenuContact();
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
                home_menu_text = (TextView) homeMenuView.findViewById(R.id.home_menu_text);
                home_menu_image = (ImageView) homeMenuView.findViewById(R.id.home_menu_image);
                home_menu_go = (ImageView) homeMenuView.findViewById(R.id.home_menu_go);
                home_menu_image.setVisibility(View.VISIBLE);
                home_menu_go.setVisibility(View.VISIBLE);
                layout_drawer.addView(homeMenuView);
                home_menu_text.setText(menuTowBean.getName());
            }
        }
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
                Toast.makeText(YehuiHomeActivity.this, "" + "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_share:

                Toast.makeText(YehuiHomeActivity.this, "" + "关于", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:

                Toast.makeText(YehuiHomeActivity.this, "" + "设置", Toast.LENGTH_SHORT).show();
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
                showShortToast("再按一次退出应用");
                exitTime = System.currentTimeMillis();
            } else {
                finishAll();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
