package com.geelaro.sunshine.news.presenter;

import com.geelaro.sunshine.beans.NewsBean;
import com.geelaro.sunshine.news.model.NewsModel;
import com.geelaro.sunshine.news.model.NewsModelImpl;
import com.geelaro.sunshine.news.model.OnLoadNewsListener;
import com.geelaro.sunshine.news.view.NewsView;
import com.geelaro.sunshine.utils.Urls;
import com.geelaro.sunshine.news.widget.NewsFragmentManager;

import java.util.List;

/**
 * Created by geelaro on 2017/10/31.
 */

public class NewsPresenterImpl implements NewsPresenter, OnLoadNewsListener {
    private NewsView mNewsView;
    private NewsModel mNewsModel;

    public NewsPresenterImpl(NewsView newsView) {
        mNewsView = newsView;
        mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNewsList(int type) {
        String url = getUrl(type);
        mNewsModel.loadNewsList(type, url, this);
    }

    @Override
    public void onSuccess(List<NewsBean> list) {
        mNewsView.addNewsData(list);
        mNewsView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsView.hideProgress();
        mNewsView.showErrorMsg(msg);
    }

    /**
     * 获取相应新闻页面的url
     */
    public static String getUrl(int type) {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case NewsFragmentManager.NEWS_TYPE_TOP:
                sb.append(Urls.NEWS_URL_HOST).append(Urls.TOP_ID);
                break;
            case NewsFragmentManager.NEWS_TYPE_NBA:
                sb.append(Urls.NEWS_COMMON_URL).append(Urls.NBA_ID);
                break;
            case NewsFragmentManager.NEWS_TYPE_CARS:
                sb.append(Urls.NEWS_COMMON_URL).append(Urls.CAR_ID);
                break;
            case NewsFragmentManager.NEWS_TYPE_JOKES:
                sb.append(Urls.NEWS_COMMON_URL).append(Urls.JOKE_ID);
                break;
            default:
                sb.append(Urls.NEWS_COMMON_URL).append(Urls.JOKE_ID);
        }
        sb.append("/").append(Urls.END_URL);
        return sb.toString();
    }
}
