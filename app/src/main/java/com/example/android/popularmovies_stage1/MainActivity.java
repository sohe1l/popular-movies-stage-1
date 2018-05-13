package com.example.android.popularmovies_stage1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieClickListener {

    private RecyclerView recyclerView;


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
        switch (orderId) {
            case R.string.mostPopular:
                Toast.makeText(this, "most pop", Toast.LENGTH_SHORT).show();
                break;
            case R.string.topRated:
                Toast.makeText(this, "top rated", Toast.LENGTH_SHORT).show();
                break;
        }

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
    }

    @Override
    public void onMovieClick(int index) {
        String msg = "item " + index;
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
