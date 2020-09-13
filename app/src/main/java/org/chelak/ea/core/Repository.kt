package org.chelak.ea.core

import androidx.lifecycle.LiveData
import org.chelak.ea.database.UserDatabase
import org.chelak.ea.database.entity.*

class Repository constructor(private val dataBase: UserDatabase) {

    // estates
    fun fetchEstateList(): LiveData<List<Estate>> = dataBase.estateDao().fetchAll()

    fun fetchEstate(uid: Long): LiveData<Estate> = dataBase.estateDao().fetchById(uid)

    fun insertEstate(estate: Estate): Long = dataBase.estateDao().insert(estate)

    fun updateEstate(estate: Estate): Int = dataBase.estateDao().update(estate)

    fun deleteEstate(id: Long) = dataBase.estateDao().delete(id)

    // meters
    fun fetchMeterList(estateId: Long): LiveData<List<Meter>> =
        dataBase.meterDao().fetchMeters(estateId = estateId)

    fun getMeterList(estateId: Long): List<Meter> =
        dataBase.meterDao().getMeters(estateId = estateId)

    fun addMeter(estateId: Long, title: String, capacity: Int): Long {
        val meter = Meter(estateUid = estateId, title = title, capacity = capacity)
        return dataBase.meterDao().insert(meter)
    }

    fun getMeter(meterId: Long): Meter = dataBase.meterDao().fetchMeter(meterId)

    // meter values
    fun meterValues(meterId: Long): LiveData<List<MeterValue>> =
        dataBase.meterValueDao().fetchValues(meterId)

    fun getMeterValue(meterValueId: Long): MeterValue =
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

    fun getRates(estateId: Long): List<Rate> = dataBase.rateDao().getRates(estateId)

    fun getRate(uid: Long): Rate = dataBase.rateDao().fetchById(uid)

    fun insertRate(rate: Rate): Long = dataBase.rateDao().insert(rate)

    fun updateRate(rate: Rate): Int = dataBase.rateDao().update(rate)

    fun deleteRate(rate: Rate): Int = dataBase.rateDao().delete(rate)

    // tariff
    fun fetchTariffList(): LiveData<List<Tariff>> = dataBase.tariffDao().fetchAll()

    fun fetchTariff(uid: Long): LiveData<Tariff> = dataBase.tariffDao().fetchById(uid)

    fun getTariff(uid: Long): Tariff = dataBase.tariffDao().getById(uid)

    fun getTariffList(): List<Tariff> = dataBase.tariffDao().tariffList()

    fun insertTariff(tariff: Tariff): Long = dataBase.tariffDao().insert(tariff)

    fun fetchThresholdList(tariffId: Long): LiveData<List<TariffThreshold>> =
        dataBase.tariffThresholdDao().fetchThresholds(tariffId)

    fun insertThreshold(threshold: TariffThreshold): Long =
        dataBase.tariffThresholdDao().insert(threshold)

    fun updateThreshold(threshold: TariffThreshold): Int =
        dataBase.tariffThresholdDao().update(threshold)

    fun deleteThreshold(id: Long) {
        dataBase.tariffThresholdDao().delete(id)
    }

    fun getThreshold(id: Long): TariffThreshold = dataBase.tariffThresholdDao().fetchById(id)

    // payment settings
    fun fetchCalculationItemList(estateId: Long): LiveData<List<CalculationItem>> =
        dataBase.calculationItemDao().fetchCalculationItems(estateId)

    fun getCalculationItem(uid: Long): CalculationItem = dataBase.calculationItemDao().fetchById(uid)

    fun updateCalculationItem(item: CalculationItem): Int =
        dataBase.calculationItemDao().update(item)

    fun insertCalculationItem(item: CalculationItem): Long =
        dataBase.calculationItemDao().insert(item)

    fun removeCalculationItem(item: CalculationItem): Int =
        dataBase.calculationItemDao().delete(item)

    fun lastOrder(estateId: Long): Int =
        dataBase.calculationItemDao().lastOrder(estateId)

    fun getMeterLinkList(calculationItemId: Long): List<CalculationLinkMeter> =
        dataBase.calculationLinkMeterDao().fetchMeterLinks(calculationItemId)

    fun removeMeterLinks(calculationItemId: Long): Int =
        dataBase.calculationLinkMeterDao().removeMeterLinks(calculationItemId)

    fun insertMeterLink(link: CalculationLinkMeter): Long =
        dataBase.calculationLinkMeterDao().insert(link)

    fun getRateLinkList(calculationItemId: Long): List<CalculationLinkRate> =
        dataBase.calculationLinkRateDao().fetchRateLinks(calculationItemId)

    fun insertRateLink(link: CalculationLinkRate): Long =
        dataBase.calculationLinkRateDao().insert(link)

    fun removeRateLinks(calculationItemId: Long): Int =
        dataBase.calculationLinkRateDao().removeRateLinks(calculationItemId)

}