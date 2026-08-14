package com.rk.advanced_mapping.entity;

import com.rk.advanced_mapping.entity.id.BookId;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
@IdClass(BookId.class)
public class Book {

    @Id
    @Column(name = "author")
    private String author;

    @Id
    @Column(name = "title")
    private String title;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(name = "rating")
    private Double rating;

    public Book() {
    }

    public Book(String author, String title, Integer publicationYear, Double rating) {
        this.author = author;
        this.title = title;
        this.publicationYear = publicationYear;
        this.rating = rating;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", rating=" + rating +
                '}';
    }
}
