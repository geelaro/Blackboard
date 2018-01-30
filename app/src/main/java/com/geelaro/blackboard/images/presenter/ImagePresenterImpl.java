package com.geelaro.blackboard.images.presenter;

import com.geelaro.blackboard.base.beans.ImageBean;
import com.geelaro.blackboard.images.model.ImageModel;
import com.geelaro.blackboard.images.model.ImageModelImpl;
import com.geelaro.blackboard.images.model.ImageModelImpl.OnLoadImageListListener;
import com.geelaro.blackboard.images.view.ImageView;

import java.util.List;

/**
 * Created by geelaro on 2017/10/14.
 */

public class ImagePresenterImpl implements ImagePresenter, OnLoadImageListListener {
    private ImageView mImageView;
    private ImageModel mImageModel;

    public ImagePresenterImpl(ImageView imageView) {
        mImageView = imageView;
        mImageModel = new ImageModelImpl();
    }

    @Override
    public void loadImageList() {
        mImageView.showProgress();
        mImageModel.loadImageList(this);
    }

    @Override
    public void onSuccess(List<ImageBean> list) {
        mImageView.addImageData(list);
        mImageView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mImageView.hideProgress();
        mImageView.showErrorMsg();

    }


}
