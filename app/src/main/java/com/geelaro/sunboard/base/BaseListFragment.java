package com.geelaro.sunboard.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geelaro.sunboard.R;
import com.geelaro.sunboard.utils.ShowToast;

/**
 * Created by geelaro on 2018/1/10.
 */

public abstract class BaseListFragment extends Fragment{
    private static final String TAG = BaseListFragment.class.getSimpleName();
    protected RecyclerView.Adapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    protected SwipeRefreshLayout mRefreshLayout;

    private static boolean isLoadingMoreData = false;


    protected abstract RecyclerView.Adapter bindAdapter();
    protected abstract void loadFromNet();
    protected abstract void loadingMoreData();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frame_common_list, container, false);

        mAdapter = bindAdapter();

        manager = new LinearLayoutManager(getActivity());
        // SwipeRefreshLayout
        mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.common_refresh);
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadFromNet();
            }
        });

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycleView_common);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(scrollListener);

        loadFromNet();

        return rootView;
    }

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (!isLoadingMoreData&&!recyclerView.canScrollVertically(1)){
                //手机向上不能滑动,到达底部
                isLoadingMoreData = true;
                Log.d("1122", "onScrollStateChanged: "+recyclerView.canScrollVertically(1));
                ShowToast.Short("下拉加载更多！");
//                loadingMoreData();
            } else {
                isLoadingMoreData = false;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };



}
