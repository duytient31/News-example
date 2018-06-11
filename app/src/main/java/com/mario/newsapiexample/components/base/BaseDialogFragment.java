package com.mario.newsapiexample.components.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by mario on 11/06/18.
 */

public abstract class BaseDialogFragment<P extends BasePresenter> extends BasePresenterImpl<P> implements BaseDialogView<P> {

    private ProgressDialog progressDialog;
    private Toast toast;

    /**
     * {@inheritDoc}
     */
    @Override
    public void showDialog(String msg, boolean cancelable) {
        if (!isDetached() && progressDialog != null) {
            progressDialog.setCancelable(cancelable);
            progressDialog.setMessage(msg);
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showDialog(@StringRes int msgResId, boolean cancelable) {
        Context context;
        if (!isDetached() && progressDialog != null && (context = getContext()) != null) {
            progressDialog.setCancelable(cancelable);
            progressDialog.setMessage(context.getResources().getString(msgResId));

            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toast(@StringRes int msgResId) {
        Context context;
        if (!isDetached() && (context = getContext()) != null) {
            if (toast != null) {
                toast.cancel();
            }

            toast = Toast.makeText(getContext(), context.getResources().getString(msgResId), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissDialog();
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = null;
    }
}