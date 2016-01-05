package org.sqlite.utils.api;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * @Annotation 对象与数据库行互相转化
 */
public interface Convertible {
    //从Cursor中导出值
    boolean dumpCursor(Cursor cursor);

    //将对象转化为ContentValues
    ContentValues toContentValues();
}