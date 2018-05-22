package com.example.android.popularmovies_stage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies_stage1.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    final private MovieClickListener mOnMovieClickListener;

    private ArrayList<Movie> movies;


    MovieAdapter(ArrayList<Movie> movies, MovieClickListener listener){
        mOnMovieClickListener = listener;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.movie_item, parent, false);

        return new MovieViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends  RecyclerView.ViewHolder
        implements View.OnClickListener{

        final ImageView imageView;
        final Context context;

        MovieViewHolder(View itemView, Context context) {
            super(itemView);

            this.context = context;

            imageView = (ImageView) itemView.findViewById(R.id.imgv_movie);

            itemView.setOnClickListener(this);
        }

        void bind(int pos){
            if(movies.size() > pos){
                Picasso.with(context).load( Movie.POSTER_BASE_URL + movies.get(pos).getPoster_path())
                        .placeholder(R.drawable.ic_cloud_queue_black_24dp)
                        .error(R.drawable.ic_error_outline_black_24dp)
                        .into(imageView);
            }
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
