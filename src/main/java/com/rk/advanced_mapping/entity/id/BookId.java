package com.rk.advanced_mapping.entity.id;

import java.io.Serializable;

public class BookId implements Serializable {

    private String author;

    private String title;

    public BookId() {
    }

    public BookId(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
