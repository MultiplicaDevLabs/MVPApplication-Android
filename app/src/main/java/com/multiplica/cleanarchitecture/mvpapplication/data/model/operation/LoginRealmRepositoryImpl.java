package com.multiplica.cleanarchitecture.mvpapplication.data.model.operation;

import com.multiplica.cleanarchitecture.mvpapplication.Application;
import com.multiplica.cleanarchitecture.mvpapplication.data.model.RealmConfig;
import com.multiplica.cleanarchitecture.mvpapplication.data.model.item.LoginRealm;
import com.multiplica.cleanarchitecture.mvpapplication.data.repository.mapper.LoginMapper;
import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseResponse;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.LoginEntity;
import com.multiplica.cleanarchitecture.mvpapplication.domain.module.RealmModule;
import com.multiplica.cleanarchitecture.mvpapplication.domain.repository.ILoginRepository;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by user on 03/08/18.
 */

public class LoginRealmRepositoryImpl implements ILoginRepository {

    private LoginMapper mapper = new LoginMapper();

    public LoginRealmRepositoryImpl(){
        Application.getInstance().getComponents().inject(this);
        realm = realmModule.getRealm();
    }

    @Inject
    RealmModule realmModule;
    Realm realm;

    @Override
    public void create(LoginEntity loginEntity) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(mapper.reverseMap(loginEntity));
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void create(final LoginEntity loginEntity, final BaseResponse callback) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(mapper.reverseMap(loginEntity));
                callback.onSuccess();
            }
        });
    }

    @Override
    public LoginEntity read(int id) {

        Realm realm2 = RealmConfig.getInstance().getRealm();

        realm2.beginTransaction();
        LoginRealm login = realm2.where(LoginRealm.class).equalTo(LoginRealm.ID,id).findFirst();
        LoginEntity loginEntity = mapper.map(login);
        realm2.commitTransaction();
        realm2.close();

        return loginEntity;
    }

    @Override
    public void update(LoginEntity loginEntity) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(mapper.reverseMap(loginEntity));
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void delete(int id) {
        realm.beginTransaction();
        LoginRealm login = realm.where(LoginRealm.class).equalTo(LoginRealm.ID,id).findFirst();
        login.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }
}
