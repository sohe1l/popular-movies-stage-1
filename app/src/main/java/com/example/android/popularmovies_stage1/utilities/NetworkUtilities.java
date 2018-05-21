package com.example.android.popularmovies_stage1.utilities;

import android.net.Uri;
import android.util.Log;

import com.example.android.popularmovies_stage1.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtilities {

    private static final String TAG = NetworkUtilities.class.getSimpleName();

    private static final String API_BASE_URL = "http://api.themoviedb.org/3/";

    private static final String API_POPULAR_PATH = "movie/popular/";

    private static final String API_TOP_RATED_PATH = "movie/top_rated/";

    private static final String API_PARAM = "api_key";


    public static URL getPopularUrl(){
        Uri uri = Uri.parse(API_BASE_URL).buildUpon()
                .appendEncodedPath(API_POPULAR_PATH)
                .appendQueryParameter(API_PARAM, getApiKey())
                .build();

        return buildURLfromURI(uri);
    }

    public static URL getTopRatedUrl(){
        Uri uri = Uri.parse(API_BASE_URL).buildUpon()
                .appendEncodedPath(API_TOP_RATED_PATH)
                .appendQueryParameter(API_PARAM, getApiKey())
                .build();

        return buildURLfromURI(uri);
    }


    // ref: sunshine app
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            String response = null;
            if (hasInput) {
                response = scanner.next();
            }
            scanner.close();
            return response;
        } finally {
            urlConnection.disconnect();
        }
    }


    /*
    Return the API key from the gradle.properties
    Add MoviedbAPIKey = "...." in  gradle.properties
     */
    private static String getApiKey(){
        return BuildConfig.MoviedbAPIKey;
    }


    private static URL buildURLfromURI(Uri uri){
        try{
            return new URL(uri.toString());
        }catch(MalformedURLException e){
            e.printStackTrace();
            return null;
        }
    }





}
