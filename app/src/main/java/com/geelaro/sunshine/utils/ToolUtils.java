package com.geelaro.sunshine.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.geelaro.sunshine.BuildConfig;
import com.geelaro.sunshine.R;
import com.geelaro.sunshine.settings.Settings;

import java.util.List;

/**
 * Created by geelaro on 2017/8/11.
 */

public class ToolUtils {
    private static Context mContext = SunshineApp.getContext();
    private static Double mLat; //纬度
    private static Double mLon; //经度


    private static void getLocation() {
        LocationManager locationManager = (LocationManager)
                mContext.getSystemService(Context.LOCATION_SERVICE);
        //
        List<String> providerList = locationManager.getProviders(true);
        String provider = null;

        if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else {
            Toast.makeText(mContext, "请开启定位.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        mLat = location.getLatitude();
        mLon = location.getLongitude();

    }

    private final LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    /**
     * @return 返回经纬度
     */
    public static Double[] getLatAndLon() {
        getLocation();
        Double[] sNum = new Double[2];
        sNum[0] = mLat;
        sNum[1] = mLon;
        return sNum;
    }



    /**
     * 获取当前设备屏幕宽度in pixel
     */
    public static int getWidthInPx(Context context) {
        final int width = context.getResources().getDisplayMetrics().widthPixels;
        SunLog.d("WidthInPx", ":" + width);
        return width;
    }

    /**
     * 获取当前设备屏幕高度in pixel
     */
    public static int getHeightInPx(Context context) {
        final int height = context.getResources().getDisplayMetrics().heightPixels;
        SunLog.d("HeightInPx", ":" + height);

        return height;
    }

    public static float getWidthInDp(Context context) {
        final int width = context.getResources().getDisplayMetrics().widthPixels;
        int widthInDp = px2dp(context, width);
        SunLog.d("widthInDp", ":" + widthInDp);
        return widthInDp;
    }

    public static float getHeightInDp(Context context) {
        final int height = context.getResources().getDisplayMetrics().heightPixels;
        int heightInDp = px2dp(context, height);
        SunLog.d("heightInDp", ":" + heightInDp);

        return heightInDp;
    }

    /**
     * change pixel to dp
     **/
    public static int px2dp(Context context, int value) {
        final float scale = context.getResources().getDisplayMetrics().density;
        SunLog.d("Density", ":" + scale);
        return (int) (value / scale);
    }

    /**
     * change dp to pixel
     **/
    public static int dp2px(Context context, int value) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value * scale);
    }


    /**
     * 温度格式
     **/
    public static String formatTemperature(Context context, double temp) {

        return context.getString(R.string.format_temperature, temp);
    }
    /** **/

    public static int getSmallWeatherImage(int weatherId) {
        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.ic_storm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.ic_light_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.ic_rain;
        } else if (weatherId == 511) {
            return R.drawable.ic_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.ic_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.ic_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.ic_fog;
        } else if (weatherId == 761 || weatherId == 771 || weatherId == 781) {
            return R.drawable.ic_storm;
        } else if (weatherId == 800) {
            return R.drawable.ic_clear;
        } else if (weatherId == 801) {
            return R.drawable.ic_light_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.ic_cloudy;
        } else if (weatherId >= 900 && weatherId <= 906) {
            return R.drawable.ic_storm;
        } else if (weatherId >= 958 && weatherId <= 962) {
            return R.drawable.ic_storm;
        } else if (weatherId >= 951 && weatherId <= 957) {
            return R.drawable.ic_clear;
        }

        SunLog.e("getSmallWeatherImage", "Unknown Weather: " + weatherId);
        return R.drawable.ic_storm;
    }

    public static int getBigWeatherImage(int weatherId) {
        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.art_storm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.art_light_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.art_rain;
        } else if (weatherId == 511) {
            return R.drawable.art_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.art_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.art_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.art_fog;
        } else if (weatherId == 761 || weatherId == 771 || weatherId == 781) {
            return R.drawable.art_storm;
        } else if (weatherId == 800) {
            return R.drawable.art_clear;
        } else if (weatherId == 801) {
            return R.drawable.art_light_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.art_clouds;
        } else if (weatherId >= 900 && weatherId <= 906) {
            return R.drawable.art_storm;
        } else if (weatherId >= 958 && weatherId <= 962) {
            return R.drawable.art_storm;
        } else if (weatherId >= 951 && weatherId <= 957) {
            return R.drawable.art_clear;
        }

        SunLog.e("getBigWeatherImage", "Unknown Weather: " + weatherId);
        return R.drawable.art_storm;
    }


}
