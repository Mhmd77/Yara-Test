package com.myapps.yara.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapps.yara.R;
import com.myapps.yara.service.model.DetailMovie;
import com.myapps.yara.view.adapters.DetailMoviesAdapter;
import com.myapps.yara.viewmodel.DetailViewModel;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.imgPoster)
    ImageView imgPoster;

    @BindView(R.id.txtRating)
    TextView txtRating;
    @BindView(R.id.detailsRecyclerView)
    RecyclerView detailsRecyclerView;

    private DetailMovie detailMovie;
    private DetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setUpViewModel();
        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getDetailMovieMutableLiveData().observe(this, new Observer<DetailMovie>() {
            @Override
            public void onChanged(@Nullable DetailMovie movie) {
                if (movie == null || movie.getRatings() == null) {
                    Log.e("Yara", "Here");
                    showRetryMessage();
                } else {
                    detailMovie = movie;
                    setUpPage();
                }
            }
        });
    }

    private void setUpPage() {
        Picasso.get().load(detailMovie.getPoster()).into(imgPoster);
        txtRating.setText(String.valueOf(detailMovie.getImdbRating()));
        //SetUpRecyclerView
        DetailMoviesAdapter adapter = new DetailMoviesAdapter(this, detailMovie.getPairs(), detailMovie);
        detailsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailsRecyclerView.setAdapter(adapter);
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        detailsRecyclerView.addItemDecoration(decoration);


    }

    private void setUpViewModel() {
        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        String imdbId = getIntent().getStringExtra(getString(R.string.IMDB_ID_KEY));
        viewModel.getDetailOfMovie(imdbId);

    }

    private void showRetryMessage() {
        Snackbar.make(detailsRecyclerView, "Failed To Connect.Please Connect At Least Once!", Snackbar.LENGTH_INDEFINITE)
                .setAction("Try Again", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String imdbId = getIntent().getStringExtra(getString(R.string.IMDB_ID_KEY));
                        viewModel.getDetailOfMovie(imdbId);
                    }
                }).show();
    }
}
