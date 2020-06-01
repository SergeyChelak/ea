package org.chelak.ea.screens.estate.meters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.chelak.ea.R
import org.chelak.ea.core.Formatter
import org.chelak.ea.core.Repository
import org.chelak.ea.core.StringResource
import org.chelak.ea.ui.Navigator
import org.chelak.ea.ui.dialog.AlertModel
import java.util.*
import javax.inject.Inject

class MeterDetailsViewModel : ViewModel() {
    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var repository: Repository
    @Inject
    lateinit var formatter: Formatter
    @Inject
    lateinit var res: StringResource

    private var meterId: Long = 0

    private var _alertData = MutableLiveData<AlertModel>()
    val alertData: LiveData<AlertModel> get() = _alertData

    val meterValues: LiveData<List<MeterValueDisplayModel>>
    get() {
        val data = repository.meterValues(meterId)
        return Transformations.map(data) { meterValues ->
            meterValues.map { meterValue ->
                MeterValueDisplayModel(
                    meterValue.meterUid,
                    formatter.formatAmount(meterValue.value),
                    meterValue.date ?: Date(),
                    formatter.formatDate(meterValue.date),
                    meterValue.isPaid
                )
            }
        }
    }


    fun saveValue(uid: Long?, userInput: MeterValueUserInput) {
        val value = formatter.stringToAmount(userInput.inputValue)
        if (value == null) {
            _alertData.value = AlertModel(
                title = res.getString(R.string.dialog_title_error),
                message = res.getString(R.string.meter_validation_incorrect_value),
                positiveTitle = res.getString(R.string.btn_ok)
            )
            return
        }

        GlobalScope.launch {
            val meterValue = if (uid == null) {
                //MeterValue()
            } else {

            }
        }
    }


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
