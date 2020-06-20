package org.chelak.ea.screens.estate.rates

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.chelak.ea.common.Logger
import org.chelak.ea.core.Repository
import org.chelak.ea.database.entity.Rate
import javax.inject.Inject

class RateListViewModel : ViewModel() {

    private var estateId: Long = 0

    @Inject
    lateinit var repository: Repository

    val rates: LiveData<List<Rate>> get() = repository.fetchRates(estateId)


    fun setEstateId(id: Long) {
        Logger.d("Estate id: $id")
        this.estateId = id

    }

}