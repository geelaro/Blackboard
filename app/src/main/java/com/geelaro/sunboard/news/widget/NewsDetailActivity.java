package com.geelaro.sunboard.news.widget;

import com.geelaro.sunboard.base.BaseWebViewActivity;
import com.geelaro.sunboard.base.beans.NewsBean;

/**
 * Created by geelaro on 2017/12/4.
 * 新闻详情页
 */

public class NewsDetailActivity extends BaseWebViewActivity {
    private final static String TAG = NewsDetailActivity.class.getSimpleName();  //TAG

    @Override
    protected String getDetailUrl() {
        NewsBean newsBean = (NewsBean) getIntent().getSerializableExtra("news");
        return newsBean.getDetailUrl();
    }

    @Override
    protected void loadData(final String data) {
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl(data);
            }
        });
    }
}
