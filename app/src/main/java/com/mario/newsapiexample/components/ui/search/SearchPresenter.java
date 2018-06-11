package com.mario.newsapiexample.components.ui.search;

import javax.inject.Inject;

/**
 * Created by mario on 11/06/18.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;

    @Inject
    SearchPresenter() {
    }

    @Override
    public void onDestroy() {
        view = null;
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
    }
}
