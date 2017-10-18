package com.geelaro.sunshine.images.model;

import com.geelaro.sunshine.beans.ImageBean;
import com.geelaro.sunshine.utils.ImageJsonUtils;
import com.geelaro.sunshine.utils.OkHttpUtils;
import com.geelaro.sunshine.utils.SunLog;
import com.geelaro.sunshine.utils.Urls;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by geelaro on 2017/10/14.
 */

public class ImageModelImpl implements ImageModel {
    /**
     * 获取图片列表
     *
     * @param listener
     */
    @Override
    public void loadImageList(final OnLoadImageListListener listener) {
        OkHttpUtils.ResultCallback<String> loadNewsCallBack = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onFailure(Exception e) {
                listener.onFailure("Fail to load image list.", e);
            }

            @Override
            public void onSuccess(String response) {
                List<ImageBean> imageBeanList = ImageJsonUtils.getImageBean(response);
                listener.onSuccess(imageBeanList);
            }
        };

        OkHttpUtils.get(Urls.IMAGE_URL, loadNewsCallBack);


    }

    public interface OnLoadImageListListener {
        void onSuccess(List<ImageBean> list);

        void onFailure(String msg, Exception e);
    }
}
