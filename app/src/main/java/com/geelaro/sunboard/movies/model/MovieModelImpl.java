package com.geelaro.sunboard.movies.model;

import android.util.Log;

import com.geelaro.sunboard.base.beans.MoviesBean;
import com.geelaro.sunboard.utils.MovieJsonUtils;
import com.geelaro.sunboard.utils.NetworkUtils;
import com.geelaro.sunboard.utils.OkHttpUtils;

import java.util.List;

/**
 * Created by geelaro on 2018/1/16.
 */

public class MovieModelImpl implements MovieModel {
    @Override
    public void loadDataFromNet(final OnLoadMovieListener listener, final int start) {
        OkHttpUtils.ResultCallback<String> loadDataFromNet = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onFailure(Exception e) {
                listener.onFailure("Fail to get MoviesData",e);
            }

            @Override
            public void onSuccess(String response) {
                List<MoviesBean> beanList = MovieJsonUtils.getMovieBean(response,start);
                listener.onSuccess(beanList);
            }
        };

        OkHttpUtils.newInstance().get(NetworkUtils.getTop250URL(start,50),loadDataFromNet);
        Log.d("1122", "loadDataFromNet: "+NetworkUtils.getTop250URL(start,50));
    }


    public interface OnLoadMovieListener{
        void onSuccess(List<MoviesBean> list);
        void onFailure(String msg,Exception e);
    }
}
