package com.geelaro.sunshine.about;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geelaro.sunshine.R;

/**
 * Created by geelaro on 2017/8/15.
 */

public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frame_about, container, false);

        return rootView;
    }
}
