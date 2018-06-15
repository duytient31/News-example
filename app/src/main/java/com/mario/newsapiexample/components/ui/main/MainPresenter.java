package com.mario.newsapiexample.components.ui.main;

import com.mario.newsapiexample.data.model.news.News;
import com.mario.newsapiexample.data.source.news.NewsRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mario on 11/06/18.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private NewsRepository newsRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    MainPresenter() {
    }

    @Override
    public void onDestroy() {
        view = null;
        compositeDisposable.clear();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
        newsRepository = new NewsRepository();
    }

    @Override
    public void fetchTopHeadlines() {
        compositeDisposable.add(newsRepository.getNewsRemoteDataSource().getTopHeadLines()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(News::getArticles)
                .subscribe(articles -> {
                    if (view != null) {
                        view.showTopHeadlines(articles);
                    }
                }));
    }

    @Override
    public void fetchLatestNews() {
        compositeDisposable.add(
                newsRepository.getNewsRemoteDataSource().getLatestNews()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(News::getArticles)
                        .subscribe(articles -> {
                            if (view != null) {
                                view.showLatestNews(articles);
                            }
                        }));
    }

    @Override
    public void searchNews(String keyword, int page) {
        compositeDisposable.add(newsRepository.getNewsRemoteDataSource().searchNews(keyword, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(News::getArticles)
                .subscribe(articles -> {
                    if (view != null) {
                        view.showSearchResults(articles);
                    }
                }, throwable -> view.toast(throwable.getMessage())));
    }
}
