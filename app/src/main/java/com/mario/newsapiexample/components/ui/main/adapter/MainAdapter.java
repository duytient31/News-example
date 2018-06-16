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
import com.mario.newsapiexample.components.ui.main.adapter.viewholders.HeaderVH;
import com.mario.newsapiexample.components.ui.main.adapter.viewholders.NewsVH;
import com.mario.newsapiexample.data.model.news.Article;
import com.mario.newsapiexample.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mario on 14/06/18.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private static final int TOP_HEADLINES_HEADER = 0;
    private static final int LATEST_NEWS_LIST = 1;
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
                return LATEST_NEWS_LIST;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        switch (viewType) {
            case TOP_HEADLINES_HEADER:
            case LATEST_NEWS_HEADER:
                holder = new HeaderVH(LayoutInflater.from(context).inflate(R.layout.layout_recyclerview_header, parent, false));
                break;
            case TOP_HEADLINES_LIST:
                holder = new HeadlinesVH(LayoutInflater.from(context).inflate(R.layout.item_recyclerview_headline, parent, false));
                break;
            case LATEST_NEWS_LIST:
                holder = new NewsVH(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false));
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
            case LATEST_NEWS_LIST:
                ((NewsVH) holder).textViewNewsTitle.setText(newsList.get(position).getTitle());
                ((NewsVH) holder).textViewNewsDescription.setText(newsList.get(position).getDescription());
                ((NewsVH) holder).textViewNewsTime.setText(Utils.convertDate(newsList.get(position).getPublishedAt()));
                ((NewsVH) holder).textViewNewsSource.setText(newsList.get(position).getSource().getName());
                break;
            case TOP_HEADLINES_LIST:
                HeadlineAdapter headlineAdapter = new HeadlineAdapter();
                headlineAdapter.setItems(headlinesList);
                ((HeadlinesVH) holder).recyclerViewHeadlines.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                ((HeadlinesVH) holder).recyclerViewHeadlines.setAdapter(headlineAdapter);
                break;
            case TOP_HEADLINES_HEADER:
                ((HeaderVH) holder).textViewHeader.setText(R.string.top_headlines);
                break;
            case LATEST_NEWS_HEADER:
                ((HeaderVH) holder).textViewHeader.setText(R.string.latest_news);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }


    /** View holders **/

    protected class HeadlinesVH extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerView_item_headline)
        RecyclerView recyclerViewHeadlines;

        HeadlinesVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
