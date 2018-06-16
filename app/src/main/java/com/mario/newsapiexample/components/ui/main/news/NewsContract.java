package com.mario.newsapiexample.components.ui.main.news;

import com.mario.newsapiexample.components.base.BaseDialogView;
import com.mario.newsapiexample.components.base.BasePresenter;
import com.mario.newsapiexample.data.model.news.Article;

import java.util.List;

/**
 * Created by mario on 14/06/18.
 */

public interface NewsContract {
    interface Presenter extends BasePresenter<View> {
        void fetchTopHeadlines();

        void fetchLatestNews();
    }

    interface View extends BaseDialogView<Presenter> {
        void showTopHeadlines(List<Article> headlinesList);

        void showLatestNews(List<Article> newsList);
    }
}
