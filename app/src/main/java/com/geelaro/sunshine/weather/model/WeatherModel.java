package com.geelaro.sunshine.weather.model;

import android.nfc.Tag;

import com.geelaro.sunshine.beans.WeatherBean;
import com.geelaro.sunshine.utils.OkHttpUtils;
import com.geelaro.sunshine.utils.SunLog;
import com.geelaro.sunshine.utils.ToolUtils;
import com.geelaro.sunshine.utils.Urls;
import com.geelaro.sunshine.utils.WeatherJsonUtils;
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
    public void loadWeather(final OnLoadWeatherListListener listener) {
        //weather API's url
        String url = ToolUtils.getWeatherURL().toString();
        OkHttpUtils.ResultCallback<String> loadWeatherCallBack = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onFailure(Exception e) {
                listener.onFailure("Fail to load WeatherList.", e);
            }

            @Override
            public void onSuccess(String response) {
                try {
                    List<WeatherBean> list = WeatherJsonUtils.getWeatherDataFromJson(response);
                    SunLog.d("Response: ",response);

                    listener.onSuccess(list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        OkHttpUtils.get(url, loadWeatherCallBack);
    }

    public interface OnLoadWeatherListListener {
        void onSuccess(List<WeatherBean> list);

        void onFailure(String msg, Exception e);
    }
}
