package com.mario.newsapiexample.components.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mario.newsapiexample.R;
import com.mario.newsapiexample.components.base.BaseDialogFragment;
import com.mario.newsapiexample.components.di.ActivityScoped;

import javax.inject.Inject;

/**
 * Created by mario on 11/06/18.
 */

@ActivityScoped
public class SearchFragment extends BaseDialogFragment<SearchContract.Presenter> implements SearchContract.View {

    @Inject
    SearchContract.Presenter presenter;

    @Inject
    public SearchFragment() {
    }

    @Override
    protected SearchContract.Presenter getPresenter() {
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
        }

    }
}
