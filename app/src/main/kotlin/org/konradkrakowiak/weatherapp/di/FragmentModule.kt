package org.konradkrakowiak.weatherapp.di

import dagger.Module
import dagger.Provides
import org.konradkrakowiak.weatherapp.MainPresenter
import org.konradkrakowiak.weatherapp.weatherlist.delegate.WeatherListDelegate


@Module
class FragmentModule {

    @Provides
    fun provideWeatherListDelegate(mainPresenter: MainPresenter): WeatherListDelegate = mainPresenter
}