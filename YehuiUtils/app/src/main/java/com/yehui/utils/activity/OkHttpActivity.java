package com.yehui.utils.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.bean.weather.WeathersBean;
import com.yehui.utils.http.action.RequestAction;
import com.yehui.utils.http.bean.DownloadFileBean;
import com.yehui.utils.http.request.RequestUrls;
import com.yehui.utils.http.request.ResponseComplete;
import com.yehui.utils.http.request.ResponseFailure;
import com.yehui.utils.http.request.ResponseSuccess;
import com.yehui.utils.utils.files.FileBean;
import com.yehui.utils.utils.files.FileContact;

import java.io.File;
import java.util.Map;

/**
 * Created by yehuijifeng
 * on 2016/1/10.
 * 基于okhttp的网络请求
 */
public class OkHttpActivity extends BaseActivity implements View.OnClickListener {
    private Button get_btn, post_btn, post_file_btn, down_btn, get_instance_btn, post_instance_btn;
    private ImageView http_image;
    private TextView http_text;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_okhttp);
    }

    @Override
    protected String setTitleText() {
        return "okhttp网络请求";
    }

    @Override
    protected void initView() {

        get_btn = (Button) findViewById(R.id.get_btn);
        get_instance_btn = (Button) findViewById(R.id.get_instance_btn);
        post_btn = (Button) findViewById(R.id.post_btn);
        post_instance_btn = (Button) findViewById(R.id.post_instance_btn);
        post_file_btn = (Button) findViewById(R.id.post_file_btn);
        down_btn = (Button) findViewById(R.id.down_btn);
        get_instance_btn.setOnClickListener(this);
        post_instance_btn.setOnClickListener(this);
        get_btn.setOnClickListener(this);
        post_btn.setOnClickListener(this);
        post_file_btn.setOnClickListener(this);
        down_btn.setOnClickListener(this);
        http_image = (ImageView) findViewById(R.id.http_image);
        http_text = (TextView) findViewById(R.id.http_text);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onRequestSuccess(ResponseSuccess success) {
        switch (success.getRequestAction()) {
            case POST_WEATHER:
            case GET_WEATHER:
                WeathersBean weathersBean = (WeathersBean) success.getResultContent();
                http_text.append(weathersBean.getWeather().get(0).getInfo().getDay().get(0) + ":" + weathersBean.getWeather().get(0).getInfo().getDay().get(1) + "\n");
                loadingClose();
                break;
        }
    }

    @Override
    protected void onRequestFailure(ResponseFailure failure) {
        switch (failure.getRequestAction()) {
            case POST_WEATHER:
            case GET_WEATHER:
                showShortToast(failure.getMessage());
                break;
        }
    }

    @Override
    protected void onRequestCompleted(ResponseComplete complete) {
    }

    @Override
    protected void onRequestDownFile(DownloadFileBean downloadFileBean) {
        if(downloadFileBean.getUrl()==RequestUrls.POST_DOWN_FILE_SMALL){
            http_text.setText((100 * downloadFileBean.getBytesRead()) / downloadFileBean.getContentLength()+"%\n");
            if(downloadFileBean.isDone()){
                FileBean fileBean=downloadFileBean.getFileBean();
                http_text.append(fileBean.getFileName()+"\n");
                http_text.append(fileBean.getFilePath()+"\n");
                http_text.append(fileBean.getFileSize()+"\n");
                http_text.append(fileBean.getFileLength()+"\n");
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_btn:
                loadingShow();
                sendGetRequest(RequestAction.GET_WEATHER);
                break;
            case R.id.post_btn:
                Map<String, Object> param = RequestAction.POST_WEATHER.parameter.getParameters();
                param.put("cityname", "乌鲁木齐");//城市
                sendPostRequest(RequestAction.POST_WEATHER);
                break;
            case R.id.post_file_btn:
                File[] files=new File[]{new File(FileContact.getSDFile()+"/Pictures/Screenshots/S60102-000146.jpg"),new File(FileContact.getSDFile()+"/Pictures/Screenshots/S60102-000143.jpg")};
                sendPostAddFileRequest(files,RequestAction.POST_UP_FILE);
                break;
            case R.id.down_btn:
                sendDownloadFile(RequestUrls.POST_DOWN_FILE_SMALL);
                break;
            case R.id.get_instance_btn:
                sendGetInstanceRequest(RequestAction.GET_WEATHER);
                break;
            case R.id.post_instance_btn:
                Map<String, Object> param2 = RequestAction.POST_WEATHER.parameter.getParameters();
                param2.put("cityname", "松花江");//城市
                sendPostInstanceRequest(RequestAction.POST_WEATHER);
                break;
        }
    }


}
