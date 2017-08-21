package com.example.shata.newsapp;

/**
 * Created by Shata on 18/07/2017.
 */

public class NewsItem {

    private String title;
    private String section;
    private String url;
    private String author;
    private String date;


    public NewsItem(String title, String section, String url, String author, String date) {
        this.title = title;
        this.section = section;
        this.url = url;
        this.author = author;
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
