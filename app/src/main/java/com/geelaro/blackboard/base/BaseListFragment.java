package com.geelaro.blackboard.base;

import android.os.Bundle;
import android.os.Handler;
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

import com.geelaro.blackboard.R;
import com.geelaro.blackboard.utils.ShowToast;

/**
 * Created by geelaro on 2018/1/10.
 */

public abstract class BaseListFragment extends Fragment{
    private static final String TAG = BaseListFragment.class.getSimpleName();
    protected RecyclerView.Adapter mAdapter;
    protected RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    protected SwipeRefreshLayout mRefreshLayout;

    public static boolean isLoadMore = false;


    protected abstract RecyclerView.Adapter bindAdapter();
    protected abstract void loadFromNet();
    protected abstract void loadingMoreData();
    protected abstract void setScrollListener();
    protected abstract RecyclerView.LayoutManager getLayoutManager();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frame_common_list, container, false);

        mAdapter = bindAdapter();

        manager = getLayoutManager();
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
        setScrollListener();


        loadFromNet();

        return rootView;
    }


}
