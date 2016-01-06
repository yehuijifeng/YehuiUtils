package com.yehui.utils.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * Created by yehuijifeng
 * on 2016/1/5.
 * 测试外键实体类
 */
@DatabaseTable(tableName = "tab_tow_demo")
public class OrmLiteDemoTowBean implements Parcelable {
    public OrmLiteDemoTowBean(){}

    @DatabaseField(generatedId = true)
    private int test_id;

    @DatabaseField(columnName = "test_name")
    private String test_name;

//    @DatabaseField(columnName = "test_list")
//    private List<String> test_list;

    public OrmLiteDemoTowBean(int test_id, String test_name) {
        this.test_id = test_id;
        this.test_name = test_name;
    }

    public OrmLiteDemoTowBean(String test_name) {
        this.test_name = test_name;
    }

    protected OrmLiteDemoTowBean(Parcel in) {
        test_id = in.readInt();
        test_name = in.readString();
    }

    public static final Creator<OrmLiteDemoTowBean> CREATOR = new Creator<OrmLiteDemoTowBean>() {
        @Override
        public OrmLiteDemoTowBean createFromParcel(Parcel in) {
            return new OrmLiteDemoTowBean(in);
        }

        @Override
        public OrmLiteDemoTowBean[] newArray(int size) {
            return new OrmLiteDemoTowBean[size];
        }
    };

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(test_id);
        dest.writeString(test_name);
    }
}
