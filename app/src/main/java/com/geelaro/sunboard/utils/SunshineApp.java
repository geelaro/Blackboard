package com.geelaro.sunboard.utils;

import android.app.Application;
import android.content.Context;

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

        //语言设置
//        LanguageUtils.updateLanguage(context);
    }

    public static Context getContext() {
        return context;
    }
}
