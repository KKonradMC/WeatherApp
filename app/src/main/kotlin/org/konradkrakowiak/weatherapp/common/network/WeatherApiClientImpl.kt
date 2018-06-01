package org.konradkrakowiak.weatherapp.common.network

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class WeatherApiClientImpl @Inject constructor(
        retrofit: Retrofit
) : WeatherApiClient {

    private val client = retrofit.create(WeatherApiClient::class.java)!!

    override fun getWeatherForCities() = client.getWeatherForCities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())!!

}
