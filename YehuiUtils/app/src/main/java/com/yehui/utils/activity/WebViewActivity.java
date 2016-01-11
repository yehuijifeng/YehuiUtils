package com.yehui.utils.activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;

/**
 * Created by yehuijifeng
 * on 2016/1/9.
 * 包含webview的activity
 */
public class WebViewActivity extends BaseActivity {
    private WebView web_view;
    private ProgressBar web_bar;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_web_view);
    }

    @Override
    protected String setTitleText() {
        return "webview";
    }

    @Override
    protected void initView() {
        web_view = (WebView) findViewById(R.id.web_view);
        web_bar = (ProgressBar) findViewById(R.id.web_bar);
    }

    @Override
    protected void initData() {
        loadDataFromServer("https://www.baidu.com");
    }

    private void loadDataFromServer(String url) {
        web_view.loadUrl(url);

        web_view.getSettings().setJavaScriptEnabled(true);

//      web_view.addJavascriptInterface(new JavascriptInterface(this), "Andriod");

        web_view.setWebViewClient(new MyWebViewClient());

        WebChromeClient webChromeClient = new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (title.length() > 12) {
                    title = title.substring(0, 12);
                    mTitleView.setTitleText(title + "...");
                } else {
                    mTitleView.setTitleText(title);
                }
            }

            /**进度改变
             * @param view
             * @param newProgress
             */
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                web_bar.setProgress(newProgress);
                if(newProgress>10){
                    web_bar.setVisibility(View.GONE);
                }else{
                    web_bar.setVisibility(View.VISIBLE);
                }
            }

        };
        web_view.setWebChromeClient(webChromeClient);
    }

    /**
     * 监听 所有点击的链接，如果拦截到我们需要的，就跳转到相对应的页面。
     */
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            try {
//                Bundle bundle = new Bundle();
//                if (url.contains("alsfox://")) {
//                    int y = url.lastIndexOf("//");
//                    String type = url.substring(y + 2, url.length());
//                    String[] split = type.split("/");
//                    bundle.putInt(Constans.PARAM_KEY_SHOPINFO_SHOPID, Integer.valueOf(split[1]));//商品ID
//                    Intent intent = new Intent();
//                    intent.putExtras(bundle);
//                    if (split[0].equals("shop")) {//TODO 商品详情
//                        intent.setClass(NoticeDetailTowActivity.this, CommodityDetailActivity.class);
//                    }
//                    startActivity(intent);
//                }
                view.loadUrl(url);
                return true;
            } catch (Exception e) {
                showShortToast("该链接无效");
                return true;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (web_view.canGoBack()) {
            web_view.goBack(); // goBack()表示返回WebView的上一页面
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        web_view.reload();//断开webview中的地址连接，刷新
    }

    @Override
    protected void onPause() {
        web_view.reload();
        super.onPause();
    }

}
