package org.konradkrakowiak.weatherapp

import org.konradkrakowiak.weatherapp.common.network.pojo.City
import spock.lang.Specification


class MainPresenterShould extends Specification {

    def mainPresenter = new MainPresenter()
    def mainView = Mock(MainView)

    def setup() {
        mainPresenter.view = mainView
    }

    def "display view"() {
        def city = GroovyMock(City)
        when:
        mainPresenter.onItemClick(city)
        then:
        1 * mainView.displayDetails(city)
    }
}
