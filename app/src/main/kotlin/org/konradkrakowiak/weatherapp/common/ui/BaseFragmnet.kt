package org.konradkrakowiak.weatherapp.common.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import org.konradkrakowiak.weatherapp.common.presenter.BaseFragmentPresenter
import org.konradkrakowiak.weatherapp.common.view.BaseView
import org.konradkrakowiak.weatherapp.di.FragmentComponent
import javax.inject.Inject

abstract class BaseFragment<PRESENTER : BaseFragmentPresenter<*>> : Fragment(), BaseView {

    @Inject
    protected lateinit var presenter: PRESENTER

    fun fragmentComponent(): FragmentComponent = FragmentComponent.create((activity as BaseActivity<*>).activityComponent!!)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        presenter.onRestoreInstanceState(savedInstanceState)
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroyView()
    }

    override fun showError(error: Throwable) {
        Toast.makeText(context, "Error occours : ${error.message}", Toast.LENGTH_LONG).show()
    }
}

