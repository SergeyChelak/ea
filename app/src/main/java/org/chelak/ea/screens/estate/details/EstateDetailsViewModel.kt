package org.chelak.ea.screens.estate.details

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.chelak.ea.common.Logger
import org.chelak.ea.core.Calculator
import org.chelak.ea.core.Formatter
import org.chelak.ea.core.Repository
import org.chelak.ea.database.entity.Estate
import org.chelak.ea.database.entity.Meter
import org.chelak.ea.database.entity.MeterValue
import org.chelak.ea.ui.Navigator
import javax.inject.Inject
import kotlin.math.absoluteValue

class EstateDetailsViewModel : ViewModel() {

    private var estateId: Long = 0

    @Inject
    lateinit var formatter: Formatter

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var repository: Repository

    private val _meters = MutableLiveData<List<Meter>>()
    var meters: LiveData<List<Meter>> = _meters

    val estate = MediatorLiveData<Estate>()

    private val _meterBriefData = MutableLiveData<MeterBriefInfo>()
    var meterBriefData: LiveData<MeterBriefInfo> = _meterBriefData

    fun setEstateId(id: Long) {
        Logger.d("Estate id: $id")
        this.estateId = id

        estate.addSource(repository.estate(estateId)) { estateEntity ->
            estate.value = estateEntity
            estate.addSource(repository.meters(estateId)) { list ->
                _meters.value = list
                for (meter in list) {
                    estate.addSource(repository.fetchLastMeterValue(meter.uid)) { lastValues ->
                        _meterBriefData.value = calculateBriefMeterInfo(meter, lastValues)
                    }
                }
            }
        }
    }

    private fun calculateBriefMeterInfo(meter: Meter, values: List<MeterValue>): MeterBriefInfo {
        val info = MeterBriefInfo()
        info.uid = meter.uid
        if (values.isEmpty()) {
            info.date = Formatter.VALUE_EMPTY
            info.value = Formatter.VALUE_EMPTY
            info.delta = Formatter.VALUE_EMPTY
            info.trendDirection = TrendDirection.ZERO
        } else {
            info.date = formatter.formatDate(values.first().date)
            if (values.size == 3) {
                val delta = Calculator.value(meter, values[0], values[1]) -
                        Calculator.value(meter, values[1], values[2])
                info.trendDirection = when {
                    delta > 0 -> {
                        TrendDirection.UP
                    }
                    delta < 0 -> {
                        TrendDirection.DOWN
                    }
                    else -> {
                        TrendDirection.ZERO
                    }
                }
                info.delta = formatter.formatAmount(delta.absoluteValue)
                info.value = formatter.formatAmount(values[0].value)
            }
        }
        return info
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
        navigator.openRateList(estateId)
    }

    fun paymentSettings() {

    }
}
