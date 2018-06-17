package com.mario.newsapiexample.components.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mario.newsapiexample.R;
import com.mario.newsapiexample.components.ui.main.adapter.viewholders.HeaderVH;
import com.mario.newsapiexample.components.ui.main.adapter.viewholders.LoadingVH;
import com.mario.newsapiexample.components.ui.main.adapter.viewholders.NewsVH;
import com.mario.newsapiexample.data.model.news.Article;
import com.mario.newsapiexample.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario on 15/06/18.
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Article> resultsList;
    private boolean isLoadingAdded = false;

    private static final int SEARCH_HEADER = 0;
    private static final int SEARCH_LIST = 1;
    private static final int LOADING = 2;

    public SearchAdapter(Context context) {
        this.context = context;
        resultsList = new ArrayList<>();
    }

    public void add(Article article) {
        resultsList.add(article);
        notifyItemInserted(resultsList.size() - 1);
    }

    public void addAll(List<Article> newsList) {
        for (Article article : newsList) {
            add(article);
        }
    }

    public void remove(Article article) {
        int position = resultsList.indexOf(article);
        if (position > -1) {
            resultsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Article());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = resultsList.size() - 1;
        Article item = getItem(position);

        if (item != null) {
            resultsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Article getItem(int position) {
        return resultsList.get(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        switch (viewType) {
            case SEARCH_HEADER:
                holder = new HeaderVH(LayoutInflater.from(context).inflate(R.layout.item_header, parent, false));
                break;
            case SEARCH_LIST:
                holder = new NewsVH(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false));
                break;
            case LOADING:
                holder = new LoadingVH(LayoutInflater.from(context).inflate(R.layout.item_progress, parent, false));
                break;
            default:
                holder = new NewsVH(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case SEARCH_LIST:
                ((NewsVH) holder).textViewNewsTitle.setText(resultsList.get(position).getTitle());
                ((NewsVH) holder).textViewNewsDescription.setText(resultsList.get(position).getDescription());
                ((NewsVH) holder).textViewNewsTime.setText(context.getString(R.string.published_ago_by, Utils.calculateTimePassed(resultsList.get(position).getPublishedAt())));
                ((NewsVH) holder).textViewNewsSource.setText(resultsList.get(position).getSource().getName());
                break;
            case SEARCH_HEADER:
                ((HeaderVH) holder).textViewHeader.setText(R.string.search_results);
                break;
            case LOADING:
                // do nothing
                break;

        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return SEARCH_HEADER;
            default:
                return (position == resultsList.size() - 1 && isLoadingAdded) ? LOADING : SEARCH_LIST;
        }
    }

    @Override
    public int getItemCount() {
        return resultsList == null ? 0 : resultsList.size();
    }
}
