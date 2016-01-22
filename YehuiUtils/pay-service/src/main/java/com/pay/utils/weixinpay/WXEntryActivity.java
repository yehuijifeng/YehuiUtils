package com.pay.utils.weixinpay;//package com.pay.utils.weixinpay;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.pay.utils.R;
//
//
//public class WXEntryActivity extends Activity {
//
//    private Button payBtn;
//    private TextView showText;
//    private WeixinBean weixinBean;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.entry);
//        showText = (TextView) findViewById(R.id.showText);
//        payBtn = (Button) findViewById(R.id.goto_pay_btn);// 支付界面
//        payBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                WeixinPayPort w = new WeixinPayPort(WXEntryActivity.this);
//                w.weixinPay(weixinBean);
//            }
//        });
//    }
//}