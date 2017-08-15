package com.geelaro.sunshine.weather;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.geelaro.sunshine.R;
import com.geelaro.sunshine.utils.HttpUtils;
import com.geelaro.sunshine.utils.ToolUtils;
import com.geelaro.sunshine.weather.model.data.WeatherInfo.WeatherEntry;
import com.geelaro.sunshine.weather.presenter.FetchWeatherTask;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

/**
 * Created by LEE on 2017/6/19.
 */

public class WeatherFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private final static String TAG = WeatherFragment.class.getSimpleName();
    private final static int ID_WEATHER_LOADER = 11;
    private ArrayAdapter<String> mWeatherAdapter;


    public WeatherFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //重写菜单生效

        getLoaderManager().initLoader(ID_WEATHER_LOADER,null,this);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frame_weather, container, false);

        mWeatherAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_item_weather,
                R.id.list_item_textview,
                new ArrayList<String>());


        ListView listView = (ListView) rootView.findViewById(R.id.listview_weather);
        listView.setAdapter(mWeatherAdapter);
        return rootView;
    }

    private void updateWeather() {
        FetchWeatherTask weatherTask = new FetchWeatherTask(getActivity(), mWeatherAdapter);
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        String location = prefs.getString(getString(R.string.pref_location_key),
//                getString(R.string.pref_default_location_value));
        weatherTask.execute(String.valueOf(R.string.default_location_value));
    }

    @Override
    public void onStart() {
        super.onStart();
        updateWeather();


    }


    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {

        switch (loaderId){
            case ID_WEATHER_LOADER:
                Uri weatherQueryUri = WeatherEntry.CONTENT_URI;

                CursorLoader loader = new CursorLoader(getContext(),
                        weatherQueryUri,
                        null,
                        null,
                        null,
                        null);
                return loader;
            default:
                throw  new RuntimeException("Loader Not Implemented: "+loaderId);
        }

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


}
