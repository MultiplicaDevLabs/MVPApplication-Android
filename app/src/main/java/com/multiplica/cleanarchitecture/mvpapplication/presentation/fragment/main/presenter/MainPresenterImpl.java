package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main.presenter;

import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BasePresenter;
import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseResponseList;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;
import com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.main.IMainInteractor;
import com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.main.MainInteractorImpl;

import java.util.ArrayList;

/**
 * Created by user on 27/06/18.
 */

public class MainPresenterImpl extends BasePresenter<IMainPresenter.View> implements IMainPresenter{

    private IMainInteractor interactor;

    @Override
    public void initialize() {
        super.initialize();
        interactor = new MainInteractorImpl();
        onGetEarthquakes();
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
