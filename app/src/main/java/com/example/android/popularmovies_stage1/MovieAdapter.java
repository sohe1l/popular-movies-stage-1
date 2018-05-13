package com.example.android.popularmovies_stage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {


    final private MovieClickListener mOnMovieClickListener;
    private int mNumberItems;


    public MovieAdapter(int numberOfItems, MovieClickListener listener){
        mOnMovieClickListener = listener;
        mNumberItems = numberOfItems;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.movie_item, parent, false);

        MovieViewHolder viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
         holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }





    class MovieViewHolder extends  RecyclerView.ViewHolder
        implements View.OnClickListener{

        TextView tv;

        MovieViewHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.tvtvtv);

            itemView.setOnClickListener(this);

        }

        void bind(int pos){
            tv.setText(String.valueOf(pos));
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