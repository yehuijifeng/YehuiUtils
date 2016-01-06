package com.yehui.utils.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by yehuijifeng
 * on 2016/1/6.
 */
@DatabaseTable(tableName = "tab_three_demo")
public class OrmLiteDemoThreeBean {
    public OrmLiteDemoThreeBean() {
    }

    @DatabaseField(generatedId = true)
    private int test_id;

    @DatabaseField(columnName = "test_float")
    private float test_float;

    public OrmLiteDemoThreeBean(float test_float) {
        this.test_float = test_float;
    }

    public OrmLiteDemoThreeBean(int test_id, float test_float) {
        this.test_id = test_id;
        this.test_float = test_float;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public float getTest_float() {
        return test_float;
    }

    public void setTest_float(float test_float) {
        this.test_float = test_float;
    }
}
