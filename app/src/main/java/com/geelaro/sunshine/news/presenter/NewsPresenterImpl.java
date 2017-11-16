package com.geelaro.sunshine.news.presenter;

import com.geelaro.sunshine.beans.NewsBean;
import com.geelaro.sunshine.news.model.NewsModel;
import com.geelaro.sunshine.news.model.NewsModelImpl;
import com.geelaro.sunshine.news.model.OnLoadNewsListener;
import com.geelaro.sunshine.news.view.NewsView;

import java.util.List;

/**
 * Created by LEE on 2017/10/31.
 */

public class NewsPresenterImpl implements NewsPresenter,OnLoadNewsListener {
    private NewsView mNewsView;
    private NewsModel mNewsModel;

    public NewsPresenterImpl(NewsView newsView){
        mNewsView = newsView;
        mNewsModel = new NewsModelImpl();
    }
    @Override
    public void loadNewsList() {
        mNewsModel.loadNewsList(this);
    }

    @Override
    public void onSuccess(List<NewsBean> list) {
        mNewsView.addNewsData(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {

    }
}
