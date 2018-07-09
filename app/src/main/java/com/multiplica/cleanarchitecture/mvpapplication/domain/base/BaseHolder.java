package com.multiplica.cleanarchitecture.mvpapplication.domain.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by user on 27/06/18.
 */

public class BaseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public BaseHolder(View view) {
        super(view);
    }

    @Override
    public void onClick(View v) {

    }
}
