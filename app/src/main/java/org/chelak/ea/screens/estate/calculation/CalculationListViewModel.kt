package org.chelak.ea.screens.estate.calculation

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.chelak.ea.common.Logger
import org.chelak.ea.core.Repository
import org.chelak.ea.screens.estate.calculation.editor.Rule
import org.chelak.ea.screens.estate.calculation.editor.RuleEditor
import org.chelak.ea.ui.list.SimpleListItem
import javax.inject.Inject

class CalculationListViewModel : ViewModel() {

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var ruleEditor: RuleEditor

    private var estateId: Long = 0

    private var tariffTitle = HashMap<Long, String>()

    val items = MediatorLiveData<List<SimpleListItem>>()

    fun setEstateId(id: Long) {
        Logger.d("Estate id: $id")
        this.estateId = id
        GlobalScope.launch {
            repository.getTariffList().forEach {
                it.title?.let { title ->
                    tariffTitle[it.uid] = title
                }
            }
            withContext(Dispatchers.Main) {
                items.addSource(repository.fetchCalculationItemList(id)) { calculationItems ->
                    items.value = calculationItems.map {
                        val item = SimpleListItem()
                        item.uid = it.uid
                        item.title = tariffTitle[it.tariffUid] ?: ""
                        item
                    }
                }
            }
        }
    }


    fun editItem(uid: Long) {
        ruleEditor.setRule(Rule(uid, estateId))
        ruleEditor.show()
    }

    fun createItem() {
        ruleEditor.setRule(Rule(uid = 0L, estateId = estateId))
        ruleEditor.startCreation()
    }

}