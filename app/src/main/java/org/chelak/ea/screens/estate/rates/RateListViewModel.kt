package org.chelak.ea.screens.estate.rates

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.chelak.ea.common.Logger
import org.chelak.ea.core.Formatter
import org.chelak.ea.core.Repository
import org.chelak.ea.database.entity.Rate
import org.chelak.ea.ui.list.SimpleListItem
import javax.inject.Inject

class RateListViewModel : ViewModel() {

    private var estateId: Long = 0

    @Inject
    lateinit var repository: Repository
    @Inject
    lateinit var formatter: Formatter

    val rates: LiveData<List<RateData>>
        get() = Transformations.map(repository.fetchRates(estateId)) { list ->
            list.map {
                val model = RateData()
                model.uid = it.uid
                model.title = it.title ?: ""
                model.value = formatter.formatAmount(it.value)
                model
            }
        }

    fun setEstateId(id: Long) {
        Logger.d("Estate id: $id")
        this.estateId = id

    }

    fun update(uid: Long, title: String, value: String) {
        GlobalScope.launch {
            if (uid == 0L) {
                val rate = Rate(estateUid = estateId)
                rate.title = title
                rate.value = formatter.stringToDecimalAmount(value)
                repository.insertRate(rate)
            } else {
                val rate = repository.getRate(uid)
                rate.title = title
                rate.value = formatter.stringToDecimalAmount(value)
                repository.updateRate(rate)
            }
        }
    }

    fun delete(uid: Long) {
        GlobalScope.launch {
            val rate = repository.getRate(uid)
            repository.deleteRate(rate)
        }
    }

}


class RateData(uid: Long = 0, title: String = "", var value: String = ""): SimpleListItem(uid, title)