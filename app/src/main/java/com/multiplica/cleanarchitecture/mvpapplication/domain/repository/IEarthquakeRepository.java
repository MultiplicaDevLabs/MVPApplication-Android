package com.multiplica.cleanarchitecture.mvpapplication.domain.repository;

import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;

import java.util.ArrayList;

/**
 * Created by user on 19/07/18.
 */

public interface IEarthquakeRepository {

    void create(EarthquakeEntity earthquakeEntity);

    void create(ArrayList<EarthquakeEntity> earthquakeEntities);

    EarthquakeEntity read(int id);

    void upadate(EarthquakeEntity earthquakeEntity);

    void delete(int id);

    ArrayList<EarthquakeEntity> getAll();




}
