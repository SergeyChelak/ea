package org.chelak.ea.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import org.chelak.ea.database.common.GenericDao
import org.chelak.ea.database.entity.Meter

@Dao
interface MeterDao : GenericDao<Meter> {

    @Query("SELECT * FROM __meters WHERE __estate_uid = :estateId")
    fun fetchMeters(estateId: Long): LiveData<List<Meter>>

    @Query("SELECT * FROM __meters WHERE __estate_uid = :estateId")
    fun getMeters(estateId: Long): List<Meter>

    @Query("SELECT * FROM __meters WHERE uid = :id LIMIT 1")
    fun fetchMeter(id: Long): Meter

}