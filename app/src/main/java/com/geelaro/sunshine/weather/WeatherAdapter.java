package com.geelaro.sunshine.weather;

import android.content.Context;
import android.database.Cursor;
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
    private Context mContext;
    private Cursor mCursor;

    private final static int VIEW_TYPE_TODAY = 0;
    private final static int VIEW_TYPE_FUTURE_DAY = 1;

    private boolean mUseTodayLayout;

    public WeatherAdapter(Context context) {
        this.mContext = context;
        mUseTodayLayout = mContext.getResources().getBoolean(R.bool.use_today_layout);
        SunLog.d(TAG, "WeatherAdapter Init.");
    }

    public void swapCursor(Cursor cursor) {
        mCursor = cursor;
        this.notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;
        switch (viewType) {
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
        mCursor.moveToPosition(position);
        int weatherImageId;
        int viewType = getItemViewType(position);

        switch (viewType) {
            case VIEW_TYPE_TODAY:
                weatherImageId = ToolUtils.getBigWeatherImage(mCursor.getInt(WeatherFragment.COLUMN_WEATHER_ID));
                holder.cityName.setText(WeatherJsonUtils.getCityName());
                break;
            case VIEW_TYPE_FUTURE_DAY:
                weatherImageId = ToolUtils.getSmallWeatherImage(mCursor.getInt(WeatherFragment.COLUMN_WEATHER_ID));
                break;
            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }

        holder.weatherImage.setImageResource(weatherImageId); //Weather Icon
        holder.weatherDesc.setText(mCursor.getString(WeatherFragment.COLUMN_DESC)); //Weather Description
        holder.lowTemp.setText(ToolUtils.formatTemperature(mContext, mCursor.getDouble(WeatherFragment.MIN_TEMP))); //Low temperature
        holder.highTemp.setText(ToolUtils.formatTemperature(mContext, mCursor.getDouble(WeatherFragment.MAX_TEMP)));//High temperature
        String date = ToolUtils.getReadableDateString(mCursor.getLong(WeatherFragment.COLUMN_DATE));
        holder.dateText.setText(date);//date
        SunLog.d(TAG, "BindView");
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mCursor != null ? mCursor.getCount() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mUseTodayLayout && position == 0) {
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
        private TextView cityName;


        public ItemViewHolder(View itemView) {
            super(itemView);
            weatherImage = (ImageView) itemView.findViewById(R.id.weather_icon);
            weatherDesc = (TextView) itemView.findViewById(R.id.weather_description);
            highTemp = (TextView) itemView.findViewById(R.id.high_temperature);
            lowTemp = (TextView) itemView.findViewById(R.id.low_temperature);
            dateText = (TextView) itemView.findViewById(R.id.date);
            cityName = (TextView) itemView.findViewById(R.id.city);
        }
    }
}
