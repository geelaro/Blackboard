package com.geelaro.sunboard.weather.model.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.format.Time;

import static com.geelaro.sunboard.weather.model.data.WeatherContract.WeatherTable.COLUMN_DATE;

/**
 * Created by LEE on 2017/6/23.
 */

public class WeatherContract {

    // The "Content authority" is a name for the entire content provider, similar to the

    public static final String CONTENT_AUTHORITY = "com.geelaro.sunshine";

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //表
    public static final String PATH_WEATHER = "weather";
    public static final String PATH_LOCATION = "location";

    // To make it easy to query for the exact date, we normalize all dates that go into
    // the database to the start of the the Julian day at UTC.
    public static long normalizeDate(long startDate) {
        // normalize the start date to the beginning of the (UTC) day
        Time time = new Time();
        time.set(startDate);
        int julianDay = Time.getJulianDay(startDate, time.gmtoff);
        return time.setJulianDay(julianDay);
    }

    /* Inner class that defines the table contents of the location table */
    public static final class LocationTable implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_LOCATION).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LOCATION;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LOCATION;

        // Table name
        public static final String TABLE_NAME = "location";

        // The location setting string is what will be sent to openweathermap
        // as the location query.
        public static final String COLUMN_LOCATION_SETTING = "location_setting";

        // Human readable location string, provided by the API.  Because for styling,
        // "Mountain View" is more recognizable than 94043.
        public static final String COLUMN_CITY_NAME = "city_name";

        // In order to uniquely pinpoint the location on the map when we launch the
        // map intent, we store the latitude and longitude as returned by openweathermap.
        public static final String COLUMN_COORD_LAT = "coord_lat";
        public static final String COLUMN_COORD_LONG = "coord_long";

        public static Uri buildLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    /* Inner class that defines the table contents of the weather table */
    public static final class WeatherTable implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_WEATHER).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_WEATHER;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_WEATHER;

        public static final String TABLE_NAME = "weather";

        // Column with the foreign key into the location table.
        public static final String COLUMN_LOC_KEY = "location_id";
        // Date, stored as long in milliseconds since the epoch
        public static final String COLUMN_DATE = "date";
        // Weather id as returned by API, to identify the icon to be used
        public static final String COLUMN_WEATHER_ID = "weather_id";

        // Short description and long description of the weather, as provided by API.
        // e.g "clear" vs "sky is clear".
        public static final String COLUMN_SHORT_DESC = "short_desc";

        // Min and max temperatures for the day (stored as floats)
        public static final String COLUMN_MIN_TEMP = "min";
        public static final String COLUMN_MAX_TEMP = "max";

    }

    public static Uri buildWeatherWithLocation(String locationSettings){
        return WeatherTable.CONTENT_URI.buildUpon().appendPath(locationSettings).build();
    }

    public static Uri buildWeatherLocationWithStartDate(String locationSettings,long startDate){
        long normalDate = normalizeDate(startDate);
        return WeatherTable.CONTENT_URI.buildUpon().appendPath(locationSettings)
                .appendQueryParameter(COLUMN_DATE,Long.toString(normalDate)).build();
    }

    public static Uri buildWeatherLocationWithDate(String locationSettings,long date){
        long normalDate = normalizeDate(date);
        return WeatherTable.CONTENT_URI.buildUpon()
                .appendPath(locationSettings).appendPath(Long.toString(normalDate)).build();
    }

    public static String getLocationSettingFromUri(Uri uri) {
        return uri.getPathSegments().get(1);
    }

    public static long getDateFromUri(Uri uri) {
        return Long.parseLong(uri.getPathSegments().get(2));
    }
    public static long getStartDateFromUri(Uri uri) {
        String dateString = uri.getQueryParameter(COLUMN_DATE);
        if (null != dateString && dateString.length() > 0)
            return Long.parseLong(dateString);
        else
            return 0;
    }

}
