package org.konradkrakowiak.weatherapp.weatherlist.delegate

import org.konradkrakowiak.weatherapp.common.network.pojo.City


interface WeatherListDelegate {

    fun onItemClick(city: City)
}