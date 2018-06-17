package com.mario.newsapiexample.data.source.news;

public class NewsRepository {
    private final NewsRemoteDataSource newsRemoteDataSource;

    public NewsRepository() {
        this.newsRemoteDataSource = new NewsRemoteDataSource();
    }

    public NewsRemoteDataSource getNewsRemoteDataSource() {
        return newsRemoteDataSource;
    }
}
