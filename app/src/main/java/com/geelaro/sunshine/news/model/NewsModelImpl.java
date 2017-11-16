package com.geelaro.sunshine.news.model;

import com.geelaro.sunshine.beans.NewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geelaro on 2017/10/31.
 */

public class NewsModelImpl implements NewsModel {

    @Override
    public void loadNewsList(OnLoadNewsListener listener) {
        List<NewsBean> list = new ArrayList<>();
        NewsBean newsBean = new NewsBean();
        newsBean.setImgsrc("http://cms-bucket.nosdn.127.net/05a02f177d794158a27174cbf44d247c20171104132521.jpeg");
        newsBean.setNewsTitle("俄'人民团结日' 普京前往红场献花");
        newsBean.setNewsText("新华社北京11月5日电题：积极探索实践形成宝贵经验国家监察体制改革试点取得实效");

        list.add(newsBean);

        listener.onSuccess(list);
    }

}
