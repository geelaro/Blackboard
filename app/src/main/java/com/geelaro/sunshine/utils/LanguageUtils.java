package com.geelaro.sunshine.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.geelaro.sunshine.settings.Settings;

import java.util.Locale;

/**
 * Created by geelaro on 2017/12/30.
 * 语言切换
 */

public class LanguageUtils {

    //SharedPreferences里的language value值
    public static int getPrefsLanguageValue() {
        return Integer.parseInt(Settings.getInstance().getString(Settings.LANGUAGE_LIST, "-1"));
    }


    public static boolean isLanguageChanged() {
        Locale currentLocale = SunshineApp.getContext().getResources().getConfiguration().locale;
        return currentLocale.equals(getLocal());
    }

    public static void updateLanguage(Context context) {

        Locale lang = getLocal();
        //根据读取到的数据设置
        Resources resource = context.getResources();
        DisplayMetrics dm = resource.getDisplayMetrics();
        Configuration configuration = resource.getConfiguration();
        configuration.setLocale(lang);
        resource.updateConfiguration(configuration, dm);

    }

    private static Locale getLocal() {
        int langNum = getPrefsLanguageValue();
        Locale lang;
        switch (langNum) {
            case -1:
                lang = Locale.CHINESE;
                break;
            case 0:
                lang = Locale.CHINESE;
                break;
            case 1:
                lang = Locale.getDefault();
                break;
            default:
                lang = Locale.CHINESE;
        }
        return lang;
    }
}
