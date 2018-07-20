package com.multiplica.cleanarchitecture.mvpapplication;

import android.support.multidex.MultiDexApplication;

import com.multiplica.cleanarchitecture.mvpapplication.data.model.RealmConfig;

import io.realm.Realm;

/**
 * Created by user on 18/07/18.
 */

public class Application extends MultiDexApplication {

    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RealmConfig.getInstance().initRealm(this);
    }

    public static Application getInstance() {
        return instance;
    }
}
