package org.konradkrakowiak.weatherapp.weatherdetails.presenter

import android.os.Bundle
import org.konradkrakowiak.weatherapp.common.network.pojo.City
import org.konradkrakowiak.weatherapp.common.presenter.BaseFragmentPresenter
import org.konradkrakowiak.weatherapp.weatherdetails.model.WeatherDetailsModelMapper
import org.konradkrakowiak.weatherapp.weatherdetails.ui.WeatherDetailsView
import javax.inject.Inject


class WeatherDetailsPresenter @Inject constructor(
        private val mapper: WeatherDetailsModelMapper
) : BaseFragmentPresenter<WeatherDetailsView>() {

    fun onViewCreated(city: City) {
        view?.displayWeatherDetails(mapper.mapFrom(city))
    }

    override fun onSaveInstanceState(bundle: Bundle?) {
    }

    override fun onRestoreInstanceState(bundle: Bundle?) {
    }
}