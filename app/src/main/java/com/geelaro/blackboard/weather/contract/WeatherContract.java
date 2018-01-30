package com.geelaro.blackboard.weather.contract;

import com.geelaro.blackboard.base.beans.WeatherBean;

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
