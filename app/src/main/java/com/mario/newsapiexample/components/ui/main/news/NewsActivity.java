package com.mario.newsapiexample.components.ui.main.news;

import com.mario.newsapiexample.R;
import com.mario.newsapiexample.components.base.BaseActivity;
import com.mario.newsapiexample.components.base.BaseFragment;
import com.mario.newsapiexample.components.ui.main.news.NewsFragment;

import javax.inject.Inject;

/**
 * Created by mario on 11/06/18.
 */

public class NewsActivity extends BaseActivity {

    @Inject
    NewsFragment newsFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base;
    }

    @Override
    protected BaseFragment getFragment() {
        return newsFragment;
    }

    @Override
    protected boolean isDisplayHomeAsUpEnabled() {
        return false;
    }
}
