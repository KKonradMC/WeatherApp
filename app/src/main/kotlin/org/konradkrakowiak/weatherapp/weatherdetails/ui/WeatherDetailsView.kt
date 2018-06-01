package org.konradkrakowiak.weatherapp.weatherdetails.ui

import org.konradkrakowiak.weatherapp.common.view.BaseView
import org.konradkrakowiak.weatherapp.weatherdetails.model.WeatherDetailsModel


interface WeatherDetailsView : BaseView {
    fun displayWeatherDetails(mapFrom: WeatherDetailsModel)
}