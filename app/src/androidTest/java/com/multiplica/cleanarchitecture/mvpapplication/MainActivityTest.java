package com.multiplica.cleanarchitecture.mvpapplication;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.multiplica.cleanarchitecture.mvpapplication.presentation.activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by user on 30/07/18.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mManinActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResorce(){
        mIdlingResource = new TestIdlingResource(mManinActivityRule.getActivity());
    }

    @Test
    public void checkDatePickerModalIsDisplayed(){
        onView(withId(R.id.select_date_button))
                .perform(click());
        onView(withText("2018")).check(matches(isDisplayed()));
    }

    @Test
    public void dateIsSelected(){
        onView(withId(R.id.select_date_button))
                .perform(click());
        onView(withText("2018")).check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.selected_date_text_view)).check(matches(withText("01/Aug/2018")));
    }

    @Test
    public void dateIsNotSelected(){
        onView(withId(R.id.select_date_button))
                .perform(click());
        onView(withText("2018")).check(matches(isDisplayed()));
        onView(withId(android.R.id.button2)).perform(click());
        onView(withId(R.id.selected_date_text_view)).check(matches(withText("")));
    }


    @Test
    public void checkEarthquakeIsDisplayed(){
        onView(withId(R.id.download_data_button))
                .perform(click());

        IdlingRegistry.getInstance().register(mIdlingResource);

        onView(withId(R.id.main_recycler_earthquakes)).check(matches(isDisplayed()));
    }

    @Test
    public void checkLocalEarthquakeIsDisplayed(){

        onView(withId(R.id.load_data_button))
                .perform(click());
        onView(withId(R.id.main_recycler_earthquakes)).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource(){
        if(mIdlingResource!=null){
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }

}

