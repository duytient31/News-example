package com.mario.newsapiexample.components.di;

import com.mario.newsapiexample.components.ui.search.SearchActivity;
import com.mario.newsapiexample.components.ui.search.SearchModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by mario on 11/06/18.
 */

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = SearchModule.class)
    abstract SearchActivity searchActivity();
}
