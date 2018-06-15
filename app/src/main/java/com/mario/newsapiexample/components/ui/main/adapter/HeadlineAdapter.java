package com.mario.newsapiexample.components.ui.main.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mario.newsapiexample.R;
import com.mario.newsapiexample.components.adapter.BaseRecyclerViewAdapter;
import com.mario.newsapiexample.data.model.news.Article;
import com.mario.newsapiexample.util.Utils;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;

public class HeadlineAdapter extends BaseRecyclerViewAdapter<Article, BaseRecyclerViewAdapter.ItemBaseVH> {

    @NonNull
    @Override
    public ItemBaseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HeadlineVH((LayoutInflater.from(parent.getContext()).inflate(R.layout.item_headline, parent, false)));
    }

    class HeadlineVH extends ItemBaseVH {

        @BindView(R.id.imageView_headline_photo)
        ImageView imageViewHeadlinePhoto;
        @BindView(R.id.textView_headline_title)
        TextView textViewHeadlineTitle;
        @BindView(R.id.textView_headline_source)
        TextView textViewHeadlineSource;
        @BindView(R.id.textView_headline_time)
        TextView textViewHeadlineTime;

        public HeadlineVH(View itemView) {
            super(itemView);

            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClick(HeadlineAdapter.this, HeadlineVH.this, getAdapterPosition());
                }
            });
        }

        @Override
        protected void performBinding(Article article) {
            Picasso.with(itemView.getContext())
                    .load(article.getUrlToImage())
                    .into(imageViewHeadlinePhoto);

            textViewHeadlineTitle.setText(article.getTitle());
            textViewHeadlineSource.setText(article.getSource().getName());
            textViewHeadlineTime.setText(Utils.convertDate(article.getPublishedAt()));
        }
    }
}
