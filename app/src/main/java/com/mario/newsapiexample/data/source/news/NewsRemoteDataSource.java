package com.mario.newsapiexample.data.source.news;

import com.mario.newsapiexample.data.model.news.News;
import com.mario.newsapiexample.network.ApiService;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class NewsRemoteDataSource {

    private final NewsApi newsApi = ApiService.createService(NewsApi.class);

    public Observable<News> getTopHeadLines() {
        return newsApi.getTopHeadlines();
    }

    public Observable<News> getLatestNews() {
        return newsApi.getLatestNews();
    }

    public Observable<News> searchNews(String keyword, int page) {
        return newsApi.searchNews(keyword, page, 10);
    }

    interface NewsApi {
        @GET("v2/top-headlines?sources=bbc-news&pageSize=10&page=1")
        Observable<News> getTopHeadlines();

        @GET("v2/top-headlines?country=us")
        Observable<News> getLatestNews();

        @GET("v2/everything")
        Observable<News> searchNews(@Query("q") String keyword,
                                    @Query("page") int page,
                                    @Query("pageSize") int pageSize);
    }
}
