package com.yehui.utils.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.utils.UmengUtil;

/**
 * Created by yehuijifeng
 * on 2016/1/15.
 * 友盟
 */
public class UMengActivity extends BaseActivity implements View.OnClickListener {
    private Button fenxiang_btn, qq_login_btn, xinlang_login_btn;
    private TextView umeng_text;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_umeng);
    }

    @Override
    protected String setTitleText() {
        return "友盟";
    }

    @Override
    protected void initView() {
        fenxiang_btn = (Button) findViewById(R.id.fenxiang_btn);
        qq_login_btn = (Button) findViewById(R.id.qq_login_btn);
        xinlang_login_btn = (Button) findViewById(R.id.xinlang_login_btn);
        fenxiang_btn.setOnClickListener(this);
        qq_login_btn.setOnClickListener(this);
        xinlang_login_btn.setOnClickListener(this);
        umeng_text = (TextView) findViewById(R.id.umeng_text);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fenxiang_btn:
                UmengUtil.shareDefault(this);
                break;
            case R.id.qq_login_btn:
                UmengUtil.loginQQ(this);
                umeng_text.setText(UmengUtil.loginQQUserInfo());
                break;
            case R.id.xinlang_login_btn:
                break;
        }
    }
}
