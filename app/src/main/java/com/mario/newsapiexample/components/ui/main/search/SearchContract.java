package com.mario.newsapiexample.components.ui.main.search;

import com.mario.newsapiexample.components.base.BaseDialogView;
import com.mario.newsapiexample.components.base.BasePresenter;
import com.mario.newsapiexample.data.model.news.Article;

import java.util.List;

public interface SearchContract {
    interface Presenter extends BasePresenter<View> {
        void searchNews(String keyword, int page);
    }

    interface View extends BaseDialogView<Presenter> {
        void showSearchResults(List<Article> newsList);

        void showNoResults();
    }
}
