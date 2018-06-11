package com.mario.newsapiexample.components.ui.search;

import com.mario.newsapiexample.components.base.BaseDialogView;
import com.mario.newsapiexample.components.base.BasePresenter;

/**
 * Created by mario on 11/06/18.
 */

interface SearchContract {
    interface Presenter extends BasePresenter<View> {
    }

    interface View extends BaseDialogView<Presenter> {

    }
}
