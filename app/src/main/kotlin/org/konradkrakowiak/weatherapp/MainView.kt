package org.konradkrakowiak.weatherapp

import android.os.Parcelable
import org.konradkrakowiak.weatherapp.common.view.BaseView


interface MainView : BaseView {
    fun displayDetails(arg: Parcelable)
}