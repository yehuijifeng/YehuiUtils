package com.yehui.utils.db.base;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.yehui.utils.db.DBOrmLiteDemoTowHelper;

/**
 * Created by yehuijifeng
 * on 2016/1/6.
 * 数据库dao方法的代理类
 */
public abstract class DBBaseDao {

    /**
     * 条件查询QueryBuilder的使用：
     * 1、简单的where 条件
     * articleDaoOpe.queryBuilder().where().eq("user_id", userId).query();直接返回Article的列表
     *
     * 2,where and
     * QueryBuilder<Article, Integer> queryBuilder = articleDaoOpe
     .queryBuilder();
     Where<Article, Integer> where = queryBuilder.where();
     where.eq("user_id", 1);
     where.and();
     where.eq("name", "xxx");

     //或者
     articleDaoOpe.queryBuilder().//
     where().//
     eq("user_id", 1).and().//
     eq("name", "xxx");
     *
     *
     *上述两种都相当与：select * from tb_article where user_id = 1 and name = 'xxx' ;
     *
     *
     *3、更复杂的查询
     *
     * where.or(
     * where.and(
     * where.eq("user_id", 1),
     * where.eq("name", "xxx")),
     * where.and(where.eq("user_id", 2),
     * where.eq("name", "yyy")));
     *
     * 相当于：select * from tb_article where ( user_id = 1 and name = 'xxx' )  or ( user_id = 2 and name = 'yyy' )  ;
     *
     *
     * 4，事务操作
     TransactionManager.callInTransaction(helper.getConnectionSource(),
     new Callable<Void>()
     {

     @Override
     public Void call() throws Exception
     {
     return null;
     }
     });
     */
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
