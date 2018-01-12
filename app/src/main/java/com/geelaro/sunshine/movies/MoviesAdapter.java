package com.geelaro.sunshine.movies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by geelaro on 2018/1/10.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ItemViewHolder> {

    public MoviesAdapter() {
        super();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
