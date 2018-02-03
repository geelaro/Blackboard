package com.geelaro.blackboard.movies;

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
import com.geelaro.blackboard.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geealro on 2018/2/3.
 */

public class MoviewFragment extends Fragment {
    public static final int MOVIE_LIVING = 0;
    public static final int MOVIE_TOP250 = 1;

    //
    private DisplayMetrics dm;
    //
    private PagerSlidingTabStrip mPageTabs;
    private ViewPager mViewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frame_movie, container, false);
        dm = getResources().getDisplayMetrics();
        mPageTabs = (PagerSlidingTabStrip) rootView.findViewById(R.id.tabs);
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpage);

        setViewPager(mViewPager);

        mPageTabs.setViewPager(mViewPager);

        setTabsValue();

        return rootView;
    }

    private void setViewPager(ViewPager mViewPager) {
        MoviesPagerAdapter pagerAdapter = new MoviesPagerAdapter(getChildFragmentManager());
        pagerAdapter.addFragment(MovieLiveFragment.newInstance(MOVIE_LIVING),getString(R.string.movie_live));
        pagerAdapter.addFragment(MovieTop250Fragment.newInstance(MOVIE_TOP250),getString(R.string.movie_top250));
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
                TypedValue.COMPLEX_UNIT_DIP, 2, dm));
        // 设置Tab标题文字的大小
        mPageTabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 14, dm));
        // 设置Tab Indicator的颜色
        mPageTabs.setIndicatorColor(Color.parseColor("#3278D3"));
        //选中时的颜色
        mPageTabs.setSelectedTextColorResource(R.color.colorPrimaryDark);
        // 取消点击Tab时的背景色
        mPageTabs.setTabBackground(0);
    }

    public class MoviesPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fmList = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MoviesPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment,String title) {
            fmList.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fmList.get(position);
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

