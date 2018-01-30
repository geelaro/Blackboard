package com.geelaro.sunboard.main.contract;

/**
 * Created by geelaro on 2017/6/21.
 */

public interface MainContract {
    interface Model {
    }

    interface View {
        void switch2Weather();

        void switch2News();

        void switch2Movies();

        void switch2Images();

        void switch2About();

        void switch2Settings();
    }

    interface Presenter {
        void switchNavigation(int id);
    }
}
