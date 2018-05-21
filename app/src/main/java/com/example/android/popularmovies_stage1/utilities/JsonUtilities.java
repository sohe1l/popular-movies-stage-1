package com.example.android.popularmovies_stage1.utilities;


import com.example.android.popularmovies_stage1.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonUtilities {



    public static ArrayList<Movie> parseMovies(String json){

        ArrayList<Movie> movies = new ArrayList<>();

        try{
            JSONObject jo = new JSONObject(json);

            JSONArray jsonArray = jo.getJSONArray("results");

            for(int i=0; i<jsonArray.length(); i++){
                Movie movie = buildMovie(jsonArray.getJSONObject(i));
                movies.add(movie);
            }

        }catch(Exception e){
            e.printStackTrace();
        }


        return movies;

    }




    public static Movie buildMovie(JSONObject jo){

        try{
            //JSONObject jo = new JSONObject(json);


            Movie movie = new Movie();

            movie.setVote_count( jo.getInt("vote_count"));

            movie.setId(jo.getInt("id"));

            movie.setVideo(jo.getBoolean("video"));
            movie.setVote_average(jo.getDouble("vote_average"));
            movie.setTitle(jo.getString("title"));
            movie.setPopularity(jo.getDouble("popularity"));
            movie.setPoster_path(jo.getString("poster_path"));
            movie.setOriginal_language(jo.getString("original_language"));

            movie.setOriginal_title(jo.getString("original_title"));

            // movie.setGenre_ids();

            movie.setBackdrop_path(jo.getString("backdrop_path"));
            movie.setAdult(jo.getBoolean("adult"));

            movie.setOverview(jo.getString("overview"));
            movie.setRelease_date(jo.getString("release_date"));

            return movie;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }





        public static Movie parseMovie(String json){

        try{
            JSONObject jo = new JSONObject(json);


            Movie movie = new Movie();

            movie.setVote_count( jo.getInt("vote_count"));

            movie.setId(jo.getInt("id"));

            movie.setVideo(jo.getBoolean("video"));
            movie.setVote_average(jo.getDouble("vote_average"));
            movie.setTitle(jo.getString("title"));
            movie.setPopularity(jo.getDouble("popularity"));
            movie.setPoster_path(jo.getString("poster_path"));
            movie.setOriginal_language(jo.getString("original_language"));

            movie.setOriginal_title(jo.getString("original_title"));

            // movie.setGenre_ids();

            movie.setBackdrop_path(jo.getString("backdrop_path"));
            movie.setAdult(jo.getBoolean("adult"));

            movie.setOverview(jo.getString("overview"));
            movie.setRelease_date(jo.getString("release_date"));

            return movie;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }






    }




}
