package com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.main;

import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseResponseList;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;

/**
 * Created by user on 13/07/18.
 */

public interface IListInteractor {

    void getEarthquakeList(BaseResponseList<EarthquakeEntity> callback);

    void getLocalEarthquakeList(BaseResponseList<EarthquakeEntity> callback);
}
