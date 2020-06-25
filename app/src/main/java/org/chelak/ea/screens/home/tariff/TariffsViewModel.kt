package org.chelak.ea.screens.home.tariff

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.chelak.ea.core.Repository
import org.chelak.ea.database.entity.Tariff
import org.chelak.ea.database.entity.TariffThreshold
import org.chelak.ea.ui.Navigator
import java.lang.Exception
import java.math.BigDecimal
import javax.inject.Inject

class TariffsViewModel : ViewModel() {

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var navigator: Navigator

    fun getTariffs() : LiveData<List<Tariff>> = repository.allTariffs()

    fun addTariff(title: String, priceText: String) {
        (try { BigDecimal(priceText) } catch (e: Exception) { null })?.let { price ->
            GlobalScope.launch {
                // TODO perform inside data manager/repository
                val tariff = Tariff(title = title)
                val tariffId = repository.insertTariff(tariff)
                val baseThreshold = TariffThreshold(tariffUid = tariffId, price = price)
                repository.insertThreshold(baseThreshold)
                withContext(Dispatchers.Main) {
                    openTariffDetails(tariffId)
                }
            }
        }
    }

    fun openTariffDetails(tariffId: Long) {
        navigator.openTariffDetails(tariffId)
    }
}
