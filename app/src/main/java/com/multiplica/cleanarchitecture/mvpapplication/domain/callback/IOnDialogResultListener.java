package com.multiplica.cleanarchitecture.mvpapplication.domain.callback;

import android.support.v7.app.AppCompatDialogFragment;

/**
 * Created by user on 06/08/18.
 */

public interface IOnDialogResultListener<T> {

    /**
     * Called when a {@link AppCompatDialogFragment} you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional
     * data from it.
     *
     * @param requestCode The integer request code originally supplied to
     *                    onDialogResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child fragment
     *                    through its setResult().
     * @param data        An Object, which can return result data to the caller
     */
    void onDialogResult(int requestCode, int resultCode, T data);

}
