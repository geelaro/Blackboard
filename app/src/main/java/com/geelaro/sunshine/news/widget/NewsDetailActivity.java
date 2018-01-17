package com.geelaro.sunshine.news.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.geelaro.sunshine.R;
import com.geelaro.sunshine.base.BaseWebViewActivity;
import com.geelaro.sunshine.beans.NewsBean;

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
