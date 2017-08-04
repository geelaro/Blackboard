package com.geelaro.sunshine.main.presenter;

import com.geelaro.sunshine.R;
import com.geelaro.sunshine.main.contract.MainContract;

/**
 * Created by geelaro on 2017/6/21.
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mMainView;

    public MainPresenter(MainContract.View mainView) {
        this.mMainView = mainView;
    }

    @Override
    public void switchNavigation(int id) {
        switch (id) {
            case R.id.nav_weather:
                mMainView.switch2Weather();
                break;
            case R.id.nav_news:
                mMainView.switch2News();
                break;
            case R.id.nav_gallery:
                mMainView.switch2Images();
                break;
            case R.id.nav_about:
                mMainView.switch2About();
                break;
            default:
                mMainView.switch2Weather();
        }
    }
}
