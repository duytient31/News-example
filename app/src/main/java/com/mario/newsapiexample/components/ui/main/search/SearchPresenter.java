package com.mario.newsapiexample.components.ui.main.search;

import com.mario.newsapiexample.data.model.news.News;
import com.mario.newsapiexample.data.source.news.NewsRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private NewsRepository newsRepository;

    @Inject
    SearchPresenter() {
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
    public void setView(SearchContract.View view) {
        this.view = view;
        newsRepository = new NewsRepository();
    }

    @Override
    public void searchNews(String keyword, int page) {
        compositeDisposable.add(newsRepository.getNewsRemoteDataSource().searchNews(keyword, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(News::getArticles)
                .subscribe(articles -> {
                    if (view != null) {
                        if (articles.isEmpty()) {
                            view.showNoResults();
                        } else {
                            view.showSearchResults(articles);
                        }
                    }
                }, throwable -> view.toast(throwable.getMessage())));
    }

}
