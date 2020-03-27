package org.chelak.ea.screens.home.tariff

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.chelak.ea.core.Repository
import org.chelak.ea.database.entity.Tariff
import org.chelak.ea.database.entity.TariffThreshold
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
}
