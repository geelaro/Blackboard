package com.geelaro.sunshine.beans;


/**
 * Created by geelaro on 2017/6/22.
 */

public class WeatherBean {

    private String cityName;
    private int imgId; //天气图
    private String desc; //天气状况
    private Long maxTemp;//最高温
    private Long minTemp;//最低温

    public String getCityName() {
        return cityName;
    }

    public int getImgId() {
        return imgId;
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

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setMaxTemp(Long maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setMinTemp(Long minTemp) {
        this.minTemp = minTemp;
    }

}
