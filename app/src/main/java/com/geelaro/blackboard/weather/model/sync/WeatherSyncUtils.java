package com.geelaro.blackboard.weather.model.sync;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Created by geelaro on 2018/1/12.
 */

public class WeatherSyncUtils {

    public static void startImmediateSync(@NonNull final Context context){
        Intent immediateIntent = new Intent(context,WeatherSyncService.class);
        context.startService(immediateIntent);
    }
}
