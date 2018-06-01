package org.konradkrakowiak.weatherapp.weatherlist.presenter

import io.reactivex.Observable
import org.konradkrakowiak.weatherapp.common.network.WeatherApiClient
import org.konradkrakowiak.weatherapp.common.network.pojo.City
import org.konradkrakowiak.weatherapp.common.network.pojo.WeatherCities
import org.konradkrakowiak.weatherapp.weatherlist.delegate.WeatherListDelegate
import org.konradkrakowiak.weatherapp.weatherlist.model.WeatherItemModelMapper
import org.konradkrakowiak.weatherapp.weatherlist.ui.WeatherListView
import retrofit2.Response
import spock.lang.Specification
import spock.lang.Unroll

class WeatherListPresenterShould extends Specification {

    def weatherApiClient = Mock(WeatherApiClient)
    def weatherItemModelMapper = Mock(WeatherItemModelMapper)
    def weatherDelegate = Mock(WeatherListDelegate)
    def weatherListView = Mock(WeatherListView)
    def weatherListPresenter = new WeatherListPresenter(
            weatherApiClient, weatherItemModelMapper, weatherDelegate
    )

    def setup() {
        weatherListPresenter.view = weatherListView
    }

    def "pass to delegate open action"() {
        given:
        def cities = Mock(List)
        def city = GroovyMock(City)
        def weatherCities = Mock(WeatherCities)
        weatherCities.cities = cities
        cities.get(10) >> city
        weatherListPresenter.weatherCities = weatherCities

        when:
        weatherListPresenter.onItemClick(10)
        then:
        1 * weatherDelegate.onItemClick(city)
    }

    def "invalidate item list when view is created"() {
        given:
        def weatherCities = GroovyMock(WeatherCities)
        weatherApiClient.getWeatherForCities() >> Observable.just(Response.success(weatherCities))
        when:
        weatherListPresenter.onViewFirstCreate()
        then:
        1 * weatherListView.invalidate()
    }

    def "display error if something happens when view is created "() {
        given:
        def throwable = new Throwable()
        weatherApiClient.getWeatherForCities() >> Observable.error(throwable)
        when:
        weatherListPresenter.onViewFirstCreate()
        then:
        1 * weatherListView.showError(throwable)
    }

    def "set list when view is created"() {
        def weatherCities = GroovyMock(WeatherCities)
        def list = Mock(List)
        weatherApiClient.getWeatherForCities() >> Observable.just(Response.success(weatherCities))
        weatherItemModelMapper.mapFrom(weatherCities) >> list
        when:
        weatherListPresenter.onViewFirstCreate()
        then:
        1 * weatherListView.setCitiesList(list)
    }

    @Unroll
    def "stop refreshing view when view is created with #resultState"() {
        weatherApiClient.getWeatherForCities() >> apiResult
        when:
        weatherListPresenter.onViewFirstCreate()
        then:
        1 * weatherListView.stopRefreshing()
        where:
        resultState | apiResult
        "success"   | Observable.just(Response.success(GroovyMock(WeatherCities)))
        "fail"      | Observable.error(new Throwable())
    }

    def "invalidate item list when view is refreshed"() {
        given:
        def weatherCities = GroovyMock(WeatherCities)
        weatherApiClient.getWeatherForCities() >> Observable.just(Response.success(weatherCities))
        when:
        weatherListPresenter.onViewRefresh()
        then:
        1 * weatherListView.invalidate()
    }

    def "display error if something happens when view is refreshed "() {
        given:
        def throwable = new Throwable()
        weatherApiClient.getWeatherForCities() >> Observable.error(throwable)
        when:
        weatherListPresenter.onViewRefresh()
        then:
        1 * weatherListView.showError(throwable)
    }

    def "set list when view is refreshed"() {
        def weatherCities = GroovyMock(WeatherCities)
        def list = Mock(List)
        weatherApiClient.getWeatherForCities() >> Observable.just(Response.success(weatherCities))
        weatherItemModelMapper.mapFrom(weatherCities) >> list
        when:
        weatherListPresenter.onViewRefresh()
        then:
        1 * weatherListView.setCitiesList(list)
    }

    @Unroll
    def "stop refreshing view when view is refreshed with #resultState"() {
        weatherApiClient.getWeatherForCities() >> apiResult
        when:
        weatherListPresenter.onViewRefresh()
        then:
        1 * weatherListView.stopRefreshing()
        where:
        resultState | apiResult
        "success"   | Observable.just(Response.success(GroovyMock(WeatherCities)))
        "fail"      | Observable.error(new Throwable())
    }

    def "stop refreshing view when view is paused"() {
        when:
        weatherListPresenter.onViewPause()
        then:
        1 * weatherListView.stopRefreshing()
    }
}
