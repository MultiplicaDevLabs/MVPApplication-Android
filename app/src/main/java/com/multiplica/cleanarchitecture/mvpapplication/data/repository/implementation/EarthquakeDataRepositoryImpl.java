package com.multiplica.cleanarchitecture.mvpapplication.data.repository.implementation;

import com.multiplica.cleanarchitecture.mvpapplication.data.model.operation.EarthquakeRealmRepositoryImpl;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;
import com.multiplica.cleanarchitecture.mvpapplication.domain.repository.IEarthquakeRepository;

import java.util.ArrayList;

/**
 * Created by user on 19/07/18.
 */

public class EarthquakeDataRepositoryImpl implements IEarthquakeRepository{

    IEarthquakeRepository repository = new EarthquakeRealmRepositoryImpl();


    public static EarthquakeDataRepositoryImpl init() {
        return new EarthquakeDataRepositoryImpl();
    }

    @Override
    public void create(EarthquakeEntity earthquakeEntity) {
        repository.create(earthquakeEntity);
    }

    @Override
    public void create(ArrayList<EarthquakeEntity> earthquakeEntities) {
        repository.create(earthquakeEntities);
    }

    @Override
    public EarthquakeEntity read(int id) {
        return repository.read(id);
    }

    @Override
    public void upadate(EarthquakeEntity earthquakeEntity) {
        repository.upadate(earthquakeEntity);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public ArrayList<EarthquakeEntity> getAll() {
        return repository.getAll();
    }
}
