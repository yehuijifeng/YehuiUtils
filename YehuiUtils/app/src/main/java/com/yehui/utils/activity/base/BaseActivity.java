package com.yehui.utils.activity.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yehui.utils.R;
import com.yehui.utils.application.ActivityCollector;
import com.yehui.utils.view.MyTitleView;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by yehuijifeng
 * on 2015/11/25.
 * 最基础的Activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 子类需要实现的抽象类
     */
    /**
     * 布局id,传入了ToolBarActivity中重写的setContentView中，以便于添加toolbar
     */
    protected abstract void setContentView();

    /**
     * 初始化title
     */
    protected abstract String setTitleText();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化data
     */
    protected abstract void initData();

    /**
     * -------------------------------------------------------------------------------------
     */

    /**
     * 获取屏幕宽高
     */
    protected DisplayMetrics outMetrics = new DisplayMetrics();

    /**
     * gson,解析json数据或者类转json时能用到
     */
    protected Gson gson = new Gson();

    /**
     * BaseActivity和BaseFragment的代理类，不用管。
     */
    public BaseHelper helper;

    /**
     * eventBus工具类
     */
    protected EventBus eventBus;

    /**
     * imageloader工具类的初始化
     */
    protected ImageLoader imageLoader;

    /**
     * activity的title
     */
    protected MyTitleView mTitleView;

    /**
     * 父布局填充
     */
    protected LayoutInflater inflater;

    /**
     * title的类型，枚举类型
     */
    protected MyTitleView.TitleMode titleMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //title,功能操作模式叠加
        requestWindowFeature(WindowCompat.FEATURE_ACTION_MODE_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView();
        ActivityCollector.addActivity(this);
        initProperties();
        initView();
        initData();
    }

    /**
     * baseactivity的初始化
     */
    private void initProperties() {
        helper = new BaseHelper(this);
        mTitleView = (MyTitleView) findViewById(R.id.my_title_view);
        if (mTitleView != null) {
            if (setCustomToolbar() != null) {
                onCreateCustomToolBar(setCustomToolbar());
            } else {
                addTitleMode();
                mTitleView.getTitleMode();
                mTitleView.setTitleText(setTitleText() + "");
            }
        }
        outMetrics = helper.outMetrics;
        eventBus = EventBus.getDefault();
        imageLoader = ImageLoader.getInstance();
        inflater = this.getLayoutInflater();
        if (!helper.isRegistered(this)) {
            helper.registerEventBus(this);
        }

    }

    /**
     * 设置标题类型
     */
    private void addTitleMode() {
        mTitleView.setTitleMode(setTitleTypeByTitleMode());
    }

    /**
     * 供子类调用的title类型
     */
    protected MyTitleView.TitleMode setTitleTypeByTitleMode() {
        return MyTitleView.TitleMode.NORMAL;
    }

    /**
     * @param view 创建自定义的toolbar
     */
    protected void onCreateCustomToolBar(View view) {
        mTitleView.setNewView(view);
    }

    /**
     * 供子类调用的title,自定义toolbar
     *
     * @return
     */
    protected View setCustomToolbar() {
        return null;
    }

    /**
     * EventBus工具类接收消息的特定类，需要注册后才能使用
     */
    public void onEventMainThread() {

    }
    /**EventBus工具类的四种方法
     1、onEvent
     2、onEventMainThread
     3、onEventBackgroundThread
     4、onEventAsync
     */

    /**
     * 解释：
     * <p>
     * onEvent:如果使用onEvent作为订阅函数，那么该事件在哪个线程发布出来的，onEvent就会在这个线程中运行，也就是说发布事件和接收事件线程在同一个线程。使用这个方法时，在onEvent方法中不能执行耗时操作，如果执行耗时操作容易导致事件分发延迟。
     * onEventMainThread:如果使用onEventMainThread作为订阅函数，那么不论事件是在哪个线程中发布出来的，onEventMainThread都会在UI线程中执行，接收事件就会在UI线程中运行，这个在Android中是非常有用的，因为在Android中只能在UI线程中跟新UI，所以在onEvnetMainThread方法中是不能执行耗时操作的。
     * onEventBackground:如果使用onEventBackgrond作为订阅函数，那么如果事件是在UI线程中发布出来的，那么onEventBackground就会在子线程中运行，如果事件本来就是子线程中发布出来的，那么onEventBackground函数直接在该子线程中执行。
     * onEventAsync：如果使用onEventAsync函数作为订阅函数，那么无论事件在哪个线程发布，都会创建新的子线程在执行onEventAsync.
     */
    /**
     * 注册事件总线的方法。
     */
    public void registerEventBus(Object o) {
        helper.registerEventBus(o);
    }

    /**
     * 反注册事件总线的方法
     */
    public void unregisterEventBus(Object o) {
        helper.unregisterEventBus(o);
    }

    /**
     * 发送事件消息的方法，所有注册了事件总线并且onEventMainThread等4个方法中的参数类型为传入该方法参数的类型时，将会受到该方法发送的通知。
     */
    public void postEventBus(Object o) {
        helper.postEventBus(o);
    }

    /**
     * 判断某个类是否已注册事件总线
     */
    public boolean isRegistered(Object o) {
        return helper.isRegistered(o);
    }

    /**
     * 需要实现eventbus中的一个方法，不然报错
     */
    public void onEventMainThread(Object obj) {

    }


    /**
     * 隐藏/显示软键盘的方法
     */
    protected void hideSoftInputFromWindow(View view) {
        helper.hideSoftInputFromWindow(view);
    }

    protected void showSoftInputFromWindow(View view) {
        helper.showSoftInputFromWindow(view);
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    protected int getWindowHeight() {
        return helper.getWindowHeight();
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    protected int getWindowWidth() {
        return helper.getWindowWidth();
    }

    /**
     * @return 获取上一个Activity传过来的Bundle
     */
    protected Bundle getBundle() {
        return helper.getBundle();
    }

    /**
     * 获取上一个Activity传过来的字符串集合
     */
    protected List<String> getStringArrayList(String key) {
        return helper.getStringArrayList(key);
    }

    /**
     * 获取上一个Activity传过来的int值
     *
     * @param key          键
     * @param defaultValue 默认值
     */
    protected int getInt(String key, int defaultValue) {
        return helper.getInt(key, defaultValue);
    }

    /**
     * 获取上一个Activity传过来的String值
     */
    protected String getString(String key, String defaultValue) {
        return helper.getString(key, defaultValue);
    }

    /**
     * 获取上一个Activity传过来的布尔值
     */
    protected boolean getBoolean(String key, boolean defaultValue) {
        return helper.getBoolean(key, defaultValue);
    }

    /**
     * 获取上一个Activity传过来的double值
     */
    protected double getDouble(String key, double defaultValue) {
        return helper.getDouble(key, defaultValue);
    }

    /**
     * 获取上一个Activity传过来的float值
     */
    protected float getFloat(String key, float defaultValue) {
        return helper.getFloat(key, defaultValue);
    }

    /**
     * 获取上一个Activity传过来的实现了Parcelable接口的对象
     */
    protected Parcelable getParcelable(String key) {
        return helper.getParcelable(key);
    }

    /**
     * 获取上一个Activity传过来的实现了Parcelable接口的对象的集合
     */
    protected ArrayList<? extends Parcelable> getParcelableList(String key) {
        return helper.getParcelableList(key);
    }

    /**
     * <h1 color='#FFAAAA'>获取资源文件下的字符串数组</h1>
     *
     * @param resId <font color='#FFFFFF'>资源ID 示例:R.array.lab_payment_methods</font>
     * @return
     */
    public String[] getResourceStringArray(int resId) {
        return helper.getResourceStringArray(resId);
    }

    /**
     * <h1 color='#FFAAAA'>获取资源文件下的字符串<h1/>
     *
     * @param resId 资源ID。<font color='#FFFFFF'>示例:R.string.xxx<font/>
     * @return
     */
    public String getResourceString(int resId) {
        return helper.getResourceString(resId);
    }

    /**
     * <h1 color='#FFAAAA'>获取资源文件下的Drawable对象<h1/>
     *
     * @param resId 资源ID.<font color='#FFFFFF'>示例:R.drawable.xxx<font/>
     * @return Drawable对象
     */
    public Drawable getResourceDrawable(int resId) {
        return helper.getResourceDrawable(resId);
    }

    /**
     * <h1 color='#FFAAAA'>获取资源文件下的颜色值<h1/>
     *
     * @param resId 资源ID。<font color='#FFFFFF'>示例:R.color.xxx<font/>
     * @return
     */
    public int getResourceColor(int resId) {
        return helper.getResourceColor(resId);
    }

    /**
     * 获取资源文件下的int值
     *
     * @param resId 资源ID。<font color='#FFFFFF'>示例:R.integer.xxx<font/>
     * @return
     */
    public int getResourceInteger(int resId) {
        return helper.getResourceInteger(resId);
    }

    /**
     * 获取资源文件下的尺寸值
     *
     * @param resId 资源ID。<font color='#FFFFFF'>示例:R.dimen.xxx<font/>
     * @return
     */
    public float getResourceDimension(int resId) {
        return helper.getResourceDimension(resId);
    }

    /**
     * 在父容器中加入view
     * root设置这个view的填充器
     */
    public View inflate(int resource, ViewGroup root) {
        return helper.inflate(resource, root);
    }

    public View inflate(int resource, ViewGroup root, boolean attachToRoot) {
        return helper.inflate(resource, root, attachToRoot);
    }

    /**
     * 显式跳转Activity的方法(不带任何参数)
     */
    public void startActivity(Class<?> cls) {
        helper.startActivity(cls);
    }

    /**
     * 显式跳转Activity的方法(带Bundle)
     *
     * @param cls    要跳转的Activity的类
     * @param bundle 装载了各种参数的Bundle
     */
    public void startActivity(Class<?> cls, Bundle bundle) {
        helper.startActivity(cls, bundle);
    }

    /**
     * 显式跳转Activity的方法(带返回结果，不带参数)
     *
     * @param cls         要跳转的Activity的类
     * @param requestCode 跳转Activity的请求码
     */
    public void startActivityForResult(Class<?> cls, int requestCode) {
        helper.startActivityForResult(cls, requestCode);
    }

    /**
     * 显式跳转Activity的方法(带返回结果，带Bundle)
     *
     * @param cls         要跳转的Activity的类
     * @param bundle      装载了各种参数的Bundle
     * @param requestCode 跳转Activity的请求码
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        helper.startActivityForResult(cls, bundle, requestCode);
    }

    /**
     * 弹出时间短暂的Toast
     */
    public void showShortToast(String text) {
        helper.showShortToast(text);
    }

    /**
     * 弹出时间较长的Toast
     */
    public void showLongToast(String text) {
        helper.showLongToast(text);
    }

    /**
     * 只有Debug模式下才会弹出的toast条，可以在开发阶段随意使用的toast，即使发布后未删除这个toast也是没关系的。
     */
    public void showDebugToast(String text) {
        helper.showDebugToast(text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper.isRegistered(this)) {
            helper.unregisterEventBus(this);
        }
        helper=null;
        ActivityCollector.removeActivity(this);
    }

}
