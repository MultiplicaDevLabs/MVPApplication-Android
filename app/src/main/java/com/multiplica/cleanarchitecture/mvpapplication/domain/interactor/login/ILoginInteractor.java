package com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.login;

import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseResponse;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.LoginEntity;

/**
 * Created by user on 03/08/18.
 */

public interface ILoginInteractor {

    void loginUser(String user, String password, BaseResponse callback);

    void createUser(LoginEntity loginEntity, BaseResponse callback);

    boolean isUserCreated();
}
