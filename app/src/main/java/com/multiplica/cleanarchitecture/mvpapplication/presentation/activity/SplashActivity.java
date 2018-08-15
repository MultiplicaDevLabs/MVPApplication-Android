package com.multiplica.cleanarchitecture.mvpapplication.presentation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.multiplica.cleanarchitecture.mvpapplication.R;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.splash.SplashFragment;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initFragment();
    }

    private void initFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, SplashFragment.newInstance(null))
                .commit();
    }
}
