package org.chelak.ea.screens.estate.meters

import java.util.*

data class MeterValueUserInput(
    val inputValue: String,
    val date: Date,
    val isChecked: Boolean
)

typealias SaveMeterValueHandler = (MeterValueUserInput) -> Unit

data class MeterValueDisplayModel(
    val uid: Long = 0,
    val formattedValue: String = "",
    val date: Date = Date(),
    val formattedDate: String = "",
    val isPaid: Boolean = false
)