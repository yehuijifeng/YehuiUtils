package com.yehui.utils.activity.animation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;

/**
 * Created by yehuijifeng
 * on 2016/1/19.
 * 属性动画
 */
public class ValueAnimActivity extends BaseActivity implements View.OnClickListener, Animation.AnimationListener {

    /**
     * 属性动画的类型
     * 1,ValueAnimator 它不作用于任何对象，使用它并没有动画效果，它只能使属性拥有动态效果
     * 2,ObjectAnimator(它继承valueAnimator)
     * 3,AnimatorSet 它是动画集合
     * <p/>
     * 注意，属性动画只有在api 11以后才有
     * <p/>
     * 三方类库：nineoldandroids
     */
    private Button
            btn_anim_translate,
            btn_anim_scale,
            btn_anim_rotate,
            btn_anim_alpha,
            btn_anim_custom;
    private ImageView show_anim;
    private static final int time = 3000;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_test_value_anim);
    }

    @Override
    protected String setTitleText() {
        return "属性动画";
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
        /**加载xml文件动画的方法*/
        RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);//子心旋转
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        //setInterpolator表示设置旋转速率。
        animation.setInterpolator(linearInterpolator);
        animation.setRepeatCount(-1);//-1表示循环运行
        animation.setFillAfter(true);//动画停留在最后位置
        animation.setDuration(time);
        show_anim.startAnimation(animation);
    }


    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btn_anim_translate://改变一个object对象的translationY的属性，让其沿着Y轴向上平移一段距离
                    ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(show_anim,"translationY",-show_anim.getHeight());
                    objectAnimator.setDuration(time);//动画执行时间
                    objectAnimator.setRepeatCount(ValueAnimator.INFINITE);//设置重复计数。设置成无限循环
                    objectAnimator.setRepeatMode(ValueAnimator.REVERSE);//设置重复的模式，反转效果
                    objectAnimator.start();
                    break;
                case R.id.btn_anim_alpha://改变对象的背景色，3s内从0xffff8080变到0xff8080ff 渐变 动画无限循环，且有反转的效果
                    ValueAnimator valueAnimator1=ObjectAnimator.ofInt(show_anim,"backgroundColor",/*红色*/0xffff8080,/*蓝色*/0xff8080ff);
                    valueAnimator1.setDuration(time);//动画执行时间
                    valueAnimator1.setEvaluator(new ArgbEvaluator());//设置估值器
                    valueAnimator1.setRepeatCount(ValueAnimator.INFINITE);//设置重复计数。设置成无限循环
                    valueAnimator1.setRepeatMode(ValueAnimator.REVERSE);//设置重复的模式，反转效果
                    valueAnimator1.start();
                    break;
                case R.id.btn_anim_custom://动画集合
                    AnimatorSet animatorSet=new AnimatorSet();
                    animatorSet.playTogether(
                            ObjectAnimator.ofFloat(show_anim,"rotationX",0,360),
                            ObjectAnimator.ofFloat(show_anim,"rotationY",0,180),
                            ObjectAnimator.ofFloat(show_anim,"rotation",0,-90),
                            ObjectAnimator.ofFloat(show_anim,"translationY",0,90),
                            ObjectAnimator.ofFloat(show_anim,"translationX",0,90),
                            ObjectAnimator.ofFloat(show_anim,"scaleX",1,1.5f),
                            ObjectAnimator.ofFloat(show_anim,"scaleY",1,1.5f),
                            ObjectAnimator.ofFloat(show_anim,"alpha",1,0.25f,1)
                    );
                    animatorSet.setDuration(time);
                    animatorSet.start();
                    break;
                case R.id.btn_anim_rotate://资源动画
                    AnimatorSet animatorSet1= (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.animator_demo);
                    animatorSet1.setTarget(show_anim);
                    animatorSet1.start();
                    break;
                case R.id.btn_anim_scale://缩放
                    break;
            }
        } catch (Exception e) {
            return;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (show_anim != null)
            show_anim.clearAnimation();
    }

    /**
     * 动画监听
     */
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
