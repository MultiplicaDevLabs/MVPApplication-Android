package com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.main;

import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseResponseList;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;

/**
 * Created by user on 27/06/18.
 */

public interface IMainInteractor {

    void getEarthquakeList(BaseResponseList<EarthquakeEntity> callback);

}
