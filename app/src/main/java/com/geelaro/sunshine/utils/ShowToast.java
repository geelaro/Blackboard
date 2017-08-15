package com.geelaro.sunshine.utils;

import android.widget.Toast;

/**
 * Created by geelaro on 2017/8/15.
 */

public class ShowToast {
    /**
     * Short Toast
     * @param text
     */
    public static void Short(CharSequence text){
        Toast.makeText(SunshineApp.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    /**
     * Long Toast
     * @param text
     */
    public static void Long(CharSequence text){
        Toast.makeText(SunshineApp.getContext(),text,Toast.LENGTH_LONG).show();
    }
}
