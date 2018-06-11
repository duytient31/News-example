package com.mario.newsapiexample.components.di;

import com.mario.newsapiexample.NewsExampleApplication;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by mario on 11/06/18.
 */

@Component(modules = {AppModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<NewsExampleApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<NewsExampleApplication> {
    }

}
