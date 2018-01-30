package com.geelaro.blackboard.images.model;

import com.geelaro.blackboard.base.beans.ImageBean;
import com.geelaro.blackboard.utils.ImageJsonUtils;
import com.geelaro.blackboard.utils.OkHttpUtils;
import com.geelaro.blackboard.utils.SunApi;

import java.util.List;

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
        OkHttpUtils.ResultCallback<String> loadImageCallBack = new OkHttpUtils.ResultCallback<String>() {
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

        OkHttpUtils.newInstance().get(SunApi.IMAGE_URL, loadImageCallBack);


    }

    public interface OnLoadImageListListener {
        void onSuccess(List<ImageBean> list);

        void onFailure(String msg, Exception e);
    }
}
