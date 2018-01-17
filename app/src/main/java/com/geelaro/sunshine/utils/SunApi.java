package com.geelaro.sunshine.utils;

/**
 * Created by geelaro on 2017/9/29.
 */

public class SunApi {
    //Weather url
    public static final String WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
    //Images url
    public static final String IMAGE_URL = "http://api.laifudao.com/open/tupian.json";
    //News url
    public static final String NEWS_URL_HOST = "http://c.m.163.com/nc/article/headline/";
    //头条ID
    public static final String TOP_ID = "T1348647909107";
    // nba ID
    public static final String NBA_ID = "T1348649145984";
    // 汽车ID
    public static final String CAR_ID = "T1348654060988";
    // 笑话ID
    public static final String JOKE_ID = "T1350383429665";
    //Common
    public static final String NEWS_COMMON_URL = "http://c.m.163.com/nc/article/list/";
    //
    public static final int PAGE_NUM = 10;
    private static final int NEWS_PAGE_SIZE = 20;
    public static final String END_URL =  "-" + NEWS_PAGE_SIZE + ".html";

    //douban Top 250
    public static final String MOVIE_TOP_250 = "http://api.douban.com/v2/movie/top250";
    private static final int MOVIE_PAGE_SIZE = 25;


}
