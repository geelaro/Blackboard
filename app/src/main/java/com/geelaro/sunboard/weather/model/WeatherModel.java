package com.geelaro.sunboard.weather.model;

import com.geelaro.sunboard.utils.NetworkUtils;
import com.geelaro.sunboard.utils.OkHttpUtils;
import com.geelaro.sunboard.utils.SunLog;
import com.geelaro.sunboard.weather.WeatherJsonUtils;
import com.geelaro.sunboard.weather.contract.WeatherContract;

import org.json.JSONException;

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
