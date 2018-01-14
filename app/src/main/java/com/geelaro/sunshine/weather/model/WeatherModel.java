package com.geelaro.sunshine.weather.model;

import com.geelaro.sunshine.beans.WeatherBean;
import com.geelaro.sunshine.utils.NetworkUtils;
import com.geelaro.sunshine.utils.OkHttpUtils;
import com.geelaro.sunshine.utils.SunLog;
import com.geelaro.sunshine.weather.WeatherJsonUtils;
import com.geelaro.sunshine.weather.contract.WeatherContract;

import org.json.JSONException;

import java.util.List;

/**
 * Created by geelaro on 2017/6/19.
 */

public class WeatherModel implements WeatherContract.Model {
    /**
     *
     */
    @Override
    public  void loadWeather() {
        //weather API's url
        String url = NetworkUtils.getWeatherURL().toString();
        OkHttpUtils.ResultCallback<String> loadWeatherCallBack = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onFailure(Exception e) {
                SunLog.e("Fail to load WeatherList.", e.getMessage());
            }

            @Override
            public void onSuccess(String response) {
                try {
                    WeatherJsonUtils.getWeatherInfo(response);
                    SunLog.d("Response: ", response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        OkHttpUtils.newInstance().get(url,loadWeatherCallBack);
    }

    public interface OnLoadWeatherListListener {
        void onSuccess();

        void onFailure(String msg, Exception e);
    }
}
