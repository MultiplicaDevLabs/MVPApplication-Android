package com.multiplica.cleanarchitecture.mvpapplication.data.repository.implementation;

import com.multiplica.cleanarchitecture.mvpapplication.data.model.operation.LoginRealmRepositoryImpl;
import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseResponse;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.LoginEntity;
import com.multiplica.cleanarchitecture.mvpapplication.domain.repository.ILoginRepository;

/**
 * Created by user on 03/08/18.
 */

public class LoginDataRepositoryImpl implements ILoginRepository {

    ILoginRepository repository = new LoginRealmRepositoryImpl();

    public static LoginDataRepositoryImpl init(){
        return new LoginDataRepositoryImpl();
    }

    @Override
    public void create(LoginEntity loginEntity) {
        repository.create(loginEntity);
    }

    @Override
    public void create(LoginEntity loginEntity, BaseResponse callback) {
        repository.create(loginEntity,callback);
    }

    @Override
    public LoginEntity read(int id) {
        return repository.read(id);
    }

    @Override
    public void update(LoginEntity loginEntity) {
        repository.update(loginEntity);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
