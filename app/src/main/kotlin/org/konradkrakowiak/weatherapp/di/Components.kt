package org.konradkrakowiak.weatherapp.di

import android.app.Activity
import dagger.Component
import dagger.Subcomponent
import org.konradkrakowiak.weatherapp.MainActivity
import org.konradkrakowiak.weatherapp.OpenWeatherApp
import org.konradkrakowiak.weatherapp.common.ui.BaseFragment
import org.konradkrakowiak.weatherapp.weatherdetails.ui.WeatherDetailsFragment
import org.konradkrakowiak.weatherapp.weatherlist.ui.WeatherListFragment
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class)])
interface AppComponent {

    companion object {
        fun create(): AppComponent {
            return DaggerAppComponent.builder().build()
        }
    }

    fun activityComponent() : ActivityComponent
}

@ActivityScope
@Subcomponent
interface ActivityComponent {

    companion object {
        fun create(appComponent: AppComponent): ActivityComponent {
            return appComponent.activityComponent()
        }
    }

    fun fragmentComponent(fragmentModule: FragmentModule): FragmentComponent

    fun inject(mainActivity: MainActivity)
}

@FragmentScope
@Subcomponent(modules = [(FragmentModule::class)])
interface FragmentComponent {

    companion object {
        fun create(activityComponent: ActivityComponent): FragmentComponent {
            return activityComponent.fragmentComponent(FragmentModule())
        }
    }

    fun inject(weatherListFragment: WeatherListFragment)

    fun inject(weatherDetailsFragment: WeatherDetailsFragment)
}


