package org.chelak.ea.database

import androidx.room.TypeConverter
import org.chelak.ea.common.BitMask
import java.math.BigDecimal
import java.util.*

class DatabaseTypeConverter {

    @TypeConverter
    fun toBitMask(value: Int?): BitMask = BitMask(value ?: 0)

    @TypeConverter
    fun toInt(value: BitMask?): Int = value?.raw ?: 0

    @TypeConverter
    fun toDate(value: Long?): Date? = if (value == null) null else Date(value)

    @TypeConverter
    fun toLong(value: Date?): Long? = value?.time

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? =
        if (value == null) null else BigDecimal(value)

    @TypeConverter
    fun toString(value: BigDecimal?): String? = value?.toString()


}