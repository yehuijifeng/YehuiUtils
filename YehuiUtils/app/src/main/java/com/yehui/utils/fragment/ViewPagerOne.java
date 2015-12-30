package com.yehui.utils.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.yehui.utils.R;

/**
 * Created by yehuijifeng
 * on 2015/12/3.
 * 测试fragment
 */
public class ViewPagerOne extends Fragment {
    private Button button_text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_test_viewpager_item, container, false);
    }

    //protected abstract void initView(View parentView);//basefragment中的view接口

    /**
     * 创建视图
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        button_text = (Button) view.findViewById(R.id.button_text);
        button_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "fragment中的点击事件", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 此方法以后才能获取activity
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 选中状态
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        //到显示状态为true，不可见为false
        if (isVisibleToUser) {
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
}
