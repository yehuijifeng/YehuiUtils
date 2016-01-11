package com.yehui.utils.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by yehuijifeng
 * on 2016/1/10.
 * 获取资源文件的工具类
 */
public class ResourcesUtil {
    /**
     * 需要注意的是，来自Resources和Assets 中的文件只可以读取而不能进行写的操作
     * InputStream in 的获取方式：
     * context.getResources().openRawResource(R.raw.test);
     * context.getResources().getAssets().open(fileName);
     */

    /**
     * 读取raw文件夹下的文件
     * @param in incontext.getResources().openRawResource(R.raw.test);
     */
    public static String getFromRaw(InputStream in){
        try {
            InputStreamReader inputReader = new InputStreamReader(in);

            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            String Result="";

            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**直接从assets中获取
     * @param  in context.getResources().getAssets().open(fileName);
     * @return
     */
    public String getFromAssets(InputStream in){
        try {
            InputStreamReader inputReader = new InputStreamReader(in);

            BufferedReader bufReader = new BufferedReader(inputReader);

            String line;
            String Result="";

            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
