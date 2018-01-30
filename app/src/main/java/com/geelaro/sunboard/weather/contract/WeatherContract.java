package com.geelaro.sunboard.weather.contract;

import com.geelaro.sunboard.base.beans.WeatherBean;

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
