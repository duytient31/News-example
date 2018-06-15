package com.mario.newsapiexample.components.di;

import com.mario.newsapiexample.components.ui.main.MainActivity;
import com.mario.newsapiexample.components.ui.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by mario on 11/06/18.
 */

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity searchActivity();
}
