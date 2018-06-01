package org.konradkrakowiak.weatherapp.weatherdetails.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_weather_details.view.*
import org.konradkrakowiak.weatherapp.R
import org.konradkrakowiak.weatherapp.common.ui.BaseFragment
import org.konradkrakowiak.weatherapp.weatherdetails.model.WeatherDetailsModel
import org.konradkrakowiak.weatherapp.weatherdetails.presenter.WeatherDetailsPresenter

class WeatherDetailsFragment : BaseFragment<WeatherDetailsPresenter>(), WeatherDetailsView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_weather_details, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initView(this)
        presenter.onViewCreated(arguments!!.getParcelable(CITY)!!)
    }

    override fun displayWeatherDetails(weatherDetailsModel: WeatherDetailsModel) {
        view?.run {
            city_name.text = weatherDetailsModel.cityName
            temp.text = context.getString(R.string.temp, weatherDetailsModel.temp)
            temp_min.text = context.getString(R.string.temp_min, weatherDetailsModel.tempMin)
            temp_max.text = context.getString(R.string.temp_max, weatherDetailsModel.tempMax)
            pressure.text = context.getString(R.string.pressure, weatherDetailsModel.pressure)
            sea_level.text = context.getString(R.string.sea_level, weatherDetailsModel.seaLevel)
            ground_level.text = context.getString(R.string.ground_level, weatherDetailsModel.grondLevel)
            humidity.text = context.getString(R.string.humidity, weatherDetailsModel.humidity)
        }
    }

    companion object {
        fun newInstance(args: Parcelable) = WeatherDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(CITY, args)
            }
        }
    }
}

const val CITY = "CITY"
