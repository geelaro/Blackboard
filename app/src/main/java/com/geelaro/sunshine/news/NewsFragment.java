package com.geelaro.sunshine.news;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geelaro.sunshine.R;
import com.geelaro.sunshine.beans.NewsBean;
import com.geelaro.sunshine.news.presenter.NewsPresenter;
import com.geelaro.sunshine.news.presenter.NewsPresenterImpl;
import com.geelaro.sunshine.news.view.NewsView;
import com.geelaro.sunshine.utils.SunshineApp;

import java.util.ArrayList;
import java.util.List;

/**
 * News tab的页面布局
 * Created by geelaro on 2017/6/15.
 */

public class NewsFragment extends Fragment implements NewsView,SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG = NewsFragment.class.getSimpleName(); //TAG
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager  manager;
    private NewsAdapter mNewsAdapter;
    private List<NewsBean> mData;
    private NewsPresenter mPresenter;

    public NewsFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new NewsPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View newsView = inflater.inflate(R.layout.frame_news, container, false);

        mNewsAdapter = new NewsAdapter(SunshineApp.getContext());
        manager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView)newsView.findViewById(R.id.recycleView_news);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mNewsAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        onRefresh();

        return newsView;
    }


    @Override
    public void addNewsData(List<NewsBean> list) {
        if (mData==null){
            mData = new ArrayList<>();
        }
        mData.clear();//clear data
        mNewsAdapter.setData(mData);
        mData.addAll(list);

    }

    @Override
    public void onRefresh() {
        mPresenter.loadNewsList();
    }
}
