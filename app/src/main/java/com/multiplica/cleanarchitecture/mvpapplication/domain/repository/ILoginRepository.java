package com.multiplica.cleanarchitecture.mvpapplication.domain.repository;

import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseResponse;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.LoginEntity;

/**
 * Created by user on 03/08/18.
 */

public interface ILoginRepository {

    void create(LoginEntity loginEntity);

    void create(LoginEntity loginEntity, BaseResponse callback);

    LoginEntity read(int id);

    void update(LoginEntity loginEntity);

    void delete(int id);
}
