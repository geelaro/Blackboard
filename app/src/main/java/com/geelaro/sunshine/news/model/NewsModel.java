package com.geelaro.sunshine.news.model;

/**
 * Created by geelaro on 2017/10/31.
 */

public interface NewsModel {
    void loadNewsList(int type,String url,OnLoadNewsListener listener);

}
