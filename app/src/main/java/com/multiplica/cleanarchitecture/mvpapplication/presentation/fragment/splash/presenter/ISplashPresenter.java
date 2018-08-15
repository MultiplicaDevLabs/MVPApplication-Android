package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.splash.presenter;

import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BasePresenter;

/**
 * Created by user on 06/08/18.
 */

public interface ISplashPresenter {

    void selectActivity();

    interface View extends BasePresenter.View {


        void startNextActivity(Class<?> cls);


    }
}
