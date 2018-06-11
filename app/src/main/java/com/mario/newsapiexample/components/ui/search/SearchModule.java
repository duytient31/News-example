package com.mario.newsapiexample.components.ui.search;

import com.mario.newsapiexample.components.di.ActivityScoped;
import com.mario.newsapiexample.components.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by mario on 11/06/18.
 */

@Module
public abstract class SearchModule {

    @ActivityScoped
    @Binds
    abstract SearchContract.Presenter provideSearchPresenter(SearchPresenter searchPresenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SearchFragment searchFragment();
}
