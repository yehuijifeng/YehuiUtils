package com.yehui.utils.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.utils.ResourcesUtil;
import com.yehui.utils.utils.files.FileBean;
import com.yehui.utils.utils.files.FileContact;
import com.yehui.utils.utils.files.FileFoundUtil;
import com.yehui.utils.utils.files.FileOperationUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by yehuijifeng
 * on 2016/1/16.
 * 本地文件的操作
 */
public class FileActivity extends BaseActivity implements View.OnClickListener {
    private Button
            btn_file_size,
            btn_cache_size,
            btn_create_file,
            btn_insert_file,
            btn_copy_file,
            btn_delete_file,
            btn_query_file;
    private TextView show_file;

    private String filePath, fileName;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_test_files);
    }

    @Override
    protected String setTitleText() {
        return "本地文件操作";
    }

    @Override
    protected void initView() {
        btn_file_size = (Button) findViewById(R.id.btn_file_size);
        btn_cache_size = (Button) findViewById(R.id.btn_cache_size);
        btn_create_file = (Button) findViewById(R.id.btn_create_file);
        btn_insert_file = (Button) findViewById(R.id.btn_insert_file);
        btn_copy_file = (Button) findViewById(R.id.btn_copy_file);
        btn_delete_file = (Button) findViewById(R.id.btn_delete_file);
        btn_query_file = (Button) findViewById(R.id.btn_query_file);
        show_file = (TextView) findViewById(R.id.show_file);

        btn_file_size.setOnClickListener(this);
        btn_cache_size.setOnClickListener(this);
        btn_create_file.setOnClickListener(this);
        btn_insert_file.setOnClickListener(this);
        btn_copy_file.setOnClickListener(this);
        btn_delete_file.setOnClickListener(this);
        btn_query_file.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        filePath = FileContact.YEHUI_FILES_PATH;
        fileName = "test_1.txt";
    }

    @Override
    public void onClick(View v) {
        try {
            if (FileOperationUtil.isSDCardEnable()) {
                switch (v.getId()) {
                    case R.id.btn_file_size://sd卡大小

                        show_file.append("sd卡总大小/可用：" + FileOperationUtil.getSDAllSize(this) + "/" + FileOperationUtil.getSDRemainingSize(this)+"\n");

                        break;
                    case R.id.btn_cache_size://手机内存大小
                        show_file.append("机身内存总大小/可用：" + FileOperationUtil.getRomAllSize(this) + "/" + FileOperationUtil.getRomRemainingSize(this)+"\n");
                        break;
                    case R.id.btn_create_file://创建空文
                        if (FileFoundUtil.createFile(1, filePath + fileName, true) == 1)
                            show_file.append("创建文件成功\n路径：" + filePath + "\n文件名：" + fileName+"\n");
                        else
                            show_file.append("创建文件失败"+"\n");
                        break;
                    case R.id.btn_insert_file://流方式写入文件
                        InputStream tInputStringStream = new ByteArrayInputStream("这是用流方式写入的数据".getBytes());
                        FileFoundUtil.insertSDCardFromInput(filePath, fileName, tInputStringStream);
                        if (FileOperationUtil.isHaveFile(filePath + fileName)) {
                            FileBean fileBean = FileOperationUtil.querySDFileByFile(filePath + fileName);
                            show_file.append("文件名：" + fileBean.getFileName() + "\n文件路径："
                                    + fileBean.getFilePath() + "\n文件大小："
                                    + fileBean.getFileSize() + "\n文件内容："
                                    + ResourcesUtil.getFromRaw(new FileInputStream(fileBean.getFile()))+"\n");
                        }
                        break;
                    case R.id.btn_copy_file://复制文件
                        if (FileFoundUtil.copyFile(new File(filePath, fileName), filePath, "copy_" + fileName))
                            show_file.append("复制成功，复制文件名：" + "copy_" + fileName+"\n");
                        else
                            show_file.append("复制失败"+"\n");
                        break;
                    case R.id.btn_delete_file://删除文件
                        FileFoundUtil.deleteFileByPath(filePath + fileName);
                        show_file.append("删除成功，删除文件名：" + "copy_" + fileName+"\n");
                        break;
                    case R.id.btn_query_file://查询文件
                        if (FileOperationUtil.isHaveFile(filePath + fileName)) {
                            FileBean fileBean = FileOperationUtil.querySDFileByFile(filePath + fileName);
                            show_file.append("文件名：" + fileBean.getFileName() + "\n文件路径："
                                    + fileBean.getFilePath() + "\n文件大小："
                                    + fileBean.getFileSize() + "\n文件内容："
                                    + ResourcesUtil.getFromRaw(new FileInputStream(fileBean.getFile()))+"\n");
                        } else
                            show_file.append("没有该文件"+"\n");
                        break;
                }
            }
        } catch (Exception e) {
            return;
        }

    }
}
