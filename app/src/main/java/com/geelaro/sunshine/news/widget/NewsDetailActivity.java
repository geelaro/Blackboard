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
import com.geelaro.sunshine.beans.NewsBean;

/**
 * Created by geelaro on 2017/12/4.
 * 新闻详情页
 */

public class NewsDetailActivity extends AppCompatActivity {
    private final static String TAG = NewsDetailActivity.class.getSimpleName();  //TAG
    //
    private Toolbar mToolbar;
    private ProgressBar mProgressBar;
    private WebView mWebView;
    //
    private String URL = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);
        mToolbar = (Toolbar)findViewById(R.id.detail_bar);
        mProgressBar = (ProgressBar)findViewById(R.id.progressbar);
        mWebView = (WebView) findViewById(R.id.detail_content);
        //URL
        NewsBean newsBean = (NewsBean) getIntent().getSerializableExtra("news");
        URL = newsBean.getDetailUrl();
        //Toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setTitleTextAppearance(this,R.style.ToolBarTextAppearance);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        initWebSettings();

        mWebView.loadUrl(URL);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView!=null) mWebView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView!=null) mWebView.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWebView!=null) mWebView.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.news_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, URL);
                startActivity(Intent.createChooser(intent, getTitle()));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //WebView init
    private void initWebSettings() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
                if (newProgress==100){
                    mProgressBar.setVisibility(View.GONE);
                }else {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        });

        mWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null) view.loadUrl(url);
                return true;
            }
        });
    }


}
