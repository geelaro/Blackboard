package com.geelaro.sunshine.movies.presenter;

import com.geelaro.sunshine.beans.MoviesBean;
import com.geelaro.sunshine.movies.model.MovieModel;
import com.geelaro.sunshine.movies.model.MovieModelImpl;
import com.geelaro.sunshine.movies.view.MovieView;

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
    public void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mModel.loadDataFromNet(listener);
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

}
