package com.geelaro.sunshine.movies.view;

import com.geelaro.sunshine.beans.MoviesBean;

import java.util.List;

/**
 * Created by LEE on 2018/1/16.
 */

public interface MovieView {

    void addData(List<MoviesBean> list);

    void showProgress();

    void hideProgress();

}
