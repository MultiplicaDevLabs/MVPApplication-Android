package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.login.presenter;

import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BasePresenter;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.LoginEntity;

import java.util.ArrayList;

/**
 * Created by user on 03/08/18.
 */

public interface ILoginPresenter {
    void onLoginUser(String user, String password);

    void onCreateUser(LoginEntity loginEntity);

    interface View extends BasePresenter.View {

        void initView();

        void sucessLogin();

        void failedLogin();

        void showCreteUserDialog();

        void showCreateUserSuccessfully();
    }

}
