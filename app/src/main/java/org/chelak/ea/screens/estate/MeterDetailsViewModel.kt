package org.chelak.ea.screens.estate

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.chelak.ea.core.Repository
import org.chelak.ea.database.entity.MeterValue
import org.chelak.ea.ui.Navigator
import javax.inject.Inject

class MeterDetailsViewModel : ViewModel() {
    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var repository: Repository

    private var meterId: Long = 0

    val meterValues: LiveData<List<MeterValue>>
        get() = repository.meterValues(meterId)


    fun setMeterId(meterId: Long) {
        this.meterId = meterId
    }

    fun editProperties() {
        //
    }

    fun removeMeter() {
        //
    }

}
