package com.yehui.utils.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pay.utils.alipay.AliPayService;
import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.utils.PayUtil;

/**
 * Created by yehuijifeng
 * on 2016/1/15.
 * 友盟
 */
public class PayActivity extends BaseActivity implements View.OnClickListener {
    private Button zhifubao_btn, weixin_btn, yinlian_btn;
    private TextView pay_text;
    private AliPayService payByAli;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_pay);
    }

    @Override
    protected String setTitleText() {
        return "支付";
    }

    @Override
    protected void initView() {
        zhifubao_btn = (Button) findViewById(R.id.zhifubao_btn);
        weixin_btn = (Button) findViewById(R.id.weixin_btn);
        yinlian_btn = (Button) findViewById(R.id.yinlian_btn);
        zhifubao_btn.setOnClickListener(this);
        weixin_btn.setOnClickListener(this);
        yinlian_btn.setOnClickListener(this);
        pay_text = (TextView) findViewById(R.id.umeng_text);
    }

    @Override
    protected void initData() {
        payByAli=PayUtil.aliPay(this);
        payByAli.setListener(new AliPayService.PayListener() {
            @Override
            public void onPaySuccess(String info) {
                showShortToast(info);
            }

            @Override
            public void onPayWait(String waitInfo) {

            }

            @Override
            public void onPayError(String errInfo) {
                showShortToast(errInfo);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhifubao_btn:
                //pay_from：shopPay 为商品支付， czPay 为充值支付
                payByAli.pay(this,"123456789","来自["+getResources().getString(R.string.app_name)+"]的支付","这是测试商品",0.01,"shopPay");
                break;
            case R.id.weixin_btn:
                break;
            case R.id.yinlian_btn:
                break;
        }
    }
}
