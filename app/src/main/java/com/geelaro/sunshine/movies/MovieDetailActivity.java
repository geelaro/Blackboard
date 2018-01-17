package com.geelaro.sunshine.movies;

import com.geelaro.sunshine.base.BaseWebViewActivity;
import com.geelaro.sunshine.beans.MoviesBean;

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
