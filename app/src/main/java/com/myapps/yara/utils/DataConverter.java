package com.myapps.yara.utils;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myapps.yara.service.model.DetailMovie;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {

    @TypeConverter
    public String fromRatingList(List<DetailMovie.Rating> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<DetailMovie.Rating>>() {
        }.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<DetailMovie.Rating> toRatingList(String ratingString) {
        if (ratingString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<DetailMovie.Rating>>() {
        }.getType();
        List<DetailMovie.Rating> countryLangList = gson.fromJson(ratingString, type);
        return countryLangList;
    }
}
