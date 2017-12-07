package com.geelaro.sunshine.images.view;

import com.geelaro.sunshine.beans.ImageBean;

import java.util.List;

/**
 * Created by geelaro on 2017/10/14.
 * ImageView的接口
 */

public interface ImageView {
    void addImageData(List<ImageBean> list);
    void showProgress();
    void hideProgress();
    void showErrorMsg();
}
