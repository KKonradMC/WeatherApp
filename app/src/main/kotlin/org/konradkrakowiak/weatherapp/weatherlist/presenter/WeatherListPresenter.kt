package org.konradkrakowiak.weatherapp.weatherlist.presenter

import android.os.Bundle
import io.reactivex.disposables.Disposable
import org.konradkrakowiak.weatherapp.common.network.WeatherApiClient
import org.konradkrakowiak.weatherapp.common.network.pojo.WeatherCities
import org.konradkrakowiak.weatherapp.common.presenter.BaseFragmentPresenter
import org.konradkrakowiak.weatherapp.di.FragmentScope
import org.konradkrakowiak.weatherapp.weatherlist.delegate.WeatherListDelegate
import org.konradkrakowiak.weatherapp.weatherlist.model.WeatherItemModelMapper
import org.konradkrakowiak.weatherapp.weatherlist.ui.WeatherListView
import retrofit2.Response
import javax.inject.Inject

@FragmentScope
class WeatherListPresenter @Inject constructor(
        private val client: WeatherApiClient,
        private val mapper: WeatherItemModelMapper,
        private val delegate: WeatherListDelegate
) : BaseFragmentPresenter<WeatherListView>() {

    var weatherCities: WeatherCities? = null
    var disposable: Disposable? = null

    override fun onSaveInstanceState(bundle: Bundle?) {
        bundle?.run {
            putParcelable(WEATHER_CITIES, weatherCities)
        }
    }

    override fun onRestoreInstanceState(bundle: Bundle?) {
        bundle?.run {
            weatherCities = getParcelable(WEATHER_CITIES)
            weatherCities?.run {
                setCities(this)
                view?.invalidate()
            }
        }
    }


    fun onViewFirstCreate() = requestForCities()

    fun onViewPause() {
        disposable?.dispose()
        view?.stopRefreshing()
    }

    fun onViewRefresh() = requestForCities()

    fun onItemClick(itemPosition: Int) {
        weatherCities?.cities?.get(itemPosition)?.let { delegate.onItemClick(it) }
    }

    private fun requestForCities() {
        disposable = client
                .getWeatherForCities()
                .subscribe(this::onSuccess, this::onError, this::onComplete)
    }

    private fun onSuccess(response: Response<WeatherCities>?) {
        response?.body()?.let {
            weatherCities = it
            setCities(it)
        }
    }

    private fun onError(error: Throwable) {
        view?.showError(error)
        view?.stopRefreshing()
    }

    private fun onComplete() {
        view?.invalidate()
        view?.stopRefreshing()
    }

    private fun setCities(weatherCities: WeatherCities) = view?.setCitiesList(mapper.mapFrom(weatherCities))
}

private const val WEATHER_CITIES = "WEATHER_CITIES"
