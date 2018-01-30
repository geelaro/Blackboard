package com.geelaro.blackboard.base.beans;

import java.io.Serializable;

/**
 * Created by geelaro on 2017/10/31.
 */

public class NewsBean implements Serializable {
    private String imgsrc;
    private String title;
    private String digest;
    private String source;
    private String mtime;
    private String url;

    public String getImgsrc() {
        return imgsrc;
    }

    public String getTitle() {
        return title;
    }

    public String getDigest() {
        return digest;
    }

    public String getDetailUrl() {
        return url;
    }

    public String getSource() {
        return source;
    }

    public String getMtime() {
        return mtime;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public void setDetailUrl(String url) {
        this.url = url;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }
}
