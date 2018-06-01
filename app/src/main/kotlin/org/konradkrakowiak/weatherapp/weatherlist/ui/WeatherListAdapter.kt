package org.konradkrakowiak.weatherapp.weatherlist.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import org.konradkrakowiak.weatherapp.R
import org.konradkrakowiak.weatherapp.weatherlist.model.WeatherItemModel
import java.util.*

class WeatherListAdapter constructor(private val items: LinkedList<WeatherItemModel> = LinkedList()) : RecyclerView.Adapter<WeatherListItemViewHolder>() {

    var onItemClickAction: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_weather_list_item, parent, false)
        val holder = WeatherListItemViewHolder(view)
        view.setOnClickListener({ onItemClickAction(holder.adapterPosition) })
        return holder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: WeatherListItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(items: List<WeatherItemModel>) = this.items.run {
        clear()
        addAll(items)
    }
}
