package org.sqlite.utils.core;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import org.sqlite.utils.annotations.AutoInc;
import org.sqlite.utils.annotations.DBIgnore;
import org.sqlite.utils.annotations.DefaultNull;
import org.sqlite.utils.annotations.NotNull;
import org.sqlite.utils.annotations.PrimaryKey;
import org.sqlite.utils.api.Convertible;
import org.sqlite.utils.model.KVEntry;

import java.lang.reflect.Field;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/***
 * 通用数据库封装类
 */
public class CommonDBHelper extends SQLiteOpenHelper {


    public static final Map<Class<?>, String> TYPES;

    static {
        TYPES = new HashMap<>();
        TYPES.put(byte.class, "BYTE");
        TYPES.put(boolean.class, "INTEGER");
        TYPES.put(short.class, "SHORT");
        TYPES.put(int.class, "INTEGER");
        TYPES.put(long.class, "LONG");
        TYPES.put(String.class, "TEXT");
        TYPES.put(byte[].class, "BLOB");
        TYPES.put(float.class, "FLOAT");
        TYPES.put(double.class, "DOUBLE");
    }

    private Collection<Class<? extends Convertible>> tables;
    private Context mContext;

    /***
     * 构造函数
     *
     * @param context 生命周期Context
     * @param tables  建表列表
     * @param VERSION 数据库版本
     */
    public CommonDBHelper(Context context, Collection<Class<? extends Convertible>> tables,
                          int VERSION) {
        super(context, context.getPackageName(), null, VERSION);
        this.tables = tables;
        mContext = context;
        Collections.addAll(this.tables, KVEntry.class);
        Logging.i("DB is not exist.");
        SQLiteDatabase db = getWritableDatabase();
        db.close();
        Logging.i("Initiate finished!!!");
    }

    /***
     * 类所对应的存储表名
     *
     * @param clazz 类
     * @return 表名称
     */
    public String getTableName(Class<? extends Convertible> clazz) {
        return clazz.getSimpleName();
    }


    /**************************************
     * 初始化部分
     **************************************/

    @Override
    public void onCreate(SQLiteDatabase db) {
        Logging.i("onCreate");
        if (tables == null) {
            Logging.i("Database initiate with null tables");
        } else {
            for (Class<? extends Convertible> clazz : tables) {
                String sqlTableBuilding = getTableBuildingSQL(clazz);
                Logging.i(sqlTableBuilding);
                db.execSQL(sqlTableBuilding);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logging.i("onUpgrade");
        if (tables == null) {
            Logging.i("Database initiate with null tables");
        } else {
            db.execSQL("drop table if exists " + getTableName(KVEntry.class));
            for (Class<? extends Convertible> clazz : tables) {
                db.execSQL("drop table if exists " + getTableName(clazz));
                Logging.i("drop table if exists " + getTableName(clazz));
                String sqlTableBuilding = getTableBuildingSQL(clazz);
                Logging.i(sqlTableBuilding);
                db.execSQL(sqlTableBuilding);
            }
        }
    }

    /**************************************
     * 数据库操作部分
     **************************************/

    /***
     * 插入对象
     *
     * @param db   SQLite数据库
     * @param bean 插入对象
     * @return 插入结果标志
     */
    public int insert(SQLiteDatabase db, Convertible bean) {
        if (bean == null) {
            return -1;
        }
        return (int) db.insert(getTableName(bean.getClass()), null, bean.toContentValues());
    }

    /***
     * 单一对象查询
     *
     * @param db    SQLite数据库
     * @param clazz 查询表
     * @return 查询结果对象
     */
    public Convertible queryOne(SQLiteDatabase db, Class<? extends Convertible> clazz) {
        Cursor cursor = db.rawQuery("select * from " + getTableName(clazz), null);
        if (cursor.moveToNext()) {
            Convertible convertible = cursorQuery(clazz, cursor);
            cursor.close();
            return convertible;
        }
        return null;
    }

    /***
     * 单一对象查询
     *
     * @param db       SQLite数据库
     * @param clazz    查询表
     * @param sqlWhere 查询条件
     * @return 查询结果对象
     */
    public Convertible queryOne(SQLiteDatabase db, Class<? extends Convertible> clazz,
                                String sqlWhere) {
        Cursor cursor = db.rawQuery("select * from " + getTableName(clazz) + " where " +
                sqlWhere, null);
        if (cursor.moveToNext()) {
            Convertible convertible = cursorQuery(clazz, cursor);
            cursor.close();
            return convertible;
        }
        return null;
    }

    /***
     * 单一对象查询
     *
     * @param db            SQLite数据库
     * @param clazz         查询表
     * @param whereClause   条件模板
     * @param selectionArgs 条件参数
     * @return 查询结果对象
     */
    public Convertible queryOne(SQLiteDatabase db, Class<? extends Convertible> clazz,
                                String whereClause, String[] selectionArgs) {
        Cursor cursor = db.rawQuery("select * from " + getTableName(clazz) +
                " where " + whereClause, selectionArgs);
        if (cursor.moveToNext()) {
            Convertible convertible = cursorQuery(clazz, cursor);
            cursor.close();
            return convertible;
        }
        return null;
    }


    /***
     * 全部对象查询
     *
     * @param db    SQLite数据库
     * @param clazz 查询表
     * @return 查询结果对象列表
     */
    public List<Convertible> queryAll(SQLiteDatabase db, Class<? extends Convertible> clazz) {
        List<Convertible> convertibleList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + getTableName(clazz), null);
        while (cursor.moveToNext()) {
            Convertible convertible = cursorQuery(clazz, cursor);
            if (convertible != null) {
                convertibleList.add(convertible);
            }
        }
        cursor.close();
        return convertibleList;
    }

    /***
     * 全部对象查询
     *
     * @param db       SQLite数据库
     * @param clazz    查询表
     * @param sqlWhere 查询条件
     * @return 查询结果对象列表
     */
    public List<Convertible> queryAll(SQLiteDatabase db, Class<? extends Convertible> clazz,
                                      String sqlWhere) {
        List<Convertible> convertibleList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + getTableName(clazz) +
                " where " + sqlWhere, null);
        while (cursor.moveToNext()) {
            Convertible convertible = cursorQuery(clazz, cursor);
            if (convertible != null) {
                convertibleList.add(convertible);
            }
        }
        cursor.close();
        return convertibleList;
    }

    /***
     * 全部对象查询
     *
     * @param db            SQLite数据库
     * @param clazz         查询表
     * @param whereClause   查询条件模板
     * @param selectionArgs 条件参数
     * @return 查询结果对象列表
     */
    public List<Convertible> queryAll(SQLiteDatabase db, Class<? extends Convertible> clazz,
                                      String whereClause, String[] selectionArgs) {
        List<Convertible> convertibleList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + getTableName(clazz) +
                " where " + whereClause, selectionArgs);
        while (cursor.moveToNext()) {
            Convertible convertible = cursorQuery(clazz, cursor);
            if (convertible != null) {
                convertibleList.add(convertible);
            }
        }
        cursor.close();
        return convertibleList;
    }

    /***
     * 更新对象
     *
     * @param db       SQLite数据库
     * @param bean     更新对象
     * @param sqlWhere 更新条件
     * @return 更新行数量
     */
    public int update(SQLiteDatabase db, Convertible bean, String sqlWhere) {
        if (bean == null) {
            return -1;
        }
        return db.update(getTableName(bean.getClass()), bean.toContentValues(), sqlWhere, null);
    }

    /***
     * 更新对象
     *
     * @param db            SQLite数据库
     * @param bean          更新对象
     * @param whereClause   更新条件模板
     * @param selectionArgs 条件参数
     * @return 更新行数量
     */
    public int update(SQLiteDatabase db, Convertible bean, String whereClause,
                      String[] selectionArgs) {
        if (bean == null) {
            return -1;
        }
        return db.update(getTableName(bean.getClass()), bean.toContentValues(),
                whereClause, selectionArgs);
    }

    /***
     * 删除对象
     *
     * @param db    SQLite数据库
     * @param clazz 删除对象
     * @return 删除行数量
     */
    public int delete(SQLiteDatabase db, Class<? extends Convertible> clazz) {
        return db.delete(getTableName(clazz), null, null);
    }

    /***
     * 删除对象
     *
     * @param db       SQLite数据库
     * @param clazz    删除对象
     * @param sqlWhere 删除对象条件
     * @return 删除行数量
     */
    public int delete(SQLiteDatabase db, Class<? extends Convertible> clazz, String sqlWhere) {
        return db.delete(getTableName(clazz), sqlWhere, null);
    }

    /***
     * 删除对象
     *
     * @param db            SQLite数据库
     * @param clazz         删除对象
     * @param whereClause   删除对象条件模板
     * @param selectionArgs 条件参数
     * @return 删除行数量
     */
    public int delete(SQLiteDatabase db, Class<? extends Convertible> clazz, String whereClause,
                      String[] selectionArgs) {
        return db.delete(getTableName(clazz), whereClause, selectionArgs);
    }

    /***
     * 键值对操作
     */
    public String get(SQLiteDatabase db, String uid, int key) {
        Convertible result = queryOne(db, KVEntry.class, "uid=? and key=?",
                new String[]{uid, String.valueOf(key)});
        if (result == null) {
            return null;
        }
        return decryptField(((KVEntry) result).value);
    }

    public void set(SQLiteDatabase db, String uid, int key, String value) {
        if (value == null) {
            Logging.e("SaveKey fail for exist null obj");
            return;
        }
        KVEntry tab = new KVEntry(uid, key, encryptField(value));
        db.delete(getTableName(KVEntry.class), "uid=? and key=?",
                new String[]{uid, key + ""});
        insert(db, tab);
    }

    public void remove(SQLiteDatabase db, String uid, int key) {
        db.delete(getTableName(KVEntry.class), "uid=? and key=?",
                new String[]{uid, key + ""});
    }

    public void removeAll(SQLiteDatabase db, String uid) {
        db.delete(getTableName(KVEntry.class), "uid=? and _id>?",
                new String[]{uid, "-1"});
    }

    /**************************************
     * 私有部分
     **************************************/

    /***
     * 基本查询
     *
     * @param clazz  查询类
     * @param cursor 查询Cursor
     * @return 查询结果
     */
    private Convertible cursorQuery(Class<? extends Convertible> clazz, Cursor cursor) {
        Convertible convertible = null;
        try {
            convertible = clazz.newInstance();
            convertible.dumpCursor(cursor);
        } catch (Exception e) {
            Logging.e(e);
        }
        return convertible;
    }

    /***
     * 根据类结构构造表。
     *
     * @param clazz 建表类
     * @return 建表SQL语句
     */
    protected String getTableBuildingSQL(Class<? extends Convertible> clazz) {
        StringBuilder sqlBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(getTableName(clazz))
                .append("(\n");
        Field[] fields = clazz.getFields();
        for (int i = fields.length - 1; i >= 0; i--) {
            Field f = fields[i];
            String type = TYPES.get(f.getType());
            if (type != null && !f.isAnnotationPresent(DBIgnore.class)) {
                sqlBuilder.append("`").append(f.getName()).append("`")
                        .append(" ").append(type);
                if (f.isAnnotationPresent(NotNull.class)) {
                    sqlBuilder.append(NotNull.value);
                } else if (f.isAnnotationPresent(DefaultNull.class)) {
                    sqlBuilder.append(DefaultNull.value);
                }
                if (f.isAnnotationPresent(PrimaryKey.class)) {
                    sqlBuilder.append(PrimaryKey.value);
                }
                if (f.isAnnotationPresent(AutoInc.class)) {
                    sqlBuilder.append(AutoInc.value);
                }
                sqlBuilder.append(",\n");
            }
        }
        if (sqlBuilder.toString().endsWith(",\n")) {
            sqlBuilder.setLength(sqlBuilder.length() - ",\n".length());
        }
        return sqlBuilder.append("\n)").toString();
    }

    /***
     * encrypt/decrypt block
     */
    private String decryptField(String field) {
        if (field == null) {
            return null;
        }
        byte[] raw = BinAscii.unhexlify(field);
        if (raw == null) return null;
        byte[] content = Crypto.newInstance(mContext).decrypt(raw);
        if (content == null) return null;
        return new String(content);
    }

    private String encryptField(String field) {
        if (field == null) {
            return null;
        }
        byte[] content = Crypto.newInstance(mContext).encrypt(field.getBytes());
        if (content == null) return null;
        return BinAscii.hexlify(content);
    }
}