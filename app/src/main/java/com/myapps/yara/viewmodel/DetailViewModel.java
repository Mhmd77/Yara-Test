package com.myapps.yara.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.myapps.yara.service.model.DetailMovie;
import com.myapps.yara.service.repository.ApiRepository;

public class DetailViewModel extends ViewModel {
    final MutableLiveData<DetailMovie> detailMovieMutableLiveData;


    public DetailViewModel() {
        this.detailMovieMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<DetailMovie> getDetailMovieMutableLiveData() {
        return detailMovieMutableLiveData;
    }

    public void getDetailOfMovie(String imdbId) {
        ApiRepository.getInstance().getDetailFromApi(imdbId, detailMovieMutableLiveData);
    }
}
