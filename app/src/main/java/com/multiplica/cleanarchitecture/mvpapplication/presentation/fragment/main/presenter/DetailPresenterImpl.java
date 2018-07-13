package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main.presenter;

import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BasePresenter;
import com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.main.IDetailInteractor;

/**
 * Created by user on 13/07/18.
 */

public class DetailPresenterImpl extends BasePresenter<IDetailPresenter.View> implements IDetailPresenter {

    private IDetailInteractor interactor;

    @Override
    public void initialize() {
        super.initialize();
        getView().initView();
    }

    @Override
    public void onGetDetailInfo() {

    }
}
