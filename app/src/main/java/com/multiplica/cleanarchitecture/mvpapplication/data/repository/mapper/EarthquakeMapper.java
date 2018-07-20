package com.multiplica.cleanarchitecture.mvpapplication.data.repository.mapper;

import com.multiplica.cleanarchitecture.mvpapplication.data.model.item.EarthquakeRealm;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;

import java.util.ArrayList;

import io.realm.RealmList;

/**
 * Created by user on 18/07/18.
 */

public class EarthquakeMapper extends Mapper<EarthquakeRealm, EarthquakeEntity> {


    @Override
    public EarthquakeEntity map(EarthquakeRealm value) {

        EarthquakeEntity earthquake = new EarthquakeEntity();
        if (value == null)
            return null;

        earthquake.setId(value.getId());
        earthquake.setPlace(value.getPlace());
        earthquake.setTime(value.getTime());
        earthquake.setTitle(value.getTitle());
        earthquake.setLatitude(value.getLatitude());
        earthquake.setLongitude(value.getLongitude());

        return earthquake;

    }

    @Override
    public EarthquakeRealm reverseMap(EarthquakeEntity value) {
        EarthquakeRealm earthquake = new EarthquakeRealm();
        if (value == null)
            return null;

        earthquake.setId(value.getId());
        earthquake.setPlace(value.getPlace());
        earthquake.setTime(value.getTime());
        earthquake.setTitle(value.getTitle());
        earthquake.setLatitude(value.getLatitude());
        earthquake.setLongitude(value.getLongitude());

        return earthquake;
    }

}
