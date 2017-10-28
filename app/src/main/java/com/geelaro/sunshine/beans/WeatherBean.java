package com.geelaro.sunshine.beans;


/**
 * Created by geelaro on 2017/6/22.
 */

public class WeatherBean {

    private int weatherId;
    private String desc; //天气状况
    private Long maxTemp;//最高温
    private Long minTemp;//最低温
    private String date;


    public int getWeatherId() {
        return weatherId;
    }

    public String getDesc() {
        return desc;
    }

    public Long getMaxTemp() {
        return maxTemp;
    }

    public Long getMinTemp() {
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


    public void setMaxTemp(Long maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setMinTemp(Long minTemp) {
        this.minTemp = minTemp;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
