package org.konradkrakowiak.weatherapp.weatherdetails.model

import org.konradkrakowiak.weatherapp.common.network.pojo.City
import javax.inject.Inject


open class WeatherDetailsModelMapper @Inject constructor() {

    open fun mapFrom(city: City) = WeatherDetailsModel(
            city.name,
            city.main.temp,
            city.main.tempMin,
            city.main.tempMax,
            city.main.pressure,
            city.main.seaLevel,
            city.main.grondLevel,
            city.main.humidity
    )
}