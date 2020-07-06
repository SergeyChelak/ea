package org.chelak.ea.screens.estate.calculation

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.chelak.ea.common.Logger
import org.chelak.ea.core.Repository
import org.chelak.ea.ui.Navigator
import javax.inject.Inject

class PaymentListItem {
    var uid: Long = 0
    var title: String = ""
}

class CalculationListViewModel : ViewModel() {

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var navigator: Navigator

    private var estateId: Long = 0

    private var tariffTitle = HashMap<Long, String>()

    val items = MediatorLiveData<List<PaymentListItem>>()

    fun setEstateId(id: Long) {
        Logger.d("Estate id: $id")
        this.estateId = id
        GlobalScope.launch {
            repository.tariffList().forEach {
                it.title?.let { title ->
                    tariffTitle[it.uid] = title
                }
            }
            withContext(Dispatchers.Main) {
                items.addSource(repository.fetchPaymentSettings(id)) { calculationItems ->
                    items.value = calculationItems.map {
                        val item = PaymentListItem()
                        item.uid = it.uid
                        item.title = tariffTitle[it.tariffUid] ?: ""
                        item
                    }
                }
            }
        }
    }


}