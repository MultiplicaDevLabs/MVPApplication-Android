package com.multiplica.cleanarchitecture.mvpapplication.domain.module;

import com.multiplica.cleanarchitecture.mvpapplication.presentation.activity.MainActivity;

import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by user on 22/07/18.
 */

@Module
public abstract class ApplicationModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeActivityInjector();

}
