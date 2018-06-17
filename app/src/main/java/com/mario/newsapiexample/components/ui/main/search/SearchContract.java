package com.mario.newsapiexample.components.ui.main.search;

import com.mario.newsapiexample.components.base.BaseDialogView;
import com.mario.newsapiexample.components.base.BasePresenter;
import com.mario.newsapiexample.data.model.news.Article;

import java.util.List;

/**
 * Created by mario on 14/06/18.
 */

public interface SearchContract {
    interface Presenter extends BasePresenter<View> {
        void searchNews(String keyword, int page);

        void onSearchCloseClicked();
    }

    interface View extends BaseDialogView<Presenter> {
        void showFirstPageResults(List<Article> newsList);

        void showNextPageResults(List<Article> newsList);

        void showNoResults();

        void replaceFragment();

        void showNoMorePages();
    }
}
