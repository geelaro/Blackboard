package com.geelaro.sunshine.weather.model.sync;

import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

/**
 * Created by LEE on 2017/12/21.
 */

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class WeatherSyncService extends IntentService {
    public WeatherSyncService() {
        super("WeatherSyncService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        WeatherSyncTask.syncWeatherData();
    }
}
