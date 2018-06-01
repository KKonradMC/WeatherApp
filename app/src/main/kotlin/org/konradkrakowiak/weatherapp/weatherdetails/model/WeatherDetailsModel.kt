package org.konradkrakowiak.weatherapp.weatherdetails.model


class WeatherDetailsModel(
        val cityName: String,
        val temp: Double,
        val tempMin: Double,
        val tempMax: Double,
        val pressure: Double,
        val seaLevel: Double,
        val grondLevel: Double,
        val humidity: Double
)

