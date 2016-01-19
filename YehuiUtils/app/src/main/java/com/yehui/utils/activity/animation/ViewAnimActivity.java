package com.yehui.utils.activity.animation;

import android.animation.TimeInterpolator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;

/**
 * Created by yehuijifeng
 * on 2016/1/19.
 * 普通view动画
 */
public class ViewAnimActivity extends BaseActivity implements View.OnClickListener,Animation.AnimationListener {

    /**
     * 四种view动画
     * 1，平移 <translate/>  TranslateAnimation
     * 1，缩放 <scale/>  ScaleAnimation
     * 1，旋转 <rotate/>  RotateAnimation
     * 1，透明度 <alpha/>  AlphaAnimation
     * */

    /**
     * 关于其中的属性意义如下（红色部分加以注意）:

     android:fromDegrees 起始的角度度数

     android:toDegrees 结束的角度度数，负数表示逆时针，正数表示顺时针。如10圈则比android:fromDegrees大3600即可

     android:pivotX 旋转中心的X坐标

     浮点数或是百分比。浮点数表示相对于Object的左边缘，如5; 百分比表示相对于Object的左边缘，如5%; 另一种百分比表示相对于父容器的左边缘，如5%p; 一般设置为50%表示在Object中心

     android:pivotY 旋转中心的Y坐标

     浮点数或是百分比。浮点数表示相对于Object的上边缘，如5; 百分比表示相对于Object的上边缘，如5%; 另一种百分比表示相对于父容器的上边缘，如5%p; 一般设置为50%表示在Object中心

     android:duration 表示从android:fromDegrees转动到android:toDegrees所花费的时间，单位为毫秒。可以用来计算速度。

     android:interpolator表示变化率，但不是运行速度。一个插补属性，可以将动画效果设置为加速，减速，反复，反弹等。默认为开始和结束慢中间快，

     android:startOffset 在调用start函数之后等待开始运行的时间，单位为毫秒，若为10，表示10ms后开始运行

     android:repeatCount 重复的次数，默认为0，必须是int，可以为-1表示不停止

     android:repeatMode 重复的模式，默认为restart，即重头开始重新运行，可以为reverse即从结束开始向前重新运行。在android:repeatCount大于0或为infinite时生效

     android:detachWallpaper 表示是否在壁纸上运行

     android:zAdjustment 表示被animated的内容在运行时在z轴上的位置，默认为normal。

     normal保持内容当前的z轴顺序

     top运行时在最顶层显示

     bottom运行时在最底层显示

     */
    private Button
            btn_anim_translate,
            btn_anim_scale,
            btn_anim_rotate,
            btn_anim_alpha,
            btn_anim_custom;
    private ImageView show_anim;
    private Animation animation;//动画
    private LinearInterpolator linearInterpolator;//线性插值器，根据时间百分比设置属性百分比
    private TimeInterpolator timeInterpolator;//时间插值器，匀速动画
    private AccelerateInterpolator accelerateInterpolator;//加速度插值器，两头慢中间快，默认的播放效果
    private DecelerateInterpolator decelerateInterpolator;//减速插值器，越来越慢
    private static final int time=3000;
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
        //animation.setAnimationListener(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btn_anim_translate://平移
                    animation=new TranslateAnimation(0, 400, 0, 0);//横向位移400个单
                    break;
                case R.id.btn_anim_alpha://透明度
                    animation=new AlphaAnimation(0,0.5f);
                    break;
                case R.id.btn_anim_custom://自定义
                    animation= AnimationUtils.loadAnimation(this,R.anim.anim_demo);

                    break;
                case R.id.btn_anim_rotate://旋转
                    animation= new RotateAnimation(0, 360);//顺时针旋转70度
                    linearInterpolator= new LinearInterpolator();
                    //setInterpolator表示设置旋转速率。
                    animation.setInterpolator(linearInterpolator);
                    animation.setRepeatCount(-1);//-1表示循环运行
                    animation.setDuration(1000);
                    break;
                case R.id.btn_anim_scale://缩放
                    animation= new ScaleAnimation(0, 5, 0, 5);//横向放大5倍，纵向放大5
                    break;
            }
            animation.setFillAfter(true);//动画停留在最后位置
            //animation.setDuration(time);
            show_anim.startAnimation(animation);
        } catch (Exception e) {
            return;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        show_anim.clearAnimation();
    }

    /**动画监听*/
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
