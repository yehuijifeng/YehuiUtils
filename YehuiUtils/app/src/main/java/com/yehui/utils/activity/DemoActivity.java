package com.yehui.utils.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;

/**
 * Created by yehuijifeng
 * on 2016/1/19.
 * 普通view动画
 */
public class DemoActivity extends BaseActivity implements View.OnClickListener {
    private Button
            btn_anim_translate,
            btn_anim_scale,
            btn_anim_rotate,
            btn_anim_alpha,
            btn_anim_custom;
    private ImageView show_anim;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_test_view_anim);
    }

    @Override
    protected String setTitleText() {
        return "view动画";
    }

    @Override
    protected void initView() {
        btn_anim_translate = (Button) findViewById(R.id.btn_anim_translate);
        btn_anim_scale = (Button) findViewById(R.id.btn_anim_scale);
        btn_anim_rotate = (Button) findViewById(R.id.btn_anim_rotate);
        btn_anim_alpha = (Button) findViewById(R.id.btn_anim_alpha);
        btn_anim_custom = (Button) findViewById(R.id.btn_anim_custom);
        show_anim = (ImageView) findViewById(R.id.show_anim);

        btn_anim_translate.setOnClickListener(this);
        btn_anim_scale.setOnClickListener(this);
        btn_anim_rotate.setOnClickListener(this);
        btn_anim_alpha.setOnClickListener(this);
        btn_anim_custom.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }


    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btn_anim_translate://创建数据库
                    break;
                case R.id.btn_anim_alpha:
                    break;
                case R.id.btn_anim_custom:
                    break;
                case R.id.btn_anim_rotate:
                    break;
                case R.id.btn_anim_scale:
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
