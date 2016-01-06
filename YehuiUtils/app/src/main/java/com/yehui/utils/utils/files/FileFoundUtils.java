package com.yehui.utils.utils.files;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by yehuijifeng
 * on 2016/1/5.
 * 创建文件工具类，复制粘贴等
 */
public class FileFoundUtils {
    /**
     * 删除文件
     * 递归删除
     */
    public static void deleteFile(File file) {
        if (file.isFile())
            file.delete();
        else {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                file.delete();
                return;
            }
            if (files != null) {
                for (final File f : files) {
                    if (f.isFile()) {
                        file.delete();
                    } else {
                        deleteFile(f);
                    }
                }
            }
        }
    }

    /**
     * 创建文件夹规则说明
     * 1，参数1，文件类型，0——文件夹，1——文件
     * 2，参数2，需要创建的文件路径
     * 3，参数3，如果该文件存在，是否替换
     * 4，返回参数：0，失败；1，成功；2，文件已存在；
     */
    public static int createFile(int fileType, String filePath, boolean bl) {
        File file = new File(filePath);
        if (fileType == 0) {
            if (!file.exists()) {
                try {
                    file.mkdirs();
                    return 1;
                } catch (Exception e) {
                    return 0;
                }
            } else {
                if (bl) {
                    deleteFile(file);
                    try {
                        file.mkdirs();
                        return 1;
                    } catch (Exception e) {
                        return 0;
                    }
                } else
                    return 2;
            }
        } else {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    return 1;
                } catch (Exception e) {
                    return 0;
                }
            } else {
                if (bl) {
                    deleteFile(file);
                    try {
                        file.createNewFile();
                        return 1;
                    } catch (Exception e) {
                        return 0;
                    }
                } else
                    return 2;
            }
        }
    }


    /**
     * 复制
     */
    public boolean copyFile(File toFile, String filePath) {
        try {
            if (toFile != null && toFile.exists()) {
                // 另外还需要一个输出流来将复制的内容写入到文件中去
                //File toFile = new File(filePath);

                // 复制：先读取，然后写入
                FileInputStream fis = new FileInputStream(toFile);

                // 为了效率高使用Buffered带缓冲区的输入流去包装fis
                BufferedInputStream buf = new BufferedInputStream(fis);

                File paste = new File(filePath + "/" + toFile.getName());
                if (!paste.exists()) {
                    paste.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(paste);
                BufferedOutputStream bufO = new BufferedOutputStream(fos);

                byte[] b = new byte[1024];

                int len;
                while ((len = buf.read(b)) != -1) {
                    // 写入
                    bufO.write(b, 0, len);
                }
                bufO.flush();
                buf.close();
                bufO.close();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 剪切
     */
    public boolean shearFile(File toFile, String filePath) {
        if (copyFile(toFile, filePath)) {
            toFile.delete();
            return true;
        }
        return false;
    }


}
