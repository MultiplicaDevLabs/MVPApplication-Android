package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.multiplica.cleanarchitecture.mvpapplication.domain.callback.IOnFragmentChangedListener;

/**
 * Created by user on 27/06/18.
 */

public class AttachFragment extends Fragment {

    public IOnFragmentChangedListener callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (IOnFragmentChangedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FragmentListener");
        }
    }
}

