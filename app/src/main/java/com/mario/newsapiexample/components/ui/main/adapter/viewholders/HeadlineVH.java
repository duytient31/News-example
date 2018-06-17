package com.mario.newsapiexample.components.ui.main.adapter.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mario.newsapiexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeadlineVH extends RecyclerView.ViewHolder {

    @BindView(R.id.imageView_headline_photo)
    public ImageView imageViewHeadlinePhoto;
    @BindView(R.id.textView_headline_title)
    public TextView textViewHeadlineTitle;
    @BindView(R.id.textView_headline_source)
    public TextView textViewHeadlineSource;
    @BindView(R.id.textView_headline_time)
    public TextView textViewHeadlineTime;

    public HeadlineVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}