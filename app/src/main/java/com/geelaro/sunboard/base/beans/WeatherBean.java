package com.geelaro.sunboard.base.beans;


/**
 * Created by geelaro on 2017/6/22.
 */

public class WeatherBean {

    private int weatherId;
    private String desc; //天气状况
    private double maxTemp;//最高温
    private double minTemp;//最低温
    private String date;


    public int getWeatherId() {
        return weatherId;
    }

    public String getDesc() {
        return desc;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public String getDate() {
        return date;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
