package com.mario.newsapiexample.components.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mario.newsapiexample.R;
import com.mario.newsapiexample.data.model.news.Article;
import com.mario.newsapiexample.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Article> newsList;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setNewsItems(List<Article> newsArticles) {
        this.newsList = newsArticles;
        notifyDataSetChanged();
    }

    private static final int SEARCH_HEADER = 0;
    private static final int SEARCH_LIST = 1;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        switch (viewType) {
            case SEARCH_HEADER:
                holder = new HeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_recyclerview_header, parent, false));
                break;
            case SEARCH_LIST:
                holder = new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false));
                break;
            default:
                holder = new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case SEARCH_LIST:
                ((NewsViewHolder) holder).textViewNewsTitle.setText(newsList.get(position).getTitle());
                ((NewsViewHolder) holder).textViewNewsDescription.setText(newsList.get(position).getDescription());
                ((NewsViewHolder) holder).textViewNewsTime.setText(Utils.convertDate(newsList.get(position).getPublishedAt()));
                ((NewsViewHolder) holder).textViewNewsSource.setText(newsList.get(position).getSource().getName());
                break;
            case SEARCH_HEADER:
                ((HeaderViewHolder) holder).textViewHeader.setText(R.string.search_results);
                break;

        }
    }


    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return SEARCH_HEADER;
            default:
                return SEARCH_LIST;
        }
    }


    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }


    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_header_news)
        TextView textViewHeader;

        HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_news_title)
        TextView textViewNewsTitle;
        @BindView(R.id.textView_news_description)
        TextView textViewNewsDescription;
        @BindView(R.id.textView_news_time)
        TextView textViewNewsTime;
        @BindView(R.id.textView_news_source)
        TextView textViewNewsSource;

        NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
