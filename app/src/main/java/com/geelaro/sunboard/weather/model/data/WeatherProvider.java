package com.geelaro.sunboard.weather.model.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.geelaro.sunboard.weather.model.data.WeatherContract.WeatherTable;
import com.geelaro.sunboard.weather.model.data.WeatherContract.LocationTable;

/**
 * Created by geelaro on 2017/6/23.
 */

public class WeatherProvider extends ContentProvider {

    private final static int WEATHER = 100;
    private final static int WEATHER_ID = 101;
    private final static int WEATHER_WITH_LOCATION = 102;
    private final static int WEATHER_WITH_LOCATION_AND_DATE = 103;
    private final static int LOCATION = 200;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final SQLiteQueryBuilder sWeatherByLocationSettingQueryBuilder;
    private WeatherDbHelper mDbHelper;

    static {
        sWeatherByLocationSettingQueryBuilder = new SQLiteQueryBuilder();

        //This is an inner join which looks like
        //weather INNER JOIN location ON weather.location_id = location._id
        sWeatherByLocationSettingQueryBuilder.setTables(
                WeatherTable.TABLE_NAME + " INNER JOIN " +
                        LocationTable.TABLE_NAME +
                        " ON " + WeatherTable.TABLE_NAME +
                        "." + WeatherTable.COLUMN_LOC_KEY +
                        " = " + LocationTable.TABLE_NAME +
                        "." + LocationTable._ID);

        sUriMatcher.addURI(WeatherContract.CONTENT_AUTHORITY, WeatherContract.PATH_WEATHER, WEATHER);
        sUriMatcher.addURI(WeatherContract.CONTENT_AUTHORITY, WeatherContract.PATH_WEATHER + "/#", WEATHER_ID);
        sUriMatcher.addURI(WeatherContract.CONTENT_AUTHORITY, WeatherContract.PATH_WEATHER + "/*", WEATHER_WITH_LOCATION);
        sUriMatcher.addURI(WeatherContract.CONTENT_AUTHORITY, WeatherContract.PATH_WEATHER + "/*/#", WEATHER_WITH_LOCATION_AND_DATE);
        sUriMatcher.addURI(WeatherContract.CONTENT_AUTHORITY, WeatherContract.PATH_LOCATION, LOCATION);
    }

    //location.location_setting = ?
    private static final String sLocationSettingSelection =
            LocationTable.TABLE_NAME+
                    "." + LocationTable.COLUMN_LOCATION_SETTING + " = ? ";

    //location.location_setting = ? AND date >= ?
    private static final String sLocationSettingWithStartDateSelection =
            WeatherContract.LocationTable.TABLE_NAME+
                    "." + WeatherContract.LocationTable.COLUMN_LOCATION_SETTING + " = ? AND " +
                    WeatherContract.WeatherTable.COLUMN_DATE + " >= ? ";

    //location.location_setting = ? AND date = ?
    private static final String sLocationSettingAndDaySelection =
            WeatherContract.LocationTable.TABLE_NAME +
                    "." + WeatherContract.LocationTable.COLUMN_LOCATION_SETTING + " = ? AND " +
                    WeatherContract.WeatherTable.COLUMN_DATE + " = ? ";

    private Cursor getWeatherByLocationSetting(Uri uri, String[] projection, String sortOrder) {
        String locationSetting = WeatherContract.getLocationSettingFromUri(uri);
        long startDate = WeatherContract.getStartDateFromUri(uri);

        String[] selectionArgs;
        String selection;

        if (startDate == 0) {
            selection = sLocationSettingSelection;
            selectionArgs = new String[]{locationSetting};
        } else {
            selectionArgs = new String[]{locationSetting, Long.toString(startDate)};
            selection = sLocationSettingWithStartDateSelection;
        }

        return sWeatherByLocationSettingQueryBuilder
                .query(mDbHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
    }

    private Cursor getWeatherByLocationSettingAndDate(
            Uri uri, String[] projection, String sortOrder) {
        String locationSetting = WeatherContract.getLocationSettingFromUri(uri);
        long date = WeatherContract.getDateFromUri(uri);

        return sWeatherByLocationSettingQueryBuilder.query(mDbHelper.getReadableDatabase(),
                projection,
                sLocationSettingAndDaySelection,
                new String[]{locationSetting, Long.toString(date)},
                null,
                null,
                sortOrder
        );
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new WeatherDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case WEATHER_WITH_LOCATION:
                cursor = getWeatherByLocationSetting(uri,projection,sortOrder);
                break;
            case WEATHER_WITH_LOCATION_AND_DATE:
                cursor = getWeatherByLocationSettingAndDate(uri,projection,sortOrder);
                break;
            case WEATHER:
                cursor = db.query(WeatherTable.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case LOCATION:
                cursor = db.query(LocationTable.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);

        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case WEATHER:
                return WeatherTable.CONTENT_TYPE; //“"vnd.android.cursor.dir/weather”
            case WEATHER_ID:
                return WeatherTable.CONTENT_ITEM_TYPE;
            case WEATHER_WITH_LOCATION:
                return WeatherTable.CONTENT_TYPE;
            case WEATHER_WITH_LOCATION_AND_DATE:
                return WeatherTable.CONTENT_ITEM_TYPE;
            case LOCATION:
                return LocationTable.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        return super.bulkInsert(uri, values);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch (match) {
            case WEATHER: {
                long _id = db.insert(WeatherTable.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = ContentUris.withAppendedId(WeatherTable.CONTENT_URI, _id);
                else
                    throw new SQLException("Fail to insert into " + uri);

                break;
            }
            case LOCATION: {
                long _id = db.insert(LocationTable.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = ContentUris.withAppendedId(LocationTable.CONTENT_URI, _id);
                else
                    throw new SQLException("Fail to inset into " + uri);

                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
