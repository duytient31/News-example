package com.mario.newsapiexample.components.di;

import com.mario.newsapiexample.components.ui.main.news.NewsActivity;
import com.mario.newsapiexample.components.ui.main.news.NewsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by mario on 11/06/18.
 */

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = NewsModule.class)
    abstract NewsActivity searchActivity();
}
