package com.example.android.popularmovies_stage1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieClickListener {

    private RecyclerView recyclerView;

    private final String SORT_ORDER_KEY = "sort_order";
    private final int SORT_ORDER_DEFAULT_VALUE = R.string.mostPopular;

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

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.sortOrder))
                        .setItems(new String[]{
                                        getString(R.string.mostPopular),
                                        getString(R.string.topRated)
                                }
                                , new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case 0:
                                                setSortOrder(R.string.mostPopular);
                                                break;
                                            case 1:
                                                setSortOrder(R.string.topRated);
                                                break;
                                        }
                                    }
                                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setSortOrder(int orderId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SORT_ORDER_KEY, orderId);
    }


    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieAdapter = new MovieAdapter(22, this);

        LayoutManager layoutManager = new GridLayoutManager(this, 2);

        recyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieAdapter);

        //PREFERENCE
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        URL requestUrl;

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

        // TEMP TODO updating data from internet
        new loadJsonData().execute(requestUrl);
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
            movieAdapter.setMovies(movies);


        }
    }

}
