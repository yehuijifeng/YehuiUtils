package com.yehui.utils.application;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.yehui.utils.easemob.InitEasemob;
import com.yehui.utils.jpush.JPushInterfaces;
import com.yehui.utils.utils.LogUtil;
import com.yehui.utils.utils.files.FileContact;
import com.yehui.utils.utils.imageloader.ImageOptions;

import java.io.File;


/**
 * Created by yehuijifeng
 * on 2015/11/26.
 * <p>
 * 夜辉宝典
 * 2015年10月27日
 * android-studio 1.51
 * 5.1sdk
 * xUtils
 * Volly
 * image-loader
 * EventBus
 * gson
 * achartengine
 * GifView
 */
public class YehuiApplication extends Application {

    //日志统一管理
    public static final String TAG = "YehuiUtils";
    //取得默认image的配置类
    public static DisplayImageOptions defaultOptions = ImageOptions.defaultOptions();
    //取得圆形image的配置类
    public static DisplayImageOptions roundOptions = ImageOptions.roundOptions();

    /**
     * login user name
     */
    public final String PREF_USERNAME = "username";

    /**
     * 当前用户nickname,为了苹果推送不是userid而是昵称
     */
    public static String currentUserNick = "";

    @Override
    public void onCreate() {
        super.onCreate();
        //创建本地项目文件夹
        FileContact.createSaveImage();
        FileContact.createFiles();
        FileContact.createLog();
        FileContact.createCacheImage();
        FileContact.createSettigns();

        /**初始化环信*/
        InitEasemob.getInstance(this);
        JPushInterfaces.setJPushDebugMode(true);// 设置开启日志,发布时请关闭日志
        JPushInterfaces.initJPush(this);// 初始化 JPush
        /**
         * 全局捕获异常的代理类
         */
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        /**
         * 初始化imageloader
         */
        initImageLoader(this);
        /**
         * 打印日志
         */
        isLog(true);
    }

    /**
     * 设置打印日志
     */
    private void isLog(boolean bl) {
        LogUtil.model = bl;
    }

    /**
     * 加载图片配置信息
     */
    private void initImageLoader(Context context) {
        /**
         * 1.完成ImageLoaderConfiguration的配置
         */

        //默认配置
        //ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);

        //自定义配置
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .memoryCacheExtraOptions(800, 800)//额外的内存缓存选项, 即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)//线程池的并发数大小
                .threadPriority(Thread.NORM_PRIORITY - 1)//线程池的优先级，标准优先级
                .tasksProcessingOrder(QueueProcessingType.FIFO)//任务处理订单，队列处理类型.FIFO:先进先出
                .denyCacheImageMultipleSizesInMemory()//缓存显示不同大小的同一张图片
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))//最低内存缓存
                .memoryCacheSize(2 * 1024 * 1024)//内存缓存大小，2M
                .memoryCacheSizePercentage(13)//内存缓存百分比
                .diskCacheFileCount(100) //缓存的文件数量
                .diskCache(new UnlimitedDiskCache(new File(FileContact.YEHUI_CACHE_IMG_PATH)))//自定义缓存图片地址
                .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .imageDownloader(new BaseImageDownloader(context)) //图片下载，当前页面
                .imageDecoder(new BaseImageDecoder(true)) //图片解码
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) //默认图像显示选项：创建简单的
                .writeDebugLogs()//写入debug的log
                .build();

        /**
         *2.单例ImageLoader类的初始化
         */
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);

    }

}
