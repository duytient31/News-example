package com.mario.newsapiexample.components.ui.main.adapter.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mario.newsapiexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeadlineRecyclerViewVH extends RecyclerView.ViewHolder {
    @BindView(R.id.recyclerView_item_headline)
    public RecyclerView recyclerViewHeadlines;

    public HeadlineRecyclerViewVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}