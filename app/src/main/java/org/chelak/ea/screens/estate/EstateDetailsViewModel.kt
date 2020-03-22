package org.chelak.ea.screens.estate

import androidx.lifecycle.ViewModel
import org.chelak.ea.ui.Navigator
import javax.inject.Inject

class EstateDetailsViewModel : ViewModel() {

    @Inject
    lateinit var navigator: Navigator

    fun openMeterList() {
        navigator.openMeterList(0)
    }
}
