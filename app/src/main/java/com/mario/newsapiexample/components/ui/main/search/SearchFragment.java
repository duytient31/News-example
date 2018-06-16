package com.mario.newsapiexample.components.ui.main.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mario.newsapiexample.R;
import com.mario.newsapiexample.components.base.BaseDialogFragment;
import com.mario.newsapiexample.components.ui.main.adapter.SearchAdapter;
import com.mario.newsapiexample.components.ui.main.news.NewsFragment;
import com.mario.newsapiexample.data.model.news.Article;
import com.mario.newsapiexample.util.Utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class SearchFragment extends BaseDialogFragment<SearchContract.Presenter> implements SearchContract.View {

    @Inject
    SearchContract.Presenter presenter;

    @BindView(R.id.editText_search)
    EditText searchViewNews;
    @BindView(R.id.recyclerView_search_results)
    RecyclerView recyclerVieSearchResults;
    @BindView(R.id.imageView_search)
    ImageView imageViewSearch;
    @BindView(R.id.textView_no_result)
    TextView textViewNoResult;

    private SearchAdapter mainAdapter;

    private LinearLayoutManager layoutManager;
    private Disposable disposable;

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

        mainAdapter = new SearchAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerVieSearchResults.setLayoutManager(layoutManager);
        recyclerVieSearchResults.setHasFixedSize(true);
        recyclerVieSearchResults.setAdapter(mainAdapter);

        RxTextView.textChanges(searchViewNews)
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
                            presenter.searchNews(charSequence.toString(), 1);
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

    }

    @Override
    public void showSearchResults(List<Article> newsList) {
        mainAdapter.setNewsItems(newsList);

        recyclerVieSearchResults.setVisibility(View.VISIBLE);
        textViewNoResult.setVisibility(View.GONE);
    }

    @Override
    public void showNoResults() {
        recyclerVieSearchResults.setVisibility(View.GONE);
        textViewNoResult.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.imageView_search)
    public void onClick() {
        Utils.hideKeyboard(getContext(), searchViewNews);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(getId(), new NewsFragment())
                .addToBackStack(null).commit();
    }

    private void setUpSearchBar() {
        imageViewSearch.setImageResource(R.drawable.ic_close);
        Utils.showKeyboard(getContext(), searchViewNews);
    }
}
