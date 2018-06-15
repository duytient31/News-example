package com.mario.newsapiexample.data.model.news;

import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("author")
    private String author;
    @SerializedName("urlToImage")
    private String urlToImage;
    @SerializedName("title")
    private String title;
    @SerializedName("source")
    private Source source;
    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String url;

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "HeadlineArticle{" +
                "publishedAt='" + publishedAt + '\'' +
                ", author='" + author + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", title='" + title + '\'' +
                ", source=" + source +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
