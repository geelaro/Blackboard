package com.geelaro.blackboard.movies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geelaro.blackboard.R;
import com.geelaro.blackboard.base.beans.MoviesBean;
import com.geelaro.blackboard.images.ImageGlide;
import com.geelaro.blackboard.utils.ToolUtils;

import java.util.List;

/**
 * Created by geelaro on 2018/1/10.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ItemViewHolder> {
    private static final String TAG = MoviesAdapter.class.getSimpleName();
    private List<MoviesBean> mData;
    private Context mContext;

    public MoviesAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public void setData(List<MoviesBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final MoviesBean moviesBean = mData.get(position);
        if (moviesBean == null) {
            return;
        }
        ImageGlide.display(mContext, moviesBean.getImgaeUri(), holder.movieImage);
        holder.movieNo.setText("NO." + moviesBean.getMovieNo());
        holder.movieTitle.setText(moviesBean.getTitile());
        holder.movieDate.setText(moviesBean.getYear());
        holder.movieScore.setText(ToolUtils.formatScore(mContext, moviesBean.getScore()));
        final String URL = moviesBean.getAlt();

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra("movies", URL);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView movieImage;
        private TextView movieTitle;
        private TextView movieDate;
        private TextView movieScore;
        private TextView movieNo;
        private View mView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            movieNo = (TextView) mView.findViewById(R.id.movie_num);
            movieImage = (ImageView) mView.findViewById(R.id.movie_image);
            movieTitle = (TextView) mView.findViewById(R.id.movie_title);
            movieDate = (TextView) mView.findViewById(R.id.movie_date);
            movieScore = (TextView) mView.findViewById(R.id.movie_score);
        }
    }
}
