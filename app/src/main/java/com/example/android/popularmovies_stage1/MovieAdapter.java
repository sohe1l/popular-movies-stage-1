package com.example.android.popularmovies_stage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {


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
        return 500;
    }





    class MovieViewHolder extends  RecyclerView.ViewHolder{

        TextView tv;

        MovieViewHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.tvtvtv);

        }

        void bind(int pos){
            tv.setText(String.valueOf(pos));
        }
    }
}
