package org.konradkrakowiak.weatherapp.weatherlist.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.view_weather_list_item.view.*
import org.konradkrakowiak.weatherapp.R
import org.konradkrakowiak.weatherapp.weatherlist.model.WeatherItemModel


class WeatherListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(weatherItemModel: WeatherItemModel) {
        itemView.city_name.text = weatherItemModel.cityName
        itemView.temp.text = itemView.context.getString(R.string.temp, weatherItemModel.temp)
    }
}