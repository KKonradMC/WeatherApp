package org.konradkrakowiak.weatherapp.common.network

import io.reactivex.Observable
import org.konradkrakowiak.weatherapp.common.network.pojo.WeatherCities
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApiClient {

    @GET(WEATHER_FOR_CITIES)
    fun getWeatherForCities(): Observable<Response<WeatherCities>>
}
