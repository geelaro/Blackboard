package com.geelaro.sunshine.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geelaro.sunshine.R;
import com.geelaro.sunshine.beans.WeatherBean;
import com.geelaro.sunshine.utils.SunLog;
import com.geelaro.sunshine.utils.ToolUtils;

import java.util.List;

/**
 * Created by geelaro on 2017/10/25.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ItemViewHolder> {
    private static final String TAG = WeatherAdapter.class.getSimpleName();
    private List<WeatherBean> mData;
    private Context mContext;

    private final static int VIEW_TYPE_TODAY = 0;
    private final static int VIEW_TYPE_FUTURE_DAY = 1;

    private boolean mUseTodayLayout;

    public WeatherAdapter(Context context) {
        this.mContext = context;
        mUseTodayLayout = mContext.getResources().getBoolean(R.bool.use_today_layout);
        SunLog.d(TAG, "WeatherAdapter Init.");
    }

    public void setData(List<WeatherBean> mData) {
        this.mData = mData;
        this.notifyDataSetChanged(); //数据发生改动时要重新get
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;
        switch (viewType){
            case VIEW_TYPE_TODAY:
                layoutId = R.layout.list_item_weather_today;
                break;
            case VIEW_TYPE_FUTURE_DAY:
                layoutId = R.layout.list_item_weather;
                break;
            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }

        View view = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        WeatherAdapter.ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        WeatherBean weatherBean = mData.get(position);
        if (weatherBean == null) {
            return;
        }
        int weatherId = weatherBean.getWeatherId();
        int weatherImageId;
        int viewType = getItemViewType(position);

        switch (viewType){
            case VIEW_TYPE_TODAY:
                weatherImageId = ToolUtils.getBigWeatherImage(weatherId);
                break;
            case VIEW_TYPE_FUTURE_DAY:
                weatherImageId = ToolUtils.getSmallWeatherImage(weatherId);
                break;
            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }

        holder.weatherImage.setImageResource(weatherImageId); //Weather Icon
        holder.weatherDesc.setText(weatherBean.getDesc()); //Weather Description
        holder.lowTemp.setText(String.valueOf(weatherBean.getMinTemp())); //Low temperature
        holder.highTemp.setText(String.valueOf(weatherBean.getMaxTemp()));//High temperature
        holder.dateText.setText(weatherBean.getDate());//date
        SunLog.d(TAG, "getView");
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mUseTodayLayout&&position==0){
            return VIEW_TYPE_TODAY;
        } else {
            return VIEW_TYPE_FUTURE_DAY;
        }

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView weatherImage;
        private TextView dateText;
        private TextView weatherDesc;
        private TextView highTemp;
        private TextView lowTemp;


        public ItemViewHolder(View itemView) {
            super(itemView);
            weatherImage = (ImageView) itemView.findViewById(R.id.weather_icon);
            weatherDesc = (TextView) itemView.findViewById(R.id.weather_description);
            highTemp = (TextView) itemView.findViewById(R.id.high_temperature);
            lowTemp = (TextView) itemView.findViewById(R.id.low_temperature);
            dateText = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
