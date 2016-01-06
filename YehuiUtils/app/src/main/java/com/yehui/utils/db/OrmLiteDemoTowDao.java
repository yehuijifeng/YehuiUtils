package com.yehui.utils.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.yehui.utils.bean.OrmLiteDemoTowBean;
import com.yehui.utils.db.base.DBBaseDao;

import java.sql.SQLException;
import java.util.List;

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
        dbDao = setDao(OrmLiteDemoTowBean.class, dbDao);
    }

    /**
     * 是否有数据库辅助类对象存在
     */
    public boolean isOpenDB() {
        if (dbDao == null) return false;
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
            dbDao = setDao(OrmLiteDemoTowBean.class, dbDao);
        }
        return dbDao;
    }

    public void deleteDB() {
        if (!isOpenDB())
            dbDao = setDao(OrmLiteDemoTowBean.class, dbDao);
        dBHelper.deleteDB();
    }

    public void insertTable() {
        if (!isOpenDB())
            dbDao = setDao(OrmLiteDemoTowBean.class, dbDao);
        dBHelper.updateDB(OrmLiteDemoTowBean.class);
    }

    public void insertData(OrmLiteDemoTowBean ormLiteDemoTowBean) {
        try {
            if (!isOpenDB())
                dbDao = setDao(OrmLiteDemoTowBean.class, dbDao);
            dbDao.create(ormLiteDemoTowBean);
        } catch (Exception e) {
            return;
        }
    }

    public void updateData() {
        try {
            if (!isOpenDB())
                dbDao = setDao(OrmLiteDemoTowBean.class, dbDao);
            OrmLiteDemoTowBean ormLiteDemoTowBean = new OrmLiteDemoTowBean("testsd");
            ormLiteDemoTowBean.setTest_id(1);
            dbDao.update(ormLiteDemoTowBean);
        } catch (Exception e) {
            return;
        }
    }

    public void deleteById(int id) {
        try {
            if (!isOpenDB())
                dbDao = setDao(OrmLiteDemoTowBean.class, dbDao);
            dbDao.deleteById(id);
            //dbDao.delete(ormLiteDemoTowBean);
        } catch (Exception e) {
            return;
        }
    }

    public List<OrmLiteDemoTowBean> queryAll() {
        try {
            if (!isOpenDB())
                dbDao = setDao(OrmLiteDemoTowBean.class, dbDao);
            return dbDao.queryForAll();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过UserId获取所有的文章
     *
     * @param userId
     * @return
     */
    public List<OrmLiteDemoTowBean> listByUserId(int userId) {
        try {
            return dbDao.queryBuilder().where().eq("user_id", userId).query();
        } catch (SQLException e) {
           return null;
        }
    }

    /**
     * 释放资源
     */
    public void closeDao() {
        dbDao = null;
    }
}
