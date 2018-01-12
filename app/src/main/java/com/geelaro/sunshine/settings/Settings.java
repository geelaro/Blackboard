package com.geelaro.sunshine.settings;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.geelaro.sunshine.utils.SunshineApp;

/**
 * Created by brian on 2017/12/30.
 */

public class Settings {

    public final static String LANGUAGE_LIST = "language_list";
    public final static String LOCATION = "location";

    public static boolean needRecreate;

    private static volatile Settings mInstance;

    private SharedPreferences mPrefs;

    private Settings() {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(SunshineApp.getContext());
    }

    public synchronized static Settings getInstance() {
        if (mInstance == null) {
            synchronized (Settings.class) {
                if (mInstance == null) {
                    mInstance = new Settings();
                }
            }
        }
        return mInstance;

    }

    public String getString(String key, String value) {
        return mPrefs.getString(key, value);
    }
}
