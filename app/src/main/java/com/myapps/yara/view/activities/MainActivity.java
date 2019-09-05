package com.myapps.yara.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;

import com.myapps.yara.R;
import com.myapps.yara.service.model.Movie;
import com.myapps.yara.view.adapters.MovieRecyclerViewAdapter;
import com.myapps.yara.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MovieRecyclerViewAdapter.OnItemClickListener {
    private MovieViewModel viewModel;
    @BindView(R.id.moviesRecyclerView)
    RecyclerView moviesRecyclerView;
    private MovieRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpViewModel();
        setUpRecyclerView();
        viewModel.getMovies();
    }

    private void setUpRecyclerView() {
        adapter = new MovieRecyclerViewAdapter(this, new ArrayList<Movie>(), this);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));
        moviesRecyclerView.setAdapter(adapter);
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(moviesRecyclerView);
    }

    private void setUpViewModel() {
        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getMovieObservableData().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (movies == null || movies.size() == 0) {
                    Log.e("Yara", "Search Result Is Null");
                    showRetryMessage();
                } else {
                    Log.i("Yara", "ID: " + movies.get(0).getImdbID());
                    adapter.setList(movies);
                }
            }
        });
    }

    private void showRetryMessage() {
        Snackbar.make(moviesRecyclerView, "Failed To Connect.Please Connect At Least Once!", Snackbar.LENGTH_INDEFINITE)
                .setAction("Try Again", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewModel.getMovies();
                    }
                }).show();
    }

    @Override
    public void onItemClick(int position, String imdbId) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(getString(R.string.IMDB_ID_KEY), imdbId);
        startActivity(intent);

    }
}
