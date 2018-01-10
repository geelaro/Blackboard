package com.geelaro.sunshine.news.model;

import com.geelaro.sunshine.beans.NewsBean;
import com.geelaro.sunshine.news.widget.NewsFragment;
import com.geelaro.sunshine.utils.NewsJsonUtils;
import com.geelaro.sunshine.utils.OkHttpUtils;
import com.geelaro.sunshine.utils.Urls;

import java.util.List;

/**
 * Created by geelaro on 2017/10/31.
 */

public class NewsModelImpl implements NewsModel {

    @Override
    public void loadNewsList(final int type,String url,final OnLoadNewsListener listener) {


        OkHttpUtils.ResultCallback<String> loadNewsCallBack = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onFailure(Exception e) {
                listener.onFailure("Fail to load News.",e);
            }

            @Override
            public void onSuccess(String response) {
                List<NewsBean> newsBeanList = NewsJsonUtils.getNewsBean(response, getId(type));
                listener.onSuccess(newsBeanList);
            }
        };

        OkHttpUtils.get(url,loadNewsCallBack);
    }

    private String getId(int type){
        String id ;
        switch (type){
            case NewsFragment.NEWS_TYPE_TOP:
                id = Urls.TOP_ID;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                id = Urls.NBA_ID;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                id = Urls.CAR_ID;
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                id = Urls.JOKE_ID;
                break;
            default:
                id = Urls.TOP_ID;
                break;
        }
        return id;
    }



}
