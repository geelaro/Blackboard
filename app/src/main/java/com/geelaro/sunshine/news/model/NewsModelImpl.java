package com.geelaro.sunshine.news.model;

import com.geelaro.sunshine.beans.NewsBean;
import com.geelaro.sunshine.news.NewsFragmentManager;
import com.geelaro.sunshine.utils.JsonUtils;
import com.geelaro.sunshine.utils.NewsJsonUtils;
import com.geelaro.sunshine.utils.OkHttpUtils;
import com.geelaro.sunshine.utils.Urls;

import java.util.ArrayList;
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
                List<NewsBean> newsBeanList = NewsJsonUtils.getNewsBean(response, Urls.TOP_ID);
                listener.onSuccess(newsBeanList);
            }
        };

        OkHttpUtils.get(url,loadNewsCallBack);
    }

    private String getId(int type){
        String id = null;
        switch (type){
            case NewsFragmentManager.NEWS_TYPE_TOP:
                id = Urls.TOP_ID;
                break;
            case NewsFragmentManager.NEWS_TYPE_NBA:
                break;
            case NewsFragmentManager.NEWS_TYPE_CARS:
                break;
            case NewsFragmentManager.NEWS_TYPE_JOKES:
                break;
            default:
                id = Urls.TOP_ID;
                break;
        }
        return id;
    }



}
