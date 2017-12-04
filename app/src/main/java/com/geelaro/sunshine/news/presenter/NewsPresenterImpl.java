package com.geelaro.sunshine.news.presenter;

import com.geelaro.sunshine.beans.NewsBean;
import com.geelaro.sunshine.news.model.NewsModel;
import com.geelaro.sunshine.news.model.NewsModelImpl;
import com.geelaro.sunshine.news.model.OnLoadNewsListener;
import com.geelaro.sunshine.news.view.NewsView;
import com.geelaro.sunshine.utils.Urls;

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
    public void loadNewsList(int type) {
        String url = getUrl();
        mNewsModel.loadNewsList(type,url,this);
    }

    @Override
    public void onSuccess(List<NewsBean> list) {
        mNewsView.addNewsData(list);
        mNewsView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsView.hideProgress();
    }

    /**
     * 获取相应新闻页面的url
     */
    public static String getUrl(){
        StringBuffer sb = new StringBuffer();
        sb.append(Urls.NEWS_URL_HOST).append(Urls.TOP_ID).append("/").append(Urls.END_URL);
        return sb.toString();
    }
}
