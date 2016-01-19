package com.yehui.utils.activity.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.activity.function.ImageCroppingActivity;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.application.YehuiApplication;
import com.yehui.utils.utils.BitmapUtil;
import com.yehui.utils.utils.DateUtil;
import com.yehui.utils.utils.PickLocalImageUtils;
import com.yehui.utils.utils.files.FileContact;
import com.yehui.utils.view.CircularImageView;
import com.yehui.utils.view.dialog.CustomDialog;
import com.yehui.utils.view.dialog.ListDialog;
import com.yehui.utils.view.dialog.LoadingDialog;
import com.yehui.utils.view.dialog.PromptDialog;
import com.yehui.utils.view.dialog.PwdDialog;

/**
 * Created by yehuijifeng
 * on 2016/1/7.
 * 提示框
 */
public class DialogActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_dialog_pwd, btn_dialog_loading, btn_dialog_prompt, btn_dialog_list,btn_dialog_custom;
    private TextView show_text;
    private PwdDialog pwdDialog;
    private LoadingDialog loadingDialog;
    private PromptDialog promptDialog;
    private CustomDialog customDialog;
    private ListDialog listDialog;
    private String imageFileName;
    private ImageView show_image;
    private CircularImageView civUserInfoHead;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_test_dialog);
    }

    @Override
    protected String setTitleText() {
        return "提示框";
    }

    @Override
    protected void initView() {
        btn_dialog_pwd = (Button) findViewById(R.id.btn_dialog_pwd);
        btn_dialog_loading = (Button) findViewById(R.id.btn_dialog_loading);
        btn_dialog_prompt = (Button) findViewById(R.id.btn_dialog_prompt);
        btn_dialog_list = (Button) findViewById(R.id.btn_dialog_list);
        btn_dialog_custom = (Button) findViewById(R.id.btn_dialog_custom);
        show_image= (ImageView) findViewById(R.id.show_image);
        btn_dialog_pwd.setOnClickListener(this);
        btn_dialog_loading.setOnClickListener(this);
        btn_dialog_prompt.setOnClickListener(this);
        btn_dialog_list.setOnClickListener(this);
        btn_dialog_custom.setOnClickListener(this);
        show_text = (TextView) findViewById(R.id.show_text);
        civUserInfoHead= (CircularImageView) findViewById(R.id.civ_user_info_head);
    }

    @Override
    protected void initData() {
        pwdDialog = new PwdDialog(this);
        loadingDialog = new LoadingDialog(this);
        promptDialog=new PromptDialog(this);
        customDialog=new CustomDialog(this);
        listDialog=new ListDialog(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_pwd:
                pwdDialog.showPwdDialog("应付金额:30.0元", new PwdDialog.PwdDialogListener() {
                    @Override
                    public void onDetermine(String password) {
                        show_text.append(password + "\n");
                    }

                    @Override
                    public void onCancel() {
                    }
                });
                break;
            case R.id.btn_dialog_loading:
                loadingDialog.showLoadingDialog("test");
                break;
            case R.id.btn_dialog_list:
                listDialog.showListDialog(getResourceStringArray(R.array.image_array), new ListDialog.ListOnClickListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onItems(int item, String itemName) {
                        if(item==0){
                            PickLocalImageUtils.toAlbum(DialogActivity.this);
                        }else{
                            imageFileName = DateUtil.format(System.currentTimeMillis(), "'IMG'_yyyyMMddHHmmss") + ".jpg";
                            PickLocalImageUtils.toCamera(DialogActivity.this, imageFileName);
                        }
                    }
                });
                break;
            case R.id.btn_dialog_prompt:
                promptDialog.showPromptDialog("这是内容啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊这是内容啊啊啊啊 啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊这是内容啊啊啊啊 啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊这是内容啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊这是内容啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊", new PromptDialog.PromptOnClickListener() {
                    @Override
                    public void onDetermine() {
                        showShortToast("确定");
                    }

                    @Override
                    public void onCancel() {

                    }
                });
                break;
            case R.id.btn_dialog_custom:
                View view=inflate(R.layout.item_test_recycler,null);
                customDialog.showCustomDialog(view, new CustomDialog.CustomOnClickListener() {
                    @Override
                    public void onDetermine() {
                        showShortToast("确定");
                    }

                    @Override
                    public void onCancel() {

                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String imagePath;
            switch (requestCode) {
                case PickLocalImageUtils.CODE_FOR_ALBUM://来自于系统相册的回调
                    if (data == null) return;
                    Uri uri = data.getData();
                    imagePath = PickLocalImageUtils.getPath(uri, getContentResolver());
                    showImage(imagePath);
                    break;
                case PickLocalImageUtils.CODE_FOR_CAMERA://来自于系统相机的回调
                    imagePath = FileContact.YEHUI_SAVE_IMG_PATH + imageFileName;
                    PickLocalImageUtils.toCrop(this, imagePath);
                    break;
                case PickLocalImageUtils.CODE_FOR_CROP://来自于剪切照片的回调
                    imagePath = data.getStringExtra(ImageCroppingActivity.KEY_SAVE_IMAGE_PATH);
                    Bitmap bitmap = BitmapUtil.decodeSampledBitmapFromFile(imagePath, 100, 100);
                    civUserInfoHead.setImageBitmap(bitmap);
                    BitmapUtil.saveBitmap(bitmap, imagePath,100);
                    showImage(imagePath);
                    break;
            }

        }
    }

    private void showImage(String imagePath){
        imageLoader.displayImage("file:///" + imagePath, show_image, YehuiApplication.defaultOptions);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
