package com.myapps.yara.service.repository;

import com.myapps.yara.service.model.DetailMovie;
import com.myapps.yara.service.model.SearchResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String BASE_URL = "http://www.omdbapi.com/";

    @GET("/")
    Call<SearchResult> getAllMovies(@Query("apikey") String apiKey, @Query("s") String search);

    @GET("/")
    Call<DetailMovie> getDetail(@Query("apikey") String apiKey, @Query("i") String imdbId);

}
