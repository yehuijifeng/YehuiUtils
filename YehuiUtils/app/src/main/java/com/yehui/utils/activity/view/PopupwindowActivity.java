package com.yehui.utils.activity.view;

import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.view.popupwindow.PopupWindowAll;

/**
 * Created by yehuijifeng
 * on 2016/1/9.
 * popuwindow的使用
 */
public class PopupwindowActivity extends BaseActivity {

    /**
     * 1.显示方法
     * <p/>
     * 显示提供了两种形式：
     * <p/>
     * showAtLocation()显示在指定位置，有两个方法重载：
     * <p/>
     * public void showAtLocation(View parent, int gravity, int x, int y)
     * <p/>
     * public void showAtLocation(IBinder token, int gravity, int x, int y)
     * <p/>
     * <p/>
     * showAsDropDown()显示在一个参照物View的周围，有三个方法重载：
     * <p/>
     * public void showAsDropDown(View anchor)
     * <p/>
     * public void showAsDropDown(View anchor, int xoff, int yoff)
     * <p/>
     * public void showAsDropDown(View anchor, int xoff, int yoff, int gravity)
     */

    private Button ok_btn;
    private PopupWindowAll popupWindowAll;
    private View root;
    private View contentview;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_test_popupwindow);

    }

    @Override
    protected String setTitleText() {
        return "popupwindow使用";
    }

    @Override
    protected void initView() {
        contentview=inflate(R.layout.activity_test_popupwindow,null);
        ok_btn= (Button) findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
    }

    @Override
    protected void initData() {
        root=inflate(R.layout.dialog_password,null);
        popupWindowAll=new PopupWindowAll(this);
    }
    private void show(){
        popupWindowAll.showAtLocationByBottom(ok_btn,root);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            popupWindowAll.showAsDropDown(root,contentview, (int)event.getRawX(), (int)event.getRawY(),Gravity.TOP);
        }
        return false;
    }
}
