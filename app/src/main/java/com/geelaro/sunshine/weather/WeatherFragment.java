package com.geelaro.sunshine.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.geelaro.sunshine.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LEE on 2017/6/19.
 */

public class WeatherFragment extends Fragment {

    private final static String TAG = WeatherFragment.class.getSimpleName();
    private ArrayAdapter<String> mWeatherAdapter;

    private List<String> mData;

    public WeatherFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //重写菜单生效
        mData = new ArrayList<>();
        mData.add("One is Sunny");
        mData.add("Two is Raining");
        mData.add("Three is Cold");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frame_weather,container,false);

        mWeatherAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_item_weather,
                R.id.list_item_textview,
                mData);


        ListView listView = (ListView)rootView.findViewById(R.id.listview_weather);
        listView.setAdapter(mWeatherAdapter);
        return rootView;
    }


}
