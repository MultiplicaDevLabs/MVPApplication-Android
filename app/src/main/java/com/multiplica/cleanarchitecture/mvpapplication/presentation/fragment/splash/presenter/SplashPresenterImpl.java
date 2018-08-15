package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.splash.presenter;

import android.os.Build;

import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BasePresenter;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.activity.LoginActivity;

/**
 * Created by user on 06/08/18.
 */

public class SplashPresenterImpl extends BasePresenter<ISplashPresenter.View> implements ISplashPresenter{


    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void selectActivity() {

        Class cls = LoginActivity.class;

        if (getView() != null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //if need to request permission set here

                getView().startNextActivity(cls);
            } else {

                getView().startNextActivity(cls);
            }

    }
}
