package com.yehui.utils.baidumap;

import android.content.Context;

import com.map.utils.LocationService;
import com.map.utils.locations.LocationBean;

import de.greenrobot.event.EventBus;

/**
 * Created by yehuijifeng
 * on 2016/1/19.
 * 百度定位，获得坐标
 */
public class LocationUtil {
    private EventBus eventBus;
    private LocationService locationService;

    public void initLoaction(Context context) {
        eventBus = EventBus.getDefault();
        locationService = new LocationService(context);

    }

    public void startLoaction() {
        locationService.startLoaction(new LocationService.BaiduMapInterface() {
            @Override
            public void showLocationMessage(LocationBean locationBean) {
                eventBus.post(locationBean);
            }
        });
    }

    public void stopLocation() {
        locationService.stopLocation();
    }
}
