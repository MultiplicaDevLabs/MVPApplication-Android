package com.multiplica.cleanarchitecture.mvpapplication.domain.component;

import com.multiplica.cleanarchitecture.mvpapplication.data.model.operation.EarthquakeRealmRepositoryImpl;
import com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.main.ListInteractorImpl;
import com.multiplica.cleanarchitecture.mvpapplication.domain.module.RealmModule;
import com.multiplica.cleanarchitecture.mvpapplication.domain.module.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by user on 20/07/18.
 */

@Singleton
@Component (modules = {RetrofitModule.class, RealmModule.class})
public interface Components {

    void inject(ListInteractorImpl target);
    void inject(EarthquakeRealmRepositoryImpl target);

}
