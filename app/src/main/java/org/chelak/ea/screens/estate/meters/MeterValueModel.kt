package org.chelak.ea.screens.estate.meters

import java.util.*

data class MeterValueModel(
    val uid: Long,
    val value: Long,
    val date: Date,
    val isPaid: Boolean
)

typealias SaveMeterValueHandler = (MeterValueModel) -> Boolean