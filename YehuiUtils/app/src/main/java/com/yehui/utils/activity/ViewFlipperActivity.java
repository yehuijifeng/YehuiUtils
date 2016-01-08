package com.yehui.utils.activity;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.utils.EmptyUtil;

/**
 * Created by yehuijifeng
 * on 2016/1/9.
 * ViewFlipper轮播
 */
public class ViewFlipperActivity extends BaseActivity implements View.OnTouchListener,GestureDetector.OnGestureListener {
    private ViewFlipper viewFlipper;
    private Button btn_1,btn_2;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_flipper_view);
    }

    @Override
    protected String setTitleText() {
        return "ViewFlipper实现轮播";
    }

    @Override
    protected void initView() {
        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
        btn_1= (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setFlipInterval(3000);
            }
        });
        btn_2= (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.stopFlipping();
            }
        });
        viewFlipper.startFlipping();
        viewFlipper.setOnTouchListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onDown(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        // TODO Auto-generated method stub

        return true;
    }
    float x,b;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            x=event.getRawX();
        }
        if(event.getAction()==MotionEvent.ACTION_MOVE){
            b=event.getRawX()-x;

        }if(event.getAction()==MotionEvent.ACTION_UP){
            if(b>viewFlipper.getWidth()/5&&event.getRawX()>0){
                viewFlipper.showNext();
            }else  if(EmptyUtil.abs((int)b)>viewFlipper.getWidth()/5&&b<0){
                viewFlipper.showPrevious();
            }
        }
        return true;
    }
}
