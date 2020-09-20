package org.chelak.ea.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import org.chelak.ea.database.common.GenericDao
import org.chelak.ea.database.entity.TariffThreshold

@Dao
interface TariffThresholdDao: GenericDao<TariffThreshold> {

    @Query("SELECT * FROM __tariff_thresholds WHERE __tariff_uid = :tariffUid ORDER BY __value")
    fun fetchThresholds(tariffUid: Long): LiveData<List<TariffThreshold>>

    @Query("SELECT * FROM __tariff_thresholds WHERE uid = :id LIMIT 1")
    fun fetchById(id: Long): TariffThreshold

    @Query("DELETE FROM __tariff_thresholds WHERE uid = :id")
    fun delete(id: Long)

}