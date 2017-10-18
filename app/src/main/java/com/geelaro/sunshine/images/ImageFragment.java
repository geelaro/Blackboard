package com.geelaro.sunshine.images;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geelaro.sunshine.R;
import com.geelaro.sunshine.beans.ImageBean;
import com.geelaro.sunshine.images.presenter.ImagePresenter;
import com.geelaro.sunshine.images.presenter.ImagePresenterImpl;
import com.geelaro.sunshine.images.view.ImageView;
import com.geelaro.sunshine.utils.SunLog;
import com.geelaro.sunshine.utils.SunshineApp;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by geelaro on 2017/10/12.
 */

public class ImageFragment extends Fragment implements ImageView {
    private final static String TAG = ImageFragment.class.getSimpleName();
    private ImageAdapter mImageAdapter;
    private List<ImageBean> mData;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private ImagePresenter mImagePresenter;

    public ImageFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImagePresenter = new ImagePresenterImpl(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frame_image, container, false);

        mImageAdapter = new ImageAdapter(SunshineApp.getContext());

        manager = new LinearLayoutManager(getActivity());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycleView_image);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mImageAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mImagePresenter.loadImageList();
        SunLog.d(TAG,"Fragment:onCreateView");

        return rootView;
    }

    @Override
    public void addImageData(List<ImageBean> list) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.clear();
        mData.addAll(list);
        mImageAdapter.setData(mData);
        SunLog.d(TAG,": addImageData");
    }
}
