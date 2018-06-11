package com.mario.newsapiexample.components.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by mario on 11/06/18.
 */

@Module
public abstract class AppModule {

    @Binds
    abstract Context bindContext(Application application);
}
