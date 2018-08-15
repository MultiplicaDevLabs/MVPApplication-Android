package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.login.presenter;

import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BasePresenter;
import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseResponse;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.LoginEntity;
import com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.login.ILoginInteractor;
import com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.login.LoginInteractorImpl;

/**
 * Created by user on 03/08/18.
 */

public class LoginPresenterImpl extends BasePresenter<ILoginPresenter.View> implements ILoginPresenter {

    private ILoginInteractor interactor;

    public LoginPresenterImpl(){
        super.initialize();
        interactor = new LoginInteractorImpl();
    }

    @Override
    public void initialize() {
        super.initialize();
        if(interactor.isUserCreated()){
            getView().initView();
        }else{
            getView().showCreteUserDialog();
        }
    }

    @Override
    public void onLoginUser(String user, String password) {
        interactor.loginUser(user, password, new BaseResponse() {
            @Override
            public void onSuccess() {

                getView().sucessLogin();
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                getView().failedLogin();
            }
        });
    }

    @Override
    public void onCreateUser(LoginEntity loginEntity) {
        interactor.createUser(loginEntity, new BaseResponse() {
            @Override
            public void onSuccess() {
                getView().showCreateUserSuccessfully();
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
            }
        });
    }
}
