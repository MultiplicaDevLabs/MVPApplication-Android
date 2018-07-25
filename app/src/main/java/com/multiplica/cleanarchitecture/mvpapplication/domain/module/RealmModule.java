package com.multiplica.cleanarchitecture.mvpapplication.domain.module;

import android.content.Context;

import com.multiplica.cleanarchitecture.mvpapplication.data.preference.SettingsData;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by user on 20/07/18.
 */

@Module
public class RealmModule {

    private Realm realm;
    private RealmConfiguration realmConfig;

    public RealmModule(Context context){
        initRealm(context);
    }

    private synchronized void initRealm(Context context) {
        Realm.init(context);
        Realm.setDefaultConfiguration(getRealmConfig());
    }

    @Provides
    public Realm getRealm() {
        return Realm.getInstance(getRealmConfig());
    }


    private synchronized RealmConfiguration getRealmConfig() {
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
