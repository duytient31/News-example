package com.mario.newsapiexample.components.ui.main.adapter.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mario.newsapiexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderVH extends RecyclerView.ViewHolder {
    @BindView(R.id.textView_header_news)
    public TextView textViewHeader;

    public HeaderVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}