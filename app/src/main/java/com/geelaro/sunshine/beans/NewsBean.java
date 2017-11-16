package com.geelaro.sunshine.beans;

/**
 * Created by geelaro on 2017/10/31.
 */

public class NewsBean {
    private String imgsrc;
    private String newsTitle;
    private String newsText;

    public String getImgsrc() {
        return imgsrc;
    }

    public String getNewsText() {
        return newsText;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
}
