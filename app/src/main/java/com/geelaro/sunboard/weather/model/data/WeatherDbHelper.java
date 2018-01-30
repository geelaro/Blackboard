package com.geelaro.sunboard.weather.model.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.geelaro.sunboard.weather.model.data.WeatherContract.LocationTable;
import com.geelaro.sunboard.weather.model.data.WeatherContract.WeatherTable;

/**
 * Created by geelaro on 2017/6/23.
 */

public class WeatherDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "weather.db";

    public WeatherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 建表location
        // 包括location setting, 城市名city name
        final String SQL_CREATE_LOCATION_TABLE = "CREATE TABLE " + LocationTable.TABLE_NAME + " (" +
                LocationTable._ID + " INTEGER PRIMARY KEY," +
                LocationTable.COLUMN_LOCATION_SETTING + " TEXT UNIQUE NOT NULL, " +
                LocationTable.COLUMN_CITY_NAME + " TEXT NOT NULL" +
                " );";

        final String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE " + WeatherTable.TABLE_NAME + " (" +

                WeatherTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

                // the ID of the location entry associated with this weather data
                WeatherTable.COLUMN_LOC_KEY + " INTEGER NOT NULL, " +
                WeatherTable.COLUMN_DATE + " INTEGER NOT NULL, " +
                WeatherTable.COLUMN_SHORT_DESC + " TEXT NOT NULL, " +
                WeatherTable.COLUMN_WEATHER_ID + " INTEGER NOT NULL," +

                WeatherTable.COLUMN_MIN_TEMP + " REAL NOT NULL, " +
                WeatherTable.COLUMN_MAX_TEMP + " REAL NOT NULL, " +

//                // 设置外键
                " FOREIGN KEY (" + WeatherTable.COLUMN_LOC_KEY + ") REFERENCES " +
                LocationTable.TABLE_NAME + " (" + LocationTable._ID + "), " +

                // To assure the application have just one weather entry per day
                // per location, it's created a UNIQUE constraint with REPLACE strategy
                " UNIQUE (" + WeatherTable.COLUMN_DATE + ", " +
                WeatherTable.COLUMN_LOC_KEY + ") ON CONFLICT REPLACE);";

        sqLiteDatabase.execSQL(SQL_CREATE_LOCATION_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LocationTable.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WeatherTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
