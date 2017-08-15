package com.geelaro.sunshine.utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by geelaro on 2017/8/10.
 */

public class SunshineApp extends Application {
    //全局Context
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
