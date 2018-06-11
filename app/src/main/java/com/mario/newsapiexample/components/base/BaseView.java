package com.mario.newsapiexample.components.base;

import android.app.Activity;
import android.content.Context;

/**
 * Created by mario on 11/06/18.
 */

/**
 * Base view for all other views
 *
 * @param <P> Presenter of view
 */
public interface BaseView<P extends BasePresenter> {

    Context getContext();

    Activity getActivity();
}
