package org.chelak.ea.screens.estate.details

import androidx.lifecycle.ViewModel
import org.chelak.ea.common.Logger
import org.chelak.ea.ui.Navigator
import javax.inject.Inject

class EstateDetailsViewModel : ViewModel() {

    @Inject
    lateinit var navigator: Navigator

    fun openMeterList() {
        navigator.openMeterList(0)
    }

    fun setEstateId(estateId: Long) {
        Logger.d("Estate id: $estateId")
    }

    fun addMeter() {
        //
    }

    fun manageRates() {

    }

    fun paymentSettings() {

    }
}
