package com.mario.newsapiexample.components.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mario.newsapiexample.R;
import com.mario.newsapiexample.components.ui.main.adapter.viewholders.HeadlineVH;
import com.mario.newsapiexample.data.model.news.Article;
import com.mario.newsapiexample.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mario on 14/06/18.
 */

public class HeadlineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Article> headlinesList;

    public HeadlineAdapter(Context context) {
        this.context = context;
    }

    public void setHeadlinesList(List<Article> headlinesList) {
        this.headlinesList = headlinesList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HeadlineVH((LayoutInflater.from(parent.getContext()).inflate(R.layout.item_headline, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Picasso.with(context)
                .load(headlinesList.get(position).getUrlToImage())
                .into(((HeadlineVH) holder).imageViewHeadlinePhoto);

        ((HeadlineVH) holder).textViewHeadlineTitle.setText(headlinesList.get(position).getTitle());
        ((HeadlineVH) holder).textViewHeadlineSource.setText(headlinesList.get(position).getSource().getName());
        ((HeadlineVH) holder).textViewHeadlineTime.setText(Utils.convertDate(headlinesList.get(position).getPublishedAt()));
    }

    @Override
    public int getItemCount() {
        return headlinesList == null ? 0 : headlinesList.size();
    }
}
