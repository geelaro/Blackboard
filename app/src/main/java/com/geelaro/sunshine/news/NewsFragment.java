package com.geelaro.sunshine.news;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private static final String NEWS_TYPE = "type";
    private int mType;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager  manager;
    private NewsAdapter mNewsAdapter;
    private List<NewsBean> mData;
    private NewsPresenter mPresenter;
    private SwipeRefreshLayout mRefreshLayout;


    public static NewsFragment newInstance(int type){
        NewsFragment nfm = new NewsFragment();
        Bundle args = new Bundle();
        args.putInt(NEWS_TYPE,type);
        nfm.setArguments(args);

        return nfm;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new NewsPresenterImpl(this);
        mType = getArguments().getInt(NEWS_TYPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View newsView = inflater.inflate(R.layout.frame_news, container, false);

        mRefreshLayout = (SwipeRefreshLayout) newsView.findViewById(R.id.news_refresh);
        /**设置刷新时旋转图标的颜色，这是一个可变参数，当设置多个颜色时，旋转一周改变一次颜色 */
        mRefreshLayout.setColorSchemeResources(R.color.color_nav_center,R.color.color_nav_end);
        mRefreshLayout.setOnRefreshListener(this);

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
    public void showProgress() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadNewsList(mType);
    }
}
