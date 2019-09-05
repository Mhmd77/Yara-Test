package com.myapps.yara.service.repository;

import com.myapps.yara.service.model.DetailMovie;
import com.myapps.yara.service.model.Movie;
import com.myapps.yara.utils.AppDatabase;
import com.myapps.yara.utils.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class DetailMoviesRepository {
    private static final DetailMoviesRepository instance = new DetailMoviesRepository();

    public static DetailMoviesRepository getInstance() {
        return instance;
    }

    private DetailMoviesRepository() {
    }

    public void insertMoviesToDatabase(List<Movie> moviesFromApi) {
        List<DetailMovie> detailMovies = new ArrayList<>();
        for (Movie m :
                moviesFromApi) {
            detailMovies.add(new DetailMovie(m));
        }
        AppDatabase.getAppDatabase(MyApplication.getAppContext()).movieDao().insertAllMovies(detailMovies);
    }

    public List<Movie> getAllMovies() {
        List<DetailMovie> detailMovies = AppDatabase.getAppDatabase(MyApplication.getAppContext()).movieDao().getAllMoviesFromDatabase();
        List<Movie> movies = new ArrayList<>();
        for (DetailMovie m :
                detailMovies) {
            movies.add(m);
        }
        return movies;
    }

    public DetailMovie getDetails(String imdbId) {
        if (imdbId == null) {
            throw new NullPointerException("Imdb Id Is Null");
        } else {
            DetailMovie movie = AppDatabase.getAppDatabase(MyApplication.getAppContext()).movieDao().getDetailFromDatabase(imdbId);
            if (movie == null) {
                throw new NullPointerException("There Is No Record With This Id");
            } else {
                return movie;
            }
        }
    }

    public void insertDetailMovie(DetailMovie movie) {
        AppDatabase.getAppDatabase(MyApplication.getAppContext()).movieDao().insertMovie(movie);
    }
}
