package com.geelaro.sunshine.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

/**
 * Created by LEE on 2017/10/14.
 */

public class JsonUtils {
    private static Gson gson = new GsonBuilder().create();


    /**
     * 将json字符串反序列化成对象
     */
    public static <T> T deserialize(String json, Class<T> clt) throws JsonSyntaxException {
        return gson.fromJson(json, clt);
    }

    /**
     * 将json对象转化成实体对象
     */
    public static <T> T deserialize(JsonObject object, Class<T> clt) throws JsonSyntaxException {
        return gson.fromJson(object, clt);
    }

}
