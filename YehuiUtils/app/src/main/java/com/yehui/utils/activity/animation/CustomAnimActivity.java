package com.yehui.utils.activity.animation;

import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.utils.DisplayUtil;

/**
 * Created by yehuijifeng
 * on 2016/1/19.
 * 自定义动画
 */
public class CustomAnimActivity extends BaseActivity implements Animation.AnimationListener, View.OnClickListener {

    /**自定义动画是基于属性动画做的处理
     * 自定义的动画作用可以改变控件本身的属性
     * 要求，该控件自身必须有get/set方法
     * */
    private Button
            btn_anim_translate,
            btn_anim_scale,
            btn_anim_rotate,
            btn_anim_alpha,
            btn_anim_custom;
    private ImageView show_anim;
    private Animation animation;//view动画
    private static final int time = 1000;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_test_custom_anim);
    }

    @Override
    protected String setTitleText() {
        return "自定义动画";
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
        /**加载xml文件动画的方法*/
        //animation = AnimationUtils.loadAnimation(this, R.anim.anim_demo);
        viewWrapper = new ViewWrapper(btn_anim_translate);
    }

    /**
     * 给没有get/set方法的控件加上该属性
     */
    private ViewWrapper viewWrapper;
    private void perfoemAnimate() {

        ObjectAnimator.ofInt(viewWrapper, "width", DisplayUtil.dip2px(this,200)).setDuration(time).start();
    }
    private void perfoemAnimate2() {

        ObjectAnimator.ofInt(viewWrapper, "width", DisplayUtil.dip2px(this,300)).setDuration(time).start();
    }
    private void perfoemAnimate3() {

        ObjectAnimator.ofInt(viewWrapper, "width", DisplayUtil.dip2px(this,50)).setDuration(time).start();
    }
    private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            this.mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }

    /**使用valueanimator监听动画过程*/
    private void performAnimate(final View target,final int start,final int end){
        ValueAnimator valueAnimator=ValueAnimator.ofInt(1,100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /**持有一个IntEvaluator对象，方便下面估值器的使用*/
            private IntEvaluator intEvaluator=new IntEvaluator();
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获得当前动画进度，整形，1到100之间
                int currentValue= (int) animation.getAnimatedValue();
                //获得当前进度占整个动画过程的比例，浮点型，0到1之间
                float fraction=animation.getAnimatedFraction();
                //直接调用整形估值器，通过比例计算出宽度，然后再设置给view
                target.getLayoutParams().width=intEvaluator.evaluate(fraction,start,end);
                target.requestLayout();
            }
        });
        valueAnimator.setDuration(time).start();
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btn_anim_translate://平移
                    perfoemAnimate();
                    break;
                case R.id.btn_anim_alpha://透明度
                    performAnimate(btn_anim_translate,btn_anim_translate.getWidth(),DisplayUtil.dip2px(CustomAnimActivity.this,200));
                    break;
                case R.id.btn_anim_custom://补帧动画
                    performAnimate(btn_anim_translate,btn_anim_translate.getWidth(),DisplayUtil.dip2px(CustomAnimActivity.this,50));
                    break;
                case R.id.btn_anim_rotate://旋转
                    perfoemAnimate2();
                    break;
                case R.id.btn_anim_scale://缩放
                    perfoemAnimate3();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
