package com.yehui.utils.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.yehui.utils.bean.OrmLiteDemoTowBean;

import java.sql.SQLException;

/**
 * Created by yehuijifeng
 * on 2016/1/6.
 * 每个实体类对应一个表，每个表对应的dao方法
 */
public class OrmLiteDemoTowDao extends DBBaseDao {

    private Context context;
    private Dao<OrmLiteDemoTowBean, Integer> dbDao;

    public OrmLiteDemoTowDao(Context context) {
        this.context = context;
        dbDao=setDao(OrmLiteDemoTowBean.class, dbDao);
    }

    /**
     * 是否有数据库辅助类对象存在
     */
    public boolean isOpenDB(){
        if(dbDao==null)return false;
        return true;
    }

    @Override
    protected Context setContext() {
        return context;
    }

    /**
     * 获得userDao
     *
     * @return
     * @throws SQLException
     */
    public synchronized Dao<OrmLiteDemoTowBean, Integer> getDaos() {
        if (dbDao == null) {
            dbDao=setDao(OrmLiteDemoTowBean.class, dbDao);
        }
        return dbDao;
    }
    /**
     * 释放资源
     */
    public void closeDao() {
        dbDao=null;
    }
}
