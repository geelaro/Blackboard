package com.geelaro.sunboard.base.beans;

import java.io.Serializable;

/**
 * Created by geelaro on 2017/10/12.
 */

public class ImageBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String thumburl;
    private String sourceurl;
    private int height;
    private int width;

    public String getTitle() {
        return title;
    }

    public String getSourceurl() {
        return sourceurl;
    }

    public String getThumburl() {
        return thumburl;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
