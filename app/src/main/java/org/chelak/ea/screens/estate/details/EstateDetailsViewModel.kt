package org.chelak.ea.screens.estate.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.chelak.ea.common.Logger
import org.chelak.ea.core.Repository
import org.chelak.ea.database.entity.Meter
import org.chelak.ea.ui.Navigator
import javax.inject.Inject

class EstateDetailsViewModel : ViewModel() {

    private val meters = MutableLiveData<List<Meter>>()
    private var estateId: Long = 0
    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var repository: Repository

    fun openMeterList() {
        navigator.openMeterList(0)
    }

    fun setEstateId(id: Long) {
        Logger.d("Estate id: $estateId")
        this.estateId = id
        GlobalScope.launch {
        }
    }

    fun deleteEstate() {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteEstate(estateId)
            }
            withContext(Dispatchers.Main) {
                navigator.navigateBackToEstates()
            }
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
