package org.sqlite.utils.model;

import android.content.ContentValues;
import android.database.Cursor;

import org.sqlite.utils.annotations.AutoInc;
import org.sqlite.utils.annotations.DBIgnore;
import org.sqlite.utils.api.Convertible;
import org.sqlite.utils.core.Logging;

import java.lang.reflect.Field;

/**
 * @Annotation 可以与数据库行互相转化的对象
 * @Warning 继承这个类的对象一定要保留一个空的构造函数!!!
 */
public class ConvertibleBean implements Convertible {

    @Override
    public boolean dumpCursor(Cursor cursor) {

        if (cursor.isBeforeFirst()) {
            cursor.moveToFirst();
        }
        Field[] arrField = this.getClass().getFields();
        for (Field f : arrField) {
            if (!f.isAnnotationPresent(DBIgnore.class)) {
                try {
                    String columnName = f.getName();
                    int columnIdx = cursor.getColumnIndex(columnName);
                    if (columnIdx != -1) {
                        if (f.isAccessible()) {
                            f.setAccessible(true);
                        }
                        Class<?> type = f.getType();
                        if (type == byte.class) {
                            f.set(this, (byte) cursor.getShort(columnIdx));
                        } else if (type == short.class || type == Short.class) {
                            f.set(this, cursor.getShort(columnIdx));
                        } else if (type == int.class || type == Integer.class) {
                            f.set(this, cursor.getInt(columnIdx));
                        } else if (type == long.class || type == Long.class) {
                            f.set(this, cursor.getLong(columnIdx));
                        } else if (type == String.class) {
                            f.set(this, cursor.getString(columnIdx));
                        } else if (type == byte[].class || type == Byte[].class) {
                            f.set(this, cursor.getBlob(columnIdx));
                        } else if (type == boolean.class || type == Boolean.class) {
                            f.set(this, cursor.getInt(columnIdx) == 1);
                        } else if (type == float.class || type == Float.class) {
                            f.set(this, cursor.getFloat(columnIdx));
                        } else if (type == double.class || type == Double.class) {
                            f.set(this, cursor.getDouble(columnIdx));
                        }
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    Logging.e(e);
                }
            }
        }
        return true;
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();

        Field[] arrField = this.getClass().getFields();
        for (Field f : arrField) {
            try {
                if (!f.isAnnotationPresent(DBIgnore.class)
                        && !f.isAnnotationPresent(AutoInc.class)) {
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    String name = f.getName();
                    Object value = f.get(this);
                    if (value == null) {
                        continue;
                    }
                    if (value instanceof Byte) {
                        cv.put(name, (Byte) value);
                    } else if (value instanceof Short) {
                        cv.put(name, (Short) value);
                    } else if (value instanceof Integer) {
                        cv.put(name, (Integer) value);
                    } else if (value instanceof Long) {
                        cv.put(name, (Long) value);
                    } else if (value instanceof String) {
                        cv.put(name, (String) value);
                    } else if (value instanceof byte[]) {
                        cv.put(name, (byte[]) value);
                    } else if (value instanceof Boolean) {
                        cv.put(name, (Boolean) value);
                    } else if (value instanceof Float) {
                        cv.put(name, (Float) value);
                    } else if (value instanceof Double) {
                        cv.put(name, (Double) value);
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                Logging.e(e);
            }
        }
        return cv;
    }

}