package com.multiplica.cleanarchitecture.mvpapplication.domain.base;

import android.app.Activity;
import android.content.Context;

/**
 * Created by user on 26/06/18.
 */

public abstract class BasePresenter<T> {

    private T view;
    private Context context;
    private Activity activity;

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void initialize() {

    }

    public interface View {

    }
}
