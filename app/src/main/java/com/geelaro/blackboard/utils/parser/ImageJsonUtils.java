package com.geelaro.blackboard.utils.parser;

import com.geelaro.blackboard.base.beans.ImageBean;
import com.geelaro.blackboard.utils.JsonUtils;
import com.geelaro.blackboard.utils.SunLog;
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
        List<ImageBean> imageBeanList = new ArrayList<>();

        try {
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(res).getAsJsonArray();
            for (JsonElement image : jsonArray) {
                ImageBean news = JsonUtils.deserialize((JsonObject) image, ImageBean.class);
                imageBeanList.add(news);
            }

        } catch (Exception e) {
            SunLog.e(TAG, "getImageBeanError", e);
        }
        return imageBeanList;
    }
}
