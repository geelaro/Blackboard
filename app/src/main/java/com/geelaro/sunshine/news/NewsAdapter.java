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
    private OnItemClickListener mOnItemClickListener;

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
        holder.newsTitle.setText(newsBean.getTitle());
        holder.newsSource.setText(newsBean.getSource());
        holder.newsMtime.setText(newsBean.getMtime());

    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    //interface
    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
         private ImageView newsImage;
         private TextView newsTitle;
         private TextView newsSource;
         private TextView newsMtime;

        public ItemViewHolder(View itemView) {
            super(itemView);
            newsImage = (ImageView) itemView.findViewById(R.id.newsImage);
            newsTitle = (TextView) itemView.findViewById(R.id.newsTitle); //新闻标题
            newsSource = (TextView) itemView.findViewById(R.id.news_source); //新闻来源
            newsMtime = (TextView) itemView.findViewById(R.id.news_mtime);//新闻时间
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener!=null){
                mOnItemClickListener.onItemClick(v,this.getPosition());
            }
        }
    }
}
