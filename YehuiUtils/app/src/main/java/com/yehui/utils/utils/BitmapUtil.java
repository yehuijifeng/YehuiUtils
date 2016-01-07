package com.yehui.utils.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Yehuijifeng
 * on 2015/10/27.
 * 关于bitmap的工具类
 */
public class BitmapUtil {

    /**
     * 谨慎使用!
     * 压缩图片，制定宽高，原图片会被压缩
     */
    public static void compressImageFile(String filePath, int width, int height) {
        try {
            BitmapUtil.saveBitmapByFile(BitmapUtil.decodeSampledBitmapFromFile(filePath, width, height), filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算图片比率大小
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math
                    .round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 从资源获取图片并压缩大小
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res,
                                                         int resId, int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 从流获取图片并压缩大小
     */
    public static Bitmap decodeSampledBitmapFromStream(InputStream is,
                                                       int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeStream(is, null, options);
    }

    /**
     * 从sd卡取图片并压缩大小变成bimap
     */
    public static Bitmap decodeSampledBitmapFromFile(String pathName,
                                                     int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeFile(pathName, options);
    }

    /**
     * 保存bitmap到本地，默认保存png格式
     */
    public static boolean saveBitmapByFile(Bitmap bitmap, String savePath) {

        if (bitmap == null) return false;
        try {
            File f = new File(savePath);
            if (f.exists()) f.delete();
            FileOutputStream fos = new FileOutputStream(f);
            f.createNewFile();
            // 把Bitmap对象解析成流
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 按照定义格式保存
     *
     * @param bitmap
     * @param savePath
     * @param imageType
     * @return
     */
    public static boolean saveBitmapByFile(Bitmap bitmap, String savePath, Bitmap.CompressFormat imageType) {

        if (bitmap == null) return false;
        try {
            File f = new File(savePath);
            if (f.exists()) f.delete();
            FileOutputStream fos = new FileOutputStream(f);
            f.createNewFile();
            // 把Bitmap对象解析成流
            bitmap.compress(imageType, 100, fos);
            fos.flush();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 添加图片到sd卡并规定压缩比例，100默认原图
     */
    public static void saveBitmap(Bitmap bitmap, String savePath, int quality) {
        if (bitmap == null)
            return;
        try {
            File f = new File(savePath);
            if (f.exists()) f.delete();
            FileOutputStream fos = new FileOutputStream(f);
            f.createNewFile();
            // 把Bitmap对象解析成流
            bitmap.compress(Bitmap.CompressFormat.PNG, quality, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 放大缩小图片
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();

        float scaleWidht = ((float) w / width);
        float scaleHeight = ((float) h / height);

        matrix.postScale(scaleWidht, scaleHeight);

        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);

        return newbmp;

    }

    /**
     * 将图片按自己的要求缩放
     */
    public static Bitmap zoomByImageID(Context context, int imageID, int newWidth, int newHeight) {
        // 图片源,raw文件夹下的图片，不会被转成二进制
        Bitmap bm2 = BitmapFactory.decodeStream(context.getResources().openRawResource(imageID));

        //从资源中获取Bitmap
        Resources res = context.getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, imageID);

        // 获得图片的宽高
        int width = bm2.getWidth();
        int height = bm2.getHeight();

        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm2, 0, 0, width, height, matrix, true);

        return newbm;
    }


}