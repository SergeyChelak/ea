package org.chelak.ea.screens.estate.details

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.chelak.ea.common.Logger
import org.chelak.ea.core.Repository
import org.chelak.ea.database.entity.Estate
import org.chelak.ea.database.entity.Meter
import org.chelak.ea.ui.Navigator
import javax.inject.Inject

class EstateDetailsViewModel : ViewModel() {

    private var estateId: Long = 0
    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var repository: Repository

    val meters: LiveData<List<Meter>> get() = repository.meters(estateId)

    val estate: LiveData<Estate> get() = repository.estate(estateId)

    fun setEstateId(id: Long) {
        Logger.d("Estate id: $id")
        this.estateId = id
    }

    fun deleteEstate() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteEstate(estateId)
            }
            withContext(Dispatchers.Main) {
                navigator.navigateBackToEstates()
            }
        }
    }

    fun addMeter(title: String) {
        viewModelScope.launch {
            var meterId: Long = 0
            withContext(Dispatchers.IO) {
                meterId = repository.addMeter(estateId, title, 7)
            }
            openMeter(meterId)
        }
    }

    fun openMeter(meterId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                navigator.openMeterDetails(meterId)
            }
        }
    }

    fun manageRates() {

    }

    fun paymentSettings() {

    }
}
