package com.mario.newsapiexample.components.ui.main.adapter.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mario.newsapiexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsVH extends RecyclerView.ViewHolder {
    @BindView(R.id.textView_news_title)
    public TextView textViewNewsTitle;
    @BindView(R.id.textView_news_description)
    public TextView textViewNewsDescription;
    @BindView(R.id.textView_news_time)
    public TextView textViewNewsTime;
    @BindView(R.id.textView_news_source)
    public TextView textViewNewsSource;

    public NewsVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}