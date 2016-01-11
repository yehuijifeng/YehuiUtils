package com.yehui.utils.db;

import android.content.Context;

import com.yehui.utils.bean.ormlite.OrmLiteDemoTowBean;
import com.yehui.utils.db.base.DBBaseHelper;

/**
 * Created by yehuijifeng
 * on 2016/1/6.
 * 数据库的helper类，一个项目可以有多个，但是要保证能够数据库名字，版本相同才可以在同一数据库中操作
 * 这里我们需要继承OrmLiteSqliteOpenHelper，其实就是间接继承了SQLiteOpenHelper
 *
 * 目前的方法只能在数据库操作一张表。不能同时添加两张表进去，如果添加多张表则会一直顶掉前面的表，只保留最后一次添加的记录
 * 实体类目前只支持基本数据类型，集合/泛型暂不支持
 */
public class DBOrmLiteDemoTowHelper extends DBBaseHelper {

    private static DBOrmLiteDemoTowHelper dbBaseHelper;

    /**初始化，将上下文传入父类中
     * @param context 上下文
     */
    public DBOrmLiteDemoTowHelper(Context context) {
        super(context);
    }

    /**
     * 单例获取该Helper
     * @param context
     * @return
     */
    public static synchronized DBOrmLiteDemoTowHelper getDBBaseHelper(Context context) {
        if (dbBaseHelper == null) {
            synchronized (DBOrmLiteDemoTowHelper.class) {
                if (dbBaseHelper == null)
                    dbBaseHelper = new DBOrmLiteDemoTowHelper(context);
            }
        }
        return dbBaseHelper;
    }

    @Override
    public Class setClass() {
        return OrmLiteDemoTowBean.class;
    }

}
