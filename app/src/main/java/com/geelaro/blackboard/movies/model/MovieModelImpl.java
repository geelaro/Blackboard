package com.geelaro.blackboard.movies.model;

import android.util.Log;

import com.geelaro.blackboard.base.beans.MoviesBean;
import com.geelaro.blackboard.utils.parser.MovieJsonUtils;
import com.geelaro.blackboard.utils.NetworkUtils;
import com.geelaro.blackboard.utils.OkHttpUtils;

import java.util.List;

/**
 * Created by geelaro on 2018/1/16.
 */

public class MovieModelImpl implements MovieModel {
    @Override
    public void loadDataFromNet(final OnLoadMovieListener listener,String url, final int start,int count) {
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

        OkHttpUtils.newInstance().get(NetworkUtils.getMovieURL(url,start,count),loadDataFromNet);

    }


    public interface OnLoadMovieListener{
        void onSuccess(List<MoviesBean> list);
        void onFailure(String msg,Exception e);
    }
}
