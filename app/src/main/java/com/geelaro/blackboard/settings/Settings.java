package com.geelaro.blackboard.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.geelaro.blackboard.R;
import com.geelaro.blackboard.utils.BkApp;

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
        mPrefs = PreferenceManager.getDefaultSharedPreferences(BkApp.getContext());
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

    public String getPreferredLocation(Context context){
        return mPrefs.getString(LOCATION,context.getString(R.string.pref_location_default));
    }

    public String getString(String key, String value) {
        return mPrefs.getString(key, value);
    }
}
