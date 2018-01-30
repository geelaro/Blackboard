package com.geelaro.blackboard.weather.presenter;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.geelaro.blackboard.R;
import com.geelaro.blackboard.base.beans.WeatherBean;
import com.geelaro.blackboard.settings.SettingsActivity;
import com.geelaro.blackboard.utils.SunLog;
import com.geelaro.blackboard.utils.ToolUtils;
import com.geelaro.blackboard.weather.contract.WeatherContract;
import com.geelaro.blackboard.weather.model.WeatherModel;

import java.util.List;

/**
 * Created by LEE on 2017/6/19.
 */

public class WeatherPresenter implements WeatherContract.Presenter {
    private WeatherContract.WeatherView mView;
    private static WeatherContract.Model mModel;
    private final static String TAG = WeatherPresenter.class.getSimpleName();

    public WeatherPresenter(WeatherContract.WeatherView view) {
        mView = view;
        mModel = new WeatherModel();
    }

    @Override
    public void loadWeatherList() {
        mModel.loadWeather(); //获取数据
        SunLog.d(TAG, "loadWeatherList()");
    }

    public static void syncData(){
        mModel.loadWeather();
    }

    /** Notification **/
    private void notifyWeather(Context context, List<WeatherBean> list) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String displayNotificationKey = context.getString(R.string.pref_enable_notifications_key);
        boolean displayNotification = prefs.getBoolean(displayNotificationKey,
                Boolean.parseBoolean(context.getString(R.string.pref_enable_notifications_default)));

        if (displayNotification){
            WeatherBean weatherBean = list.get(0);

            int weatherId = ToolUtils.getSmallWeatherImage(weatherBean.getWeatherId());
            String weatherDesc = weatherBean.getDesc();
            double low = weatherBean.getMinTemp();
            double high = weatherBean.getMaxTemp();
            String title = context.getString(R.string.app_name);
            String contentText = String.format(context.getString(R.string.format_notification),
                    weatherDesc,
                    ToolUtils.formatTemperature(context, high),
                    ToolUtils.formatTemperature(context, low));

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(weatherId)
                            .setContentTitle(title)
                            .setContentText(contentText)
                            .setAutoCancel(true);
            // Creates an explicit intent for an Activity in your app
            Intent resultIntent = new Intent(context, SettingsActivity.class);

            // The stack builder object will contain an artificial back stack for the
            // started Activity.
            // This ensures that navigating backward from the Activity leads out of
            // your application to the Home screen.
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            // Adds the back stack for the Intent (but not the Intent itself)
            stackBuilder.addParentStack(SettingsActivity.class);
            // Adds the Intent that starts the Activity to the top of the stack
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            // mId allows you to update the notification later on.
            mNotificationManager.notify(3400, mBuilder.build());
        }

    }


}
