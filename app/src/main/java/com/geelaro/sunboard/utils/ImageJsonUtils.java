package com.geelaro.sunboard.utils;

import com.geelaro.sunboard.base.beans.ImageBean;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geelaro on 2017/10/14.
 */

public class ImageJsonUtils {
    private final static String TAG = ImageJsonUtils.class.getSimpleName();

    public static List<ImageBean> getImageBean(String res) {
        //直接解析成一个List
//        List<ImageBean> imageBeanList = gson.fromJson(res, new TypeToken<List<ImageBean>>(){}.getType());
        List<ImageBean> imageBeanList = new ArrayList<>();

        try {
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(res).getAsJsonArray();
            for (JsonElement image : jsonArray) {
                ImageBean news = JsonUtils.deserialize((JsonObject) image, ImageBean.class);
                imageBeanList.add(news);
//            JSONArray jsonArray = new JSONArray(res);
//            for (int i =0;i<jsonArray.length();i++){
//                JSONObject imageObj = jsonArray.getJSONObject(i);
//                ImageBean news  = new ImageBean();
//                news.setTitle(imageObj.getString("title"));
//                news.setThumburl("thumburl");
//                imageBeanList.add(news);
            }

        } catch (Exception e) {
            SunLog.e(TAG, "getImageBeanError", e);
        }
        return imageBeanList;
    }
}
