package com.myapps.yara.service.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.util.Pair;

import com.google.gson.annotations.Expose;
import com.myapps.yara.utils.DataConverter;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "detail_movie")
public class DetailMovie extends Movie {
    public DetailMovie(Movie movie) {
        super(movie);
    }

    public DetailMovie() {
    }

    @Expose
    private String Rated;
    @Expose
    private String Released;
    @Expose
    private String Runtime;
    @Expose
    private String Genre;
    @Expose
    private String Director;
    @Expose
    private String Writer;
    @Expose
    private String Actors;
    @Expose
    private String Plot;
    @Expose
    private String Language;
    @Expose
    private String Country;
    @Expose
    private String Awards;
    @Expose
    @TypeConverters({DataConverter.class})
    private List<Rating> Ratings;
    @Expose
    private String Metascore;
    @Expose
    private String imdbRating;
    @Expose
    private String DVD;
    @Expose
    private String BoxOffice;
    @Expose
    private String Production;
    @Expose
    private String Website;
    @Expose
    private boolean Response;

    public String getGenre() {
        return Genre;
    }

    public class Rating {
        @Expose
        private String Source;
        @Expose
        private String Value;

        public String getSource() {
            return Source;
        }

        public String getValue() {
            return Value;
        }
    }


    public String getRated() {
        return Rated;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public List<Rating> getRatings() {
        return Ratings;
    }

    public void setRatings(List<Rating> ratings) {
        Ratings = ratings;
    }

    public String getMetascore() {
        return Metascore;
    }

    public void setMetascore(String metascore) {
        Metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getDVD() {
        return DVD;
    }

    public void setDVD(String DVD) {
        this.DVD = DVD;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        BoxOffice = boxOffice;
    }

    public String getProduction() {
        return Production;
    }

    public void setProduction(String production) {
        Production = production;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public boolean isResponse() {
        return Response;
    }

    public void setResponse(boolean response) {
        Response = response;
    }


    public List<Pair<String, String>> getPairs() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("Writer", Writer));
        list.add(new Pair<>("Actors", Actors));
        list.add(new Pair<>("Language", Language));
        list.add(new Pair<>("Country", Country));
        list.add(new Pair<>("Awards", Awards));
        //For Ratings

//        StringBuilder builder = new StringBuilder();
//        for (Rating r :
//                Ratings) {
//            builder.append(r.getSource() + ": " + r.getValue());
//            builder.append('\n');
//        }
//        list.add(new Pair<>("Ratings", builder.toString()));

        list.add(new Pair<>("BoxOffice", BoxOffice));
        list.add(new Pair<>("Website", Website));

        return list;
    }
}
