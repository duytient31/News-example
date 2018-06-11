package com.mario.newsapiexample.components.base;

/**
 * Created by mario on 11/06/18.
 */

public interface BasePresenter<V extends BaseView> {

    /**
     * Notify the presenter when the View is destroyed
     */
    void onDestroy();

    /**
     * Notify the presenter when the View is resumed
     */
    void onResume();

    /**
     * Notify the presenter when the View is paused
     */
    void onPause();

    /**
     * Pass the view to the presenter
     */
    void setView(V v);
}