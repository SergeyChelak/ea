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

class TariffsViewModel : ViewModel() {

    @Inject
    lateinit var repository: Repository

    fun getTariffs() : LiveData<List<Tariff>> = repository.allTariffs()

    fun addTariff() {
        populateNewTariff()
    }

    private fun populateNewTariff() {
        GlobalScope.launch {
            val tariff = Tariff(title = "New Tariff")
            val tariffId = repository.addTariff(tariff)
            val baseThreshold = TariffThreshold(tariffUid = tariffId, value = 0, price = BigDecimal.ZERO)
            repository.addThreshold(baseThreshold)
        }
    }
}
