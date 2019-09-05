package com.myapps.yara.service.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.myapps.yara.service.model.DetailMovie;
import com.myapps.yara.service.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("Select * from detail_movie")
    List<DetailMovie> getAllMoviesFromDatabase();

    @Query("Select * from detail_movie where imdb_id = :imdbId")
    DetailMovie getDetailFromDatabase(String imdbId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllMovies(List<DetailMovie> movies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(DetailMovie movie);

    @Update
    void updateMovie(DetailMovie movie);
}
