package com.geelaro.sunshine.news;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.geelaro.sunshine.R;

import java.util.ArrayList;
import java.util.List;

import static com.geelaro.sunshine.R.color.color_nav_end;

/**
 * Created by geelaro on 2017/12/2.
 */

public class NewsFragmentManager extends Fragment {
    public static final int NEWS_TYPE_TOP = 0;
    public static final int NEWS_TYPE_NBA = 1;
    public static final int NEWS_TYPE_CARS = 2;
    public static final int NEWS_TYPE_JOKES = 3;

    //
    private DisplayMetrics dm;
    //
    private PagerSlidingTabStrip mPageTabs;
    private ViewPager mViewPager;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frame_news_fm,container,false);
        dm=getResources().getDisplayMetrics();
        mPageTabs = (PagerSlidingTabStrip)rootView.findViewById(R.id.tabs);
        mViewPager = (ViewPager)rootView.findViewById(R.id.viewpage);

        setViewPager(mViewPager);

        mPageTabs.setViewPager(mViewPager);

        setTabsValue();

        return rootView;
    }

    private void setViewPager(ViewPager mViewPager){
        NewsPagerAdapter pagerAdapter = new NewsPagerAdapter(getChildFragmentManager());
        pagerAdapter.addFragment(getString(R.string.news_top));
        pagerAdapter.addFragment(getString(R.string.news_nba));
        pagerAdapter.addFragment(getString(R.string.news_cars));
        pagerAdapter.addFragment(getString(R.string.news_jokes));

        mViewPager.setAdapter(pagerAdapter);
    }

    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        mPageTabs.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        mPageTabs.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        mPageTabs.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        mPageTabs.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, dm));
        // 设置Tab标题文字的大小
        mPageTabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, dm));
        // 设置Tab Indicator的颜色
        mPageTabs.setIndicatorColor(Color.parseColor("#4caf50"));
        // 取消点击Tab时的背景色
        mPageTabs.setTabBackground(0);
    }

    public class NewsPagerAdapter extends FragmentPagerAdapter {
        private final List<String> mFragmentTitles = new ArrayList<>();

        public NewsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(String title){
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return NewsFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return mFragmentTitles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}