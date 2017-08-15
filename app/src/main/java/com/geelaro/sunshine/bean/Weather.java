package com.geelaro.sunshine.bean;


/**
 * Created by geelaro on 2017/6/22.
 */

public class Weather {

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

}
