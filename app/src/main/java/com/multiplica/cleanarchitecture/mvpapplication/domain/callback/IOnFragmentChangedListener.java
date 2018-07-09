package com.multiplica.cleanarchitecture.mvpapplication.domain.callback;

import android.support.v4.app.Fragment;

/**
 * Created by user on 27/06/18.
 */

public interface IOnFragmentChangedListener {

    void onFragmentChanged(Fragment fragment, int container);
}
