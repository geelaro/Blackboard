package com.geelaro.sunshine.images;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.geelaro.sunshine.utils.ShowToast;
import com.geelaro.sunshine.utils.SunLog;
import com.geelaro.sunshine.utils.SunshineApp;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by geelaro on 2017/10/12.
 * ImageFragment
 */

public class ImageFragment extends Fragment implements ImageView, SwipeRefreshLayout.OnRefreshListener {
    private final static String TAG = ImageFragment.class.getSimpleName();
    private ImageAdapter mImageAdapter;
    private List<ImageBean> mData;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private ImagePresenter mImagePresenter;
    private SwipeRefreshLayout mRefreshLayout;

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
        // SwipeRefreshLayout
        mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.image_refresh);
        mRefreshLayout.setColorSchemeResources(R.color.color_nav_center);
        mRefreshLayout.setOnRefreshListener(this);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycleView_image);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mImageAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(mOnScrollListener);

        SunLog.d(TAG, "Fragment:onCreateView");
        onRefresh();

        return rootView;
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                    lastVisibleItem + 1 == mImageAdapter.getItemCount()) {
                ShowToast.Short(getString(R.string.image_hit));
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = manager.findLastVisibleItemPosition();
        }
    };

    @Override
    public void addImageData(List<ImageBean> list) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.clear();
        mData.addAll(list);
        mImageAdapter.setData(mData);
        SunLog.d(TAG, ": addImageData");
    }

    @Override
    public void showProgress() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showErrorMsg() {
        if (isAdded()) {
            ShowToast.Short(getString(R.string.load_fail));
        }
    }

    @Override
    public void onRefresh() {
        mImagePresenter.loadImageList();
    }
}
