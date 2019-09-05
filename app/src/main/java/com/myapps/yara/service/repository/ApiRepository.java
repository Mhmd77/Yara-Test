package com.myapps.yara.service.repository;


import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.myapps.yara.service.model.DetailMovie;
import com.myapps.yara.service.model.Movie;
import com.myapps.yara.service.model.SearchResult;
import com.myapps.yara.utils.AppDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRepository {
    private static final String API_KEY = "7e213962";
    private static final String SEARCHED_MOVIE = "batman";
    private static ApiRepository apiRepository;
    protected static ApiService apiService;
    private AppDatabase appDatabase;

    private ApiRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public synchronized static ApiRepository getInstance() {
        if (apiRepository == null) {
            if (apiRepository == null) {
                apiRepository = new ApiRepository();
            }
        }
        return apiRepository;
    }

    public void getAllMoviesFromApi(final MutableLiveData<List<Movie>> data) {
        apiService.getAllMovies(API_KEY, SEARCHED_MOVIE).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                Log.i("Yara", "Code " + response.code());
                if (response.code() == 200 && response.body().isResponse()) {//If response is true
                    List<Movie> moviesFromApi = response.body().getMovies();
                    data.setValue(moviesFromApi);
                    DetailMoviesRepository.getInstance().insertMoviesToDatabase(moviesFromApi);
                } else {
                    onFailure(call, new Exception(response.message()));
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.e("Yara", "Failed To Get All Movies " + t.getMessage());
                //Get From Database
                List<Movie> moviesFromDatabase = DetailMoviesRepository.getInstance().getAllMovies();
                if (moviesFromDatabase == null || moviesFromDatabase.size() == 0) {
                    Log.e("Yara", "Didn't Connected Yet!");
                    data.setValue(null);
                } else {
                    data.setValue(moviesFromDatabase);
                }
            }
        });
    }


    public void getDetailFromApi(final String imdbID, final MutableLiveData<DetailMovie> data) {
        apiService.getDetail(API_KEY, imdbID).enqueue(new Callback<DetailMovie>() {
            @Override
            public void onResponse(Call<DetailMovie> call, Response<DetailMovie> response) {
                Log.i("Yara", "Code " + response.code());
                if (response.code() == 200 && response.body().isResponse()) {//If response is true
                    data.setValue(response.body());
                    DetailMoviesRepository.getInstance().insertDetailMovie(response.body());
                } else {
                    onFailure(call, new Exception(response.code() + "  " + response.message()));
                }

            }

            @Override
            public void onFailure(Call<DetailMovie> call, Throwable t) {
                Log.e("Yara", "Failed To Get Detail Of Movie " + t.getMessage());
                DetailMovie movie = DetailMoviesRepository.getInstance().getDetails(imdbID);
                if (movie == null) {
                    Log.e("Yara", "There is no record with id " + imdbID + " in table");
                    data.setValue(null);
                } else {
                    data.setValue(movie);
                }
            }
        });
    }
}
