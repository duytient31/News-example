package com.mario.newsapiexample.components.base;

import android.support.annotation.StringRes;

/**
 * Created by mario on 11/06/18.
 */


/**
 * Base dialogView, basically to avoid boilerplate just extend this interface to get basic Android dial component
 *
 * @param <P> the presenter for this view
 */
public interface BaseDialogView<P extends BasePresenter> extends BaseView<P> {

    /**
     * Show dialog
     *
     * @param msgResId   the resource id of this dialog message
     * @param cancelable false if you want the user no to cancelToPreferences the dialog
     */
    void showDialog(@StringRes int msgResId, boolean cancelable);

    /**
     * Show dialog
     *
     * @param msg        the dialog msg
     * @param cancelable false if you want the user no to cancelToPreferences the dialog
     */
    void showDialog(String msg, boolean cancelable);

    /**
     * Dismiss the previously created dialog
     *
     * Note that implementation of this interface might decide whether or not an exception should be thrown in case
     * dialog were not previously created
     */
    void dismissDialog();

    /**
     * Toast a message on the view
     *
     * @param msgResId the resource id of this toast string message
     */
    void toast(@StringRes int msgResId);

    /**
     * Toast a message on the view
     *
     * @param msgResId the message to be displayed
     */
    void toast(String msgResId);

}