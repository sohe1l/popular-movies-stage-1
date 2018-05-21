package com.example.android.popularmovies_stage1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.MatrixCursor;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.popularmovies_stage1.model.Movie;
import com.example.android.popularmovies_stage1.utilities.JsonUtilities;
import com.example.android.popularmovies_stage1.utilities.NetworkUtilities;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieClickListener {

    private RecyclerView recyclerView;

    private final String SORT_ORDER_KEY = "sort_order";

    private SharedPreferences sharedPreferences;

    private ArrayList<Movie> movies;

















































    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int selected = item.getItemId();

        switch (selected) {
            case R.id.action_change_sort_order:

                final String[] listLabels = getResources().getStringArray(R.array.pref_order_labels);
                final String[] listKeys = getResources().getStringArray(R.array.pref_order_keys);

                // Get the current selected item

                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.pref_sort_title))
                    .setSingleChoiceItems(
                            listLabels
                            ,getSortOrder()
                            , new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    setSortOrder(which);
                                }
                            })
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // if not the same... refresh

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private int getSortOrder() {
        Log.wtf("TTZZ", "Getting order: " +  Integer.toString( sharedPreferences.getInt(SORT_ORDER_KEY,0) ) );

        return sharedPreferences.getInt(SORT_ORDER_KEY,0);
    }

    private void setSortOrder(int index) {

        Log.wtf("TTZZ", "Setting order: " +  Integer.toString( index ) );

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SORT_ORDER_KEY, index);
        editor.commit();
    }


    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //PREFERENCE
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        URL requestUrl;

        /*
        switch (sharedPreferences.getInt(SORT_ORDER_KEY,SORT_ORDER_DEFAULT_VALUE)) {
            case R.string.mostPopular:
                requestUrl = NetworkUtilities.getPopularUrl();
                Toast.makeText(this, "most pop:" + NetworkUtilities.getPopularUrl(), Toast.LENGTH_LONG).show();
                break;
            case R.string.topRated:
                requestUrl = NetworkUtilities.getTopRatedUrl();
                Toast.makeText(this, "top rated:" + NetworkUtilities.getTopRatedUrl(), Toast.LENGTH_LONG).show();
                break;
            default:
                requestUrl = null;
        }

*/
        // TEMP TODO updating data from internet
        // new loadJsonData().execute(requestUrl);
    }

    @Override
    public void onMovieClick(int index) {
        String msg = "item " + index;
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    public class loadJsonData extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            String jsonResult = "";
            try{
                jsonResult = NetworkUtilities.getResponseFromHttpUrl(urls[0]);

            }catch(IOException e){
                e.printStackTrace();
            }
            return jsonResult;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s != null && !s.equals("")){
                Log.i("TEST", s);
            }

            movies = JsonUtilities.parseMovies(s);
            updateMovies(movies);


        }
    }

    private void updateMovies(ArrayList<Movie> movies){


        movieAdapter = new MovieAdapter(movies,22, this);

        LayoutManager layoutManager = new GridLayoutManager(this, 2);

        recyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieAdapter);

    }

}
