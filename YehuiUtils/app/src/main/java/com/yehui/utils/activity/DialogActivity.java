package com.yehui.utils.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
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
        btn_dialog_pwd.setOnClickListener(this);
        btn_dialog_loading.setOnClickListener(this);
        btn_dialog_prompt.setOnClickListener(this);
        btn_dialog_list.setOnClickListener(this);
        btn_dialog_custom.setOnClickListener(this);
        show_text = (TextView) findViewById(R.id.show_text);

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
                listDialog.showListDialog(new String[]{"设置", "关于", "退出"}, new ListDialog.ListOnClickListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onItems(int item, String itemName) {
                        showShortToast("点击了第"+item+"个"+" 内容："+itemName);
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
    protected void onDestroy() {
        super.onDestroy();
        pwdDialog.dismissPwdDialog();
    }
}
