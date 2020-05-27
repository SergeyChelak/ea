package org.chelak.ea.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.chelak.ea.common.DatabaseTypeConverter
import org.chelak.ea.database.dao.*
import org.chelak.ea.database.entity.*

@TypeConverters(DatabaseTypeConverter::class)
@Database(
    entities = [
        Estate::class,
        Meter::class, MeterValue::class,
        Rate::class,
        Payment::class, PaymentItem::class,
        Tariff::class, TariffThreshold::class,
        CalculationItem::class, CalculationLinkMeter::class, CalculationLinkRate::class
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

    abstract fun meterValueDao(): MeterValueDao

    abstract fun rateDao(): RateDao

    abstract fun paymentDao(): PaymentDao

    abstract fun paymentItemDao(): PaymentItemDao

    abstract fun tariffDao(): TariffDao

    abstract fun tariffThresholdDao(): TariffThresholdDao

    abstract fun calculationItemDao(): CalculationItemDao

    abstract fun calculationLinkMeterDao(): CalculationLinkMeterDao

    abstract fun calculationLinkRateDao(): CalculationLinkRateDao
}