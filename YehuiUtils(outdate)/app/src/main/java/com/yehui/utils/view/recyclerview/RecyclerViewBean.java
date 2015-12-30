package com.yehui.utils.view.recyclerview;

/**
 * Created by yehuijifeng
 * on 2015/12/21.
 * recyclerview发送消息的实体类
 */
public class RecyclerViewBean  {

    public final static int NO_LOAD=-1;//没有赋值的状态为-1
    public final static int NORMAL_LOAD=0;//发送了请求为0
    public final static int SUCCESS_LOAD=1;//请求成功处理为1
    public final static int FAIL_LOAD=2;//请求处理失败为2


    private int isRefresh=NO_LOAD;//刷新是否完成
    private int isLoadMore=NO_LOAD;//加载更多是否完成

    public int getIsRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(int isRefresh) {
        this.isRefresh = isRefresh;
    }

    public int getIsLoadMore() {
        return isLoadMore;
    }

    public void setIsLoadMore(int isLoadMore) {
        this.isLoadMore = isLoadMore;
    }
}
