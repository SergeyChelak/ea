package org.chelak.ea.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import org.chelak.ea.database.common.GenericDao
import org.chelak.ea.database.entity.Tariff

@Dao
interface TariffDao : GenericDao<Tariff> {

    @Query("SELECT * FROM __tariffs")
    fun fetchAll(): LiveData<List<Tariff>>

    @Query("SELECT * FROM __tariffs")
    fun tariffList(): List<Tariff>

    @Query("SELECT * FROM __tariffs WHERE uid = :id LIMIT 1")
    fun fetchById(id: Long): LiveData<Tariff>

}