package org.konradkrakowiak.weatherapp

import android.os.Bundle
import org.konradkrakowiak.weatherapp.common.network.pojo.City
import org.konradkrakowiak.weatherapp.common.presenter.BaseActivityPresenter
import org.konradkrakowiak.weatherapp.di.ActivityScope
import org.konradkrakowiak.weatherapp.weatherlist.delegate.WeatherListDelegate
import javax.inject.Inject

@ActivityScope
class MainPresenter @Inject constructor(
) : BaseActivityPresenter<MainView>(), WeatherListDelegate {

    override fun onItemClick(city: City) {
        view?.displayDetails(city)
    }

    override fun onSaveInstanceState(bundle: Bundle?) {

    }

    override fun onRestoreInstanceState(bundle: Bundle?) {

    }
}