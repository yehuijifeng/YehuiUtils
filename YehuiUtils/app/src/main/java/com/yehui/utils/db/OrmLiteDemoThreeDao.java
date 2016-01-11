package com.yehui.utils.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.yehui.utils.bean.ormlite.OrmLiteDemoThreeBean;
import com.yehui.utils.bean.ormlite.OrmLiteDemoTowBean;
import com.yehui.utils.db.base.DBBaseDao;

import java.sql.SQLException;

/**
 * Created by yehuijifeng
 * on 2016/1/6.
 * 每个实体类对应一个表，每个表对应的dao方法
 */
public class OrmLiteDemoThreeDao extends DBBaseDao {

    private Context context;
    private Dao<OrmLiteDemoThreeBean, Integer> dbDao;

    public OrmLiteDemoThreeDao(Context context) {
        this.context = context;
        dbDao=setDao(OrmLiteDemoThreeBean.class, dbDao);
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
    public synchronized Dao<OrmLiteDemoThreeBean, Integer> getDaos() {
        if (dbDao == null)
            dbDao=setDao(OrmLiteDemoThreeBean.class, dbDao);
        return dbDao;
    }

    public void testAdd(){
        if (dbDao == null)
            dbDao=setDao(OrmLiteDemoThreeBean.class, dbDao);
        OrmLiteDemoTowBean ormLiteDemoTowBean=new OrmLiteDemoTowBean();
        ormLiteDemoTowBean.setTest_name("夜辉疾风的外键");
        OrmLiteDemoThreeBean ormLiteDemoThreeBean=new OrmLiteDemoThreeBean();
        ormLiteDemoThreeBean.setTest_float(0.5f);
        ormLiteDemoThreeBean.setOrmLiteDemoTowBean(ormLiteDemoTowBean);
        try {
            dbDao.create(ormLiteDemoThreeBean);
        } catch (SQLException e) {
            return;
        }
    }
}
