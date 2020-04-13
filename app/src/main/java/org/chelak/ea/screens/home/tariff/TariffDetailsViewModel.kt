package org.chelak.ea.screens.home.tariff

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.chelak.ea.core.Repository
import org.chelak.ea.database.entity.Tariff
import org.chelak.ea.database.entity.TariffThreshold
import java.math.BigDecimal
import javax.inject.Inject

class TariffDetailsViewModel : ViewModel() {

    @Inject
    lateinit var repository: Repository

    private var tariffId: Long = 0

    val tariff: LiveData<Tariff> get() = repository.tariff(tariffId)

    val thresholds: LiveData<List<TariffThreshold>> get() = repository.thresholds(tariffId)

    fun setTariffId(tariffId: Long) {
        this.tariffId = tariffId
    }

    fun addThreshold(startFrom: String, price: String) {
        val start = startFrom.toLong()
        val priceValue = BigDecimal(price)
        GlobalScope.launch {
            val threshold = TariffThreshold(tariffUid = tariffId, value = start, price = priceValue)
            repository.addThreshold(threshold)
        }
    }

    fun updateThreshold(id: Long, startFrom: String, price: String) {
        GlobalScope.launch {
            // TODO formatting
            val threshold = repository.fetchThreshold(id)
            threshold.price = BigDecimal(price)
            threshold.value = startFrom.toLong()
            repository.updateThreshold(threshold)
            // TODO handle update result
        }
    }

    fun deleteThreshold(id: Long) {
        GlobalScope.launch {
            repository.deleteThreshold(id)
        }
    }
}
