package org.chelak.ea.screens.estate.calculation.editor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.chelak.ea.R
import org.chelak.ea.core.StringResource
import org.chelak.ea.ui.dialog.AlertModel
import javax.inject.Inject


class StepSelectViewModel : ViewModel() {

    @Inject
    lateinit var ruleEditor: RuleEditor
    @Inject
    lateinit var stringResource: StringResource

    private var _stepId: Long = 0L
    var stepId: Long
        get() = _stepId
        set(value) {
            _stepId = value
            GlobalScope.launch {
                val items = ruleEditor.items(value)
                withContext(Dispatchers.Main) {
                    _items.value = items
                }
            }
        }

    private var _items = MutableLiveData<SelectItemList>()
    val items: LiveData<SelectItemList>
        get() = _items

    var alerts = MutableLiveData<AlertModel>()
        private set

    fun update(items: SelectItemList) {
        if (ruleEditor.isValid(stepId, items)) {
            ruleEditor.commit(stepId, items)
        } else {
            val model = AlertModel(
                title = stringResource.getString(R.string.dialog_title_error),
                message = stringResource.getString(R.string.calculation_error_nothing_selected),
                positiveTitle = stringResource.getString(R.string.btn_ok),
                positiveAction = {
                    alerts.value = null
                }
            )
            alerts.value = model
        }
    }

}