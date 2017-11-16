package com.geelaro.sunshine.news;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geelaro.sunshine.R;
import com.geelaro.sunshine.beans.NewsBean;
import com.geelaro.sunshine.images.ImageGlide;
import com.geelaro.sunshine.utils.SunLog;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by geelaro on 2017/11/3.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ItemViewHolder> {
    private static final String TAG = NewsAdapter.class.getSimpleName();
    private List<NewsBean> mData;
    private Context mContext;

     public  NewsAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<NewsBean> list){
         this.mData = list;
         notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_news,parent,false);
        ItemViewHolder viewHolder  = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        NewsBean newsBean = mData.get(position);
        if (newsBean==null){
            return;
        }
        ImageGlide.display(mContext,newsBean.getImgsrc(),  holder.newsImage);
        holder.newsTitle.setText(newsBean.getNewsTitle());
        holder.newsText.setText(newsBean.getNewsText());
        SunLog.d(TAG,"onBindViewHolder");

    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
         private ImageView newsImage;
         private TextView newsTitle;
         private TextView newsText;

        public ItemViewHolder(View itemView) {
            super(itemView);
            newsImage = (ImageView) itemView.findViewById(R.id.newsImage);
            newsTitle = (TextView) itemView.findViewById(R.id.newsTitle); //新闻标题
            newsText = (TextView) itemView.findViewById(R.id.newsText); //新闻简略正文
        }
    }
}
