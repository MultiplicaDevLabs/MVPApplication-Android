package com.multiplica.cleanarchitecture.mvpapplication;

import android.support.multidex.MultiDexApplication;

import com.multiplica.cleanarchitecture.mvpapplication.domain.component.Components;
import com.multiplica.cleanarchitecture.mvpapplication.domain.component.DaggerComponents;
import com.multiplica.cleanarchitecture.mvpapplication.domain.module.RealmModule;
import com.multiplica.cleanarchitecture.mvpapplication.domain.module.RetrofitModule;

/**
 * Created by user on 18/07/18.
 */

public class Application extends MultiDexApplication {

    private static Application instance;
    private Components components;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //RealmConfig.getInstance().initRealm(this);
        components = DaggerComponents.builder()
                .realmModule(new RealmModule(this))
                .retrofitModule(new RetrofitModule())
                .build();


    }

    public static Application getInstance() {
        return instance;
    }

    public Components getComponents() {
        return components;
    }
}
