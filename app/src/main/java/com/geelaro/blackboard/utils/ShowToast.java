package com.geelaro.blackboard.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.geelaro.blackboard.R;

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

    public static void shortCenter(Context context,CharSequence text){
        Toast toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

}
