package com.yehui.utils.utils.FileUtil;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yehuijifeng
 * on 2016/1/5.
 * 本地文件的操作工具类
 */
public class FileOperationUtil {

    /**
     * 是否存在该文件
     *
     * @param path
     * @return
     */
    public static boolean isHaveFile(String path) {

        File file = new File(path);
        if (file.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 文件详情
     */
    public static FileBean queryFileByDetails(File f) {
        FileBean fileBean = new FileBean();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        fileBean.setIsDirectory(f.isDirectory());
        fileBean.setFileName(f.getName());
        fileBean.setFilePath(f.getPath());
        if (!f.isDirectory()) {
            fileBean.setFileLength(f.length());
            if (((double) f.length() / 1024 / 1024) <= 1) {
                fileBean.setFileSize(decimalFormat.format(f.length() / 1024) + "KB");
            } else if (((double) f.length() / 1024 / 1024 / 1024) <= 1) {
                fileBean.setFileSize(decimalFormat.format(f.length() / 1024 / 1024) + "MB");
            } else if (((double) f.length() / 1024 / 1024 / 1024 / 1024) <= 1) {
                fileBean.setFileSize(decimalFormat.format(f.length() / 1024 / 1024 / 1024) + "GB");
            } else if (((double) f.length() / 1024 / 1024 / 1024 / 1024 / 1024) <= 1) {
                fileBean.setFileSize(decimalFormat.format(f.length() / 1024 / 1024 / 1024 / 1024) + "TB");
            }
        } else
            fileBean.setFileSize("");
        return fileBean;
    }

    private static ExecutorService threadPoolFind;//线程池
    private static List<FileBean> sdFileListFind;//文件集合


    /**
     * 外部调用，获得本机所有的文件集合
     */
    public static List<FileBean> querySDFileByAll() {
        queryFileByAll(FileContact.SD_FILE);
        return sdFileListFind;
    }

    /**
     * 查询本机sd卡所有文件夹，内部自己调用的方法，不需要外部调用
     */
    private static void queryFileByAll(File filePath) {
        if (filePath == FileContact.SD_FILE) {
            sdFileListFind = new ArrayList<>();
            threadPoolFind = Executors.newFixedThreadPool(5);
        }
        File[] files = filePath.listFiles();
        if (files != null) {
            for (final File f : files) {
                if (f.isFile()) {
                    FileBean fileBean = queryFileByDetails(f);
                    sdFileListFind.add(fileBean);
                } else {
                    threadPoolFind.execute(new Runnable() {
                        @Override
                        public void run() {
                            queryFileByDetails(f);
                        }
                    });
                }
            }
        }
    }

    private static ExecutorService threadPoolSerch;//线程池
    private static List<FileBean> sdFileListSerch;//文件集合

    /**
     * 外部调用，获得本机搜索出来的文件
     */
    public static List<FileBean> querySDFileByName(String name) {
        queryFileByName(FileContact.SD_FILE, name);
        return sdFileListSerch;
    }

    /**
     * 根据关键词查询本地文件
     */
    private static void queryFileByName(File filePath, final String name) {
        if (filePath == FileContact.SD_FILE) {
            sdFileListSerch = new ArrayList<>();
            threadPoolSerch = Executors.newFixedThreadPool(5);
        }
        File[] files = filePath.listFiles();
        if (files != null) {
            for (final File f : files) {
                if (f.isFile()) {
                    if (f.getName().contains(name)) {
                        FileBean fileBean = queryFileByDetails(f);
                        sdFileListSerch.add(fileBean);
                    }
                } else {
                    threadPoolFind.execute(new Runnable() {
                        @Override
                        public void run() {
                            queryFileByName(f, name);
                        }
                    });
                }
            }

        }
    }

    /**
     * 查询文件类型
     * 0,文件夹；
     * 1，图片
     * 2，音乐
     * 3，视频
     * 4，文档
     * 5，表格
     * 6，ppt
     * 7，apk
     * 8，记事本
     * 9，压缩文件
     * 10，其他
     */
    public static int queryFileType(File f) {
        if (f.isDirectory()) {
            return 0;
        } else {
            String fileName = f.getName();
            String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String image[] = FileType.getFileType().imageFileType();
            for (int ii = 0; ii < image.length; ii++) {
                if (prefix.equalsIgnoreCase(image[ii])) {
                    return 1;
                }
            }
            String music[] = FileType.getFileType().musicFileType();
            for (int ii = 0; ii < music.length; ii++) {
                if (prefix.equalsIgnoreCase(music[ii])) {
                    return 2;
                }
            }
            String video[] = FileType.getFileType().videoFileType();
            for (int ii = 0; ii < video.length; ii++) {
                if (prefix.equalsIgnoreCase(video[ii])) {
                    return 3;
                }
            }
            String word[] = FileType.getFileType().wordFileType();
            for (int ii = 0; ii < word.length; ii++) {
                if (prefix.equalsIgnoreCase(word[ii])) {
                    return 4;
                }
            }
            String excel[] = FileType.getFileType().excelFileType();
            for (int ii = 0; ii < excel.length; ii++) {
                if (prefix.equalsIgnoreCase(excel[ii])) {
                    return 5;
                }
            }
            String ppt[] = FileType.getFileType().pptFileType();
            for (int ii = 0; ii < ppt.length; ii++) {
                if (prefix.equalsIgnoreCase(ppt[ii])) {
                    return 6;
                }
            }
            String apk[] = FileType.getFileType().apkFileType();
            for (int ii = 0; ii < apk.length; ii++) {
                if (prefix.equalsIgnoreCase(apk[ii])) {
                    return 7;
                }
            }
            String txt[] = FileType.getFileType().txtFileType();
            for (int ii = 0; ii < txt.length; ii++) {
                if (prefix.equalsIgnoreCase(txt[ii])) {
                    return 7;
                }
            }
            String zip[] = FileType.getFileType().zipFileType();
            for (int ii = 0; ii < zip.length; ii++) {
                if (prefix.equalsIgnoreCase(zip[ii])) {
                    return 9;
                }
            }
            return 10;
        }
    }
}