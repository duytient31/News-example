package com.mario.newsapiexample.components.ui.main.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mario.newsapiexample.R;
import com.mario.newsapiexample.components.adapter.BaseRecyclerViewAdapter;
import com.mario.newsapiexample.data.model.news.Article;
import com.mario.newsapiexample.util.Utils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by mario on 14/06/18.
 */

public class NewsAdapter extends BaseRecyclerViewAdapter<Article, BaseRecyclerViewAdapter.ItemBaseVH> {

    @Inject
    public NewsAdapter() {
    }

    @NonNull
    @Override
    public ItemBaseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsVH((LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false)));
    }


    public class NewsVH extends ItemBaseVH {
        @BindView(R.id.textView_news_title)
        TextView textViewNewsTitle;
        @BindView(R.id.textView_news_description)
        TextView textViewNewsDescription;
        @BindView(R.id.textView_news_time)
        TextView textViewNewsTime;
        @BindView(R.id.textView_news_source)
        TextView textViewNewsSource;

        public NewsVH(View itemView) {
            super(itemView);

            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClick(NewsAdapter.this, NewsVH.this, getAdapterPosition());
                }
            });
        }

        @Override
        protected void performBinding(Article article) {
            textViewNewsTitle.setText(article.getTitle());
            textViewNewsDescription.setText(article.getDescription());
            textViewNewsSource.setText(article.getSource().getName());
            textViewNewsTime.setText(Utils.convertDate(article.getPublishedAt()));
        }
    }
}
