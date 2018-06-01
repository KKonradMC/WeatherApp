package org.konradkrakowiak.weatherapp.weatherdetails.presenter

import org.konradkrakowiak.weatherapp.common.network.pojo.City
import org.konradkrakowiak.weatherapp.weatherdetails.model.WeatherDetailsModel
import org.konradkrakowiak.weatherapp.weatherdetails.model.WeatherDetailsModelMapper
import org.konradkrakowiak.weatherapp.weatherdetails.ui.WeatherDetailsView
import spock.lang.Specification


class WeatherDetailsPresenterShould extends Specification {

    def mapper = Mock(WeatherDetailsModelMapper)
    def weatherDetailsPresenter = new WeatherDetailsPresenter(mapper)
    def view = Mock(WeatherDetailsView)

    def setup(){
        weatherDetailsPresenter.view = view
    }

    def "display weather details when view is created"() {
        given:
        def city = GroovyMock(City)
        def weatherDetailsModel = GroovyMock(WeatherDetailsModel)
        mapper.mapFrom(city) >> weatherDetailsModel

        when:
        weatherDetailsPresenter.onViewCreated(city)

        then:
        1 * view.displayWeatherDetails(weatherDetailsModel)
    }
}
