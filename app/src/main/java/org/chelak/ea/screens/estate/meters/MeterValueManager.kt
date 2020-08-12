package org.chelak.ea.screens.estate.meters

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import org.chelak.ea.core.*
import org.chelak.ea.core.Formatter
import org.chelak.ea.database.entity.MeterValue
import java.util.*

class MeterValueManager(
    val repository: Repository,
    private val formatter: Formatter
) {

    var meterId: Long = 0

    val meterValues: LiveData<List<MeterValueDisplayModel>>
        get() {
            val data = repository.meterValues(meterId)
            return Transformations.map(data) { meterValues ->
                meterValues.map { meterValue ->
                    MeterValueDisplayModel(
                        meterValue.uid,
                        formatter.formatAmount(meterValue.value),
                        meterValue.date ?: Date(),
                        formatter.formatDate(meterValue.date),
                        meterValue.isPaid
                    )
                }
            }
        }


    fun saveMeterValue(valueId: Long?, userInput: MeterValueUserInput) {
        val value =
            formatter.stringToAmount(userInput.inputValue) ?: throw IncorrectValueException()
        val meter = repository.getMeter(meterId)
        if (!Calculator.isCorrect(value, meter)) {
            throw OutOfRangeException()
        }
        val modifier = { meterValue: MeterValue ->
            meterValue.value = value
            meterValue.date = userInput.date
            meterValue.isPaid = userInput.isChecked
        }
        if (valueId != null) {
            updateValue(valueId, modifier)
        } else {
            insertValue(modifier)
        }
    }

    private fun insertValue(modifier: (MeterValue) -> Unit) {
        val meterValue = MeterValue(
            0,
            date = null,
            value = 0,
            meterUid = meterId
        )
        modifier(meterValue)
        if (repository.insertMeterValue(meterValue) == 0L)
            throw OperationFailureException()
    }

    private fun updateValue(valueId: Long, modifier: (MeterValue) -> Unit) {
        val meterValue = repository.getMeterValue(valueId)
        modifier(meterValue)
        val count = repository.updateMeterValue(meterValue)
        if (count == 0)
            throw OperationFailureException()
    }

    fun deleteMeterValue(valueId: Long) {
        val meterValue = repository.getMeterValue(valueId)
        val count = repository.deleteMeterValue(meterValue)
        if (count == 0) {
            throw OperationFailureException()
        }
    }
}