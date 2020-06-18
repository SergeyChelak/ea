package org.chelak.ea.core

import org.chelak.ea.database.entity.Meter
import org.chelak.ea.database.entity.MeterValue
import kotlin.math.pow
import kotlin.math.roundToLong

class Calculator {

    companion object {

        private fun maxValue(meter: Meter): Long = 10.0.pow(meter.capacity).roundToLong()

        fun value(meter: Meter, current: MeterValue, previous: MeterValue): Long {
            val delta = current.value - previous.value
            return if (delta < 0) {
                delta + maxValue(meter)
            } else {
                delta
            }
        }

        fun isCorrect(value: Long?, meter: Meter): Boolean {
            if (value == null) {
                return false
            }
            return value < maxValue(meter) && value >= 0
        }
    }

}