package com.geelaro.sunshine.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.geelaro.sunshine.BuildConfig;
import com.geelaro.sunshine.settings.Settings;

/**
 * Created by geelaro on 2018/1/12.
 */

public class NetworkUtils {

    /**
     * 判断网络情况
     *
     * @param context
     * @return boolean
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        if (networkInfo!= null) {
            return networkInfo.isAvailable();
        }
        return false;
    }

    /**
     * weather data URL
     **/
    public static Uri getWeatherURL() {
        String format = "json";
        String units = "metric";
        int numDays = 7;
//        String defalut = mContext.getString(R.string.default_location_value);
        String param = Settings.getInstance().getString(Settings.LOCATION,"Nanjing");

        final String QUERY_PARAM = "q";
        final String FORMAT_PARAM = "mode";
        final String UNITS_PARAM = "units";
        final String DAYS_PARAM = "cnt";
        final String APPID_PARAM = "APPID";

        Uri builtUri = Uri.parse(SunApi.WEATHER_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, param)
                .appendQueryParameter(FORMAT_PARAM, format)
                .appendQueryParameter(UNITS_PARAM, units)
                .appendQueryParameter(DAYS_PARAM, Integer.toString(numDays))
                .appendQueryParameter(APPID_PARAM, BuildConfig.OPEN_WEATHER_API_KEY)
                .build();

        return builtUri;
    }

    /**
     * 豆瓣电影TOP250
     */

    public static String getTop250URL(int startNum,int countNum){
        final String START = "start";
        final String COUNT = "count";

        Uri topUri = Uri.parse(SunApi.MOVIE_TOP_250).buildUpon()
                .appendQueryParameter(START,Integer.toString(startNum))
                .appendQueryParameter(COUNT,Integer.toString(countNum))
                .build();
        return topUri.toString();
    }
}
