package com.yehui.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by yehuijifeng
 * on 2016/1/6.
 * 数据库帮助类代理类
 */
public abstract class DBBaseHelper extends OrmLiteSqliteOpenHelper {


    /**
     * 传入需要生成表的类
     */
    public abstract Class setClass();

    /**
     * 参数说明
     *
     * @param context 上下文
     *                2 数据库名称
     *                3 代理工厂，null
     *                4 数据库版本
     */
    public DBBaseHelper(Context context) {
        super(context, DBContact.DB_NAME, null, DBContact.DB_VERSION);
    }

    /**
     * 数据库实例
     */
    protected  SQLiteDatabase sqLiteDatabase;

    /**
     * 连接源
     */
    protected   ConnectionSource connectionSource=getConnectionSource();

    /**
     * 创建数据库
     *
     * @param sqLiteDatabase   数据库
     * @param connectionSource 连接源
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            /**
             * 创建表
             * 1，连接源
             * 2，表信息
             */
            TableUtils.createTable(connectionSource, setClass());
            this.sqLiteDatabase=sqLiteDatabase;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 升级数据库
     *
     * @param sqLiteDatabase   数据库
     * @param connectionSource 连接源
     * @param i                原来的版本
     * @param i1               升级的版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            /**
             * 先删除表，然后再调用创建表
             * 1，连接源
             * 2，表信息
             * 3，是否忽略错误
             */
            TableUtils.dropTable(connectionSource, setClass(), true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改数据库的表结构
     */
    public void updateDB(Class cla) {
        /**
         * 先删除表，然后再调用创建表
         * 1，连接源
         * 2，表信息
         * 3，是否忽略错误
         */
        try {
            TableUtils.dropTable(getConnectionSource(), setClass(), true);
            TableUtils.createTable(getConnectionSource(), cla);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除数据库的表结构
     */
    public void deleteDB() {
        /**
         * 先删除表，然后再调用创建表
         * 1，连接源
         * 2，表信息
         * 3，是否忽略错误
         */
        try {
            TableUtils.dropTable(getConnectionSource(), setClass(), true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 充值数据库的表结构,无参数，则表示重置原来的表结构
     */
    public void resetDB() {
        /**
         * 先删除表，然后再调用创建表
         * 1，连接源
         * 2，表信息
         * 3，是否忽略错误
         */
        try {
            TableUtils.dropTable(getConnectionSource(), setClass(), true);
            TableUtils.createTable(getConnectionSource(), setClass());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
