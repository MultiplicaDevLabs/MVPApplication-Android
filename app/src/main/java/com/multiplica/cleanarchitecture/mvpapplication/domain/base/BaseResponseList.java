package com.multiplica.cleanarchitecture.mvpapplication.domain.base;

import java.util.ArrayList;

/**
 * Created by user on 27/06/18.
 */

public abstract class BaseResponseList<T> {

    public abstract void onSuccess(ArrayList<T> objList);

    public void onError(Exception e, String msg) {
    }

}
