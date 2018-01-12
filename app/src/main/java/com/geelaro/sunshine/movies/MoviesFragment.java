package com.geelaro.sunshine.movies;

import android.support.v7.widget.RecyclerView;

import com.geelaro.sunshine.BaseListFragment;

/**
 * Created by geelaro on 2018/1/10.
 */

public class MoviesFragment extends BaseListFragment {


    @Override
    public RecyclerView.Adapter bindAdapter() {
        return new MoviesAdapter();
    }

    @Override
    protected void loadFromNet() {

    }


}
