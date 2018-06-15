package com.mario.newsapiexample.components.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;

import com.jakewharton.rxbinding2.widget.RxSearchView;
import com.mario.newsapiexample.R;
import com.mario.newsapiexample.components.base.BaseDialogFragment;
import com.mario.newsapiexample.components.di.ActivityScoped;
import com.mario.newsapiexample.components.ui.main.adapter.MainAdapter;
import com.mario.newsapiexample.data.model.news.Article;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by mario on 11/06/18.
 */

@ActivityScoped
public class MainFragment extends BaseDialogFragment<MainContract.Presenter> implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;

    @Inject
    public MainFragment() {
    }

    @BindView(R.id.recyclerView_news)
    RecyclerView recyclerViewNews;
    @BindView(R.id.searchview_news)
    SearchView searchViewNews;

    private MainAdapter mainAdapter;
    private LinearLayoutManager layoutManager;
    private Disposable disposable;

    @Override
    protected MainContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter != null) {
            presenter.setView(this);
            presenter.fetchLatestNews();
            presenter.fetchTopHeadlines();
        }

        mainAdapter = new MainAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerViewNews.setLayoutManager(layoutManager);
        recyclerViewNews.setHasFixedSize(true);
        recyclerViewNews.setAdapter(mainAdapter);

        RxSearchView.queryTextChanges(searchViewNews)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        if (charSequence.length() != 0) {
                            presenter.searchNews(charSequence.toString(), 1);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(getClass().getSimpleName(), "onError: ", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i(getClass().getSimpleName(), "onComplete: ");
                    }
                });

    }

    @Override
    public void showTopHeadlines(List<Article> headlinesList) {
        mainAdapter.setHeadlineItems(headlinesList);
    }

    @Override
    public void showLatestNews(List<Article> newsList) {
        mainAdapter.setNewsItems(newsList);
    }

    @Override
    public void showSearchResults(List<Article> newsList) {
        mainAdapter.setNewsItems(newsList);
        mainAdapter.notifyDataSetChanged();

    }
}
