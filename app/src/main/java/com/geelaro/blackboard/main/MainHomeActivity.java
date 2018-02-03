package com.geelaro.blackboard.main;

import android.content.Intent;
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

import com.geelaro.blackboard.R;
import com.geelaro.blackboard.about.AboutFragment;
import com.geelaro.blackboard.images.ImageFragment;
import com.geelaro.blackboard.main.contract.MainContract;
import com.geelaro.blackboard.main.presenter.MainPresenter;
import com.geelaro.blackboard.movies.MovieTop250Fragment;
import com.geelaro.blackboard.movies.MoviewFragment;
import com.geelaro.blackboard.news.widget.NewsFragment;
import com.geelaro.blackboard.settings.SettingsActivity;
import com.geelaro.blackboard.utils.LanguageUtils;
import com.geelaro.blackboard.utils.ShowToast;
import com.geelaro.blackboard.weather.WeatherFragment;


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
        //language
        LanguageUtils.updateLanguage(this);

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

        mainPresenter = new MainPresenter(this);

        switch2Weather();//首次进入程序是WeatherFragment
//        switch2News();

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getExtras()!=null){
            if (intent.getExtras().getBoolean("setLanguage",false)){
                startActivity(new Intent(this,MainHomeActivity.class));
            }
        }
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
//        getMenuInflater().inflate(R.menu.main, menu);
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
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

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
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new WeatherFragment())
                .commit();
        toolbar.setTitle(R.string.fragment_weather);
    }

    @Override
    public void switch2News() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NewsFragment())
                .commit();
        toolbar.setTitle(R.string.fragment_news);

    }

    @Override
    public void switch2Movies() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,new MoviewFragment())
                .commit();
        toolbar.setTitle(R.string.fragment_movies);
    }

    @Override
    public void switch2Images() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ImageFragment())
                .commit();
        toolbar.setTitle(R.string.fragment_images);
    }

    @Override
    public void switch2About() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new AboutFragment())
                .commit();
        toolbar.setTitle(R.string.fragment_me);
    }



    @Override
    public void switch2Settings() {
        startActivity(new Intent(this,SettingsActivity.class));

    }
}
