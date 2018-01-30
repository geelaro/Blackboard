package com.geelaro.blackboard.news.model;

import com.geelaro.blackboard.base.beans.NewsBean;
import com.geelaro.blackboard.news.widget.NewsFragment;
import com.geelaro.blackboard.utils.NewsJsonUtils;
import com.geelaro.blackboard.utils.OkHttpUtils;
import com.geelaro.blackboard.utils.SunApi;

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

        OkHttpUtils.newInstance().get(url,loadNewsCallBack);
    }

    private String getId(int type){
        String id ;
        switch (type){
            case NewsFragment.NEWS_TYPE_TOP:
                id = SunApi.TOP_ID;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                id = SunApi.NBA_ID;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                id = SunApi.CAR_ID;
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                id = SunApi.JOKE_ID;
                break;
            default:
                id = SunApi.TOP_ID;
                break;
        }
        return id;
    }



}
