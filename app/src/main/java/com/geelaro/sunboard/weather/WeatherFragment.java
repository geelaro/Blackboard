package com.geelaro.sunboard.weather;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geelaro.sunboard.R;
import com.geelaro.sunboard.base.beans.WeatherBean;
import com.geelaro.sunboard.settings.Settings;
import com.geelaro.sunboard.utils.ShowToast;
import com.geelaro.sunboard.weather.contract.WeatherContract;
import com.geelaro.sunboard.weather.presenter.WeatherPresenter;

import java.util.List;

import com.geelaro.sunboard.weather.model.data.WeatherContract.WeatherTable;


/**
 * Created by geelaro on 2017/6/19.
 */

public class WeatherFragment extends Fragment implements WeatherContract.WeatherView,
        SwipeRefreshLayout.OnRefreshListener, LoaderManager.LoaderCallbacks<Cursor> {

    private final static String TAG = WeatherFragment.class.getSimpleName();
    private final static int WEATHER_LOAD = 110;
    private WeatherAdapter mWeatherAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private WeatherPresenter mPresenter;

    private static final String[] WEATHER_COLUMS = new String[]{
            WeatherTable.TABLE_NAME + "." + WeatherTable._ID,
            WeatherTable.COLUMN_DATE,
            WeatherTable.COLUMN_SHORT_DESC,
            WeatherTable.COLUMN_WEATHER_ID,
            WeatherTable.COLUMN_MIN_TEMP,
            WeatherTable.COLUMN_MAX_TEMP
    };

    static final int WEATHER_ID = 0;
    static final int COLUMN_DATE = 1;
    static final int COLUMN_DESC = 2;
    static final int COLUMN_WEATHER_ID = 3;
    static final int MIN_TEMP = 4;
    static final int MAX_TEMP = 5;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //重写菜单生效
        mPresenter = new WeatherPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frame_weather, container, false);

        mWeatherAdapter = new WeatherAdapter(getActivity());
        manager = new LinearLayoutManager(getActivity());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.listview_weather);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mWeatherAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        onRefresh();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getLoaderManager().getLoader(WEATHER_LOAD) == null) {
            getLoaderManager().initLoader(WEATHER_LOAD, null, this);
        } else
            getLoaderManager().restartLoader(WEATHER_LOAD, null, this);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(WEATHER_LOAD, null, this);
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void addWeatherData(List<WeatherBean> list) {
    }

    @Override
    public void showError() {

        ShowToast.Short("数据加载错误。");
    }

    @Override
    public void onRefresh() {
        mPresenter.loadWeatherList();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        String sortOrder = WeatherTable.COLUMN_DATE + " ASC";
        String locationSettings = Settings.getInstance().getPreferredLocation(getActivity());

        Uri weatherWithDateUri = com.geelaro.sunboard.weather.model.data.WeatherContract
                .buildWeatherWithLocation(locationSettings);
        return new CursorLoader(getActivity(),
                weatherWithDateUri,
                WEATHER_COLUMS,
                null,
                null,
                sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mWeatherAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader loader) {
        mWeatherAdapter.swapCursor(null);
    }
}
