package com.geelaro.blackboard.utils;

import com.geelaro.blackboard.base.beans.NewsBean;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geelaro on 2017/11/29.
 */

public class NewsJsonUtils {
    private static final String TAG = NewsJsonUtils.class.getSimpleName();

    public static List<NewsBean> getNewsBean(String strRes,String value){
        List<NewsBean> newsBeanList = new ArrayList<>();

        try {
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(strRes).getAsJsonObject();
            JsonElement jsonElement = jsonObject.get(value);
            if (jsonElement==null){
                return null;
            }
            //Json转成Json数组
            JsonArray jsonArray = jsonElement.getAsJsonArray();

            int size = jsonArray.size();
            for (int i=0;i<size;i++){
                JsonObject jObject = jsonArray.get(i).getAsJsonObject();
                if (jObject.has("skipType")&&"special".equals(jObject.get("skipType").getAsString())){
                    continue;
                }
                if (jObject.has("TAGS")&&!jObject.has("TAG")){
                    continue;
                }
                if (!jObject.has("imgextra")){
                    NewsBean news = JsonUtils.deserialize(jObject,NewsBean.class);
                    newsBeanList.add(news);
                }
            }

        }catch (Exception e){
            SunLog.e(TAG,"getNewsBeanError",e);
        }

        return newsBeanList;
    }
}
