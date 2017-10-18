package com.geelaro.sunshine.weather;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.geelaro.sunshine.R;
import com.geelaro.sunshine.weather.presenter.FetchWeatherTask;

import java.util.ArrayList;


/**
 * Created by geelaro on 2017/6/19.
 */

public class WeatherFragment extends Fragment {

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

//        getLoaderManager().initLoader(ID_WEATHER_LOADER,null,this);


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

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        super.onPrepareOptionsMenu(menu);

    }

    private void updateWeather() {
        FetchWeatherTask weatherTask = new FetchWeatherTask(getActivity(), mWeatherAdapter);
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        String location = prefs.getString(getString(R.string.pref_location_key),
//                getString(R.string.pref_default_location_value));
        weatherTask.execute(String.valueOf(R.string.default_location_city));
    }

    @Override
    public void onStart() {
        super.onStart();
        updateWeather();
    }


}
