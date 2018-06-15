package com.mario.newsapiexample.components.ui.main;

import com.mario.newsapiexample.components.base.BaseDialogView;
import com.mario.newsapiexample.components.base.BasePresenter;
import com.mario.newsapiexample.data.model.news.Article;

import java.util.List;

/**
 * Created by mario on 11/06/18.
 */

interface MainContract {
    interface Presenter extends BasePresenter<View> {
        void fetchTopHeadlines();

        void fetchLatestNews();

        void searchNews(String keyword, int page);
    }

    interface View extends BaseDialogView<Presenter> {
        void showTopHeadlines(List<Article> headlinesList);

        void showLatestNews(List<Article> newsList);

        void showSearchResults(List<Article> newsList);
    }
}
