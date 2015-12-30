package com.yehui.utils.utils;

/**
 * Created by yehuijifeng
 * on 2015/12/22.
 * 判断空指针的工具类
 */
public class EmptyUtil {

    public static boolean isStringEmpty(String str){
        if(str==null||("").equals(str))return true;
        return false;
    }
}
