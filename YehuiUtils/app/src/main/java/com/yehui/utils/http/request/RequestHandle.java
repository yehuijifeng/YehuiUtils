package com.yehui.utils.http.request;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.yehui.utils.R;
import com.yehui.utils.activity.base.BaseActivity;
import com.yehui.utils.http.StatusCode;
import com.yehui.utils.http.action.RequestAction;
import com.yehui.utils.http.interfaces.RequestInterface;
import com.yehui.utils.utils.GsonUtil;
import com.yehui.utils.utils.LogUtil;
import com.yehui.utils.utils.NetWorkUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;

/**
 * Created by yehuijifeng
 * on 2016/1/10.
 * 网络请求的操作类
 */
public class RequestHandle implements RequestInterface {

    /**
     * 单例请求网络,只能传入baseactivity
     */
    private static volatile RequestInterface requestHandle = null;


    public synchronized static RequestInterface getRequestInterface(BaseActivity context) {
        mContext = context;
        if (requestHandle == null) {
            synchronized (RequestHandle.class) {
                if (requestHandle == null) {
                    requestHandle = new RequestHandle();
                }
            }
        }
        return requestHandle;
    }

    public synchronized static RequestInterface getRequestInterface() {
        if (requestHandle == null) {
            synchronized (RequestHandle.class) {
                if (requestHandle == null) {
                    requestHandle = new RequestHandle();
                }
            }
        }
        return requestHandle;
    }

    private RequestHandle() {
        //创建okHttpClient对象
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(VALUE_DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS);//连接超时
        mOkHttpClient.setReadTimeout(VALUE_DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS);//读取超时
        mOkHttpClient.setWriteTimeout(VALUE_DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS);//写入超时
        eventBus = EventBus.getDefault();
        gson = new Gson();
    }

    private static BaseActivity mContext;

    private EventBus eventBus;

    private Gson gson;

    private OkHttpClient mOkHttpClient;

    //创建队列，用于异步调用
    private Request request;
    //访问
    private Call call;

    //网络请求成功
    private ResponseResult createResponseSuccess(JSONObject response, Class<?> cls, RequestAction requestAction) {
        try {
            ResponseSuccess responseSuccess = new ResponseSuccess();
            responseSuccess.setStatusCode(response.getInt(TAG_STATUS_CODE));
            responseSuccess.setMessage(response.getString(TAG_MESSAGE));
            responseSuccess.setRequestAction(requestAction);
            if (cls == null) return responseSuccess;
            if (response.has(TAG_RESULT)) {//单个数据
                JSONObject result = new JSONObject(response.getString(TAG_RESULT));
                responseSuccess.setResultContent(GsonUtil.fromJsonObject(result.getString(TAG_DATA), cls));
            }
//                //数据集合
//                JSONArray array = response.getJSONArray(TAG_OBJECT);
//                int length = array.length();
//                List<Object> data = new ArrayList<>();
//                for (int i = 0; i < length; i++) {
//                    data.add(gson.fromJson(array.getString(i), cls));
//                }
//                responseSuccess.setResultContent(data);
            else
                responseSuccess.setResultContent(mContext.getResourceString(R.string.json_error));
            return responseSuccess;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    //网络请求失败
    private ResponseResult createResponseFailure(int statusCode, String responseString, RequestAction requestAction) {
        ResponseResult responseResult = new ResponseFailure();
        responseResult.setMessage(responseString);
        responseResult.setStatusCode(statusCode);
        responseResult.setRequestAction(requestAction);
        return responseResult;
    }

    //网络请求不管成功还是失败都会调用
    private ResponseResult createResponseComplete(int statusCode, String responseString, RequestAction requestAction) {
        ResponseResult responseResult = new ResponseComplete();
        responseResult.setMessage(responseString);
        responseResult.setStatusCode(statusCode);
        responseResult.setRequestAction(requestAction);
        return responseResult;
    }

    /**
     * 判断是否有网络的请求
     */
    private void judgeNetWork(RequestAction action) {
        if (!NetWorkUtil.isConnected(mContext)) {
            ResponseResult result = createResponseFailure(StatusCode.SERVER_NO_NETWORK, mContext.getResourceString(R.string.network_error), action);
            eventBus.post(result);
            return;
        }
    }


    /**
     * 网络请求成功回调后的处理方法
     *
     * @param response
     */
    private void setResponse(Response response, final RequestAction action) {
        try {
            String jsonStr = new String(response.body().bytes(), "UTF-8");//二进制字节数组
            //String jsonStr=response.body().string();//字符串
            //InputStream inputStream=response.body().byteStream();//输入流
            if (jsonStr == null) {
                eventBus.post(createResponseFailure(StatusCode.NOT_MORE_DATA, mContext.getResourceString(R.string.lading_empty), action));
                eventBus.post(createResponseComplete(StatusCode.NOT_MORE_DATA, mContext.getResourceString(R.string.lading_empty), action));
                return;
            }
            JSONObject jsonObject = new JSONObject(jsonStr);
            final int statusCode = jsonObject.getInt(TAG_STATUS_CODE);
            if (statusCode == StatusCode.REQUEST_SUCCESS) {
                eventBus.post(createResponseSuccess(jsonObject, action.parameter.getCls(), action));
            } else {
                eventBus.post(createResponseFailure(statusCode, jsonObject.getString(TAG_MESSAGE), action));
            }
            eventBus.post(createResponseComplete(statusCode, jsonObject.getString(TAG_MESSAGE), action));
        } catch (Exception e) {
            e.printStackTrace();
            eventBus.post(createResponseFailure(StatusCode.SERVER_ERROR, mContext.getResourceString(R.string.json_error), action));
        }
    }


    @Override
    public void sendGetRequest(RequestAction action) {
        judgeNetWork(action);
        //创建一个Request
        request = new Request.Builder()
                .url(action.parameter.getRequestUrl())
                .build();
        callBackByEnqueue(request, action);
    }

    /**
     * 线程阻塞的单例发送方法
     */
    public void sendGetRequestByInstance(RequestAction action) {
        judgeNetWork(action);
        //创建一个Request
        request = new Request.Builder()
                .url(action.parameter.getRequestUrl())
                .build();
        callBackByExecute(request, action);
    }

    /**
     * okhttp线程阻塞发送
     */
    private void callBackByExecute(Request request, final RequestAction action) {
        try {
            //new call
            call = mOkHttpClient.newCall(request);
            Response response = call.execute();
            setResponse(response, action);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * okhttp线程队列发送，异步的
     */
    private void callBackByEnqueue(Request request, final RequestAction action) {
        //new call
        call = mOkHttpClient.newCall(request);
        //请求加入调度，请求一步进行
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                String message;
                int statusCode;
                if (!NetWorkUtil.isConnected(mContext)) {
                    message = mContext.getResourceString(R.string.network_error);
                    statusCode = StatusCode.SERVER_NO_NETWORK;
                } else {
                    message = mContext.getResourceString(R.string.server_error);
                    statusCode = StatusCode.SERVER_BUSY;
                }
                eventBus.post(createResponseFailure(statusCode, message, action));
                eventBus.post(createResponseComplete(statusCode, message, action));
            }

            @Override
            public void onResponse(Response response) {
                setResponse(response, action);
            }
        });
    }


    @Override
    public void sendPostRequest(RequestAction action) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        Map<String, Object> parameters = action.parameter.getParameters();
        Set set = parameters.keySet();//得到所有map里面key的集合
        //遍历
        for (Iterator iter = set.iterator(); iter.hasNext(); ) {
            String key= (String) iter.next();
            String value= (String) parameters.get(key);
            builder.add(key,value);
        }
        //如果网络请求需要签名，则加上这行代码
        //builder.add(SignUtil.KEY_SIGN, SignUtil.createSign(parameters));
        request = new Request.Builder()
                .url(action.parameter.getRequestUrl())
                .post(builder.build())
                .build();
        LogUtil.e(request.httpUrl().toString());
        callBackByEnqueue(request, action);
    }

    @Override
    public void sendPostAddFileRequest(File[] files, RequestAction action) {

    }

    @Override
    public void cancelByActionRequests(RequestAction action) {

    }

    @Override
    public void cancelAllRequests(boolean isCancelAllRequests) {

    }

    @Override
    public void setTimeOut(int value) {
        if (mOkHttpClient == null) return;
        mOkHttpClient.setConnectTimeout(value, TimeUnit.MILLISECONDS);//连接超时
        mOkHttpClient.setReadTimeout(value, TimeUnit.MILLISECONDS);//读取超时
        mOkHttpClient.setWriteTimeout(value, TimeUnit.MILLISECONDS);//写入超时
    }

    @Override
    public void downloadFile(String url, File[] files) {

    }
}
