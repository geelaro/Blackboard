package com.geelaro.sunshine;
import android.support.test.runner.AndroidJUnit4;

import com.geelaro.sunshine.main.MainHomeActivity;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by geelaro on 2017/7/5.
 */
@RunWith(AndroidJUnit4.class)
public class WeatherFragmentAndroidUnitTest  {


    public void testOnCreate(){
        onView(withId(R.id.list_item_textview))
                .perform(click());

        onData(withText(R.id.textView)).perform();


    }


}
