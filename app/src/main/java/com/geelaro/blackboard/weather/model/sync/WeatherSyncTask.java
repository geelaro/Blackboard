package com.geelaro.blackboard.weather.model.sync;

import com.geelaro.blackboard.weather.presenter.WeatherPresenter;

/**
 * Created by LEE on 2018/1/12.
 */

public class WeatherSyncTask {

    synchronized static void syncWeatherData(){
        WeatherPresenter.syncData();
    }
}
