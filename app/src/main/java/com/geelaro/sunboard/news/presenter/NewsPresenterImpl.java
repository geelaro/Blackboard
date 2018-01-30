package com.geelaro.sunboard.news.presenter;

import com.geelaro.sunboard.base.beans.NewsBean;
import com.geelaro.sunboard.news.model.NewsModel;
import com.geelaro.sunboard.news.model.NewsModelImpl;
import com.geelaro.sunboard.news.model.OnLoadNewsListener;
import com.geelaro.sunboard.news.view.NewsView;
import com.geelaro.sunboard.utils.SunApi;
import com.geelaro.sunboard.utils.SunLog;
import com.geelaro.sunboard.news.widget.NewsFragment;

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
    public void loadNewsList(int type, int pageIndex) {
        String url = getUrl(type, pageIndex);
        if (pageIndex == 0) {
            mNewsView.showProgress();
        }
        mNewsModel.loadNewsList(type, url, this);
        SunLog.d("NewsURL: ",url);
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
    public static String getUrl(int type, int pageIndex) {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                sb.append(SunApi.NEWS_URL_HOST).append(SunApi.TOP_ID);
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                sb.append(SunApi.NEWS_COMMON_URL).append(SunApi.NBA_ID);
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                sb.append(SunApi.NEWS_COMMON_URL).append(SunApi.CAR_ID);
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                sb.append(SunApi.NEWS_COMMON_URL).append(SunApi.JOKE_ID);
                break;
            default:
                sb.append(SunApi.NEWS_URL_HOST).append(SunApi.TOP_ID);
        }
        sb.append("/").append(pageIndex).append(SunApi.END_URL);
        return sb.toString();
    }
}
