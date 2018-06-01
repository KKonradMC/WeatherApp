package org.konradkrakowiak.weatherapp.weatherlist.ui

import org.konradkrakowiak.weatherapp.common.view.BaseView
import org.konradkrakowiak.weatherapp.weatherlist.model.WeatherItemModel


interface WeatherListView : BaseView {
    fun setCitiesList(city: List<WeatherItemModel>)
    fun invalidate()
    fun stopRefreshing()
}