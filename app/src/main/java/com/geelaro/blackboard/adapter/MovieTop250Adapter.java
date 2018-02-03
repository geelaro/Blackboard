package com.geelaro.blackboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.geelaro.blackboard.R;
import com.geelaro.blackboard.base.beans.MoviesBean;
import com.geelaro.blackboard.images.ImageGlide;
import com.geelaro.blackboard.movies.MovieDetailActivity;
import com.geelaro.blackboard.utils.ToolUtils;

import java.util.List;

/**
 * Created by geelaro on 2018/1/10.
 */

public class MovieTop250Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = MovieTop250Adapter.class.getSimpleName();
    private List<MoviesBean> mData;
    private Context mContext;
    private final static int VIEW_TYPE_FOOTER = -1;
    private final static int VIEW_TYPE_TOP = 0;
    public static final int LOAD_MORE = 1;
    public static final int LOAD_PULL_TO = 2;
    public static final int LOAD_NONE = 3;
    public static final int LOAD_END = 4;
    private int status = 2;

    public MovieTop250Adapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public void setData(List<MoviesBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_TYPE_TOP:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_movie, parent, false);
                return new ItemViewHolder(view);

            case VIEW_TYPE_FOOTER:
                view = LayoutInflater.from(mContext).inflate(R.layout.footer, parent, false);
                return new FooterViewHolder(view);
            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MoviesBean moviesBean = mData.get(position);
        if (moviesBean == null) {
            return;
        }
        int viewType = getItemViewType(position);
        if (viewType == VIEW_TYPE_FOOTER) {

            ((FooterViewHolder) holder).bindItem(status);

        } else if (viewType == VIEW_TYPE_TOP) {

            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            ImageGlide.display(mContext, moviesBean.getImgaeUri(), itemViewHolder.movieImage);
            itemViewHolder.movieNo.setText("NO." + moviesBean.getMovieNo());
            itemViewHolder.movieTitle.setText(moviesBean.getTitile());
            itemViewHolder.movieDate.setText(moviesBean.getYear());
            itemViewHolder.movieScore.setText(ToolUtils.formatScore(mContext, moviesBean.getScore()));
            final String URL = moviesBean.getAlt();

            itemViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MovieDetailActivity.class);
                    intent.putExtra("movies", URL);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return VIEW_TYPE_FOOTER;
        } else {
            return VIEW_TYPE_TOP;
        }
    }

    public void updateStatus(int status){
        this.status = status;
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
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

    class FooterViewHolder extends RecyclerView.ViewHolder {
        private TextView loadingText;
        private ProgressBar progress;

        public FooterViewHolder(View itemView) {
            super(itemView);
            loadingText = (TextView) itemView.findViewById(R.id.tv_loading);
            progress = (ProgressBar) itemView.findViewById(R.id.progress_loading);
        }

        private void bindItem(int status) {
            switch (status) {
                case LOAD_MORE:
                    progress.setVisibility(View.VISIBLE);
                    loadingText.setText("正在加载...");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_PULL_TO:
                    progress.setVisibility(View.GONE);
                    loadingText.setText("上拉加载更多");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_NONE:
                    System.out.print("--LOAD NONE--");
                    progress.setVisibility(View.GONE);
                    loadingText.setText("已无更多数据");
                    break;
                case LOAD_END:
                    itemView.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    }
}
