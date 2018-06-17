package com.mario.newsapiexample.components.ui.main.news;

import com.mario.newsapiexample.components.di.ActivityScoped;
import com.mario.newsapiexample.components.di.FragmentScoped;
import com.mario.newsapiexample.components.ui.main.adapter.NewsAdapter;
import com.mario.newsapiexample.components.ui.main.adapter.SearchAdapter;
import com.mario.newsapiexample.components.ui.main.search.SearchContract;
import com.mario.newsapiexample.components.ui.main.search.SearchFragment;
import com.mario.newsapiexample.components.ui.main.search.SearchPresenter;
import com.mario.newsapiexample.data.source.news.NewsRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by mario on 11/06/18.
 */

@Module
public abstract class NewsModule {

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

    @Provides
    @ActivityScoped
    static NewsAdapter provideNewsAdapter(NewsActivity newsActivity) {
        return new NewsAdapter(newsActivity);
    }

    @Provides
    @ActivityScoped
    static SearchAdapter provideSearchAdapter(NewsActivity newsActivity) {
        return new SearchAdapter(newsActivity);
    }

    @ActivityScoped
    @Provides
    static NewsRepository provideNewsRepository() {
        return new NewsRepository();
    }
}
