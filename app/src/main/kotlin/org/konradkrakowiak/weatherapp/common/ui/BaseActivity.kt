package org.konradkrakowiak.weatherapp.common.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import org.konradkrakowiak.weatherapp.OpenWeatherApp
import org.konradkrakowiak.weatherapp.common.presenter.BaseActivityPresenter
import org.konradkrakowiak.weatherapp.common.view.BaseView
import org.konradkrakowiak.weatherapp.di.ActivityComponent
import javax.inject.Inject


open class BaseActivity<PRESENTER : BaseActivityPresenter<*>> : AppCompatActivity(), BaseView {

    @Inject
    lateinit var presenter: PRESENTER

    var activityComponent: ActivityComponent? = null

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        presenter.onRestoreInstanceState(savedInstanceState)
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onDestroy() {
        presenter.destroyView()
        super.onDestroy()
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, "Error occours : ${error.message}", Toast.LENGTH_LONG).show()
    }
}

fun BaseActivity<*>.activityComponent(): ActivityComponent = activityComponent
        ?: ActivityComponent.create((applicationContext as OpenWeatherApp).appComponent).run {
            activityComponent = this
            this
        }