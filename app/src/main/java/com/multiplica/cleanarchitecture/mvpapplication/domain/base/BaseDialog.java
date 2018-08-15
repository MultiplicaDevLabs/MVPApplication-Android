package com.multiplica.cleanarchitecture.mvpapplication.domain.base;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.multiplica.cleanarchitecture.mvpapplication.R;
import com.multiplica.cleanarchitecture.mvpapplication.domain.callback.IOnDialogResultListener;

/**
 * Created by user on 06/08/18.
 */

public class BaseDialog extends AppCompatDialogFragment {

    public IOnDialogResultListener result;

    public void setOnDialogListener(IOnDialogResultListener result) {
        this.result = result;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.support.v4.app.DialogFragment.STYLE_NO_TITLE, R.style.AppTheme_NoActionBar);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_Fade;
        } catch (Exception e) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_Fade;
            e.printStackTrace();
        }
    }

    public void startContentAnimation(View view, @AnimRes final int id) {
        Animation anim = AnimationUtils.loadAnimation(getActivity(), id);
        anim.reset();
        view.clearAnimation();
        view.startAnimation(anim);
    }

    public void dismissAndContentAnimation(View view, @AnimRes final int id) {
        Animation anim = AnimationUtils.loadAnimation(getActivity(), id);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        anim.reset();
        view.clearAnimation();
        view.startAnimation(anim);
    }
}
