package com.geelaro.blackboard.movies.widget;

import com.geelaro.blackboard.base.BaseWebViewActivity;

/**
 * Created by geelaro on 2018/1/17.
 * 电影详情页
 */

public class MovieDetailActivity extends BaseWebViewActivity {
    @Override
    protected String getDetailUrl() {
        return getIntent().getStringExtra("movies");
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
