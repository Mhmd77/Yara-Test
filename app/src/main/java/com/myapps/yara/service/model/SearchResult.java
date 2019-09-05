package com.myapps.yara.service.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {
    @SerializedName("Search")
    private List<Movie> movies;
    @SerializedName("Response")
    private boolean response;

    public boolean isResponse() {
        return response;
    }

    public List<Movie> getMovies() {
        return movies;
    }

}
