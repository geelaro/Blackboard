package com.geelaro.sunshine.weather.model;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by LEE on 2017/12/21.
 */

public class WeatherSyncService extends IntentService {
    public WeatherSyncService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
