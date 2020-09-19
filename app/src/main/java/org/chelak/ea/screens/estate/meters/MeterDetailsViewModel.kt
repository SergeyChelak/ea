package org.chelak.ea.screens.estate.meters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.chelak.ea.R
import org.chelak.ea.core.IncorrectValueException
import org.chelak.ea.core.OutOfRangeException
import org.chelak.ea.core.StringResource
import org.chelak.ea.ui.AlertEmitter
import org.chelak.ea.ui.Navigator
import org.chelak.ea.ui.dialog.AlertModel
import javax.inject.Inject

class MeterDetailsViewModel : ViewModel(), AlertEmitter {
    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var meterValueManager: MeterValueManager

    @Inject
    lateinit var res: StringResource

    private var _alertData = MutableLiveData<AlertModel>()
    override val alertData: LiveData<AlertModel> get() = _alertData

    val meterValues: LiveData<List<MeterValueDisplayModel>>
        get() = meterValueManager.meterValues

    fun saveValue(uid: Long?, userInput: MeterValueUserInput) {
        GlobalScope.launch {
            try {
                meterValueManager.saveMeterValue(uid, userInput)
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    val message = when (e) {
                        is IncorrectValueException -> res.getString(R.string.meter_validation_incorrect_value)
                        is OutOfRangeException -> res.getString(R.string.meter_validation_value_out_of_range)
                        else -> res.getString(R.string.app_error_unexpected)
                    }
                    _alertData.value = AlertModel(
                        title = res.getString(R.string.dialog_title_error),
                        message = message,
                        positiveTitle = res.getString(R.string.btn_ok),
                        positiveAction = { _alertData.value = null }
                    )
                }
            }
        }
    }


    fun deleteValue(valueId: Long) {
        _alertData.value = AlertModel(
            title = res.getString(R.string.dialog_title_warning),
            message = res.getString(R.string.dialog_confirm_undone_action),
            positiveTitle = res.getString(R.string.btn_yes),
            positiveAction = {
                performDeleteValue(valueId)
                _alertData.value = null
            },
            negativeTitle = res.getString(R.string.btn_no),
            negativeAction = { _alertData.value = null }
        )
    }

    private fun performDeleteValue(valueId: Long) {
        GlobalScope.launch {
            try {
                meterValueManager.deleteMeterValue(valueId)
            } catch (e: Exception) {
                _alertData.value = AlertModel(
                    title = res.getString(R.string.dialog_title_error),
                    message = res.getString(R.string.app_error_unexpected),
                    positiveTitle = res.getString(R.string.btn_ok),
                    positiveAction = { _alertData.value = null }
                )
            }
        }
    }


    fun setMeterId(meterId: Long) {
        meterValueManager.meterId = meterId
    }


    fun editProperties() {
        //
    }


    fun removeMeter() {
        //
    }

}
