package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main.presenter;

import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BasePresenter;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;

import java.util.ArrayList;

/**
 * Created by user on 27/06/18.
 */

public interface IMainPresenter {

    void onGetEarthquakes();

    interface View extends BasePresenter.View {

        void initView(ArrayList<EarthquakeEntity> earthquakes);

        void initNotAvailableView();

        void updateView();
    }
}
