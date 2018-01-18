package com.geelaro.sunshine.beans;

import java.io.Serializable;

/**
 * Created by geelaro on 2018/1/16.
 */

public class MoviesBean implements Serializable {

    private int movieNo;
    private String titile;
    private String imgaeUri;
    private String year;
    private String alt;
    private String score;



    public void setMovieNo(int movieNo) {
        this.movieNo = movieNo;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public void setImgaeUri(String imgaeUri) {
        this.imgaeUri = imgaeUri;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public int getMovieNo(){
        return movieNo;
    }
    public String getTitile() {
        return titile;
    }

    public String getImgaeUri() {
        return imgaeUri;
    }

    public String getYear() {
        return year;
    }

    public String getScore() {
        return score;
    }

    public String getAlt() {
        return alt;
    }

}
