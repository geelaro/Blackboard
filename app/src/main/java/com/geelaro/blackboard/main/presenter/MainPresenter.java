package com.geelaro.blackboard.main.presenter;

import com.geelaro.blackboard.R;
import com.geelaro.blackboard.main.contract.MainContract;

/**
 * Created by geelaro on 2017/6/21.
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mMainView;

    public MainPresenter(MainContract.View mainView) {
        this.mMainView = mainView;
    }

    //NavigationView 按钮
    @Override
    public void switchNavigation(int id) {
        switch (id) {
            case R.id.nav_weather:
                mMainView.switch2Weather();
                break;
            case R.id.nav_news:
                mMainView.switch2News();
                break;
            case R.id.nav_movies:
                mMainView.switch2Movies();
                break;
            case R.id.nav_gallery:
                mMainView.switch2Images();
                break;
            case R.id.nav_about:
                mMainView.switch2About();
                break;
            case R.id.nav_settings:
                mMainView.switch2Settings();
                break;
            default:
                mMainView.switch2Weather();
        }
    }
}
