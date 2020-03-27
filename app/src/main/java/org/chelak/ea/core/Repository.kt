package org.chelak.ea.core

import androidx.lifecycle.LiveData
import org.chelak.ea.database.UserDatabase
import org.chelak.ea.database.entity.Estate
import org.chelak.ea.database.entity.Meter

class Repository constructor(private val dataBase: UserDatabase) {

    // estates
    fun allEstates(): LiveData<List<Estate>> = dataBase.estateDao().fetchAll()

    fun estate(uid: Long): LiveData<Estate> = dataBase.estateDao().fetchById(uid)

    fun addEstate(estate: Estate): Long = dataBase.estateDao().insert(estate)

    fun updateEstate(estate: Estate): Int = dataBase.estateDao().update(estate)

    // meters
    fun meters(estateId: Long): LiveData<List<Meter>> = dataBase.meterDao().fetchMeters(estateId = estateId)

    // tariff
}