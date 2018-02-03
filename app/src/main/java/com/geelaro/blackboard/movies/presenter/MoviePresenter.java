package com.geelaro.blackboard.movies.presenter;

/**
 * Created by LEE on 2018/1/16.
 */

public interface MoviePresenter {
    void getMovieTop250(int start,int count);
    void getMovieLive(int start,int count);
}
