package com.geelaro.sunshine.movies.model;

/**
 * Created by LEE on 2018/1/16.
 */

public interface MovieModel {

    void loadDataFromNet(MovieModelImpl.OnLoadMovieListener listener,int start);
}