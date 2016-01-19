package com.yehui.utils.activity.function;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.utils.DisplayUtil;
import com.yehui.utils.utils.ZxingUtils;
import com.yehui.utils.view.zxingview.activity.CaptureActivity;

/**
 * Created by yehuijifeng
 * on 2016/1/9.
 * axing.jar包的使用，二维码，条形码扫描和使用
 */
public class ZxingActivity extends BaseActivity {
    /**
     * ZXing库里面主要的类以及这些类的作用：
     * <p/>
     * CaptureActivity。这个是启动Activity 也就是扫描器。
     * CaptureActivityHandler 解码处理类，负责调用另外的线程进行解码。
     * DecodeThread 解码的线程。
     * com.google.zxing.client.android.camera 包，摄像头控制包。
     * ViewfinderView 自定义的View，就是我们看见的拍摄时中间的框框了。
     */
    private Button button1, button2, button3, button4;
    private TextView saoma_text;
    private ImageView sc_image;
    private final static int SCANNIN_GREQUEST_CODE = 2000;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_test_zxing);
    }

    @Override
    protected String setTitleText() {
        return "zxing.jar包的使用";
    }

    @Override
    protected void initView() {
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ZxingActivity.this, CaptureActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
            }
        });
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ZxingUtils.createTowDC("https://www.baidu.com", DisplayUtil.dip2px(ZxingActivity.this, 300), DisplayUtil.dip2px(ZxingActivity.this, 300));
                if (bitmap != null) {
                    sc_image.setImageBitmap(bitmap);
                }
            }
        });
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ZxingUtils.createStripDC("ABC-abc-1234567", DisplayUtil.dip2px(ZxingActivity.this, 250), DisplayUtil.dip2px(ZxingActivity.this, 100));
                if (bitmap != null) {
                    sc_image.setImageBitmap(bitmap);
                }
            }
        });
        saoma_text = (TextView) findViewById(R.id.saoma_text);
        sc_image = (ImageView) findViewById(R.id.sc_image);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        // 显示扫描到的内容
                        saoma_text.setText(bundle.getString("result"));
                        // 显示
                        sc_image.setImageBitmap((Bitmap) data
                                .getParcelableExtra("bitmap"));
                    }
                }
                break;
        }
    }

}
