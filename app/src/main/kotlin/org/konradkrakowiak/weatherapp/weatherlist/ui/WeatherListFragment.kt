package org.konradkrakowiak.weatherapp.weatherlist.ui

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_weather_list.view.*
import org.konradkrakowiak.weatherapp.R
import org.konradkrakowiak.weatherapp.common.ui.BaseFragment
import org.konradkrakowiak.weatherapp.weatherlist.model.WeatherItemModel
import org.konradkrakowiak.weatherapp.weatherlist.presenter.WeatherListPresenter


class WeatherListFragment : BaseFragment<WeatherListPresenter>(), WeatherListView {

    private var weatherListAdapter = WeatherListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentComponent().inject(this)
        super.onCreate(savedInstanceState)
        presenter.run {
            initView(this@WeatherListFragment)
            if (savedInstanceState == null) {
                onViewFirstCreate()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_weather_list, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.cities_list.run {
            layoutManager = LinearLayoutManager(context)
            adapter = weatherListAdapter.apply {
                onItemClickAction = { presenter.onItemClick(it) }
            }
            addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
        }
        view.refresh_cities_list.setOnRefreshListener { presenter.onViewRefresh() }
    }

    override fun onPause() {
        super.onPause()
        presenter.onViewPause()
    }

    override fun setCitiesList(city: List<WeatherItemModel>) {
        weatherListAdapter.setItems(city)
    }

    override fun invalidate() {
        weatherListAdapter.notifyDataSetChanged()
    }

    override fun stopRefreshing() {
        view?.refresh_cities_list?.isRefreshing = false
    }

    companion object {
        fun newInstance() = WeatherListFragment()
    }
}
