package com.geelaro.sunboard.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.geelaro.sunboard.R;

/**
 * Created by geelaro on 2017/12/30.
 */

public class SettingsActivity extends AppCompatActivity{

    private Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);

        //Toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setTitleTextAppearance(this,R.style.ToolBarTextAppearance);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //SettingsFragment
        getFragmentManager().beginTransaction()
                .replace(R.id.container,new SettingsFragment())
                .commit();
    }


}
