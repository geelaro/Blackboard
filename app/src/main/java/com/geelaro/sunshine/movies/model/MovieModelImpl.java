package com.geelaro.sunshine.movies.model;

import android.graphics.Movie;

import com.geelaro.sunshine.beans.MoviesBean;
import com.geelaro.sunshine.utils.MovieJsonUtils;
import com.geelaro.sunshine.utils.OkHttpUtils;
import com.geelaro.sunshine.utils.SunApi;

import java.util.List;

/**
 * Created by LEE on 2018/1/16.
 */

public class MovieModelImpl implements MovieModel {
    @Override
    public void loadDataFromNet(final OnLoadMovieListener listener) {
        OkHttpUtils.ResultCallback<String> loadDataFromNet = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onFailure(Exception e) {
                listener.onFailure("Fail to get MoviesData",e);
            }

            @Override
            public void onSuccess(String response) {
                List<MoviesBean> beanList = MovieJsonUtils.getMovieBean(response);
                listener.onSuccess(beanList);
            }
        };

        OkHttpUtils.newInstance().get(SunApi.MOVIE_TOP_250,loadDataFromNet);
    }


    public interface OnLoadMovieListener{
        void onSuccess(List<MoviesBean> list);
        void onFailure(String msg,Exception e);
    }
}
