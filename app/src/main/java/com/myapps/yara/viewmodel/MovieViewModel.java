package com.myapps.yara.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.myapps.yara.service.model.Movie;
import com.myapps.yara.service.repository.ApiRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private final MutableLiveData<List<Movie>> movieObservableData;

    public MovieViewModel() {
        this.movieObservableData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Movie>> getMovieObservableData() {
        return movieObservableData;
    }

    public void getMovies() {
        ApiRepository.getInstance().getAllMoviesFromApi(movieObservableData);
    }
}
