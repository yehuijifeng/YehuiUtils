package com.yehui.utils.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;

/**
 * Created by yehuijifeng on 2016/1/4.
 */
public class YehuiHomeActivity extends BaseActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private ListView layout_drawer_list;
    private String[] lvs = {"BaseActivity",
            "BaseListActivity",
            "BaseViewPagerActivity",
            "BaseGridViewActivity",
            "BaseStaggeredActivity",
            "BaseExpandableListView"};
    private ArrayAdapter arrayAdapter;

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
        //toolbar.setLogo(R.drawable.ic_launcher);//标题栏的logo图标
        //toolbar.setSubtitle(R.string.loading_refresh);//二级标题
        toolbar.setTitle("夜辉宝典");//设置Toolbar标题
        toolbar.setTitleTextColor(getResources().getColor(R.color.white)); //设置标题颜色
        //如果设置了actionBarDrawerToggle，则该设置无效
        toolbar.setNavigationIcon(R.drawable.ic_launcher);//左菜单图标

        drawerLayout = (DrawerLayout) findViewById(R.id.yehui_drawer_home);
        layout_drawer_list = (ListView) findViewById(R.id.layout_drawer_list);
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
        //设置菜单列表
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lvs);
        layout_drawer_list.setAdapter(arrayAdapter);
        // Menu item click 的監聽事件一樣要設定在 setSupportActionBar 才有作用
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
    }

    @Override
    protected void initData() {

    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_edit:
                    msg += "Click edit";
                    break;
                case R.id.action_share:
                    msg += "Click share";
                    break;
                case R.id.action_settings:
                    msg += "Click setting";
                    break;
            }

            if (!msg.equals("")) {
                Toast.makeText(YehuiHomeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

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

                Toast.makeText(YehuiHomeActivity.this, "" + "编辑", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:

                Toast.makeText(YehuiHomeActivity.this, "" + "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_exit:

                Toast.makeText(YehuiHomeActivity.this, "" + "退出", Toast.LENGTH_SHORT).show();
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
                finish();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
