package org.chelak.ea.core

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

class Formatter {

    companion object {
        const val VALUE_EMPTY = "--"
    }

    fun formatAmount(amount: Long?): String {
        var result = VALUE_EMPTY
        amount?.let {
            result = it.toString()
        }
        return result
    }

    fun stringToAmount(string: String?): Long? {
        var result: Long? = null
        string?.let {
            result = it.toLongOrNull()
        }
        return result
    }

    fun formatCurrency(amount: BigDecimal?): String {
        var result = VALUE_EMPTY
        amount?.let {
            val decimal = amount.dec().setScale(2, RoundingMode.HALF_EVEN)
            result = decimal.toPlainString()
        }
        return result
    }

    fun stringToCurrency(string: String): BigDecimal? {
        return null
    }

    fun formatDate(date: Date?): String {
        var result = VALUE_EMPTY
        date?.let {
            val format = SimpleDateFormat("dd.MM.YYYY", Locale.getDefault())
            result = format.format(date)
        }
        return result
    }

}