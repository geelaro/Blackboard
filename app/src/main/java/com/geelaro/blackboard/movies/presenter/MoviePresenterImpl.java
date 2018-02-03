package com.geelaro.blackboard.movies.presenter;

import android.util.Log;

import com.geelaro.blackboard.base.beans.MoviesBean;
import com.geelaro.blackboard.movies.model.MovieModel;
import com.geelaro.blackboard.movies.model.MovieModelImpl;
import com.geelaro.blackboard.movies.view.MovieView;
import com.geelaro.blackboard.utils.SunApi;

import java.util.List;

/**
 * Created by LEE on 2018/1/16.
 */

public class MoviePresenterImpl implements MoviePresenter{
    private MovieModel mModel;
    private MovieView mMovieView;

    public MoviePresenterImpl(MovieView mMovieView){
        this.mMovieView = mMovieView;
        mModel = new MovieModelImpl();
    }
    @Override
    public void getMovieTop250(final int start, final int count) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mModel.loadDataFromNet(listener,SunApi.MOVIE_TOP_250,start,count);

            }
        }).start();
    }

    @Override
    public void getMovieLive(final int start, final int count) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mModel.loadDataFromNet(listener,SunApi.MoVIE_IN_THEATERS,start,count);

            }
        }).start();
    }

    private MovieModelImpl.OnLoadMovieListener listener = new MovieModelImpl.OnLoadMovieListener() {
        @Override
        public void onSuccess(List<MoviesBean> list) {
            mMovieView.addData(list);
            mMovieView.hideProgress();
        }

        @Override
        public void onFailure(String msg, Exception e) {
            mMovieView.hideProgress();
        }
    };

//    private String getMovieBaseURL(int type){
//        switch (type){
//            case 0:
//                return SunApi.MoVIE_IN_THEATERS;
//            case 1:
//                return SunApi.MOVIE_TOP_250;
//            default:
//                throw new IllegalArgumentException("Unknown Fragment's type");
//        }




}
