package com.geelaro.sunboard.base;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.geelaro.sunboard.R;

/**
 * Created by LEE on 2018/1/17.
 */

public abstract class BaseWebViewActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ProgressBar mProgressBar;
    protected WebView mWebView;
    //
    protected String URL = null;


    protected abstract String getDetailUrl();

    protected abstract void loadData(String data);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_detail);
        mToolbar = (Toolbar) findViewById(R.id.detail_bar);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mWebView = (WebView) findViewById(R.id.detail_content);
        //URL
        URL = getDetailUrl();
        //Toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setTitleTextAppearance(this, R.style.ToolBarTextAppearance);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        initWebSettings();

        loadData(URL);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView != null) mWebView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) mWebView.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWebView != null) mWebView.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.news_share:
                //系统分享
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, URL);
                startActivity(Intent.createChooser(intent, getTitle()));
                return true;

            case R.id.refresh:
                mWebView.reload();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //WebView init
    private void initWebSettings() {
        WebSettings settings = mWebView.getSettings();
        //设置AppCache存储路径
        String cacheDirPath = getApplicationContext().getFilesDir().getAbsolutePath() + "cache/";
        settings.setAppCachePath(cacheDirPath);
        //存储大小
        settings.setAppCacheMaxSize(20 * 1024 * 1024);
        //开启Application Cache存储机制
        settings.setAppCacheEnabled(true);

        settings.setJavaScriptEnabled(true); //开启js,即开启IndexedDB
        settings.setDomStorageEnabled(true);//开启Dom Storage缓存机制

        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (URLUtil.isNetworkUrl(url)) {
                    return false;
                }
                if (url.startsWith("https")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
                return true;
            }
        });
    }


}
