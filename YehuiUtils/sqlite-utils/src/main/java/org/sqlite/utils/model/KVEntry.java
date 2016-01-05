package org.sqlite.utils.model;

import org.sqlite.utils.annotations.AutoInc;
import org.sqlite.utils.annotations.NotNull;
import org.sqlite.utils.annotations.PrimaryKey;

/***
 * KV table,
 * 使用类类型建表的基本要求:
 * 1. 继承ConvertibleBean
 * 2. 保留一个空的构造函数
 */
public class KVEntry extends ConvertibleBean {

    @PrimaryKey @NotNull @AutoInc int _id;
    @NotNull public String uid;             //用户id
    @NotNull public int key;                //键
    @NotNull public String value;           //值

    public KVEntry() {
    }

    public KVEntry(String uid, int key, String value) {
        this.uid = uid;
        this.key = key;
        this.value = value == null ? "" : value;
    }
}