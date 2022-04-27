package com.example.polls.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class Movie {
    private String id;
    private String title;
    private String realeseYear;
    private String ratings;

    public Movie(String id, String title, String realeseYear, String ratings) {
        this.id = id;
        this.title = title;
        this.realeseYear = realeseYear;
        this.ratings = ratings;
    }
}
