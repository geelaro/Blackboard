package com.geelaro.sunshine.weather.presenter;

import com.geelaro.sunshine.beans.WeatherBean;
import com.geelaro.sunshine.weather.contract.WeatherContract;
import com.geelaro.sunshine.weather.model.WeatherModel;

import java.util.List;

/**
 * Created by LEE on 2017/6/19.
 */

public class WeatherPresenter implements WeatherContract.Presenter,WeatherModel.OnLoadWeatherListListener{
    private WeatherContract.WeatherView mView;
    private WeatherContract.Model mModel;

    public WeatherPresenter(WeatherContract.WeatherView view){
        mView = view;
        mModel = new WeatherModel();
    }

    @Override
    public void loadWeatherList() {
        mModel.loadWeather(this); //获取数据
    }

    @Override
    public void onSuccess(List<WeatherBean> list) {
        mView.addWeatherData(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {

    }


}
