package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main.presenter;

import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BasePresenter;
import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseResponseList;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;
import com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.main.IListInteractor;
import com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.main.ListInteractorImpl;

import java.util.ArrayList;

/**
 * Created by user on 13/07/18.
 */

public class ListPresenterImpl extends BasePresenter<IListPresenter.View> implements IListPresenter {

    private IListInteractor interactor;

    @Override
    public void initialize() {
        super.initialize();
        interactor = new ListInteractorImpl();
    }

    @Override
    public void onGetEarthquakes() {
        interactor.getEarthquakeList(new BaseResponseList<EarthquakeEntity>() {
            @Override
            public void onSuccess(ArrayList<EarthquakeEntity> objList) {
                if(objList.size()>0){
                    getView().initView(objList);
                }else{
                    getView().initNotAvailableView();
                }
            }

        });
    }
}
