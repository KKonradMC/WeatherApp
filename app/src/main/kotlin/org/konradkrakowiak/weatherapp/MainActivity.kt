package org.konradkrakowiak.weatherapp

import android.os.Bundle
import android.os.Parcelable
import org.konradkrakowiak.weatherapp.common.ui.BaseActivity
import org.konradkrakowiak.weatherapp.common.ui.activityComponent
import org.konradkrakowiak.weatherapp.weatherdetails.ui.WeatherDetailsFragment
import org.konradkrakowiak.weatherapp.weatherlist.ui.WeatherListFragment

class MainActivity : BaseActivity<MainPresenter>(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent().inject(this)
        super.onCreate(savedInstanceState)
        presenter.initView(this)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.main_container, WeatherListFragment.newInstance()).commit()
        }
    }

    override fun displayDetails(arg: Parcelable) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, WeatherDetailsFragment.newInstance(arg))
                .addToBackStack(DETAILS)
                .commit()
    }
}

const val DETAILS = "DETAILS"

