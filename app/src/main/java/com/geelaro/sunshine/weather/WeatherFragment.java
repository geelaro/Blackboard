package com.geelaro.sunshine.weather;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geelaro.sunshine.BaseListFragment;
import com.geelaro.sunshine.R;
import com.geelaro.sunshine.beans.WeatherBean;
import com.geelaro.sunshine.utils.ShowToast;
import com.geelaro.sunshine.utils.SunLog;
import com.geelaro.sunshine.utils.SunshineApp;
import com.geelaro.sunshine.weather.contract.WeatherContract;
import com.geelaro.sunshine.weather.presenter.WeatherPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by geelaro on 2017/6/19.
 */

public class WeatherFragment extends Fragment implements WeatherContract.WeatherView,SwipeRefreshLayout.OnRefreshListener {

    private final static String TAG = WeatherFragment.class.getSimpleName();
    private WeatherAdapter mWeatherAdapter;
    private RecyclerView recyclerView;
    private Context mContext;
    private List<WeatherBean> mData;
    private RecyclerView.LayoutManager manager;
    private WeatherPresenter mPresenter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //重写菜单生效
        mContext = SunshineApp.getContext();
        mPresenter = new WeatherPresenter(this);
        SunLog.d(TAG, "onCreate");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frame_weather, container, false);

        mWeatherAdapter = new WeatherAdapter(mContext);
        manager = new LinearLayoutManager(getActivity());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.listview_weather);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mWeatherAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        SunLog.d(TAG, "onCreateView");
        onRefresh();
        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        SunLog.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void addWeatherData(List<WeatherBean> list) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.clear();
        mData.addAll(list);
        mWeatherAdapter.setData(mData);
    }

    @Override
    public void showError() {

        ShowToast.Short("数据加载错误。");
    }

    @Override
    public void onRefresh() {
        mPresenter.loadWeatherList();
    }
}
