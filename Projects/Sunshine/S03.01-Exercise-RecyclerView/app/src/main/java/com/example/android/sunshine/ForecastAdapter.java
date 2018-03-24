package com.example.android.sunshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * Created by zhanchengsong on 2018-03-24.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapaterViewHolder> {
    private String[] mWeatherData;

    public class ForecastAdapaterViewHolder extends RecyclerView.ViewHolder{

        public final TextView mWeatherTextView;

        public ForecastAdapaterViewHolder(View itemView) {
            super(itemView);
            mWeatherTextView = (TextView) itemView.findViewById(R.id.tv_weather_data);
        }


    }


    @Override
    public ForecastAdapaterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.forecast_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);

        return new ForecastAdapaterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastAdapaterViewHolder forecastAdapaterViewholder, int position) {
        String weatherForThisDay = mWeatherData[position];
        forecastAdapaterViewholder.mWeatherTextView.setText(weatherForThisDay);

    }

    @Override
    public int getItemCount() {

        if (mWeatherData == null) {return 0;}
        else {return mWeatherData.length; }
    }

    public void setWeatherData(String[] weatherData) {
        mWeatherData = weatherData;
        notifyDataSetChanged();

    }

}
