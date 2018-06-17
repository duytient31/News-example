package com.mario.newsapiexample.components.ui.main.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.mario.newsapiexample.R;
import com.mario.newsapiexample.components.adapter.AdapterItemDivider;
import com.mario.newsapiexample.components.base.BaseDialogFragment;
import com.mario.newsapiexample.components.ui.main.adapter.NewsAdapter;
import com.mario.newsapiexample.components.ui.main.search.SearchFragment;
import com.mario.newsapiexample.data.model.news.Article;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

/**
 * Created by mario on 14/06/18.
 */

public class NewsFragment extends BaseDialogFragment<NewsContract.Presenter> implements NewsContract.View {

    @Inject
    NewsContract.Presenter presenter;

    @BindView(R.id.recyclerView_news)
    RecyclerView recyclerViewNews;
    @BindView(R.id.editText_search)
    EditText editTextNews;

    private Disposable disposable;
    private NewsAdapter newsAdapter;
    private LinearLayoutManager layoutManager;
    private AdapterItemDivider adapterItemDivider;

    @Inject
    public NewsFragment() {
    }

    @Override
    protected NewsContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter != null) {
            presenter.setView(this);
            presenter.fetchLatestNews();
            presenter.fetchTopHeadlines();
        }

        newsAdapter = new NewsAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapterItemDivider = new AdapterItemDivider(getContext(), R.drawable.recyclerview_divider_medium);

        setUpRecyclerView();

        editTextNews.setFocusable(false);
        editTextNews.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(getId(), new SearchFragment())
                    .addToBackStack(null).commit();
        });
    }


    @Override
    public void showTopHeadlines(List<Article> headlinesList) {
        newsAdapter.setHeadlineItems(headlinesList);
    }

    @Override
    public void showLatestNews(List<Article> newsList) {
        newsAdapter.setNewsItems(newsList);
    }

    private void setUpRecyclerView() {
        recyclerViewNews.setLayoutManager(layoutManager);
        recyclerViewNews.setHasFixedSize(true);
        recyclerViewNews.addItemDecoration(adapterItemDivider);
        recyclerViewNews.setAdapter(newsAdapter);
    }

}
