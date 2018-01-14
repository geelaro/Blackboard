package com.geelaro.sunshine.weather;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.format.Time;

import com.geelaro.sunshine.beans.WeatherBean;
import com.geelaro.sunshine.settings.Settings;
import com.geelaro.sunshine.utils.SunLog;
import com.geelaro.sunshine.utils.SunshineApp;
import com.geelaro.sunshine.weather.model.data.WeatherContract.LocationTable;
import com.geelaro.sunshine.weather.model.data.WeatherContract.WeatherTable;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static com.geelaro.sunshine.utils.ToolUtils.getReadableDateString;

/**
 * Created by geelaro on 2017/6/23.
 */

public class WeatherJsonUtils {
    private static final String OWM_CITY = "city";
    private final static String OWM_CITY_NAME = "name";
    private static final String OWM_LIST = "list";
    private static final String OWM_WEATHER = "weather";
    private static final String OWM_TEMPERATURE = "temp";
    private static final String OWM_MAX = "max";
    private static final String OWM_MIN = "min";
    private static final String OWM_DESCRIPTION = "main";
    private static final String OWM_ID = "id";

    private static String cityName;

    private static Context context = SunshineApp.getContext();


    public static String getCityName() {

        return cityName;
    }

    public static void getWeatherInfo(String jsonStr) throws JSONException {
        // These are the names of the JSON objects that need to be extracted.

        JSONObject forecastJson = new JSONObject(jsonStr);
        JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);

        JSONObject cityObj = forecastJson.getJSONObject(OWM_CITY);
        cityName = cityObj.getString(OWM_CITY_NAME);

        Vector<ContentValues> cVVector = new Vector<ContentValues>(weatherArray.length());

        String locationSettings = Settings.getInstance().getPreferredLocation(context);
        long locationId = addLocation(locationSettings, cityName);

        // OWM returns daily forecasts based upon the local time of the city that is being
        // asked for, which means that we need to know the GMT offset to translate this data
        // properly.

        // Since this data is also sent in-order and the first day is always the
        // current day, we're going to take advantage of that to get a nice
        // normalized UTC date for all of our weather.

        Time dayTime = new Time();
        dayTime.setToNow();

        // we start at the day returned by local time. Otherwise this is a mess.
        int julianStartDay = Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);

        // now we work exclusively in UTC
        dayTime = new Time();

        // Data is fetched in Celsius by default.

        for (int i = 0; i < weatherArray.length(); i++) {
            // For now, using the format "Day, description, hi/low"
            String weatherDesc;
            int weatherId;

            // Get the JSON object representing the day
            JSONObject dayForecast = weatherArray.getJSONObject(i);

            // The date/time is returned as a long.  We need to convert that
            // into something human-readable, since most people won't read "1400356800" as
            // "this saturday".
            long dateTime;
            // Cheating to convert this to UTC time, which is what we want anyhow
            dateTime = dayTime.setJulianDay(julianStartDay + i);

            // description is in a child array called "weather", which is 1 element long.
            JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            weatherDesc = weatherObject.getString(OWM_DESCRIPTION);
            weatherId = weatherObject.getInt(OWM_ID);

            // Temperatures are in a child object called "temp".  Try not to name variables
            // "temp" when working with temperature.  It confuses everybody.
            JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
            long high = formatTemp(temperatureObject.getDouble(OWM_MAX));
            long low = formatTemp(temperatureObject.getDouble(OWM_MIN));

            ContentValues values = new ContentValues();
            values.put(WeatherTable.COLUMN_LOC_KEY, locationId);
            values.put(WeatherTable.COLUMN_DATE, dateTime);
            values.put(WeatherTable.COLUMN_SHORT_DESC, weatherDesc);
            values.put(WeatherTable.COLUMN_WEATHER_ID, weatherId);
            values.put(WeatherTable.COLUMN_MIN_TEMP, low);
            values.put(WeatherTable.COLUMN_MAX_TEMP, high);

            cVVector.add(values);

        }
        if (cVVector.size() > 0) {
            ContentValues[] cvArray = new ContentValues[cVVector.size()];
            cVVector.toArray(cvArray);
            context.getContentResolver().bulkInsert(WeatherTable.CONTENT_URI, cvArray);
            SunLog.i("WeatherJsonUtils", "SyncWeatherData " + cvArray.length + " rows");
            //删除旧数据
            context.getContentResolver().delete(WeatherTable.CONTENT_URI,
                    WeatherTable.COLUMN_DATE + "<=?",
                    new String[]{Long.toString(dayTime.setJulianDay(julianStartDay - 1))});
        }
    }

    static long addLocation(String locationSettings, String cityName) {
        long locationId;

        Cursor locationCursor = context.getContentResolver().query(
                LocationTable.CONTENT_URI,
                new String[]{LocationTable._ID},
                LocationTable.COLUMN_LOCATION_SETTING + "=?",
                new String[]{locationSettings},
                null
        );
        if (locationCursor.moveToFirst()) {
            int index = locationCursor.getColumnIndex(LocationTable._ID);
            locationId = locationCursor.getLong(index);
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(LocationTable.COLUMN_LOCATION_SETTING, locationSettings);
            contentValues.put(LocationTable.COLUMN_CITY_NAME, cityName);

            Uri uri = context.getContentResolver().insert(LocationTable.CONTENT_URI, contentValues);

            locationId = ContentUris.parseId(uri);
        }

        locationCursor.close();

        return locationId;
    }


//    public static List<WeatherBean> getWeatherDataFromJson(String forecastJsonStr)
//            throws JSONException {
//
//        List<WeatherBean> weatherBeanList = new ArrayList<>();
//        // These are the names of the JSON objects that need to be extracted.
//
//
//        JSONObject forecastJson = new JSONObject(forecastJsonStr);
//        JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);
//
//        JSONObject cityObj = forecastJson.getJSONObject(OWM_CITY);
//        cityName = cityObj.getString(OWM_CITY_NAME);
//
//        // OWM returns daily forecasts based upon the local time of the city that is being
//        // asked for, which means that we need to know the GMT offset to translate this data
//        // properly.
//
//        // Since this data is also sent in-order and the first day is always the
//        // current day, we're going to take advantage of that to get a nice
//        // normalized UTC date for all of our weather.
//
//        Time dayTime = new Time();
//        dayTime.setToNow();
//
//        // we start at the day returned by local time. Otherwise this is a mess.
//        int julianStartDay = Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);
//
//        // now we work exclusively in UTC
//        dayTime = new Time();
//
//        // Data is fetched in Celsius by default.
//
//        for (int i = 0; i < weatherArray.length(); i++) {
//            // For now, using the format "Day, description, hi/low"
//            String day;
//            String weatherDesc;
//            int weatherId;
//
//            // Get the JSON object representing the day
//            JSONObject dayForecast = weatherArray.getJSONObject(i);
//
//            // The date/time is returned as a long.  We need to convert that
//            // into something human-readable, since most people won't read "1400356800" as
//            // "this saturday".
//            long dateTime;
//            // Cheating to convert this to UTC time, which is what we want anyhow
//            dateTime = dayTime.setJulianDay(julianStartDay + i);
//            day = getReadableDateString(dateTime);
//
//            // description is in a child array called "weather", which is 1 element long.
//            JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
//            weatherDesc = weatherObject.getString(OWM_DESCRIPTION);
//            weatherId = weatherObject.getInt(OWM_ID);
//
//            // Temperatures are in a child object called "temp".  Try not to name variables
//            // "temp" when working with temperature.  It confuses everybody.
//            JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
//            long high = formatTemp(temperatureObject.getDouble(OWM_MAX));
//            long low = formatTemp(temperatureObject.getDouble(OWM_MIN));
//
//            WeatherBean weather = new WeatherBean();
//            weather.setDesc(weatherDesc);
//            weather.setMaxTemp(high);
//            weather.setMinTemp(low);
//            weather.setDate(day);
//            weather.setWeatherId(weatherId);
//            weatherBeanList.add(weather);
//
//        }
//
//
//        return weatherBeanList;
//    }

    /**
     * get the weather high/lows temp by round.
     */
    private static Long formatTemp(double temp) {

        long roundedTemp = Math.round(temp);

        return roundedTemp;
    }

}
