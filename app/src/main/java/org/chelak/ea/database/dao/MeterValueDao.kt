package org.chelak.ea.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import org.chelak.ea.database.common.GenericDao
import org.chelak.ea.database.entity.Meter
import org.chelak.ea.database.entity.MeterValue

@Dao
interface MeterValueDao: GenericDao<MeterValue> {

    @Query("SELECT * FROM __meters WHERE __estate_uid = :estateId")
    fun fetchMeters(estateId: Long): LiveData<List<Meter>>

    @Query("SELECT * FROM __meters WHERE uid = :id LIMIT 1")
    fun fetchById(id: Long): LiveData<Meter>

}