package com.mario.newsapiexample.components.ui.main;

import com.mario.newsapiexample.components.di.ActivityScoped;
import com.mario.newsapiexample.components.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by mario on 11/06/18.
 */

@Module
public abstract class MainModule {

    @ActivityScoped
    @Binds
    abstract MainContract.Presenter provideSearchPresenter(MainPresenter searchPresenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainFragment searchFragment();
}
