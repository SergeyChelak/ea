package org.chelak.ea.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import org.chelak.ea.database.common.GenericDao
import org.chelak.ea.database.entity.MeterValue

@Dao
interface MeterValueDao: GenericDao<MeterValue> {

    @Query("SELECT * FROM __meter_values WHERE __meter_uid = :meterId")
    fun fetchValues(meterId: Long): LiveData<List<MeterValue>>

    @Query("SELECT * FROM __meter_values WHERE uid = :valueId")
    fun fetchValue(valueId: Long): MeterValue

}