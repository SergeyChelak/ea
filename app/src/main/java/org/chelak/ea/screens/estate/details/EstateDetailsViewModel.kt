package org.chelak.ea.screens.estate.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.chelak.ea.common.Logger
import org.chelak.ea.core.Repository
import org.chelak.ea.database.entity.Meter
import org.chelak.ea.ui.Navigator
import javax.inject.Inject

class EstateDetailsViewModel : ViewModel() {

    private val meters = MutableLiveData<List<Meter>>()

    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var repository: Repository

    fun openMeterList() {
        navigator.openMeterList(0)
    }

    fun setEstateId(estateId: Long) {
        Logger.d("Estate id: $estateId")
        GlobalScope.launch {
        }
    }

    fun addMeter() {
        //
    }

    fun manageRates() {

    }

    fun paymentSettings() {

    }
}
