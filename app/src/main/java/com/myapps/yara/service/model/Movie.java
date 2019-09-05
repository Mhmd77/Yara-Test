package com.myapps.yara.service.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;

public class Movie {

    public Movie(Movie movie) {
        this.Title = movie.Title;
        this.imdbID = movie.imdbID;
        this.Poster = movie.Poster;
        this.Type = movie.Type;
        this.Year = movie.Year;
    }

    public Movie() {
    }

    @Expose
    private String Title;

    @Expose
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "imdb_id")
    private String imdbID;

    @Expose
    private String Year;

    @Expose
    private String Type;

    @Expose
    private String Poster;

    public String getImdbID() {
        return imdbID;
    }

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getType() {
        return Type;
    }

    public String getPoster() {
        return Poster;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setYear(String year) {
        Year = year;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }
}