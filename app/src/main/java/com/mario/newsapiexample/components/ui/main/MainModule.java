package com.mario.newsapiexample.components.ui.main;

import com.mario.newsapiexample.components.di.ActivityScoped;
import com.mario.newsapiexample.components.di.FragmentScoped;
import com.mario.newsapiexample.components.ui.main.news.NewsContract;
import com.mario.newsapiexample.components.ui.main.news.NewsFragment;
import com.mario.newsapiexample.components.ui.main.news.NewsPresenter;
import com.mario.newsapiexample.components.ui.main.search.SearchContract;
import com.mario.newsapiexample.components.ui.main.search.SearchFragment;
import com.mario.newsapiexample.components.ui.main.search.SearchPresenter;

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
    abstract NewsContract.Presenter provideNewsPresenter(NewsPresenter newsPresenter);

    @ActivityScoped
    @Binds
    abstract SearchContract.Presenter provideSearchPresenter(SearchPresenter searchPresenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract NewsFragment newsFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SearchFragment searchFragment();
}
