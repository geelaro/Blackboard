package com.geelaro.blackboard.utils;

import android.widget.Toast;

/**
 * Created by geelaro on 2017/8/15.
 */

public class ShowToast {
    private static Toast mToast;

    /**
     * Short Toast
     *
     * @param text
     */
    public static void Short(CharSequence text) {
        if (mToast != null) {
            mToast.cancel(); //检查上一个Toast是否为空，不为空则取消它，显示当前的Toast。
        }
        mToast = Toast.makeText(BkApp.getContext(), text, Toast.LENGTH_SHORT);
        mToast.show();
    }

    /**
     * Long Toast
     *
     * @param text
     */
    public static void Long(CharSequence text) {
        Toast.makeText(BkApp.getContext(), text, Toast.LENGTH_LONG).show();
    }
}
