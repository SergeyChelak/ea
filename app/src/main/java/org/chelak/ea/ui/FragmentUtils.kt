package org.chelak.ea.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.chelak.ea.di.AppComponent
import org.chelak.ea.ui.dialog.AlertModel
import org.chelak.ea.ui.dialog.presentAlert


interface AlertEmitter {
    val alertData: LiveData<AlertModel>
}

fun Fragment.observeAlerts(emitter: AlertEmitter) {
    emitter.alertData.observe(viewLifecycleOwner, {
        it?.let {
            presentAlert(it)
        }
    })
}

val Fragment.appComponent: AppComponent? get() = (activity as? MainActivity)?.component