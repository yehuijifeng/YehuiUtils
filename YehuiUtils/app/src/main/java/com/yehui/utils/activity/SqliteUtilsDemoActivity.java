package com.yehui.utils.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.bean.OrmLiteDemoThreeBean;
import com.yehui.utils.bean.OrmLiteDemoTowBean;
import com.yehui.utils.db.OrmLiteDemoThreeDao;
import com.yehui.utils.db.OrmLiteDemoTowDao;

/**
 * Created by yehuijifeng on 2016/1/5.
 */
public class SqliteUtilsDemoActivity extends BaseActivity implements View.OnClickListener {
    private Button
            btn_sql_create_db,
            btn_sql_create_table,
            btn_sql_create_insert,
            btn_sql_create_update,
            btn_sql_create_delete,
            btn_sql_create_query,
            btn_sql_delete_db;
    private TextView show_table;
    private OrmLiteDemoTowBean ormLiteDemoTowBean;
    private OrmLiteDemoTowDao ormListeDemoTowDao;
    private OrmLiteDemoThreeBean ormLiteDemoThreeBean;
    private OrmLiteDemoThreeDao ormLiteDemoThreeDao;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_test_sqlite_tuils);
    }

    @Override
    protected String setTitleText() {
        return "三方工具库操作sqlite";
    }

    @Override
    protected void initView() {
        btn_sql_create_db = (Button) findViewById(R.id.btn_sql_create_db);
        btn_sql_create_table = (Button) findViewById(R.id.btn_sql_create_table);
        btn_sql_create_insert = (Button) findViewById(R.id.btn_sql_create_insert);
        btn_sql_create_update = (Button) findViewById(R.id.btn_sql_create_update);
        btn_sql_create_delete = (Button) findViewById(R.id.btn_sql_create_delete);
        btn_sql_create_query = (Button) findViewById(R.id.btn_sql_create_query);
        btn_sql_delete_db = (Button) findViewById(R.id.btn_sql_delete_db);
        show_table = (TextView) findViewById(R.id.show_table);

        btn_sql_create_db.setOnClickListener(this);
        btn_sql_create_table.setOnClickListener(this);
        btn_sql_create_insert.setOnClickListener(this);
        btn_sql_create_update.setOnClickListener(this);
        btn_sql_create_delete.setOnClickListener(this);
        btn_sql_create_query.setOnClickListener(this);
        btn_sql_delete_db.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        initDb();
    }

    /**
     * 初始化数据库
     */
    private void initDb() {
        ormLiteDemoTowBean = new OrmLiteDemoTowBean("test");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sql_create_db://创建数据库
                openDB();
                break;
            case R.id.btn_sql_create_table:
                ormListeDemoTowDao.insertTable();
                show_table.setText("\n添加表成功");
                break;
            case R.id.btn_sql_create_insert:
                ormListeDemoTowDao.insertData(ormLiteDemoTowBean);
                show_table.setText("\n添加数据成功");
                ormListeDemoTowDao.queryAll();
                break;
            case R.id.btn_sql_create_update:
                ormListeDemoTowDao.updateData();
                show_table.setText("\n更新数据成功");
                ormListeDemoTowDao.queryAll();
                break;
            case R.id.btn_sql_create_delete:
                ormListeDemoTowDao.deleteById(2);
                show_table.setText("\n删除成功：删除id为2的数据");
                ormListeDemoTowDao.queryAll();
                break;
            case R.id.btn_sql_create_query:

                String information = "\n查询成功：";
                for (OrmLiteDemoTowBean ormLiteDemoBeanTow : ormListeDemoTowDao.queryAll()) {
                    information += "\nid:  "
                            + ormLiteDemoBeanTow.getTest_id() + "\nname:  "
                            + ormLiteDemoBeanTow.getTest_name();
                }
                show_table.append(information);
                break;
            case R.id.btn_sql_delete_db:
                ormListeDemoTowDao.deleteDB();
                show_table.setText("\n清空数据库成功");
                break;
        }
    }

    private void openDB() {
        ormListeDemoTowDao = new OrmLiteDemoTowDao(this);
        show_table.setText("\n打开数据库成功");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ormListeDemoTowDao.closeDao();
    }
}
