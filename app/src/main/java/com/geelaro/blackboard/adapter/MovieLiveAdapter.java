package com.geelaro.blackboard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geelaro.blackboard.R;
import com.geelaro.blackboard.base.beans.MoviesBean;
import com.geelaro.blackboard.images.ImageGlide;
import com.geelaro.blackboard.utils.ToolUtils;

import java.util.List;

/**
 * Created by geelaro on 2018/2/3.
 * 豆瓣电影热映榜
 */

public class MovieLiveAdapter extends RecyclerView.Adapter<MovieLiveAdapter.ItemViewHolder> {
    private Context mContext;
    private List<MoviesBean> mData;

    public MovieLiveAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<MoviesBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie_live, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (mData == null) {
            return;
        }
        MoviesBean bean = mData.get(position);
        //Image 尺寸
        int width = (ToolUtils.getHeightInPx(mContext) - ToolUtils.dp2px(mContext,60)) / 3;
        int height = (int) (width * (383.0 / 270.0));
        holder.movieImage.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        //加载
        ImageGlide.display(mContext, bean.getImgaeUri(), holder.movieImage);
        holder.movieTitle.setText(bean.getTitile());
        if (TextUtils.isEmpty(bean.getScore())||bean.getScore().equals("0")) {
            holder.movieScore.setText("暂无评分");
        } else {
            holder.movieScore.setText(ToolUtils.formatScore(mContext, bean.getScore()));
        }

    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView movieImage;
        private TextView movieTitle;
        private TextView movieScore;

        public ItemViewHolder(View itemView) {
            super(itemView);
            movieImage = (ImageView) itemView.findViewById(R.id.movie_image);
            movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
            movieScore = (TextView) itemView.findViewById(R.id.movie_score);
        }
    }
}
