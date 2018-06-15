package com.mario.newsapiexample.components.ui.main;

import com.mario.newsapiexample.R;
import com.mario.newsapiexample.components.base.BaseActivity;
import com.mario.newsapiexample.components.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by mario on 11/06/18.
 */

public class MainActivity extends BaseActivity {

    @Inject
    MainFragment searchFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base;
    }

    @Override
    protected BaseFragment getFragment() {
        return searchFragment;
    }

    @Override
    protected boolean isDisplayHomeAsUpEnabled() {
        return false;
    }
}
