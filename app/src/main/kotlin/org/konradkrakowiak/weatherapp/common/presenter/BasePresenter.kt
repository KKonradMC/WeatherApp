package org.konradkrakowiak.weatherapp.common.presenter

import android.os.Bundle
import org.konradkrakowiak.weatherapp.common.view.BaseView

abstract class BasePresenter<VIEW : BaseView> {
    var view: VIEW? = null

    fun initView(view: VIEW) {
        this.view = view
    }

    abstract fun onSaveInstanceState(bundle: Bundle?)

    abstract fun onRestoreInstanceState(bundle: Bundle?)

    fun destroyView() {
        view = null
    }
}
