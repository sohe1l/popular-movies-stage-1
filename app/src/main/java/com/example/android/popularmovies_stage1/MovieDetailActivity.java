package com.example.android.popularmovies_stage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.popularmovies_stage1.model.Movie;

import org.w3c.dom.Text;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        TextView movie_title = (TextView) findViewById(R.id.movie_title_tv);

        Intent creatingIntent = getIntent();

        if(creatingIntent.hasExtra(getString(R.string.movie_detail_intent_key))){
            Movie movie = creatingIntent.getParcelableExtra(getString(R.string.movie_detail_intent_key));

            movie_title.setText( movie.getTitle() );
        }


    }
}
