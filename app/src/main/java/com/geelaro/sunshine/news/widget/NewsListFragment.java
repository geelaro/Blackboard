package com.geelaro.sunshine.news.widget;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geelaro.sunshine.R;
import com.geelaro.sunshine.beans.NewsBean;
import com.geelaro.sunshine.news.NewsAdapter;
import com.geelaro.sunshine.news.presenter.NewsPresenter;
import com.geelaro.sunshine.news.presenter.NewsPresenterImpl;
import com.geelaro.sunshine.news.view.NewsView;
import com.geelaro.sunshine.utils.ShowToast;
import com.geelaro.sunshine.utils.SunLog;
import com.geelaro.sunshine.utils.SunshineApp;

import java.util.ArrayList;
import java.util.List;

/**
 * News tab的页面布局
 * Created by geelaro on 2017/6/15.
 */

public class NewsListFragment extends Fragment implements NewsView, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = NewsListFragment.class.getSimpleName(); //TAG
    private static final String NEWS_TYPE = "type";
    private int pageIndex = 0;
    private int mType;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private NewsAdapter mNewsAdapter;
    private List<NewsBean> mData;
    private NewsPresenter mPresenter;
    private SwipeRefreshLayout mRefreshLayout;


    public static NewsListFragment newInstance(int type) {
        NewsListFragment nfm = new NewsListFragment();
        Bundle args = new Bundle();
        args.putInt(NEWS_TYPE, type);
        nfm.setArguments(args);
        SunLog.d("type: ",""+type);

        return nfm;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new NewsPresenterImpl(this);
        mType = getArguments().getInt(NEWS_TYPE);
        SunLog.d("mType",""+mType);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View newsView = inflater.inflate(R.layout.frame_news_list, container, false);

        mRefreshLayout = (SwipeRefreshLayout) newsView.findViewById(R.id.news_refresh);
        /**设置刷新时旋转图标的颜色，这是一个可变参数，当设置多个颜色时，旋转一周改变一次颜色 */
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(this);

        mNewsAdapter = new NewsAdapter(SunshineApp.getContext());
        mNewsAdapter.setOnItemClickListener(itemClickListener);
        manager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) newsView.findViewById(R.id.recycleView_news);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mNewsAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(mOnScrollListener);

        onRefresh();

        return newsView;
    }

    //加载更多
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE&&
                    lastVisibleItem + 1 == mNewsAdapter.getItemCount()) {
                //加载更多
//                mPresenter.loadNewsList(mType, pageIndex + Urls.PAGE_NUM);
                ShowToast.Short("向上滑动加载更多精彩内容...");
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = manager.findLastVisibleItemPosition();
        }
    };

    @Override
    public void addNewsData(List<NewsBean> list) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.clear();//clear data
        mData.addAll(list);
        if (pageIndex == 0) {
            mNewsAdapter.setData(mData);
        } else {
            mNewsAdapter.notifyDataSetChanged();
        }

//        pageIndex += Urls.PAGE_NUM; //new pageIndex
    }

    private NewsAdapter.OnItemClickListener itemClickListener = new NewsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            if (mData.size() <= 0) {
                return;
            }
            NewsBean news = mData.get(position);
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            intent.putExtra("news", news);

            startActivity(intent);
        }
    };

    @Override
    public void showProgress() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showErrorMsg(String msg) {
        ShowToast.Short(msg);
    }

    @Override
    public void onRefresh() {
        int pageIndex = 0;

        mPresenter.loadNewsList(mType, pageIndex);
    }
}
