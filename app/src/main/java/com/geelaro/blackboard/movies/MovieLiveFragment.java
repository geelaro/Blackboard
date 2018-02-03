package com.geelaro.blackboard.movies;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.geelaro.blackboard.adapter.MovieLiveAdapter;
import com.geelaro.blackboard.base.BaseListFragment;
import com.geelaro.blackboard.base.beans.MoviesBean;
import com.geelaro.blackboard.movies.presenter.MoviePresenter;
import com.geelaro.blackboard.movies.presenter.MoviePresenterImpl;
import com.geelaro.blackboard.movies.view.MovieView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LEE on 2018/2/3.
 */

public class MovieLiveFragment extends BaseListFragment implements MovieView {
    private static final String MOVIE_TYPE = "type";
    private List<MoviesBean> mData;
    private MovieLiveAdapter movieLiveAdapter;
    private MoviePresenter moviePresenter = new MoviePresenterImpl(this);


    public static MovieLiveFragment newInstance(int type) {
        MovieLiveFragment nfm = new MovieLiveFragment();
        Bundle args = new Bundle();
        args.putInt(MOVIE_TYPE, type);
        nfm.setArguments(args);

        return nfm;
    }

    @Override
    protected RecyclerView.Adapter bindAdapter() {
        movieLiveAdapter  = new MovieLiveAdapter(getActivity());
        return movieLiveAdapter;
    }

    @Override
    protected void loadFromNet() {
        moviePresenter.getMovieLive(0,20);
    }

    @Override
    protected void loadingMoreData() {

    }

    @Override
    protected void setScrollListener() {

    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getActivity(),3);
    }

    @Override
    public void addData(List<MoviesBean> list) {
        if (mData==null){
            mData=new ArrayList<>();
        }
        mData.addAll(list);
        movieLiveAdapter.setData(mData);

    }

    @Override
    public void showProgress() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mRefreshLayout.setRefreshing(false);
    }
}
