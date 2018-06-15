package com.mario.newsapiexample.components.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
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

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private static final int TOP_HEADLINES_HEADER = 0;
    private static final int NEWS_LIST = 1;
    private static final int TOP_HEADLINES_LIST = 2;
    private static final int LATEST_NEWS_HEADER = 3;

    private List<Article> headlinesList;
    private List<Article> newsList;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public void setHeadlineItems(List<Article> headlineArticles) {
        this.headlinesList = headlineArticles;
        notifyDataSetChanged();
    }

    public void setNewsItems(List<Article> newsArticles) {
        this.newsList = newsArticles;
        notifyDataSetChanged();
    }

    public void clearItems() {
        this.newsList.clear();
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TOP_HEADLINES_HEADER;
            case 1:
                return TOP_HEADLINES_LIST;
            case 2:
                return LATEST_NEWS_HEADER;
            default:
                return NEWS_LIST;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        RecyclerView.ViewHolder holder;
        switch (viewType) {
            case TOP_HEADLINES_HEADER:
            case LATEST_NEWS_HEADER:
                view = inflater.inflate(R.layout.layout_recyclerview_header, parent, false);
                holder = new HeaderViewHolder(view);
                break;
            case TOP_HEADLINES_LIST:
                view = inflater.inflate(R.layout.item_recyclerview_headline, parent, false);
                holder = new HeadlinesViewHolder(view);
                break;
            case NEWS_LIST:
                view = inflater.inflate(R.layout.item_news, parent, false);
                holder = new NewsViewHolder(view);
                break;
            default:
                view = inflater.inflate(R.layout.item_news, parent, false);
                holder = new NewsViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case NEWS_LIST:
                ((NewsViewHolder) holder).textViewNewsTitle.setText(newsList.get(position).getTitle());
                ((NewsViewHolder) holder).textViewNewsDescription.setText(newsList.get(position).getDescription());
                ((NewsViewHolder) holder).textViewNewsTime.setText(Utils.convertDate(newsList.get(position).getPublishedAt()));
                ((NewsViewHolder) holder).textViewNewsSource.setText(newsList.get(position).getSource().getName());
                break;
            case TOP_HEADLINES_LIST:
                HeadlineAdapter headlineAdapter = new HeadlineAdapter();
                headlineAdapter.setItems(headlinesList);
                ((HeadlinesViewHolder) holder).recyclerViewHeadlines.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                ((HeadlinesViewHolder) holder).recyclerViewHeadlines.setAdapter(headlineAdapter);
                break;
            case TOP_HEADLINES_HEADER:
                ((HeaderViewHolder) holder).textViewHeader.setText(R.string.top_headlines);
                break;
            case LATEST_NEWS_HEADER:
                ((HeaderViewHolder) holder).textViewHeader.setText(R.string.latest_news);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }


    public class HeadlinesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerView_item_headline)
        RecyclerView recyclerViewHeadlines;

        HeadlinesViewHolder(View itemView) {
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

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_header_news)
        TextView textViewHeader;

        HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
