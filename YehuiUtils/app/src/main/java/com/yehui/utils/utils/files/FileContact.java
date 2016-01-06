package com.yehui.utils.utils.files;

import android.os.Environment;

import java.io.File;

/**
 * Created by yehuijifeng
 * on 2016/1/5.
 */
public class FileContact {
    /**
     * sd卡根目录
     */
    public static File SD_FILE = Environment.getExternalStorageDirectory();

    /**
     * sd卡根目录路径
     */
    public static String SD_PATH = Environment.getExternalStorageDirectory().getPath();

    /**
     * app根目录
     */
    public final static String YEHUI_PATH = SD_PATH + "/YehuiUtils/";

    /**
     * app图片缓存路径
     */
    public final static String YEHUI_CACHE_IMG_PATH = YEHUI_PATH + "CacheImage/";

    /**
     * app日志存放路径
     */
    public final static String YEHUI_LOG_PATH = YEHUI_PATH + "Log/";

    /**
     * app配置文件存放路径
     */
    public final static String YEHUI_SETTINGS_PATH = YEHUI_PATH + "Settings/";


}
