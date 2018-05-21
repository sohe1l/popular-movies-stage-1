package com.example.android.popularmovies_stage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies_stage1.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    final private MovieClickListener mOnMovieClickListener;

    private int mNumberItems;


    private ArrayList<Movie> movies;

    public MovieAdapter(ArrayList<Movie> movies, int numberOfItems, MovieClickListener listener){
        mOnMovieClickListener = listener;
        mNumberItems = numberOfItems;
        this.movies = movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.movie_item, parent, false);

        MovieViewHolder viewHolder = new MovieViewHolder(view, context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        String t = "";
        if(movies.size() > position){
            t = movies.get(position).getOriginal_title();

        }

        holder.bind(position, t);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }





    class MovieViewHolder extends  RecyclerView.ViewHolder
        implements View.OnClickListener{

        TextView tv;
        ImageView imageView;
        Context context;

        MovieViewHolder(View itemView, Context context) {
            super(itemView);

            this.context = context;

            tv = (TextView) itemView.findViewById(R.id.tvtvtv);

            imageView = (ImageView) itemView.findViewById(R.id.imgv_movie);

            itemView.setOnClickListener(this);



        }

        void bind(int pos, String title){
            if(movies.size() > pos){
                tv.setText(title);
            }
            // Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);


        }

        @Override
        public void onClick(View v) {
            int clickedPos = getAdapterPosition();
            mOnMovieClickListener.onMovieClick(clickedPos);
        }
    }

    public interface MovieClickListener{
        void onMovieClick(int index);
    }

}
