package com.multiplica.cleanarchitecture.mvpapplication.data.model;

import android.content.Context;

import com.multiplica.cleanarchitecture.mvpapplication.data.preference.SettingsData;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by user on 18/07/18.
 */

public class RealmConfig {
    private static RealmConfig singleton = new RealmConfig();
    private Realm realm;
    private RealmConfiguration realmConfig;

    public static RealmConfig getInstance() {

        if(singleton==null){
            singleton = new RealmConfig();
        }

        return singleton;
    }

    private RealmConfig() {
    }

    public synchronized void initRealm(Context context) {
        Realm.init(context);
        Realm.setDefaultConfiguration(getRealmConfig());
    }

    public Realm getRealm() {
        return Realm.getInstance(getRealmConfig());
    }

    public synchronized RealmConfiguration getRealmConfig() {
        if (realmConfig == null)
            realmConfig = new RealmConfiguration
                    .Builder()
                    .name("earthquake.realm")
                    .schemaVersion(1)
                    .deleteRealmIfMigrationNeeded()
                    .encryptionKey(SettingsData.getInstance().getEncryptionKey())
                    .build();

        return realmConfig;
    }

}
