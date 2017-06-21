package com.geelaro.sunshine.weather.contract;

/**
 * Created by LEE on 2017/6/19.
 */

public interface WeatherContract {
    interface Model {
        void loadWeather();
    }

    interface View {
        void showWeather();
        void showError();
    }

    interface Presenter {
        void getWeather();
    }
}
