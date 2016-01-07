package com.yehui.utils.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.utils.LogUtil;
import com.yehui.utils.view.dialog.LoadingDialog;
import com.yehui.utils.view.dialog.PwdDialog;

/**
 * Created by yehuijifeng
 * on 2016/1/7.
 * 提示框
 */
public class DialogActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_dialog_pwd, btn_dialog_loading, btn_dialog_prompt, btn_dialog_list;
    private TextView show_text;
    private PwdDialog pwdDialog;
    private LoadingDialog loadingDialog;

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
        btn_dialog_pwd.setOnClickListener(this);
        btn_dialog_loading.setOnClickListener(this);
        btn_dialog_prompt.setOnClickListener(this);
        btn_dialog_list.setOnClickListener(this);
        show_text = (TextView) findViewById(R.id.show_text);
        pwdDialog = new PwdDialog(this);
        loadingDialog = new LoadingDialog(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_pwd:
                pwdDialog.showDialog("应付金额:30.0元", new PwdDialog.PwdDialogListener() {
                    @Override
                    public void onDetermine(String password) {
                        show_text.append(password+"\n");
                    }

                    @Override
                    public void onCancel() {
                    }
                });
                break;
            case R.id.btn_dialog_loading:
                loadingDialog.showDialog("test");
                break;
            case R.id.btn_dialog_list:
                startActivity(YehuiHomeActivity.class);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pwdDialog.dismissDialog();
        LogUtil.e("eeeeeee");
    }
}
