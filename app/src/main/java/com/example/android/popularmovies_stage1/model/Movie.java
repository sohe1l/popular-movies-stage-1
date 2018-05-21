package com.example.android.popularmovies_stage1.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie {

    private int vote_count;
    private double vote_average;

    private double popularity;

    private int id;
    private Boolean video;

    private String title;

    private String poster_path;
    private String original_language;
    private String original_title;

    private String[] genre_ids;

    private String backdrop_path;
    private Boolean adult;
    private String overview;
    private Date release_date;


    /*
    "vote_count": 3745,
    "id": 299536,
    "video": false,
    "vote_average": 8.5,
    "title": "Avengers: Infinity War",
    "popularity": 577.421593,
    "poster_path": "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
    "original_language": "en",
    "original_title": "Avengers: Infinity War",
    "genre_ids": [
    12,
    878,
    14,
    28
    ],
    "backdrop_path": "/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg",
    "adult": false,
    "overview": "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
    "release_date": "2018-04-25"
*/






    //<editor-fold desc="Getters and setters">
    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(String[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public void setRelease_date(String release_date) {

        try {
            this.release_date = new SimpleDateFormat("yyyy-MM-dd").parse(release_date);
        } catch (Exception e) {
            this.release_date = null;
        }
    }
    //</editor-fold>

}