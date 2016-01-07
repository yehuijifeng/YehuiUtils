package com.yehui.utils.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * 2016年1月7日13:11:05
 * fragment添加到activity的utils
 * 该工具类的作用在于：
 * 一个activity
 */
public class FragmentUtils {

    public static void finish(Activity activity) {
        activity.finish();
    }

    /**fragment事务，添加frgmanet
     * 参数说明：
     * 1，fragment所在的父activity
     * 2，fragment的实例
     * 3，fragment需要放在某个视图中的资源id
     * */
    public static FragmentTransaction addFragment(FragmentActivity activity,
                                                  Fragment fragment, int resource) {
        FragmentTransaction mFragmentTransaction = activity
                .getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(resource, fragment);
        mFragmentTransaction.commit();
        return mFragmentTransaction;
    }

    //fragment事务，替换frgmanet
    public static FragmentTransaction replaceFragment(
            FragmentActivity activity, Fragment fragment, int resource) {
        FragmentTransaction mFragmentTransaction = activity
                .getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(resource, fragment);
        mFragmentTransaction.commit();
        return mFragmentTransaction;
    }

}