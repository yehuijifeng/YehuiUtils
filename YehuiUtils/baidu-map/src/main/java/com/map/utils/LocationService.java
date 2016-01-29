package com.map.utils;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.map.utils.locations.LocationBean;


/**
 * Created by luhao
 * on 2016/1/19.
 * 百度定位，获得坐标
 */
public class LocationService {
    private LocationClient mLocationClient;
    private MyLocationListener mMyLocationListener;
    private String locationStr;
    private LocationBean locationBean;
    private BaiduMapInterface baiduMapInterface;

    public LocationService(Context context) {
        locationBean = new LocationBean();
        mLocationClient = new LocationClient(context);
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
        getLocation();
    }

    public void startLoaction(BaiduMapInterface baiduMapInterface) {
        this.baiduMapInterface = baiduMapInterface;
        mLocationClient.start();//定位SDK start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
        mLocationClient.requestLocation();
    }

    public void stopLocation() {
        if (mLocationClient != null)
            mLocationClient.stop();
    }

    private void getLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        /**
		bd09ll  表示百度经纬度坐标，

         gcj02   表示经过国测局加密的坐标，

         wgs84   表示gps获取的坐标。
		*/
		option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，
        int span = 0;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        mLocationClient.setLocOption(option);
    }

    /**
     * 实现实时位置回调监听
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            locationBean.setTime(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            locationBean.setErrorCode(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            locationBean.setLatitude(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            locationBean.setLontitude(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            locationBean.setRadius(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("gps定位成功");
                locationBean.setLocationStr("gps定位成功");
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("网络定位成功");
                locationBean.setLocationStr("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("离线定位成功，离线定位结果也是有效的");
                locationBean.setLocationStr("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                locationBean.setLocationStr("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
                locationBean.setLocationStr("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                locationBean.setLocationStr("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            locationStr = sb.toString();
            baiduMapInterface.showLocationMessage(locationBean);
        }
    }

    public interface BaiduMapInterface {
        void showLocationMessage(LocationBean locationBean);
    }
}
