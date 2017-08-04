package com.geelaro.sunshine.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.service.carrier.CarrierMessagingService;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by geelaro on 2017/6/23.
 */

public class HttpUtils {

    private static final String TAG = HttpUtils.class.getSimpleName(); //

    private static HttpUtils mInstance;
    private OkHttpClient mOkHttpClient;

    private Handler mDelivery;

    private HttpUtils() {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();

        mDelivery = new Handler(Looper.getMainLooper());

    }

    public static HttpUtils newInstance() {
        if (mInstance == null) {
            mInstance = new HttpUtils();
        }
        return mInstance;
    }

    //GET
    private void getRequest(String url, final CarrierMessagingService.ResultCallback callback){
        Request request = new Request.Builder().url(url).build();

    }




    /**
     * 判断网络情况
     * @param context
     * @return boolean
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo.isAvailable();
    }
}



