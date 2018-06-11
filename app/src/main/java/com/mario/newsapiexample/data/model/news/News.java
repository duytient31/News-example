package com.mario.newsapiexample.data.model.news;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {
    @SerializedName("status")
    private String status;
    @SerializedName("totalResults")
    private String totalResults;
    @SerializedName("articles")
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "News{" +
                "status='" + status + '\'' +
                ", totalResults='" + totalResults + '\'' +
                ", articles=" + articles +
                '}';
    }
}
