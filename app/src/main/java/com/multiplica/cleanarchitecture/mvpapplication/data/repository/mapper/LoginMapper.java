package com.multiplica.cleanarchitecture.mvpapplication.data.repository.mapper;

import com.multiplica.cleanarchitecture.mvpapplication.data.model.item.LoginRealm;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.LoginEntity;

/**
 * Created by user on 03/08/18.
 */

public class LoginMapper extends Mapper<LoginRealm, LoginEntity> {
    @Override
    public LoginEntity map(LoginRealm value) {

        LoginEntity loginEntity = new LoginEntity();

        if (value == null)
            return null;

        loginEntity.setId(value.getId());
        loginEntity.setUser(value.getUser());
        loginEntity.setPassword(value.getPassword());

        return loginEntity;
    }

    @Override
    public LoginRealm reverseMap(LoginEntity value) {

        LoginRealm loginRealm = new LoginRealm();

        if (value == null)
            return null;

        loginRealm.setId(value.getId());
        loginRealm.setUser(value.getUser());
        loginRealm.setPassword(value.getPassword());

        return loginRealm;
    }
}
