package com.yehui.utils.view.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yehui.utils.R;
import com.yehui.utils.utils.EmptyUtil;

/**
 * Created by yehuijifeng
 * on 2016/1/7.
 * loading页
 */
public class LoadingDialog extends View {

    private View root;
    private ImageView loading_img;
    private TextView loading_text;
    private ProgressDialog progressDialog;

    public LoadingDialog(Context context) {
        super(context);
    }

    /**
     * 关闭dialog
     */
    public void dismissLoadingDialog() {
        progressDialog.dismiss();
    }

    private void initView(){
        root =  View.inflate(getContext(), R.layout.dialog_loading, null);
        loading_text = (TextView) root.findViewById(R.id.loading_text);
        loading_img = (ImageView) root.findViewById(R.id.loading_img);
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        progressDialog.setContentView(root);
    }
    public void showLoadingDialog(String loadingStr, Drawable drawable) {
        initView();
        if (!EmptyUtil.isStringEmpty(loadingStr))
            loading_text.setText(loadingStr + "");
        if (drawable != null)
            loading_img.setImageDrawable(drawable);
    }

    public void showLoadingDialog(String loadingStr) {
        showLoadingDialog(loadingStr, null);
    }

    public void showLoadingDialog() {
        showLoadingDialog(null, null);
    }
}
