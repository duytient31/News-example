package com.mario.newsapiexample;

import com.mario.newsapiexample.components.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by mario on 11/06/18.
 */

public class NewsExampleApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends NewsExampleApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
