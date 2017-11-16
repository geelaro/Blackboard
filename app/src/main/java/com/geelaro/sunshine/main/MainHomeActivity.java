package com.geelaro.sunshine.main;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.geelaro.sunshine.R;
import com.geelaro.sunshine.about.AboutFragment;
import com.geelaro.sunshine.images.ImageFragment;
import com.geelaro.sunshine.main.contract.MainContract;
import com.geelaro.sunshine.main.presenter.MainPresenter;
import com.geelaro.sunshine.news.NewsFragment;
import com.geelaro.sunshine.utils.ShowToast;
import com.geelaro.sunshine.weather.WeatherFragment;


public class MainHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private MainContract.Presenter mainPresenter;

    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        mainPresenter = new MainPresenter(this);

        switch2Weather();//首次进入程序是WeatherFragment
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ShowToast.Short("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        mainPresenter.switchNavigation(id);
        item.setChecked(true);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void switch2Weather() {
        // Handle the WeatherFragment action

        //getSupportFragmentManager() v4.fragment使用此方法
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new WeatherFragment())
                .commit();
        toolbar.setTitle(R.string.fragment_weather);
    }

    @Override
    public void switch2News() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new NewsFragment())
                .commit();
        toolbar.setTitle(R.string.fragment_news);

    }

    @Override
    public void switch2Images() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new ImageFragment())
                .commit();
        toolbar.setTitle(R.string.fragment_images);
    }

    @Override
    public void switch2About() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new AboutFragment())
                .commit();
        toolbar.setTitle(R.string.fragment_me);
    }
}
