package com.yehui.utils.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

/**
 * Created by yehuijifeng
 * on 2016/1/6.
 * 数据库dao方法的代理类
 */
public abstract class DBBaseDao {

    /**
     * 传入上下文
     */
    protected abstract Context setContext();

    /**
     * 数据库帮助类
     */
    public DBOrmLiteDemoTowHelper dBHelper;

    /**
     * 工具类中操作增删改查的dao方法
     */
    public Dao setDao(Class cla,Dao dbDao) {
        if (dbDao == null) {
            try {
                dBHelper = DBOrmLiteDemoTowHelper.getDBBaseHelper(setContext());
                dbDao=dBHelper.getDao(cla);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dbDao;
    }

}
