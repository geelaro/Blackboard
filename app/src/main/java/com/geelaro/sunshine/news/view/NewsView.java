package com.geelaro.sunshine.news.view;

import com.geelaro.sunshine.beans.NewsBean;

import java.util.List;

/**
 * Created by geelaro on 2017/10/31.
 */

public interface NewsView {
    void addNewsData(List<NewsBean> list);
    void showProgress();
    void hideProgress();

}
