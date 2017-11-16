package com.geelaro.sunshine.news.model;

import com.geelaro.sunshine.beans.NewsBean;

import java.util.List;

/**
 * Created by LEE on 2017/11/1.
 */

public interface OnLoadNewsListener {
    void onSuccess(List<NewsBean> list);

    void onFailure(String msg, Exception e);

}
