package org.sqlite.utils.api;

import android.database.sqlite.SQLiteDatabase;

import org.sqlite.utils.core.CommonDBHelper;

import java.util.List;

/**
 * @Annotation CommonDBHelper 开发接口
 */
public interface CommonDBAction {

    void showLog(boolean enable);

    String getTableName(Class clazz);

    String getTableBuildingSQL(Class clazz);

    int insert(SQLiteDatabase db, Convertible bean);

    Convertible queryOne(SQLiteDatabase db, Class<? extends Convertible> clazz);

    Convertible queryOne(SQLiteDatabase db, Class<? extends Convertible> clazz,
                         String sqlWhere);

    Convertible queryOne(SQLiteDatabase db, Class<? extends Convertible> clazz,
                         String whereClause, String[] selectionArgs);

    List<Convertible> queryAll(SQLiteDatabase db, Class<? extends Convertible> clazz);

    List<Convertible> queryAll(SQLiteDatabase db, Class<? extends Convertible> clazz,
                               String sqlWhere);

    List<Convertible> queryAll(SQLiteDatabase db, Class<? extends Convertible> clazz,
                               String whereClause, String[] selectionArgs);

    int update(SQLiteDatabase db, Convertible bean, String sqlWhere);

    int update(SQLiteDatabase db, Convertible bean, String whereClause,
               String[] selectionArgs);

    int deleteAll(SQLiteDatabase db, Class<? extends Convertible> clazz);

    int deleteAll(SQLiteDatabase db, Class<? extends Convertible> clazz,
                  String sqlWhere);

    int deleteAll(SQLiteDatabase db, Class<? extends Convertible> clazz,
                  String whereClause, String[] selectionArgs);

}
