package org.konradkrakowiak.weatherapp

import android.app.Application
import org.konradkrakowiak.weatherapp.di.AppComponent


class OpenWeatherApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.create()
    }
}