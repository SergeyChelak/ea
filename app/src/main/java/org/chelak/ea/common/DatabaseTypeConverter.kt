package org.chelak.ea.common

import androidx.room.TypeConverter
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

    @TypeConverter
    fun toInt(value: EstateType?): Int = value?.typeId ?: 0

    @TypeConverter
    fun toEstateType(value: Int?): EstateType = EstateType.instance(value ?: 0)
}