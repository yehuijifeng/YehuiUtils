package com.yehui.utils.bean.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by yehuijifeng
 * on 2016/1/23.
 * 存放用户信息的实体类
 */
@DatabaseTable(tableName = "tab_userinfo")
public class UserInfoBean extends BaseDaoEnabled<UserInfoBean, Integer> implements Parcelable {

    @DatabaseField(generatedId = true)
    private String user_id;
    @DatabaseField(columnName = "user_name")
    private String user_name;
    @DatabaseField(columnName = "user_pwd")
    private String user_pwd;
    @DatabaseField(columnName = "user_image")
    private String user_image;

    protected UserInfoBean(Parcel in) {
        user_id = in.readString();
        user_name = in.readString();
        user_pwd = in.readString();
        user_image = in.readString();
    }

    public static final Creator<UserInfoBean> CREATOR = new Creator<UserInfoBean>() {
        @Override
        public UserInfoBean createFromParcel(Parcel in) {
            return new UserInfoBean(in);
        }

        @Override
        public UserInfoBean[] newArray(int size) {
            return new UserInfoBean[size];
        }
    };

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public UserInfoBean(String user_name, String user_pwd, String user_image) {
        this.user_name = user_name;
        this.user_pwd = user_pwd;
        this.user_image = user_image;
    }

    public UserInfoBean() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user_id);
        dest.writeString(user_name);
        dest.writeString(user_pwd);
        dest.writeString(user_image);
    }
}
