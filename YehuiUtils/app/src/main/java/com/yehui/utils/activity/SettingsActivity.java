package com.yehui.utils.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.contacts.SettingContact;
import com.yehui.utils.utils.AppUtil;
import com.yehui.utils.utils.EmptyUtil;
import com.yehui.utils.view.popupwindow.PopupWindowAll;

import java.util.Locale;

/**
 * Created by yehuijifeng
 * on 2016/1/21.
 * 设置中心
 */
public class SettingsActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 对于不常用的语种可以使用：
     * config.locale =new Locale("ar");
     * ar代表阿拉伯语
     * 设置语言的时候要确定到国家，若不是，则获取不到语种类型
     */

    private PopupWindowAll popupWindowAll;
    private View languageView;
    private RelativeLayout setting_language_layout;

    private TextView zh_cn_text;
    private TextView en_gb_text;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected String setTitleText() {
        return getResourceString(R.string.setting);
    }

    @Override
    protected void initView() {
        popupWindowAll = new PopupWindowAll(this);
        languageView = inflate(R.layout.layout_popupwindow_language, null);
        setting_language_layout = (RelativeLayout) findViewById(R.id.setting_language_layout);
        setting_language_layout.setOnClickListener(this);

        zh_cn_text = (TextView) languageView.findViewById(R.id.zh_cn_text);
        en_gb_text = (TextView) languageView.findViewById(R.id.en_gb_text);
        zh_cn_text.setOnClickListener(this);
        en_gb_text.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        getSharedPreferences();
    }

    @Override
    public void onClick(View v) {
        String language_str;
        switch (v.getId()) {
            case R.id.setting_language_layout:
                popupWindowAll.showAtLocationByBottom(v, languageView);
                break;
            case R.id.zh_cn_text:
                language_str = AppUtil.setUserLanguage(this, Locale.CHINA);
                sharedPreferences.saveString(SettingContact.APP_LANGUAGE, EmptyUtil.isStringEmpty(language_str) ? SettingContact.DEFAULT_LANGUAGE : language_str);
                popupWindowAll.dismissWindow();
                AppUtil.reStartApp(SettingsActivity.this);
                showShortToast("设置成功，即将重启app");
                break;
            case R.id.en_gb_text:
                language_str = AppUtil.setUserLanguage(this, Locale.UK);
                sharedPreferences.saveString(SettingContact.APP_LANGUAGE, EmptyUtil.isStringEmpty(language_str) ? SettingContact.DEFAULT_LANGUAGE : language_str);
                popupWindowAll.dismissWindow();
                AppUtil.reStartApp(this);
                showShortToast("设置成功，即将重启app");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(YehuiHomeActivity.class);
    }
}
