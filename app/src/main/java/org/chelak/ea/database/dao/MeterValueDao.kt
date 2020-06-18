package org.chelak.ea.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import org.chelak.ea.database.common.GenericDao
import org.chelak.ea.database.entity.MeterValue

@Dao
interface MeterValueDao: GenericDao<MeterValue> {

    @Query("SELECT * FROM __meter_values WHERE __meter_uid = :meterId ORDER BY __date DESC")
    fun fetchValues(meterId: Long): LiveData<List<MeterValue>>

    @Query("SELECT * FROM __meter_values WHERE __meter_uid = :meterId ORDER BY __date DESC LIMIT 3")
    fun fetchLastValues(meterId: Long): LiveData<List<MeterValue>>

    @Query("SELECT * FROM __meter_values WHERE uid = :valueId")
    fun fetchValue(valueId: Long): MeterValue

}