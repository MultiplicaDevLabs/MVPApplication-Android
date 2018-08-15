package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.multiplica.cleanarchitecture.mvpapplication.R;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.splash.presenter.ISplashPresenter;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.splash.presenter.SplashPresenterImpl;

/**
 * Created by user on 06/08/18.
 */

public class SplashFragment extends Fragment implements ISplashPresenter.View{

    View rootView;

    private Thread splashThread;
    private final int time = 2500;
    private SplashPresenterImpl presenter;


    public static SplashFragment newInstance(Bundle args) {
        SplashFragment fragmentSplash = new SplashFragment();
        fragmentSplash.setHasOptionsMenu(true);
        fragmentSplash.setArguments(args);
        return fragmentSplash;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_splash, container, false);
        initResources();
        initPresenter();
        initTimer(time);
        return rootView;
    }

    private void initResources(){

    }

    private void initPresenter(){
        presenter = new SplashPresenterImpl();
        presenter.setView(this);
        presenter.initialize();
    }

    private void initTimer(final Integer time) {
        splashThread = new Thread() {
            public void run() {
                try {
                    sleep(time);
                    presenter.selectActivity();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splashThread.start();
    }

    @Override
    public void startNextActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
