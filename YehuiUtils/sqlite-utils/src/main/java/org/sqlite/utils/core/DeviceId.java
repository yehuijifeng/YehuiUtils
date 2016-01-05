package org.sqlite.utils.core;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public class DeviceId {

    private static final String SECRET = "fjafo123:sdf";

    private String sDeviceId = null;
    private String sPlainDeviceId = null;
    private Context mContext;

    public static DeviceId newInstance(Context context) {
        return new DeviceId(context);
    }

    private DeviceId(Context context) {
        mContext = context;
    }

    public String getDeviceId() {
        return getDeviceId(false);
    }

    public String getPlainDeviceId() {
        return getDeviceId(true);
    }

    private synchronized String getDeviceId(boolean plain) {
        if (sDeviceId != null) {
            if (plain) {
                return sPlainDeviceId;
            }
            return sDeviceId;
        }
        String deviceId = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (deviceId == null || deviceId.equals("") || deviceId.equals("") || deviceId.length() < 15) {
            // possibly bad droid2 (motorola)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
                deviceId = getBuildSerial();
            }
        }
        if (deviceId == null || deviceId.length() == 0) {
            String imei = getIMEI();
            if (imei != null && imei.length() > 5) {
                deviceId = imei;
            }
        }
        if (deviceId == null || deviceId.length() == 0) {
            deviceId = "";
        }
        sPlainDeviceId = deviceId;
        sDeviceId = BinAscii.hexlify(BinAscii.md5((SECRET + deviceId).getBytes()));
        return getDeviceId(plain);
    }

    private String getBuildSerial() {
        String serial = android.os.Build.SERIAL;
        if (serial == null) return null;
        if (serial.length() < 10) return null;
        if (serial.replace("0", "").equals("")) return null;
        return serial;
    }

    public String getIMEI() {
        TelephonyManager mTelManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelManager != null && mTelManager.getDeviceId() != null) {
            return (mTelManager.getDeviceId());
        }
        return "";
    }
}