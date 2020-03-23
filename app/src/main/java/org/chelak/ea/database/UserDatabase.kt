package org.chelak.ea.database

import android.content.Context
import androidx.room.*
import org.chelak.ea.common.BitMask
import org.chelak.ea.database.dao.*
import org.chelak.ea.database.entity.*
import java.math.BigDecimal
import java.util.*

@TypeConverters(DatabaseTypeConverter::class)
@Database(
    entities = [
        Estate::class,
        Meter::class, MeterValue::class,
        Rate::class,
        Payment::class, PaymentItem::class,
        Tariff::class, TariffThreshold::class,
        CalculationItem::class
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

    abstract fun paymentDao(): PaymentDao

    abstract fun paymentItemDao(): PaymentItemDao

    abstract fun tariffDao(): TariffDao

    abstract fun tariffThresholdDao(): TariffThresholdDao

    abstract fun calculationItemDao(): CalculationItemDao
}