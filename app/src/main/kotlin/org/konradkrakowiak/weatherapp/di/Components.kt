package org.konradkrakowiak.weatherapp.di

import dagger.Component
import dagger.Subcomponent
import org.konradkrakowiak.weatherapp.common.ui.BaseFragment
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class)])
interface AppComponent {

    companion object {
        fun create(): AppComponent {
            return DaggerAppComponent.builder().build()
        }
    }

    fun activityComponent(): ActivityComponent
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

}

@FragmentScope
@Subcomponent(modules = [(FragmentModule::class)])
interface FragmentComponent {

    companion object {
        fun create(activityComponent: ActivityComponent, baseFragment: BaseFragment<*>): FragmentComponent {
            return activityComponent.fragmentComponent(FragmentModule())
        }
    }
}


