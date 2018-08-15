package com.multiplica.cleanarchitecture.mvpapplication.domain.base;

/**
 * Created by user on 03/08/18.
 */

public abstract class BaseResponse {

    public abstract void onSuccess();

    public void onError(Exception e) {}
}
