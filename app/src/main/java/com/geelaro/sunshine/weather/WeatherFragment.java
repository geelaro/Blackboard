package com.geelaro.sunshine.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.geelaro.sunshine.R;
import com.geelaro.sunshine.weather.presenter.FetchWeatherTask;

import java.util.ArrayList;

/**
 * Created by LEE on 2017/6/19.
 */

public class WeatherFragment extends Fragment {

    private final static String TAG = WeatherFragment.class.getSimpleName();
    private ArrayAdapter<String> mWeatherAdapter;


    public WeatherFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //重写菜单生效

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
        weatherTask.execute("94043");
    }

    @Override
    public void onStart() {
        super.onStart();
        updateWeather();
    }


}
