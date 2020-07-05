package com.genpact.assignment.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private long id;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Library library;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    @Override
    public String toString() {
        return this.id + ":" + this.title + ":" + this.library.getId();
    }
}
