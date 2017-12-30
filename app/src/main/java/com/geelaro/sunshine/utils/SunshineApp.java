package com.geelaro.sunshine.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;

import com.geelaro.sunshine.R;

import java.util.Locale;

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
//        LanguageUtils.changeLanguage(context);
    }

    public static Context getContext() {
        return context;
    }
}
