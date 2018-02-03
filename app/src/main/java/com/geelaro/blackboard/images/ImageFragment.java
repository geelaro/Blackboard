package com.geelaro.blackboard.images;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geelaro.blackboard.R;
import com.geelaro.blackboard.adapter.ImageAdapter;
import com.geelaro.blackboard.base.beans.ImageBean;
import com.geelaro.blackboard.images.presenter.ImagePresenter;
import com.geelaro.blackboard.images.presenter.ImagePresenterImpl;
import com.geelaro.blackboard.images.view.ImageView;
import com.geelaro.blackboard.utils.ShowToast;
import com.geelaro.blackboard.utils.SunLog;
import com.geelaro.blackboard.utils.BkApp;

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

        mImageAdapter = new ImageAdapter(BkApp.getContext());

        manager = new LinearLayoutManager(getActivity());
        // SwipeRefreshLayout
        mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.image_refresh);
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        mRefreshLayout.setOnRefreshListener(this);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycleView_image);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mImageAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(mOnScrollListener);

        onRefresh();
        SunLog.d(TAG,"onCreateView");
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
