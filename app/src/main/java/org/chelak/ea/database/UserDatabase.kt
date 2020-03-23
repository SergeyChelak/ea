package org.chelak.ea.database

import android.content.Context
import androidx.room.*
import org.chelak.ea.common.BitMask
import org.chelak.ea.database.dao.EstateDao
import org.chelak.ea.database.dao.MeterDao
import org.chelak.ea.database.dao.RateDao
import org.chelak.ea.database.entity.Estate
import org.chelak.ea.database.entity.Meter
import org.chelak.ea.database.entity.MeterValue
import org.chelak.ea.database.entity.Rate
import java.math.BigDecimal
import java.util.*

@TypeConverters(DatabaseTypeConverter::class)
@Database(
    entities = [
        Estate::class,
        Meter::class, MeterValue::class,
        Rate::class
    ],
    exportSchema = false,
    version = 1
)
abstract class UserDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context, file: String): UserDatabase = Room.databaseBuilder(
            context, UserDatabase::class.java, file
        ).build()
    }

    abstract fun estateDao(): EstateDao

    abstract fun meterDao(): MeterDao

    abstract fun rateDao(): RateDao
}