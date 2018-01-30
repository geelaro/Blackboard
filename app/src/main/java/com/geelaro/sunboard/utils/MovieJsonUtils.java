package com.geelaro.sunboard.utils;

import com.geelaro.sunboard.base.beans.MoviesBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geelaro on 2018/1/17.
 */

public class MovieJsonUtils {
    private static final String MOV_LIST = "subjects";
    private static final String MOV_RATING = "rating";
    private static final String MOV_AVERAGE = "average";
    private static final String MOV_TITLE = "title";
    private static final String MOV_YEAR = "year";
    private static final String MOV_ALT = "alt";
    private static final String MOV_IMAGES = "images";
    private static final String MOV_IMAGE_MEDIUM = "small";


    public static List<MoviesBean> getMovieBean(String jsonStr,int start){
        List<MoviesBean> moviesBeanList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray movieArray = jsonObject.getJSONArray(MOV_LIST);

            int size = movieArray.length();
            for (int i=0;i<size;i++){
                JSONObject movieObj = movieArray.getJSONObject(i);
                //平均分
                JSONObject ratingObj = movieObj.getJSONObject(MOV_RATING);
                String average = ratingObj.getString(MOV_AVERAGE);
                //movie title
                String title = movieObj.getString(MOV_TITLE); //电影名
                String year = movieObj.getString(MOV_YEAR); //年代
                String alt = movieObj.getString(MOV_ALT); //电影详情页
                //
                JSONObject imageObj = movieObj.getJSONObject(MOV_IMAGES);
                String imageUri = imageObj.getString(MOV_IMAGE_MEDIUM);

                MoviesBean moviesBean = new MoviesBean();
                moviesBean.setMovieNo(start+i+1);
                moviesBean.setTitile(title);
                moviesBean.setYear(year);
                moviesBean.setScore(average);
                moviesBean.setImgaeUri(imageUri);
                moviesBean.setAlt(alt);

                moviesBeanList.add(moviesBean);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return moviesBeanList;
    }
}
