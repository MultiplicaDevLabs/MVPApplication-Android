package com.multiplica.cleanarchitecture.mvpapplication;

import android.support.test.espresso.IdlingResource;
import android.support.v4.app.Fragment;
import android.view.View;

import com.multiplica.cleanarchitecture.mvpapplication.presentation.activity.MainActivity;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main.ListFragment;

import java.util.List;

/**
 * Created by user on 01/08/18.
 */

public class TestIdlingResource implements IdlingResource {

    private MainActivity activity;
    private ResourceCallback callback;

    public TestIdlingResource(MainActivity activity){
        this.activity = activity;
    }


    @Override
    public String getName() {
        return "ProgressbarIdlingResource";
    }

    @Override
    public boolean isIdleNow() {
        ListFragment fragment = (ListFragment) activity.getSupportFragmentManager().getFragments().get(0);
        View view = fragment.getView().findViewById(R.id.progressBar);

        boolean loadingHide = !view.isShown();

        if (loadingHide)
            callback.onTransitionToIdle();

        return loadingHide;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }

}
