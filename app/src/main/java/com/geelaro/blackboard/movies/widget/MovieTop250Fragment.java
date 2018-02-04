package com.geelaro.blackboard.movies.widget;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.geelaro.blackboard.adapter.MovieTop250Adapter;
import com.geelaro.blackboard.base.BaseListFragment;
import com.geelaro.blackboard.base.beans.MoviesBean;
import com.geelaro.blackboard.movies.presenter.MoviePresenter;
import com.geelaro.blackboard.movies.presenter.MoviePresenterImpl;
import com.geelaro.blackboard.movies.view.MovieView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geelaro on 2018/1/10.
 */

public class MovieTop250Fragment extends BaseListFragment implements MovieView {
    private static final String TAG = MovieTop250Fragment.class.getSimpleName();
    private MoviePresenter mMoviePresenter = new MoviePresenterImpl(this);

    private static final String MOVIE_TYPE = "type";
    private List<MoviesBean> mData;
    private MovieTop250Adapter mMovieTop250Adapter;
    private static int start = 0;
    private static final int count = 20;

    public static MovieTop250Fragment newInstance(int type) {
        MovieTop250Fragment nfm = new MovieTop250Fragment();
        Bundle args = new Bundle();
        args.putInt(MOVIE_TYPE, type);
        nfm.setArguments(args);

        return nfm;
    }


    @Override
    public RecyclerView.Adapter bindAdapter() {
        mMovieTop250Adapter = new MovieTop250Adapter(getActivity());
        return mMovieTop250Adapter;
    }

    @Override
    protected void loadFromNet() {
        int start = 0;
        mMoviePresenter.getMovieTop250(start, count);
    }

    @Override
    protected void loadingMoreData() {
        if (isLoadMore) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    start += count;
                    mMoviePresenter.getMovieTop250(start, count);
                    Log.d(TAG, "loadingMoreData: ");
                }
            }, 1000);
        }

    }

    @Override
    protected void setScrollListener() {
        recyclerView.addOnScrollListener(scrollListener);

    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void addData(List<MoviesBean> list) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(list);
        if (!isLoadMore) {
            mMovieTop250Adapter.setData(mData);
        }
        if (list==null){
            mMovieTop250Adapter.updateStatus(MovieTop250Adapter.LOAD_NONE);
        } else {
            mMovieTop250Adapter.updateStatus(MovieTop250Adapter.LOAD_PULL_TO);
        }
        mMovieTop250Adapter.notifyDataSetChanged();

    }

    @Override
    public void showProgress() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mRefreshLayout.setRefreshing(false);
    }

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (!recyclerView.canScrollVertically(1)) {
                //手机向上不能滑动,到达底部
                isLoadMore = true;

                mMovieTop250Adapter.updateStatus(MovieTop250Adapter.LOAD_MORE);

                loadingMoreData();
            }
        }

    };

}
