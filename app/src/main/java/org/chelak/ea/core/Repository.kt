package org.chelak.ea.core

import androidx.lifecycle.LiveData
import org.chelak.ea.database.UserDatabase
import org.chelak.ea.database.entity.Estate
import org.chelak.ea.database.entity.Meter
import org.chelak.ea.database.entity.Tariff
import org.chelak.ea.database.entity.TariffThreshold

class Repository constructor(private val dataBase: UserDatabase) {

    // estates
    fun allEstates(): LiveData<List<Estate>> = dataBase.estateDao().fetchAll()

    fun estate(uid: Long): LiveData<Estate> = dataBase.estateDao().fetchById(uid)

    fun addEstate(estate: Estate): Long = dataBase.estateDao().insert(estate)

    fun updateEstate(estate: Estate): Int = dataBase.estateDao().update(estate)

    fun deleteEstate(id: Long) = dataBase.estateDao().delete(id)

    // meters
    fun meters(estateId: Long): LiveData<List<Meter>> =
        dataBase.meterDao().fetchMeters(estateId = estateId)

    // tariff
    fun allTariffs(): LiveData<List<Tariff>> = dataBase.tariffDao().fetchAll()

    fun tariff(uid: Long): LiveData<Tariff> = dataBase.tariffDao().fetchById(uid)

    fun addTariff(tariff: Tariff): Long = dataBase.tariffDao().insert(tariff)

    fun thresholds(tariffId: Long): LiveData<List<TariffThreshold>> =
        dataBase.tariffThresholdDao().fetchThresholds(tariffId)

    fun addThreshold(threshold: TariffThreshold): Long =
        dataBase.tariffThresholdDao().insert(threshold)

    fun updateThreshold(threshold: TariffThreshold): Int = dataBase.tariffThresholdDao().update(threshold)

    fun deleteThreshold(id: Long) {
        dataBase.tariffThresholdDao().delete(id)
    }

    fun fetchThreshold(id: Long ): TariffThreshold = dataBase.tariffThresholdDao().fetchById(id)
}