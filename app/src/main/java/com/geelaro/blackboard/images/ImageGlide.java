package com.geelaro.blackboard.images;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.geelaro.blackboard.R;

/**
 * Created by geelaro on 2017/10/14.
 */

public class ImageGlide {
    /**
     * 利用开源库Glide加载图片
     */
    public static void display(Context context, String url, ImageView imageView) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context)
                .load(url)
                .crossFade()
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_image_loadfail)
                .into(imageView);
    }
}
