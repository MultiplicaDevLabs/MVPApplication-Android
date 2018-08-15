package com.multiplica.cleanarchitecture.mvpapplication.data.model.operation;

import com.multiplica.cleanarchitecture.mvpapplication.Application;
import com.multiplica.cleanarchitecture.mvpapplication.data.model.RealmConfig;
import com.multiplica.cleanarchitecture.mvpapplication.data.model.item.EarthquakeRealm;
import com.multiplica.cleanarchitecture.mvpapplication.data.repository.implementation.EarthquakeDataRepositoryImpl;
import com.multiplica.cleanarchitecture.mvpapplication.data.repository.mapper.EarthquakeMapper;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;
import com.multiplica.cleanarchitecture.mvpapplication.domain.module.RealmModule;
import com.multiplica.cleanarchitecture.mvpapplication.domain.repository.IEarthquakeRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by user on 19/07/18.
 */

public class EarthquakeRealmRepositoryImpl implements IEarthquakeRepository{

    private EarthquakeMapper mapper = new EarthquakeMapper();

    public EarthquakeRealmRepositoryImpl(){
        Application.getInstance().getComponents().inject(this);
        realm = realmModule.getRealm();
    }

    @Inject
    RealmModule realmModule;
    Realm realm;

    @Override
    public void create(EarthquakeEntity earthquakeEntity) {
        //Realm realm = RealmConfig.getInstance().getRealm();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(mapper.reverseMap(earthquakeEntity));
        realm.commitTransaction();

        realm.close();
    }

    @Override
    public void create(ArrayList<EarthquakeEntity> earthquakeEntities) {
        //Realm realm = RealmConfig.getInstance().getRealm();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(mapper.reverseMap(earthquakeEntities));
        realm.commitTransaction();

        realm.close();
    }

    @Override
    public EarthquakeEntity read(int id) {

        //Realm realm = RealmConfig.getInstance().getRealm();

        realm.beginTransaction();
        EarthquakeRealm earthquake = realm.where(EarthquakeRealm.class).equalTo(EarthquakeRealm.ID,id).findFirst();
        realm.commitTransaction();

        realm.close();

        return mapper.map(earthquake);
    }

    @Override
    public void upadate(EarthquakeEntity earthquakeEntity) {
        //Realm realm = RealmConfig.getInstance().getRealm();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(mapper.reverseMap(earthquakeEntity));
        realm.commitTransaction();

        realm.close();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ArrayList<EarthquakeEntity> getAll() {

        realm.beginTransaction();
        RealmResults<EarthquakeRealm> earthquakes = realm.where(EarthquakeRealm.class).findAll();
        realm.commitTransaction();

        ArrayList<EarthquakeEntity> earthquakeEntities = mapper.map(earthquakes);

        realm.close();

        return earthquakeEntities;
    }
}
