package org.konradkrakowiak.weatherapp.weatherlist.model

import org.konradkrakowiak.weatherapp.common.network.pojo.City
import org.konradkrakowiak.weatherapp.common.network.pojo.WeatherCities
import java.util.*
import javax.inject.Inject

open class WeatherItemModelMapper @Inject constructor() {

    open fun mapFrom(weatherCities: WeatherCities): List<WeatherItemModel> {
        val weatherItemModels = LinkedList<WeatherItemModel>()
        weatherCities.cities.forEach { weatherItemModels.add(mapFrom(it)) }
        return weatherItemModels
    }

    private fun mapFrom(city: City) = WeatherItemModel(city.name, city.main.temp)
}
