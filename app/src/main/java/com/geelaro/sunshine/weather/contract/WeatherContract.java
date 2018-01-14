package com.geelaro.sunshine.weather.contract;

import com.geelaro.sunshine.beans.WeatherBean;
import com.geelaro.sunshine.weather.model.WeatherModel;

import java.util.List;

/**
 * Created by geelaro on 2017/6/19.
 */

public interface WeatherContract {
    interface Model {
        void loadWeather();
    }

    interface WeatherView {
        void addWeatherData(List<WeatherBean> list);

        void showError();
    }

    interface Presenter {
        void loadWeatherList();

    }
}
