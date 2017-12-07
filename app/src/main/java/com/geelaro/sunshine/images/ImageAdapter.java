package com.geelaro.sunshine.images;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geelaro.sunshine.R;
import com.geelaro.sunshine.beans.ImageBean;
import com.geelaro.sunshine.utils.SunLog;
import com.geelaro.sunshine.utils.SunshineApp;
import com.geelaro.sunshine.utils.ToolUtils;

import java.util.List;

/**
 * Created by geelaro on 2017/10/12.
 * ImageFragment的adapter
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ItemViewHolder> {
    private final static String TAG = ImageAdapter.class.getSimpleName();
    private List<ImageBean> mData;
    private Context mContext;
    private int mMaxWidth;
    private int mMaxHeight;


    public ImageAdapter(Context mContext) {
        this.mContext = mContext;
        SunLog.d(TAG, "mData");
        mMaxWidth = ToolUtils.getWidthInPx(mContext) - ToolUtils.dp2px(mContext, 32);
//        mMaxHeight = ToolUtils.getHeightInPx(mContext) - ToolUtils.dp2px(mContext, 156);
    }

    /**
     * 传递数据
     *
     * @param mData
     */
    public void setData(List<ImageBean> mData) {
        this.mData = mData;
        this.notifyDataSetChanged();//数据发生变动时要notify
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_image, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        SunLog.d(TAG, "onCreateViewHolder");

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ImageBean imageBean = mData.get(position);
        if (imageBean == null) {
            return;
        }
        holder.titleView.setText(imageBean.getTitle());
        //图尺寸
        float scale = (float) imageBean.getWidth() / (float) mMaxWidth;
        int height = (int) (imageBean.getHeight() / scale);
//        if (height > mMaxHeight) {
//            height = mMaxHeight;
//        }
        holder.imageView.setLayoutParams(new LinearLayout.LayoutParams(mMaxWidth, height));
        //使用Glide加载图片
        ImageGlide.display(mContext, imageBean.getThumburl(), holder.imageView);
        SunLog.d(TAG, "ImageGlide:display");

    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    /**
     * ViewHolder
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_item);
            titleView = (TextView) itemView.findViewById(R.id.image_text);
        }
    }
}
