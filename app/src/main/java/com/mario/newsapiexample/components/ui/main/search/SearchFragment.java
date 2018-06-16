package com.mario.newsapiexample.components.ui.main.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mario.newsapiexample.R;
import com.mario.newsapiexample.components.adapter.AdapterItemDivider;
import com.mario.newsapiexample.components.base.BaseDialogFragment;
import com.mario.newsapiexample.components.ui.main.adapter.SearchAdapter;
import com.mario.newsapiexample.components.ui.main.news.NewsFragment;
import com.mario.newsapiexample.data.model.news.Article;
import com.mario.newsapiexample.listener.PaginationScrollListener;
import com.mario.newsapiexample.util.Utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by mario on 14/06/18.
 */

public class SearchFragment extends BaseDialogFragment<SearchContract.Presenter> implements SearchContract.View {

    @Inject
    SearchContract.Presenter presenter;

    @BindView(R.id.editText_search)
    EditText editTextSearch;
    @BindView(R.id.recyclerView_search_results)
    RecyclerView recyclerViewSearchResults;
    @BindView(R.id.imageView_search)
    ImageView imageViewSearch;
    @BindView(R.id.textView_no_result)
    TextView textViewNoResult;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    private SearchAdapter searchAdapter;

    private LinearLayoutManager layoutManager;
    private Disposable disposable;

    private static final int PAGE_START = 1;
    // Indicates if footer ProgressBar is shown (i.e. next page is loading)
    private boolean isLoading = false;
    // If current page is the last page (Pagination will stop after this page load)
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 3;
    private int currentPage = PAGE_START;

    @Inject
    public SearchFragment() {
    }

    @Override
    protected SearchContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter != null) {
            presenter.setView(this);
        }

        setUpSearchBar();

        searchAdapter = new SearchAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        final AdapterItemDivider adapterItemDivider = new AdapterItemDivider(getContext(), R.drawable.recyclerview_divider_medium);

        recyclerViewSearchResults.setLayoutManager(layoutManager);
        recyclerViewSearchResults.setHasFixedSize(true);
        recyclerViewSearchResults.addItemDecoration(adapterItemDivider);
        recyclerViewSearchResults.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSearchResults.setAdapter(searchAdapter);

        RxTextView.textChanges(editTextSearch)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        if (charSequence.length() != 0) {
                            presenter.searchNews(charSequence.toString(), PAGE_START);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(getClass().getSimpleName(), "onError: ", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i(getClass().getSimpleName(), "onComplete: ");
                    }
                });


        recyclerViewSearchResults.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                presenter.searchNews(editTextSearch.getText().toString(), currentPage);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });


    }

    @Override
    public void showFirstPageResults(List<Article> newsList) {
        searchAdapter.addAll(newsList);

        progressBar.setVisibility(View.GONE);

        if (currentPage <= TOTAL_PAGES) {
            searchAdapter.addLoadingFooter();
        } else {
            isLastPage = true;
        }

        shouldDisplayNoResultText(false);
    }

    @Override
    public void showNextPageResults(List<Article> newsList) {
        searchAdapter.removeLoadingFooter();
        isLoading = false;
        searchAdapter.addAll(newsList);

        progressBar.setVisibility(View.GONE);
        if (currentPage != TOTAL_PAGES) {
            searchAdapter.addLoadingFooter();
        } else {
            isLastPage = true;
        }

        shouldDisplayNoResultText(false);
    }

    @Override
    public void showNoResults() {
        shouldDisplayNoResultText(true);
    }

    @OnClick(R.id.imageView_search)
    public void onClick() {
        Utils.hideKeyboard(getContext(), editTextSearch);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(getId(), new NewsFragment())
                .addToBackStack(null).commit();
    }

    private void setUpSearchBar() {
        imageViewSearch.setImageResource(R.drawable.ic_close);
        Utils.showKeyboard(getContext(), editTextSearch);
    }

    private void shouldDisplayNoResultText(boolean isVisible) {
        if (isVisible) {
            recyclerViewSearchResults.setVisibility(View.GONE);
            textViewNoResult.setVisibility(View.VISIBLE);
        } else {
            recyclerViewSearchResults.setVisibility(View.VISIBLE);
            textViewNoResult.setVisibility(View.GONE);
        }
    }
}
