package com.geektach.newsapp42.models;

import com.geektach.newsapp42.NewsFragment;

import java.io.Serializable;

public class Article implements Serializable {

    private String text;
    private long date;

    public Article(String text, long date) {
        this.text = text;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
