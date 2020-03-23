package org.chelak.ea.database

import android.content.Context
import androidx.room.*
import org.chelak.ea.common.BitMask
import org.chelak.ea.database.dao.EstateDao
import org.chelak.ea.database.dao.MeterDao
import org.chelak.ea.database.entity.Estate
import org.chelak.ea.database.entity.Meter
import org.chelak.ea.database.entity.MeterValue
import java.math.BigDecimal
import java.util.*

@TypeConverters(UserDatabase.Companion::class)
@Database(
    entities = [
        Estate::class,
        Meter::class, MeterValue::class
    ],
    exportSchema = false,
    version = 1
)
abstract class UserDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context, file: String): UserDatabase = Room.databaseBuilder(
            context, UserDatabase::class.java, file
        ).build()

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

    abstract fun estateDao(): EstateDao

    abstract fun meterDao(): MeterDao
}