package com.mario.newsapiexample.components.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

/**
 * Created by mario on 11/06/18.
 */

public abstract class BaseFragment extends DaggerFragment {

    private Unbinder unbinder;

    /**
     * Provide the resource layout for fragment
     *
     * @return layoutId
     */

    protected abstract int getLayoutId();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final int layoutId = getLayoutId();
        if (layoutId <= 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        final View view = inflater.inflate(layoutId, null);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }

}
