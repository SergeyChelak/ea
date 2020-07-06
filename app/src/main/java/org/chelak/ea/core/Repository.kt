package org.chelak.ea.core

import androidx.lifecycle.LiveData
import org.chelak.ea.database.UserDatabase
import org.chelak.ea.database.entity.*

class Repository constructor(private val dataBase: UserDatabase) {

    // estates
    fun allEstates(): LiveData<List<Estate>> = dataBase.estateDao().fetchAll()

    fun estate(uid: Long): LiveData<Estate> = dataBase.estateDao().fetchById(uid)

    fun insertEstate(estate: Estate): Long = dataBase.estateDao().insert(estate)

    fun updateEstate(estate: Estate): Int = dataBase.estateDao().update(estate)

    fun deleteEstate(id: Long) = dataBase.estateDao().delete(id)

    // meters
    fun meters(estateId: Long): LiveData<List<Meter>> =
        dataBase.meterDao().fetchMeters(estateId = estateId)

    fun addMeter(estateId: Long, title: String, capacity: Int): Long {
        val meter = Meter(estateUid = estateId, title = title, capacity = capacity)
        return dataBase.meterDao().insert(meter)
    }

    fun fetchMeter(meterId: Long): Meter = dataBase.meterDao().fetchMeter(meterId)

    // meter values
    fun meterValues(meterId: Long): LiveData<List<MeterValue>> =
        dataBase.meterValueDao().fetchValues(meterId)

    fun fetchMeterValue(meterValueId: Long): MeterValue =
        dataBase.meterValueDao().fetchValue(meterValueId)

    fun fetchLastMeterValue(meterValueId: Long): LiveData<List<MeterValue>> =
        dataBase.meterValueDao().fetchLastValues(meterValueId)

    fun insertMeterValue(meterValue: MeterValue): Long =
        dataBase.meterValueDao().insert(meterValue)

    fun updateMeterValue(meterValue: MeterValue): Int =
        dataBase.meterValueDao().update(meterValue)

    fun deleteMeterValue(meterValue: MeterValue): Int =
        dataBase.meterValueDao().delete(meterValue)

    // rates
    fun fetchRates(estateId: Long): LiveData<List<Rate>> = dataBase.rateDao().fetchRates(estateId)

    fun fetchRate(uid: Long): Rate = dataBase.rateDao().fetchById(uid)

    fun insertRate(rate: Rate): Long = dataBase.rateDao().insert(rate)

    fun updateRate(rate: Rate): Int = dataBase.rateDao().update(rate)

    fun deleteRate(rate: Rate): Int = dataBase.rateDao().delete(rate)

    // tariff
    fun allTariffs(): LiveData<List<Tariff>> = dataBase.tariffDao().fetchAll()

    fun tariff(uid: Long): LiveData<Tariff> = dataBase.tariffDao().fetchById(uid)

    fun tariffList(): List<Tariff> = dataBase.tariffDao().tariffList()

    fun insertTariff(tariff: Tariff): Long = dataBase.tariffDao().insert(tariff)

    fun thresholds(tariffId: Long): LiveData<List<TariffThreshold>> =
        dataBase.tariffThresholdDao().fetchThresholds(tariffId)

    fun insertThreshold(threshold: TariffThreshold): Long =
        dataBase.tariffThresholdDao().insert(threshold)

    fun updateThreshold(threshold: TariffThreshold): Int =
        dataBase.tariffThresholdDao().update(threshold)

    fun deleteThreshold(id: Long) {
        dataBase.tariffThresholdDao().delete(id)
    }

    fun fetchThreshold(id: Long): TariffThreshold = dataBase.tariffThresholdDao().fetchById(id)

    // payment settings

    fun fetchPaymentSettings(estateId: Long): LiveData<List<CalculationItem>> =
        dataBase.calculationItemDao().fetchCalculationItems(estateId)
}