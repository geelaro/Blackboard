package com.geelaro.sunshine.main.contract;

/**
 * Created by Administrator on 2017/6/21.
 */

public interface MainContract {
    interface Model {
    }

    interface View {
        void switch2Weather();
        void switch2News();
        void switch2Images();
        void switch2About();
    }

    interface Presenter {
        void switchNavigation(int id);
    }
}
